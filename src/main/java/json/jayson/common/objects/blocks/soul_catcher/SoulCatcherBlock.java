package json.jayson.common.objects.blocks.soul_catcher;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlockEntity;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SoulCatcherBlock extends HorizontalKineticBlock implements IBE<SoulCatcherBlockEntity> {
    public SoulCatcherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction prefferedSide = getPreferredHorizontalFacing(context);
        if (prefferedSide != null)
            return defaultBlockState().setValue(HORIZONTAL_FACING, prefferedSide);
        return super.getStateForPlacement(context);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HORIZONTAL_FACING)
                .getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(HORIZONTAL_FACING)
                .getAxis();
    }


    @Override
    public Class<SoulCatcherBlockEntity> getBlockEntityClass() {
        return SoulCatcherBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SoulCatcherBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.SOUL_CATCHER.get();
    }
}
