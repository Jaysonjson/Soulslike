package org.soulslike.common.registries;

import com.mojang.serialization.Codec;
import org.soulslike.common.objects.codecs.EnchantmentCodec;

import java.util.List;

public class SoulsExtraCodecs {

    public static final Codec<List<EnchantmentCodec>> ENCHANTMENT_ARRAY = Codec.list(SoulsCodecs.ENCHANTMENT.get());

}
