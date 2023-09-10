package org.soulslike.common.objects.blocks.fire_altar;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.soulslike.common.registries.SoulsBlockEntities;

public class FireAltarEntity extends BlockEntity {

    public boolean soulFlame = false;


    public FireAltarEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(SoulsBlockEntities.FIRE_ALTAR.get(), p_155229_, p_155230_);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putBoolean("soul_flame", soulFlame);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        soulFlame = tag.getBoolean("soul_flame");
    }
}
