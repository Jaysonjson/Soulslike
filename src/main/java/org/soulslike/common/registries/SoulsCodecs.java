package org.soulslike.common.registries;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;
import org.soulslike.common.objects.codecs.EnchantmentCodec;
import org.soulslike.common.objects.codecs.RandomAmountCodec;

import java.util.function.Supplier;

public class SoulsCodecs {

    public static final Supplier<Codec<EnchantmentCodec>> ENCHANTMENT = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> inst.group(
                 ExtraCodecs.NON_EMPTY_STRING.optionalFieldOf("enchantment", "").forGetter(m -> m.enchantment))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("level", 1).forGetter(m -> m.level))
            .and(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("chance", 0f).forGetter(m -> m.chance))
            .and(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("upgrade_chance", 0f).forGetter(m -> m.upgrade_chance))
            .apply(inst, EnchantmentCodec::new)));

    public static final Supplier<Codec<RandomAmountCodec>> RANDOM_AMOUNT = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> inst.group(
                    ExtraCodecs.POSITIVE_INT.optionalFieldOf("min", 0).forGetter(m -> m.min))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("max", 1).forGetter(m -> m.max))
            .apply(inst, RandomAmountCodec::new)));

}
