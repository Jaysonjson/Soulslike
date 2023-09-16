package json.jayson.common.objects.blocks.vase;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import json.jayson.network.packet.GenericVaseInvSyncS2CPacket;
import json.jayson.network.packet.ModMessages;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
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
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GenericVase extends BaseEntityBlock {
    public GenericVase(Properties p_49795_) {
        super(p_49795_);
    }

    private static VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

    public @NotNull VoxelShape getShape(BlockState p_53517_, BlockGetter p_53518_, BlockPos p_53519_, CollisionContext p_53520_) {
        Vec3 vec3 = p_53517_.getOffset(p_53518_, p_53519_);
        BlockEntity block = p_53518_.getBlockEntity(p_53519_);
        if(block instanceof GenericVaseEntity mudVaseEntity) {
            mudVaseEntity.offset = vec3;
        }
        if (block instanceof Container container) {
            GenericVaseEntity mudVaseEntity = (GenericVaseEntity) block;
            for (int i = 0; i < container.getContainerSize(); i++) {
                if (!container.getItem(i).is(Items.AIR)) {
                    mudVaseEntity.renderItems.add(container.getItem(i));
                    ModMessages.sendToClients(new GenericVaseInvSyncS2CPacket(container.getItem(i), p_53519_));
                }
            }
        }

        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new GenericVaseEntity(p_153215_, p_153216_);
    }

    public void onRemove(BlockState p_51538_, Level p_51539_, BlockPos p_51540_, BlockState p_51541_, boolean p_51542_) {
        if (!p_51538_.is(p_51541_.getBlock())) {
            BlockEntity blockentity = p_51539_.getBlockEntity(p_51540_);
            if (blockentity instanceof Container container) {
                Containers.dropContents(p_51539_, p_51540_, container);
                p_51539_.updateNeighbourForOutputSignal(p_51540_, this);
            }

            super.onRemove(p_51538_, p_51539_, p_51540_, p_51541_, p_51542_);
        }
    }

    private List<Integer> getAvailableSlots(Container p_230920_) {
        ObjectArrayList<Integer> objectarraylist = new ObjectArrayList<>();

        for(int i = 0; i < p_230920_.getContainerSize(); ++i) {
            if (p_230920_.getItem(i).isEmpty()) {
                objectarraylist.add(i);
            }
        }

        return objectarraylist;
    }

    @Override
    public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide()) {
            BlockEntity blockentity = level.getBlockEntity(blockPos);
            if (interactionHand == InteractionHand.MAIN_HAND) {
                if (blockentity instanceof GenericVaseEntity mudVaseEntity) {
                    if (player.getMainHandItem().is(Items.AIR)) {
                        BlockEntity container = level.getBlockEntity(blockPos);
                        if (container instanceof Container container1) {
                            Containers.dropContents(level, blockPos, container1);
                            mudVaseEntity.renderItems.clear();
                            ModMessages.sendToClients(new GenericVaseInvSyncS2CPacket(new ItemStack(Items.AIR), blockPos, true));
                        }
                    } else {
                        if (mudVaseEntity.items.size() < GenericVaseEntity.SIZE + 1) {
                            ItemStack itemStack = player.getMainHandItem();
                            List<Integer> list = getAvailableSlots(mudVaseEntity);
                            if (!list.isEmpty()) {
                                ItemStack newStack = itemStack.copy();
                                newStack.setCount(1);
                                mudVaseEntity.setItem(list.get(0), newStack);
                                player.getMainHandItem().setCount(player.getMainHandItem().getCount() - 1);
                                ModMessages.sendToClients(new GenericVaseInvSyncS2CPacket(mudVaseEntity.getItem(list.get(0)), blockPos));
                            }
                        }
                    }
                }
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
        BlockEntity blockentity = p_152431_.getBlockEntity(p_152432_);
        if (blockentity instanceof Container container) {
            for (int i = 0; i < 2; i++) {
                if(!ItemStack.isSameItem(container.getItem(i), new ItemStack(Items.AIR))) {
                    GenericVaseEntity mudVaseEntity = (GenericVaseEntity) blockentity;
                    mudVaseEntity.renderItems.add(container.getItem(i));
                    if(!p_152431_.isClientSide()) ModMessages.sendToClients(new GenericVaseInvSyncS2CPacket(container.getItem(i), p_152432_));
                }
            }
        }
        super.stepOn(p_152431_, p_152432_, p_152433_, p_152434_);
    }
}
