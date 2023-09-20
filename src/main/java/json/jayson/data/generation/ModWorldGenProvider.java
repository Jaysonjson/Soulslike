package json.jayson.data.generation;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import json.jayson.Soulslike;
import json.jayson.common.registries.SoulsConfiguredFeatures;
import json.jayson.common.registries.SoulsPlacedFeatures;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider{

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, SoulsConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, SoulsPlacedFeatures::bootstrap);
	
	public ModWorldGenProvider(PackOutput output, CompletableFuture<Provider> registries) {
		super(output, registries, BUILDER, Set.of(Soulslike.MODID));
	}

}
