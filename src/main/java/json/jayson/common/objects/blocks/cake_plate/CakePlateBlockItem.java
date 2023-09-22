package json.jayson.common.objects.blocks.cake_plate;

import json.jayson.client.ClientEvents;
import json.jayson.client.renderer.BlockOutlineHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class CakePlateBlockItem extends BlockItem {
    public CakePlateBlockItem(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    @Override
    public Component getName(ItemStack p_41458_) {
        if(getBlock() instanceof CakePlate cakePlate) {
            ClientEvents.BLOCK_OUTLINE.outline.shape = cakePlate.SHAPE();
        }
        return super.getName(p_41458_);
    }
}
