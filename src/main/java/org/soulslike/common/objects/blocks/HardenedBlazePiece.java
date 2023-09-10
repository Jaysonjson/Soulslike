package org.soulslike.common.objects.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class HardenedBlazePiece extends Block {

    public HardenedBlazePiece(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        Vec3 vec3 = p_60555_.getOffset(p_60556_, p_60557_);
        VoxelShape shape = makeShape();
        return shape.move(vec3.x, vec3.y, vec3.z);
    }

    public VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.create(0.375, 0, 0.3125, 0.6875, 0.0625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.4375, 0.0625, 0.375, 0.625, 0.1875, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.375, 0.1875, 0.3125, 0.6875, 0.25, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.625, 0.0625, 0.5625, 0.6875, 0.1875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.375, 0.0625, 0.3125, 0.4375, 0.1875, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.375, 0.0625, 0.5625, 0.4375, 0.1875, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.625, 0.0625, 0.3125, 0.6875, 0.1875, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.create(0.375, 0, 0.3125, 0.6875, 0.25, 0.625), BooleanOp.OR);
        return shape;
    }
}
