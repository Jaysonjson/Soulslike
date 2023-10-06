package json.jayson.common.objects.blocks.soul_drain;

import com.simibubi.create.AllShapes;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.encasing.CasingConnectivity;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
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

public class SoulDrainBlock extends Block implements IWrenchable, IBE<SoulDrainBlockEntity> {
    public SoulDrainBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_,
                               CollisionContext p_220053_4_) {
        return AllShapes.CASING_13PX.get(Direction.UP);
    }

    @Override
    public Class<SoulDrainBlockEntity> getBlockEntityClass() {
        return SoulDrainBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SoulDrainBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.SOUL_DRAINER.get();
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 4;
    }
}
