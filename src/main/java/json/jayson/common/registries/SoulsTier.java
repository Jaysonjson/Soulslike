package json.jayson.common.registries;

import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum SoulsTier implements Tier {
    BLAZE(3, 1361, 8.0F, 2.5F, 10, () -> Ingredient.of(Items.NETHERITE_INGOT)),
    SCRAPPED(4, 223, 9.0F, 4.4F, 20, () -> Ingredient.of(Items.AIR)),
    WOOD_SMALL(Tiers.WOOD.getLevel(), (int) (Tiers.WOOD.getUses() * 0.8), Tiers.WOOD.getSpeed(), 0.0F, 15, () -> Ingredient.of(ItemTags.PLANKS)),
    STONE_SMALL(Tiers.STONE.getLevel(),  (int) (Tiers.STONE.getUses() * 0.7), Tiers.STONE.getSpeed(), 1.0F, 5, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
    IRON_SMALL(Tiers.IRON.getLevel(),  (int) (Tiers.IRON.getUses() * 0.7), Tiers.IRON.getSpeed(), 2.0F, 14, () -> Ingredient.of(Items.IRON_INGOT)),
    DIAMOND_SMALL(Tiers.DIAMOND.getLevel(),  (int) (Tiers.DIAMOND.getUses() * 0.7), Tiers.DIAMOND.getSpeed(), 3.0F, 10, () -> Ingredient.of(Items.DIAMOND)),
    GOLD_SMALL(Tiers.GOLD.getLevel(),  (int) (Tiers.GOLD.getUses() * 0.7), 12.0F, Tiers.GOLD.getSpeed(), 22, () -> Ingredient.of(Items.GOLD_INGOT)),
    NETHERITE_SMALL(Tiers.NETHERITE.getLevel(),  (int) (Tiers.NETHERITE.getUses() * 0.7), Tiers.NETHERITE.getSpeed(), 4.0F, 15, () -> Ingredient.of(Items.NETHERITE_INGOT));

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

     SoulsTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredientSupplier) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(ingredientSupplier);
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}
