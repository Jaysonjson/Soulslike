package json.jayson.common.objects.blocks.soul_entity_spawner;

import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.content.contraptions.bearing.BearingBlock;
import com.simibubi.create.content.contraptions.bearing.BearingInstance;
import com.simibubi.create.content.contraptions.bearing.BearingRenderer;
import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutBlock;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.gearbox.GearboxBlock;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.registries.SoulsBlockEntities;
import json.jayson.helpers.SoulsUtil;
import json.jayson.network.packet.CakePlateSyncS2CPacket;
import json.jayson.network.packet.ModMessages;
import json.jayson.network.packet.SoulEntitySpawnerSyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;

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
                te.setEntity(EntityType.ZOMBIE.getDescriptionId());
                te.setChanged();
                ModMessages.sendToClients(new SoulEntitySpawnerSyncS2CPacket(p_60508_.getBlockPos(), te.getEntity()));
            }
        }
        return super.use(state, level, pos, player, hand, p_60508_);
    }

    @Override
    public SpeedLevel getMinimumRequiredSpeedLevel() {
        return SpeedLevel.FAST;
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
