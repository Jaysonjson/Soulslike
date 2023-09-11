package org.soulslike.common.objects.codecs;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Deprecated
public class EnchantmentCodecArray {

    public ArrayList<EnchantmentCodec> enchantments = new ArrayList<>();
    public static class Serializer implements JsonSerializer<EnchantmentCodecArray>, JsonDeserializer<EnchantmentCodecArray> {
        @Override
        public EnchantmentCodecArray deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final EnchantmentCodecArray result = new EnchantmentCodecArray();
            if (json instanceof JsonArray) {
                for (final JsonElement element : (JsonArray) json) {
                    if (element instanceof JsonObject object) {
                        EnchantmentCodec codec = new EnchantmentCodec(
                                object.getAsJsonPrimitive("enchantment").getAsString(),
                                object.getAsJsonPrimitive("level").getAsInt(),
                                object.getAsJsonPrimitive("chance").getAsFloat(),
                                object.getAsJsonPrimitive("upgrade_chance").getAsFloat());
                        result.enchantments.add(codec);
                    }
                }
            }
            return result;
        }

        @Override
        public JsonElement serialize(EnchantmentCodecArray src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonArray result = new JsonArray();
            for (final EnchantmentCodec enchantmentCodec : src.enchantments) {
                final JsonObject object = new JsonObject();
                object.addProperty("enchantment", enchantmentCodec.enchantment);
                object.addProperty("level", enchantmentCodec.level);
                object.addProperty("chance", enchantmentCodec.chance);
                object.addProperty("upgrade_chance", enchantmentCodec.upgrade_chance);
                result.add(object);
            }
            return result;
        }
    }
}
