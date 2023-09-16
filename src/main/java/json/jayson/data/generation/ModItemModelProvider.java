package json.jayson.data.generation;

import json.jayson.Soulslike;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public static ModItemModelProvider INSTANCE;
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Soulslike.MODID, existingFileHelper);
        INSTANCE = this;
    }

    @Override
    protected void registerModels() {
        for (ModBlockDataGeneration.ModBlockDataGenHolder block : ModBlockDataGeneration.blocks) {
            if(block.blockDataModel != null) {
                if(block.blockDataModel.modelFile == null) {
                    switch (block.blockDataModel.blockStateType) {
                        case SIMPLE_ITEM -> withExistingParent(block.block.Item().getId().getPath(), new ResourceLocation(Soulslike.MODID, "block/" + block.block.getItem().toString()));
                    }
                }
            }
        }
    }
}
