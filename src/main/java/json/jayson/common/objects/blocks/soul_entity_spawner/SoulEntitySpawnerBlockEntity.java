package json.jayson.common.objects.blocks.soul_entity_spawner;

import com.simibubi.create.api.behaviour.BlockSpoutingBehaviour;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.behaviour.BeltProcessingBehaviour;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.fluid.FluidHelper;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.NBTHelper;
import json.jayson.common.registries.SoulsFluids;
import json.jayson.helpers.SoulsUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.SpawnerBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

public class SoulEntitySpawnerBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    SmartFluidTankBehaviour tank;
    private String entity = "";
    public int soulsCache = 0;
    public EntityType<?> typeCache;

    public SoulEntitySpawnerBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
        this.soulsCache = SoulsUtil.getEntitySouls(entity);
        this.typeCache = SoulsUtil.getEntity(entity);
    }

    private FluidStack getCurrentFluidInTank() {
        return tank.getPrimaryHandler()
                .getFluid();
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER && side != Direction.DOWN)
            return tank.getCapability()
                    .cast();
        return super.getCapability(cap, side);
    }

    int idle = 0;
    public void tick() {
        super.tick();
        if(!entity.isEmpty() && isSpeedRequirementFulfilled()) {
            int fluidAmount = getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getAmount();
            Fluid fluid = getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getFluid();
            if(fluid == SoulsFluids.SOURCE_SOUL.get()) {
                if(fluidAmount > getRequiredSouls() - 1) {
                    ++idle;
                    if (idle > this.getLevel().random.nextInt(60, 300)) {
                        idle = 0;
                        if (typeCache != null) {
                            Entity createdEntity = typeCache.create(level);

                            double x = (double)worldPosition.getX() + (this.getLevel().random.nextDouble() - this.getLevel().random.nextDouble()) * 2.5d;
                            double y = (double)(worldPosition.getY() + this.getLevel().random.nextInt(2) - 1);
                            double z = (double)worldPosition.getZ() + (this.getLevel().random.nextDouble() - this.getLevel().random.nextDouble()) * 2.5d;

                            createdEntity.teleportTo(x, y, z);
                            level.addFreshEntity(createdEntity);
                            tank.getPrimaryHandler().drain(getRequiredSouls(), IFluidHandler.FluidAction.EXECUTE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        tank = SmartFluidTankBehaviour.single(this, 50000);
        behaviours.add(tank);
    }

    public int getRequiredSouls() {
        return (int) (soulsCache * 2.3);
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        compound.putString("entity", entity);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        if(compound.contains("entity")) {
            entity = compound.getString("entity");
            setEntity(entity);
        }
    }


    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        containedFluidTooltip(tooltip, isPlayerSneaking,
                getCapability(ForgeCapabilities.FLUID_HANDLER));
        if(!entity.isEmpty()) {
            tooltip.add(Component.literal("    Entity Info:"));
            tooltip.add(Component.literal("     " + Component.translatable(entity).getString()).withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("     Required Souls: " + getRequiredSouls()).withStyle(ChatFormatting.GRAY));
        }
        return true;
    }
}
