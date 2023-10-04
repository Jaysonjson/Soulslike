package json.jayson.common.objects.blocks.soul_drain;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SoulDrainBlock extends Block implements IWrenchable, IBE<SoulDrainBlockEntity> {
    public SoulDrainBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public Class<SoulDrainBlockEntity> getBlockEntityClass() {
        return SoulDrainBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SoulDrainBlockEntity> getBlockEntityType() {
        return SoulsBlockEntities.SOUL_DRAINER.get();
    }
}
