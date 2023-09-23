package json.jayson.data.generation;

import json.jayson.data.generation.builders.SoulsRecipeBuilder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import json.jayson.SoulsTags;
import json.jayson.Soulslike;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.common.registries.SoulsItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends SoulsRecipeBuilder {
    public ModRecipeProvider(PackOutput p_248933_) {
        super(p_248933_);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> p_251297_) {
        bigSwordRecipe(p_251297_, SoulsItems.BIG_WOODEN_SWORD.get(), ItemTags.PLANKS);
        bigSwordRecipe(p_251297_, SoulsItems.BIG_STONE_SWORD.get(), ItemTags.STONE_TOOL_MATERIALS);
        bigSwordRecipe(p_251297_, SoulsItems.BIG_IRON_SWORD.get(), Items.IRON_INGOT);
        bigSwordRecipe(p_251297_, SoulsItems.BIG_GOLD_SWORD.get(), Items.GOLD_INGOT);
        bigSwordRecipe(p_251297_, SoulsItems.BIG_DIAMOND_SWORD.get(), Items.DIAMOND);
        bigSwordRecipe(p_251297_, SoulsItems.BIG_BLAZE_SWORD.get(), SoulsItems.HARDENED_BLAZE.get());
        bigSwordRecipe(p_251297_, SoulsItems.BIG_TITANITOL_SWORD.get(), SoulsItems.TITANITOL_INGOT.get());
        netheriteSmithing(p_251297_, SoulsItems.BIG_DIAMOND_SWORD.get(), RecipeCategory.COMBAT, SoulsItems.BIG_NETHERITE_SWORD.get());

        daggerRecipe(p_251297_, SoulsItems.WOODEN_DAGGER.get(), ItemTags.PLANKS);
        daggerRecipe(p_251297_, SoulsItems.STONE_DAGGER.get(), ItemTags.STONE_TOOL_MATERIALS);
        daggerRecipe(p_251297_, SoulsItems.IRON_DAGGER.get(), Items.IRON_INGOT);
        daggerRecipe(p_251297_, SoulsItems.GOLDEN_DAGGER.get(), Items.GOLD_INGOT);
        daggerRecipe(p_251297_, SoulsItems.DIAMOND_DAGGER.get(), Items.DIAMOND);
        daggerRecipe(p_251297_, SoulsItems.TITANITOL_DAGGER.get(), SoulsItems.TITANITOL_INGOT.get());
        daggerRecipe(p_251297_, SoulsItems.BLAZE_DAGGER.get(), SoulsItems.HARDENED_BLAZE.get());
        netheriteSmithing(p_251297_, SoulsItems.DIAMOND_DAGGER.get(), RecipeCategory.COMBAT, SoulsItems.NETHERITE_DAGGER.get());

        //crateRecipe(p_251297_, SoulsBlocks.ACACIA_CRATE.getItem(), Blocks.ACACIA_PLANKS.asItem(), Blocks.ACACIA_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.OAK_LOG_CRATE.getItem(), Blocks.OAK_PLANKS.asItem(), Blocks.OAK_LOG.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.CRIMSON_CRATE.getItem(), Blocks.CRIMSON_PLANKS.asItem(), Blocks.CRIMSON_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.OAK_CRATE.getItem(), Blocks.OAK_PLANKS.asItem(), Blocks.OAK_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.WARPED_CRATE.getItem(), Blocks.WARPED_PLANKS.asItem(), Blocks.WARPED_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.BIRCH_CRATE.getItem(), Blocks.BIRCH_PLANKS.asItem(), Blocks.BIRCH_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.MANGROVE_CRATE.getItem(), Blocks.MANGROVE_PLANKS.asItem(), Blocks.MANGROVE_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.CHERRY_CRATE.getItem(), Blocks.CHERRY_PLANKS.asItem(), Blocks.CHERRY_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.SPRUCE_CRATE.getItem(), Blocks.SPRUCE_PLANKS.asItem(), Blocks.SPRUCE_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.JUNGLE_CRATE.getItem(), Blocks.JUNGLE_PLANKS.asItem(), Blocks.JUNGLE_SLAB.asItem());
        //crateRecipe(p_251297_, SoulsBlocks.DARK_OAK_CRATE.getItem(), Blocks.DARK_OAK_PLANKS.asItem(), Blocks.DARK_OAK_SLAB.asItem());

        craftingTable(p_251297_, SoulsBlocks.CHERRY_CRAFTING_TABLE.getItem(), Blocks.CHERRY_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.MANGROVE_CRAFTING_TABLE.getItem(), Blocks.MANGROVE_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.ACACIA_CRAFTING_TABLE.getItem(), Blocks.ACACIA_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.WARPED_CRAFTING_TABLE.getItem(), Blocks.WARPED_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.CRIMSON_CRAFTING_TABLE.getItem(), Blocks.CRIMSON_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.BAMBOO_CRAFTING_TABLE.getItem(), Blocks.BAMBOO_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.SPRUCE_CRAFTING_TABLE.getItem(), Blocks.SPRUCE_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.BIRCH_CRAFTING_TABLE.getItem(), Blocks.BIRCH_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.JUNGLE_CRAFTING_TABLE.getItem(), Blocks.JUNGLE_PLANKS.asItem());
        craftingTable(p_251297_, SoulsBlocks.DARK_OAK_CRAFTING_TABLE.getItem(), Blocks.DARK_OAK_PLANKS.asItem());

        fullBlock(p_251297_, SoulsBlocks.RUBY_BLOCK.getItem(), SoulsItems.RUBY.get());
        fullBlock(p_251297_, SoulsBlocks.SAPPHIRE_BLOCK.getItem(), SoulsItems.SAPPHIRE.get());
        fullBlock(p_251297_, SoulsBlocks.SAPPHIRE_SHARD_BLOCK.getItem(), SoulsItems.SAPPHIRE_SHARD.get());
        fullBlock(p_251297_, SoulsBlocks.RUBY_SHARD_BLOCK.getItem(), SoulsItems.RUBY_SHARD.get());

        single(p_251297_, SoulsBlocks.RUBY_BLOCK.getItem(), SoulsItems.RUBY.get(), 9);
        single(p_251297_, SoulsBlocks.SAPPHIRE_BLOCK.getItem(), SoulsItems.SAPPHIRE.get(),9);
        single(p_251297_, SoulsBlocks.SAPPHIRE_SHARD_BLOCK.getItem(), SoulsItems.SAPPHIRE_SHARD.get(), 9);
        single(p_251297_, SoulsBlocks.RUBY_SHARD_BLOCK.getItem(), SoulsItems.RUBY_SHARD.get(), 9);

        /*Override Vanilla */
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE.asItem()).
                define('M',  Blocks.OAK_PLANKS.asItem()).
                pattern("MM").
                pattern("MM").
                unlockedBy( Blocks.OAK_PLANKS.asItem().toString(), inventoryTrigger(ItemPredicate.Builder.item().of( Blocks.OAK_PLANKS.asItem()).build()))
                .save(p_251297_);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SoulsItems.ROSE_WAND.get())
                        .define('R', SoulsBlocks.RUBY_ROSE.getItem())
                                .define('S', Items.STICK)
                                        .pattern("R")
                                                .pattern("S").unlockedBy(SoulsBlocks.RUBY_ROSE.getItem().toString(), inventoryTrigger(ItemPredicate.Builder.item().of(SoulsBlocks.RUBY_ROSE.getItem()).build())).save(p_251297_);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.CRAFTING_TABLE).requires(SoulsTags.CRAFTING_TABLES_ITEM)
                .unlockedBy(Blocks.CRAFTING_TABLE.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.CRAFTING_TABLE).build()))
                .save(p_251297_,  new ResourceLocation(Soulslike.MODID, "oak_crafting_table"));


        blasting(p_251297_, SoulsItems.HARDENED_BLAZE.get(), Items.BLAZE_POWDER, 0.8f, 150);
        //blasting(p_251297_, SoulsItems.RUBY_SHARD.get(), SoulsItems.RUBY.get(), 5f, 500);
        //blasting(p_251297_, SoulsItems.SAPPHIRE_SHARD.get(), SoulsItems.SAPPHIRE.get(), 5f, 500);
    }
}
