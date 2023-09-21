package json.jayson.common.objects.blocks.soul_entity_spawner;

import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class SoulEntitySpawnerBlock extends KineticBlock implements IBE<SoulEntitySpawnerBlockEntity>, ICogWheel {
    public SoulEntitySpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
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
