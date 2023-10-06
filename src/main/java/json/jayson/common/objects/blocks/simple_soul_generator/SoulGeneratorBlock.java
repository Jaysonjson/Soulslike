package json.jayson.common.objects.blocks.simple_soul_generator;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.objects.blocks.soul_drain.SoulDrainBlockEntity;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SoulGeneratorBlock extends DirectionalKineticBlock implements IWrenchable, IBE<SoulGeneratorBlockEntity> {
    public SoulGeneratorBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferred = getPreferredFacing(context);
        if ((context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown()) || preferred == null)
            return super.getStateForPlacement(context);
        return defaultBlockState().setValue(FACING, preferred);
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(FACING);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING)
                .getAxis();
    }

    @Override
    public Class<SoulGeneratorBlockEntity> getBlockEntityClass() {
        return SoulGeneratorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SoulGeneratorBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.SIMPLE_SOUL_GENERATOR.get();
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 4;
    }

}
