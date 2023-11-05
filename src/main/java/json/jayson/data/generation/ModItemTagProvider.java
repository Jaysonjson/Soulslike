package json.jayson.data.generation;

import json.jayson.Soulslike;
import json.jayson.common.registries.SoulsLootCodecs;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;
import json.jayson.SoulsTags;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.common.registries.SoulsItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Soulslike.MODID, existingFileHelper);
    }

    @SafeVarargs
    public final <T extends Item> void addItems(TagKey<Item> tagKey, RegistryObject<T>... items) {
        for (RegistryObject<T> item : items) {
            this.tag(tagKey).add(item.get()).replace(false);
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        addItems(SoulsTags.BIG_SWORDS, SoulsItems.BIG_NETHERITE_SWORD, SoulsItems.BIG_SCRAPPED_SWORD, SoulsItems.BIG_BLAZE_SWORD, SoulsItems.BIG_DIAMOND_SWORD, SoulsItems.BIG_GOLD_SWORD, SoulsItems.BIG_IRON_SWORD,
        SoulsItems.BIG_STONE_SWORD, SoulsItems.BIG_WOODEN_SWORD);

        addItems(SoulsTags.CRAFTING_TABLES_ITEM, SoulsBlocks.CHERRY_CRAFTING_TABLE.Item(), SoulsBlocks.BAMBOO_CRAFTING_TABLE.Item(), SoulsBlocks.ACACIA_CRAFTING_TABLE.Item(), SoulsBlocks.CRIMSON_CRAFTING_TABLE.Item(),
                SoulsBlocks.BIRCH_CRAFTING_TABLE.Item(), SoulsBlocks.JUNGLE_CRAFTING_TABLE.Item(), SoulsBlocks.SPRUCE_CRAFTING_TABLE.Item(), SoulsBlocks.DARK_OAK_CRAFTING_TABLE.Item(), SoulsBlocks.MANGROVE_CRAFTING_TABLE.Item(),
                SoulsBlocks.WARPED_CRAFTING_TABLE.Item());

        addItems(SoulsTags.CLOTH, SoulsItems.BLACK_CLOTH, SoulsItems.YELLOW_CLOTH,
                SoulsItems.WHITE_CLOTH, SoulsItems.RED_CLOTH, SoulsItems.PINK_CLOTH, SoulsItems.PURPLE_CLOTH, SoulsItems.ORANGE_CLOTH, SoulsItems.MAGENTA_CLOTH, SoulsItems.CYAN_CLOTH, SoulsItems.LIME_CLOTH,
                SoulsItems.GREEN_CLOTH, SoulsItems.LIGHT_GRAY_CLOTH, SoulsItems.LIGHT_BLUE_CLOTH, SoulsItems.BLUE_CLOTH, SoulsItems.GREEN_CLOTH, SoulsItems.GRAY_CLOTH, SoulsItems.BROWN_CLOTH);

        addItems(SoulsTags.BARK, SoulsItems.OAK_BARK, SoulsItems.BAMBOO_BARK, SoulsItems.BIRCH_BARK, SoulsItems.CHERRY_BARK, SoulsItems.CRIMSON_BARK, SoulsItems.DARK_OAK_BARK, SoulsItems.JUNGLE_BARK,
                SoulsItems.MANGROVE_BARK, SoulsItems.SPRUCE_BARK, SoulsItems.WARPED_BARK, SoulsItems.ACACIA_BARK);
    }
}
