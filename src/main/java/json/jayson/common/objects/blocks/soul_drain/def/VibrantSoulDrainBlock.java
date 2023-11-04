package json.jayson.common.objects.blocks.soul_drain.def;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VibrantSoulDrainBlock extends Block implements IWrenchable, IBE<VibrantSoulDrainBlockEntity> {
    public VibrantSoulDrainBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_,
                               CollisionContext p_220053_4_) {
        return AllShapes.CASING_13PX.get(Direction.UP);
    }

    @Override
    public Class<VibrantSoulDrainBlockEntity> getBlockEntityClass() {
        return VibrantSoulDrainBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends VibrantSoulDrainBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.VIBRANT_SOUL_DRAINER.get();
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 4;
    }
}
