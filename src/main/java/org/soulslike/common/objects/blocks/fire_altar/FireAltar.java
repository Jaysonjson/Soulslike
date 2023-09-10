package org.soulslike.common.objects.blocks.fire_altar;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.soulslike.ModMessages;
import org.soulslike.SoulsTags;
import org.soulslike.network.packet.FireAltarSyncS2CPacket;

public class FireAltar extends BaseEntityBlock {

    public static final BooleanProperty UNLIGHTED = BooleanProperty.create("unlighted");


    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape SHAPE = Shapes.empty();
        SHAPE = Shapes.join(SHAPE, Block.box(2, 0, 2, 14, 2, 14), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(3, 2, 3, 13, 8, 13), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(1, 8, 1, 15, 16, 15), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(1, 16, 2, 2, 17, 14), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(1, 16, 1, 15, 17, 2), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(14, 16, 2, 15, 17, 14), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(1, 16, 14, 15, 17, 15), BooleanOp.OR);
        return SHAPE;
    }

    public FireAltar(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            ItemStack itemStack = player.getItemInHand(interactionHand);
            FireAltarEntity fireAltarEntity = (FireAltarEntity) level.getBlockEntity(blockPos);
            if(itemStack.is(Items.AIR)) {
                level.setBlock(blockPos, blockState.setValue(UNLIGHTED,true), 3);
                if(fireAltarEntity != null) {
                    fireAltarEntity.soulFlame = false;
                    ModMessages.sendToClients(new FireAltarSyncS2CPacket(blockPos, false));
                }
            } else if(itemStack.is(SoulsTags.CAN_CREATE_FLAME)) {
                //level.setBlock(blockPos, blockState.cycle(UNLIGHTED), 3);
                level.setBlock(blockPos, blockState.setValue(UNLIGHTED, false), 3);
            } else if(itemStack.is(Items.SOUL_TORCH)) {
                level.setBlock(blockPos, blockState.setValue(UNLIGHTED, false), 3);
                if(fireAltarEntity != null) {
                    fireAltarEntity.soulFlame = true;
                    ModMessages.sendToClients(new FireAltarSyncS2CPacket(blockPos, true));
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        stateBuilder.add(UNLIGHTED);
    }

    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new FireAltarEntity(p_153215_, p_153216_);
    }
}
