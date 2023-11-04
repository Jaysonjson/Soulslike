package json.jayson.common.objects.blocks.soul_catcher;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.crusher.CrushingWheelBlock;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import json.jayson.common.registries.SoulsBlockEntities;
import json.jayson.common.registries.SoulsFluids;
import net.minecraft.ChatFormatting;
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
import org.lwjgl.system.MathUtil;

import java.util.List;

public class SoulCatcherBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {

    SmartFluidTankBehaviour tank;

    public SoulCatcherBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }


    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER && side != Direction.DOWN)
            return tank.getCapability()
                    .cast();
        return super.getCapability(cap, side);
    }

    int idle = 0;
    @Override
    public void tick() {
        super.tick();
        ++idle;
        if(idle > getWaitingTicks() - 1) {
            idle = 0;
            tank.getPrimaryHandler().fill(new FluidStack(SoulsFluids.SOURCE_SOUL.get(), 1), IFluidHandler.FluidAction.EXECUTE);
        }
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        tank = SmartFluidTankBehaviour.single(this, 1000);
        behaviours.add(tank);
    }

    public int getWaitingTicks() {
        return (int) (145000 / Math.abs(getSpeed()));
    }

    public int getWaitingSeconds() {
        return getWaitingTicks() / 20;
    }

    public int getWaitingMinutes() {
        return getWaitingSeconds() / 60;
    }

    public int getWaitingHours() {
        return getWaitingMinutes() / 60;
    }

    public String getFormattedWaiting() {
        String str = getWaitingSeconds() + " Second(s)";
        if(getWaitingSeconds() > 60) {
            str = getWaitingMinutes() + " Minute(s)";
        }
        if(getWaitingSeconds() > 3600) {
            str = getWaitingMinutes() + " Hour(s)";
        }
        return str;
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        containedFluidTooltip(tooltip, isPlayerSneaking,
                getCapability(ForgeCapabilities.FLUID_HANDLER));
            tooltip.add(Component.literal("    Catcher Info:"));
            tooltip.add(Component.literal("     Catch Time: " + getFormattedWaiting()).withStyle(ChatFormatting.GRAY));
        return true;
    }
}
