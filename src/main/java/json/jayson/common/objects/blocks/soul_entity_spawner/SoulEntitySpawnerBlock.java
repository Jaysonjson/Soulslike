package json.jayson.common.objects.blocks.soul_entity_spawner;

import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.objects.items.SoulVialItem;
import json.jayson.common.registries.SoulsBlockEntities;
import json.jayson.common.registries.SoulsItems;
import json.jayson.network.packet.SoulsNetwork;
import json.jayson.network.packet.SoulEntitySpawnerSyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoulEntitySpawnerBlock extends HorizontalKineticBlock implements IBE<SoulEntitySpawnerBlockEntity> {
    public SoulEntitySpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == getRotationAxis(state);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        if(!level.isClientSide) {
            if(level.getBlockEntity(p_60508_.getBlockPos()) instanceof SoulEntitySpawnerBlockEntity te) {
                ItemStack itemStack = player.getItemInHand(hand);
                if(itemStack.is(SoulsItems.SOUL_VIAL.get())) {
                    if(itemStack.getTag().getInt("amount") >= SoulVialItem.MAX) {
                        te.setEntity(itemStack.getTag().getString("entity"));
                        te.setChanged();
                        if(!player.isCreative()) itemStack.shrink(1);
                        player.setItemInHand(hand, itemStack);
                        SoulsNetwork.sendToClients(new SoulEntitySpawnerSyncS2CPacket(p_60508_.getBlockPos(), te.getEntity()));
                    }
                } else if(itemStack.getItem() instanceof SpawnEggItem spawnEggItem) {
                    te.setEntity(spawnEggItem.getType(itemStack.getTag()).getDescriptionId());
                    te.setChanged();
                    if(!player.isCreative()) itemStack.shrink(1);
                    player.setItemInHand(hand, itemStack);
                    SoulsNetwork.sendToClients(new SoulEntitySpawnerSyncS2CPacket(p_60508_.getBlockPos(), te.getEntity()));
                }
                return InteractionResult.CONSUME;
            }
        }
        return super.use(state, level, pos, player, hand, p_60508_);
    }

    @Override
    public Class<SoulEntitySpawnerBlockEntity> getBlockEntityClass() {
        return SoulEntitySpawnerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SoulEntitySpawnerBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.SOUL_ENTITY_SPAWNER.get();
    }

}
