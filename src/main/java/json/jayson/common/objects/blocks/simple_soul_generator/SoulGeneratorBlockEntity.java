package json.jayson.common.objects.blocks.simple_soul_generator;

import com.simibubi.create.content.fluids.spout.SpoutBlock;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import com.simibubi.create.content.kinetics.motor.CreativeMotorRenderer;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelBlockEntity;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelInstance;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import json.jayson.common.registries.SoulsFluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

public class SoulGeneratorBlockEntity extends GeneratingKineticBlockEntity {

    SmartFluidTankBehaviour internalTank;
    int cap = 5000;
    boolean running = false;
    public SoulGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(internalTank = SmartFluidTankBehaviour.single(this, cap)
                .forbidExtraction()
                .allowInsertion());
    }

    @Override
    public void tick() {
        if(getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getFluid() == SoulsFluids.SOURCE_SOUL.get()) {
            int fluidAmount = getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getAmount();
            if (fluidAmount > 19) {
                internalTank.getPrimaryHandler().drain(20, IFluidHandler.FluidAction.EXECUTE);
                running = true;
            } else {
                running = false;
            }
        } else {
            running = false;
        }
        running = true;
        super.tick();
    }

    @Override
    public float getGeneratedSpeed() {
        if(!running) return 0;
        return 64;
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
