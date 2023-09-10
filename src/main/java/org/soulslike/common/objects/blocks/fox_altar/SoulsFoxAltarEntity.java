package org.soulslike.common.objects.blocks.fox_altar;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.soulslike.common.registries.SoulsBlockEntities;
import org.soulslike.common.registries.SoulsFluids;

public class SoulsFoxAltarEntity extends BlockEntity implements MenuProvider {

    private final FluidTank BLOOD_TANK = new FluidTank(64000) {
        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == SoulsFluids.SOURCE_BLOOD.get();
        }
    };

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    public void setBlood(FluidStack stack) {
        this.BLOOD_TANK.setFluid(stack);
    }

    public FluidStack getBloodTank() {
        return BLOOD_TANK.getFluid();
    }

    public SoulsFoxAltarEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(SoulsBlockEntities.FOX_ALTAR.get(), p_155229_, p_155230_);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.FLUID_HANDLER) {
            return lazyFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyFluidHandler = LazyOptional.of(() -> BLOOD_TANK);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag = BLOOD_TANK.writeToNBT(tag);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        BLOOD_TANK.readFromNBT(tag);
        super.load(tag);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.soulslike.fox_altar");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return null;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, SoulsFoxAltarEntity soulsFoxAltarEntity) {

    }
}
