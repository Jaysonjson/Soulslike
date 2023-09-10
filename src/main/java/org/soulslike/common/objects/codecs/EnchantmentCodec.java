package org.soulslike.common.objects.codecs;

/*
* Example:
*  "enchantment": {
*   "enchantment": "minecraft:sharpness",
    "level": 1,
    "chance": 0.55,
    "upgrade_chance": 0.15
*  }
*
* */
public class EnchantmentCodec {

    public final String enchantment;
    public final int level;
    public final float chance;
    /* Chance that Level gets upgraded by 1 */
    public final float upgrade_chance;

    public EnchantmentCodec(String enchantment, int level, float chance, float upgrade_chance) {
        this.enchantment = enchantment;
        this.level = level;
        this.chance = chance;
        this.upgrade_chance = upgrade_chance;
    }

}
