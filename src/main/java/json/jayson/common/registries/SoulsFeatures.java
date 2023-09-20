package json.jayson.common.registries;

import json.jayson.Soulslike;
import json.jayson.common.objects.features.SapphireSeaGrassFeature;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SoulsFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, Soulslike.MODID);

    public static final RegistryObject<SapphireSeaGrassFeature> SAPPHIRE_SEAGRASS = register("sapphire_seagrass", () -> new SapphireSeaGrassFeature(ProbabilityFeatureConfiguration.CODEC));


    private static <T extends Feature<?>> RegistryObject<T> register(String name, Supplier<T> feature) {
        RegistryObject<T> registryObject = FEATURES.register(name, feature);
        return registryObject;
    }

    public static void registerBus(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

}
