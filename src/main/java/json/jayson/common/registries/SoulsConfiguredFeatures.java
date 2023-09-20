package json.jayson.common.registries;


import json.jayson.Soulslike;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class SoulsConfiguredFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAPPHIRE_SEAGRASS_KEY = createKey("sapphire_seagrass");
	
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		//register(context, SAPPHIRE_SEAGRASS_KEY, Feature.SEAGRASS, new ProbabilityFeatureConfiguration(1F));
		register(context, SAPPHIRE_SEAGRASS_KEY, SoulsFeatures.SAPPHIRE_SEAGRASS.get(), new ProbabilityFeatureConfiguration(0.5f));
	}
	
	
	
	public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String key) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Soulslike.MODID, key));
	}
	
	private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
		context.register(key, new ConfiguredFeature<>(feature, config));
	}
	
}
