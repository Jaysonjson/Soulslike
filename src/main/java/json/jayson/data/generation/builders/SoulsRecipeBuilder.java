package json.jayson.data.generation.builders;

import json.jayson.SoulsTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import json.jayson.Soulslike;

import java.util.function.Consumer;

public abstract class SoulsRecipeBuilder extends RecipeProvider implements IConditionBuilder {
    public SoulsRecipeBuilder(PackOutput p_248933_) {
        super(p_248933_);
    }

    public void crateRecipe(Consumer<FinishedRecipe> consumer, Item crate, Item planks, Item slabs) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, crate).
                define('S', planks).
                define('M', slabs).
                pattern("MMM").
                pattern("S S").
                pattern("MMM").
                unlockedBy(planks.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(planks).build())).save(consumer);
    }

    public void bigSwordRecipe(Consumer<FinishedRecipe> consumer, Item sword, Item material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword).define('S', Items.STICK).define('M', material).pattern(" MM").pattern("MMM").pattern("SM ").unlockedBy(material.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(material).build())).save(consumer);
    }

    public void daggerRecipe(Consumer<FinishedRecipe> consumer, Item sword, Item material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("M")
                .pattern("S")
                .unlockedBy(material.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(material).build())).save(consumer);
    }

    public void daggerRecipe(Consumer<FinishedRecipe> consumer, Item sword, TagKey<Item> material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .define('S', Items.STICK)
                .define('M', material)
                .pattern("M")
                .pattern("S")
                .unlockedBy(material.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(material).build())).save(consumer);
    }

    public void craftingTable(Consumer<FinishedRecipe> consumer, Item table, Item planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, table).
                define('M', planks).
                pattern("MM").
                pattern("MM").
                unlockedBy(planks.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(planks).build()))
                .save(consumer);
        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .requires(table)
                .unlockedBy(table.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(table).build()))
                .save(consumer, new ResourceLocation(Soulslike.MODID, table + "_to_vanilla"));*/
    }

    public void fullBlock(Consumer<FinishedRecipe> consumer, Item table, Item planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, table).
                define('M', planks).
                pattern("MMM").
                pattern("MMM").
                pattern("MMM").
                unlockedBy(planks.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(planks).build()))
                .save(consumer);
    }

    public void single(Consumer<FinishedRecipe> consumer, Item table, Item planks) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, table).requires(table)
                .unlockedBy(planks.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(planks).build()))
                .save(consumer);
    }

    public void bigSwordRecipe(Consumer<FinishedRecipe> consumer, Item sword, TagKey<Item> material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword).define('S', Items.STICK).define('M', material).pattern(" MM").pattern("MMM").pattern("SM ").unlockedBy(material.toString(), inventoryTrigger(ItemPredicate.Builder.item().of(material).build())).save(consumer);
    }

    public void smelting(Consumer<FinishedRecipe> recipeConsumer, ItemLike in, ItemLike out, float xp, int tickTime) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(in), RecipeCategory.BUILDING_BLOCKS, out, xp, tickTime).unlockedBy(getHasName(in), has(in)).save(recipeConsumer, new ResourceLocation(Soulslike.MODID, out.toString() + "_smelting"));
    }

    public void blasting(Consumer<FinishedRecipe> recipeConsumer, ItemLike in, ItemLike out, float xp, int tickTime) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(in), RecipeCategory.BUILDING_BLOCKS, out, xp, tickTime).unlockedBy(getHasName(in), has(in)).save(recipeConsumer, new ResourceLocation(Soulslike.MODID, out.toString() + "_smelting"));
    }

}
