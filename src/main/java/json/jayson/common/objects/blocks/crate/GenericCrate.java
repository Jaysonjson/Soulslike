package json.jayson.common.objects.blocks.crate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class GenericCrate extends BaseEntityBlock {
    public GenericCrate(Properties p_49224_) {
        super(p_49224_);
    }
    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape SHAPE = Shapes.empty();
        SHAPE = Shapes.join(SHAPE, Block.box(0, 0, 0, 16, 14, 16), BooleanOp.OR);
        return SHAPE;
    }

    public InteractionResult use(BlockState p_51531_, Level p_51532_, BlockPos p_51533_, Player p_51534_, InteractionHand p_51535_, BlockHitResult p_51536_) {
        if (p_51532_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuprovider = this.getMenuProvider(p_51531_, p_51532_, p_51533_);
            if (menuprovider != null) {
                p_51534_.openMenu(menuprovider);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new GenericCrateEntity(p_153215_, p_153216_);
    }
}
