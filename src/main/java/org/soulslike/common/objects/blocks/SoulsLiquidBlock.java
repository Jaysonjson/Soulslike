package org.soulslike.common.objects.blocks;

import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class SoulsLiquidBlock extends LiquidBlock {

    public SoulsLiquidBlock(FlowingFluid p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }

    public SoulsLiquidBlock(Supplier<? extends FlowingFluid> p_54694_, Properties p_54695_) {
        super(p_54694_, p_54695_);
    }
}
