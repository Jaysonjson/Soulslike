package org.soulslike.common.objects.blocks.bee_statue;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.soulslike.common.registries.SoulsBlockEntities;

public class SoulsBeeStatueEntity extends BlockEntity {
    public boolean activated = false;
    public SoulsBeeStatueEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(SoulsBlockEntities.BEE_STATUE.get(), p_155229_, p_155230_);
    }
    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putBoolean("activated", activated);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        activated = tag.getBoolean("activated");
    }
}
