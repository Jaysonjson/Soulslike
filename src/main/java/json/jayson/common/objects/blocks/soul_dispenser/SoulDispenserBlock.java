package json.jayson.common.objects.blocks.soul_dispenser;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import json.jayson.common.objects.blocks.soul_drain.SoulDrainBlockEntity;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoulDispenserBlock extends WrenchableDirectionalBlock implements IWrenchable, IBE<SoulDispenserBlockEntity> {
    public SoulDispenserBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_,
                               CollisionContext p_220053_4_) {
        return AllShapes.CASING_13PX.get(Direction.UP);
    }


    @Override
    public Class<SoulDispenserBlockEntity> getBlockEntityClass() {
        return SoulDispenserBlockEntity.class;
    }

    public BlockState rotate(BlockState p_154354_, Rotation p_154355_) {
        return p_154354_.setValue(FACING, p_154355_.rotate(p_154354_.getValue(FACING)));
    }

    public BlockState mirror(BlockState p_154351_, Mirror p_154352_) {
        return p_154351_.setValue(FACING, p_154352_.mirror(p_154351_.getValue(FACING)));
    }

    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block block, BlockPos pos2, boolean p_55671_) {
        if(!level.isClientSide) {
            if(level.getBlockEntity(pos) instanceof SoulDispenserBlockEntity te) {
                te.powered = level.hasNeighborSignal(pos);
            }
        }
    }

    @Override
    public BlockEntityType<? extends SoulDispenserBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.SOUL_DISPENSER.get();
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 4;
    }
}
