package json.jayson.data.generation;

import json.jayson.Soulslike;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import json.jayson.SoulsTags;
import json.jayson.common.registries.SoulRegistryBlockItem;
import json.jayson.common.registries.SoulsBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Soulslike.MODID, existingFileHelper);
    }

    public static final TagKey<Block> PICKAXE = BlockTags.create(new ResourceLocation("minecraft", "pickaxe"));
    public static final TagKey<Block> AXE = BlockTags.create(new ResourceLocation("minecraft", "axe"));
    public static final TagKey<Block> SHOVEL = BlockTags.create(new ResourceLocation("minecraft", "shovel"));
    public static final TagKey<Block> HOE = BlockTags.create(new ResourceLocation("minecraft", "hoe"));

    @SafeVarargs
    public final void addBlocks(TagKey<Block> tagKey, SoulRegistryBlockItem<Block>... blockItem) {
        for (SoulRegistryBlockItem<Block> blockSoulRegistryBlockItem : blockItem) {
            this.tag(tagKey).add(blockSoulRegistryBlockItem.getBlock()).replace(false);
        }
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (ModBlockDataGeneration.ModBlockDataGenHolder block : ModBlockDataGeneration.blocks) {
            if(block.toolType.toolTag() != null) {
                this.tag(block.toolType.toolTag()).replace(false).add(block.block.getBlock());
            }
        }

        System.err.println("MANUALLY MOVE TAGS TO MINEABLE!");
        addBlocks(SoulsTags.CRATE, SoulsBlocks.OAK_CRATE, SoulsBlocks.OAK_LOG_CRATE, SoulsBlocks.CRIMSON_CRATE, SoulsBlocks.DARK_OAK_CRATE, SoulsBlocks.MANGROVE_CRATE, SoulsBlocks.ACACIA_CRATE,
                SoulsBlocks.BIRCH_CRATE, SoulsBlocks.JUNGLE_CRATE, SoulsBlocks.WARPED_CRATE, SoulsBlocks.SPRUCE_CRATE);
        addBlocks(SoulsTags.VASE, SoulsBlocks.MUD_VASE, SoulsBlocks.QUARTZ_VASE, SoulsBlocks.STONE_VASE, SoulsBlocks.NETHER_BRICKS_VASE);
        addBlocks(SoulsTags.CRAFTING_TABLES, SoulsBlocks.CHERRY_CRAFTING_TABLE, SoulsBlocks.BAMBOO_CRAFTING_TABLE, SoulsBlocks.ACACIA_CRAFTING_TABLE, SoulsBlocks.CRIMSON_CRAFTING_TABLE,
                SoulsBlocks.BIRCH_CRAFTING_TABLE, SoulsBlocks.JUNGLE_CRAFTING_TABLE, SoulsBlocks.SPRUCE_CRAFTING_TABLE, SoulsBlocks.DARK_OAK_CRAFTING_TABLE, SoulsBlocks.MANGROVE_CRAFTING_TABLE,
                SoulsBlocks.WARPED_CRAFTING_TABLE);
    }
}
