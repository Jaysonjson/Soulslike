package json.jayson.common.registries;

import com.mojang.serialization.Codec;
import json.jayson.Soulslike;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import json.jayson.common.objects.loot_modifiers.NewSingleItemLootModifier;
import json.jayson.common.objects.loot_modifiers.SingleItemLootModifier;

public class SoulsLootCodecs {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> CODECS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Soulslike.MODID);
    public static final RegistryObject<Codec<SingleItemLootModifier>> SINGLE_ITEM = CODECS.register("single_item", SingleItemLootModifier.CODEC);
    public static final RegistryObject<Codec<NewSingleItemLootModifier>> NEW_SINGLE_ITEM = CODECS.register("new_single_item", NewSingleItemLootModifier.CODEC);

    public static void register(IEventBus eventBus) {
        CODECS.register(eventBus);
    }
}
