package json.jayson.common.registries;

import com.mojang.serialization.Codec;
import json.jayson.common.objects.codecs.EnchantmentCodec;

import java.util.List;

public class SoulsExtraCodecs {

    public static final Codec<List<EnchantmentCodec>> ENCHANTMENT_ARRAY = Codec.list(SoulsCodecs.ENCHANTMENT.get());

}
