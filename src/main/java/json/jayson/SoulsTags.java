package json.jayson;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SoulsTags {

    public static final TagKey<Item> CAN_CREATE_FLAME = ItemTags.create(new ResourceLocation(Soulslike.MODID, "can_create_flame"));
    public static final TagKey<Block> CRATE = BlockTags.create(new ResourceLocation(Soulslike.MODID, "crate"));
    public static final TagKey<Block> VASE = BlockTags.create(new ResourceLocation(Soulslike.MODID, "vase"));
    public static final TagKey<Item> BIG_SWORDS = ItemTags.create(new ResourceLocation(Soulslike.MODID, "big_swords"));

    public static final TagKey<Block> CRAFTING_TABLES = BlockTags.create(new ResourceLocation(Soulslike.MODID, "souls_crafting_tables"));
    public static final TagKey<Item> CRAFTING_TABLES_ITEM = ItemTags.create(new ResourceLocation(Soulslike.MODID, "souls_crafting_tables"));

}
