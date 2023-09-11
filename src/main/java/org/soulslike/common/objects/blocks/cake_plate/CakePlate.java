package org.soulslike.common.objects.blocks.cake_plate;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import org.soulslike.network.packet.ModMessages;
import org.soulslike.network.packet.CakePlateSyncS2CPacket;

public class CakePlate extends BaseEntityBlock {

    public CakePlate(Properties p_49795_) {
        super(p_49795_);
    }

    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        VoxelShape SHAPE = Shapes.empty();
        SHAPE = Shapes.join(SHAPE, Block.box(12, 4, 4, 13, 5, 12), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(6, 1, 6, 10, 3, 10), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(4, 0, 4, 12, 1, 12), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(3, 3, 3, 13, 4, 13), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(3, 4, 3, 13, 5, 4), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(3, 4, 12, 13, 5, 13), BooleanOp.OR);
        SHAPE = Shapes.join(SHAPE, Block.box(3, 4, 4, 4, 5, 12), BooleanOp.OR);
        return SHAPE;
    }

    @Override
    public void onRemove(BlockState p_60515_, Level level, BlockPos blockPos, BlockState p_60518_, boolean p_60519_) {
        if(!level.isClientSide) {
            CakePlateEntity cakePlateEntity = (CakePlateEntity) level.getBlockEntity(blockPos);
            if(cakePlateEntity != null) {
                cakePlateEntity.reloadCakeBlock();
                if(cakePlateEntity.cakeBlock != null) {
                    level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(cakePlateEntity.cakeBlock.asItem())));
                }
            }
        }
        super.onRemove(p_60515_, level, blockPos, p_60518_, p_60519_);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide) {
            CakePlateEntity cakePlateEntity = (CakePlateEntity) level.getBlockEntity(blockPos);
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if(cakePlateEntity != null) {
                cakePlateEntity.reloadCakeBlock();
                if(cakePlateEntity.cakeBlock != null) {
                    level.addFreshEntity(new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(cakePlateEntity.cakeBlock.asItem())));
                }
                if(itemStack.getItem() != Items.AIR) {
                    cakePlateEntity.cakeString = itemStack.getItem().builtInRegistryHolder().key().location().toString();
                    itemStack.shrink(1);
                    player.setItemInHand(interactionHand, itemStack);
                } else {
                    cakePlateEntity.cakeString = "minecraft:air";
                    cakePlateEntity.cakeBlock = null;
                }
                cakePlateEntity.setChanged();
                ModMessages.sendToClients(new CakePlateSyncS2CPacket(blockPos, cakePlateEntity.cakeString));
            }
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new CakePlateEntity(p_153215_, p_153216_);
    }
}
