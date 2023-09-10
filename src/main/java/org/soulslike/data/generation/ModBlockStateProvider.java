package org.soulslike.data.generation;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.soulslike.Soulslike;

public class ModBlockStateProvider extends BlockStateProvider {

    public static ModBlockStateProvider INSTANCE;

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Soulslike.MODID, exFileHelper);
        INSTANCE = this;
    }

    @Override
    public void registerStatesAndModels() {
        for (ModBlockDataGeneration.ModBlockDataGenHolder block : ModBlockDataGeneration.blocks) {
            if(block.blockDataModel != null) {
                if(block.blockDataModel.modelFile == null) {
                    switch (block.blockDataModel.blockStateType) {
                        case SIMPLE_BLOCK_WITH_ITEM ->  simpleBlockWithItem(block.block.getBlock(), cubeAll(block.block.getBlock()));
                        //case SIMPLE_ITEM -> getVariantBuilder(block.block.getBlock()).partialState().setModels(new ConfiguredModel(ModItemModelProvider.INSTANCE.getBuilder(block.block.getItem().toString()).parent(new ModelFile.UncheckedModelFile("block/" + block.block.getItem().toString()))));
                        case SIMPLE_ITEM ->  getVariantBuilder(block.block.getBlock()).partialState().setModels(new ConfiguredModel(ModItemModelProvider.INSTANCE.getBuilder(block.block.getItem().toString()).parent(new ModelFile.UncheckedModelFile("block/" + block.block.getItem().toString()))));
                        case SIMPLE_BLOCK -> simpleBlock(block.block.getBlock(), cubeAll(block.block.getBlock()));
                    }
                } else {
                    simpleBlockWithItem(block.block.getBlock(), block.blockDataModel.modelFile);
                }
            }
        }
    }
}
