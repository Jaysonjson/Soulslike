package json.jayson.common.objects.blocks.soul_dispenser;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import json.jayson.common.capabilities.providers.EntitySoulsProvider;
import json.jayson.common.capabilities.providers.PlayerSoulsProvider;
import json.jayson.common.objects.entities.SoulOrbEntity;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.common.registries.SoulsFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SoulDispenserBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {
    public static final int cap = 500;
    public boolean powered = true;
    SmartFluidTankBehaviour internalTank;
    public SoulDispenserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(internalTank = SmartFluidTankBehaviour.single(this, cap)
                .allowInsertion()
                .allowExtraction());
        registerAwardables(behaviours, AllAdvancements.DRAIN, AllAdvancements.CHAINED_DRAIN);
    }

    @Override
    public void tick() {
        super.tick();
        if(!powered) return;
        if(level instanceof ServerLevel serverLevel) {
            int fluidAmount = getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getAmount();
            Fluid fluid = getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getFluid();
            if(fluid == SoulsFluids.SOURCE_SOUL.get()) {
                BlockPos pos = worldPosition;
                pos.offset(level.getBlockState(pos).getValue(WrenchableDirectionalBlock.FACING).getNormal());
                if(fluidAmount > 0) {
                    if (fluidAmount > 1) {
                        internalTank.getPrimaryHandler().drain(2, IFluidHandler.FluidAction.EXECUTE);
                        SoulOrbEntity.award(serverLevel, new Vec3(pos.getX(), pos.getY(), pos.getZ()), 2);
                    } else {
                        SoulOrbEntity.award(serverLevel, new Vec3(pos.getX(), pos.getY(), pos.getZ()), fluidAmount);
                        internalTank.getPrimaryHandler().drain(fluidAmount, IFluidHandler.FluidAction.EXECUTE);
                    }
                }
            }
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (side != Direction.UP && isFluidHandlerCap(cap))
            return internalTank.getCapability()
                    .cast();

        return super.getCapability(cap, side);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedFluidTooltip(tooltip, isPlayerSneaking, getCapability(ForgeCapabilities.FLUID_HANDLER));
    }
}
