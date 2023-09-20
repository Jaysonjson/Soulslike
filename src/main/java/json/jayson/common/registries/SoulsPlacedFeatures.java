package json.jayson.common.registries;

import java.util.List;

import json.jayson.Soulslike;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class SoulsPlacedFeatures {
public static final ResourceKey<PlacedFeature> SAPPHIRE_SEAGRASS_KEY = createKey("sapphire_seagrass_key");
	
	public static void bootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
		register(context, SAPPHIRE_SEAGRASS_KEY, configuredFeatures.getOrThrow(SoulsConfiguredFeatures.SAPPHIRE_SEAGRASS_KEY), List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(1), BiomeFilter.biome()));
	}

	
	public static ResourceKey<PlacedFeature> createKey(String key) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Soulslike.MODID, key));
	}
	
	private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> config, java.util.List<PlacementModifier> modifiers) {
		context.register(key, new PlacedFeature(config, modifiers));
	}
	
}
