package json.jayson.common.objects.armor;

import json.jayson.Soulslike;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class SoulsArmorMaterial implements ArmorMaterial {



    private final String name;
    private final int durability;
    private final int[] protection;
    private final int enchantment;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockBack;
    private final Supplier<Ingredient> ingredient;

    private static final int[] BASE = { 11, 16, 15, 13};

    public SoulsArmorMaterial(String name, int durability, int[] protection, int enchantment, SoundEvent equipSound, float toughness, float knockBack, Supplier<Ingredient> ingredient) {
        this.name = name;
        this.durability = durability;
        this.protection = protection;
        this.enchantment = enchantment;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockBack = knockBack;
        this.ingredient = ingredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type p_266807_) {
        return BASE[p_266807_.ordinal()] * durability;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type p_267168_) {
        return protection[p_267168_.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantment;
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return ingredient.get();
    }

    @Override
    public String getName() {
        return Soulslike.MODID + ":" + name;
    }

    @Override
    public float getToughness() {
        return toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return knockBack;
    }
}
