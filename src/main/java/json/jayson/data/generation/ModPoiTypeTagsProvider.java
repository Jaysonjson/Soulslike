package json.jayson.data.generation;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import json.jayson.Soulslike;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PoiTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.PoiTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModPoiTypeTagsProvider extends PoiTypeTagsProvider {

	public ModPoiTypeTagsProvider(PackOutput p_256012_, CompletableFuture<Provider> p_256617_,
			@Nullable ExistingFileHelper existingFileHelper) {
		super(p_256012_, p_256617_, Soulslike.MODID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void addTags(Provider p_256206_) {
		tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
		.addOptional(new ResourceLocation(Soulslike.MODID, "gem_poi"));
		super.addTags(p_256206_);
	}


}
