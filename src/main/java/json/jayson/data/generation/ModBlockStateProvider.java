package json.jayson.data.generation;

import json.jayson.common.registries.SoulsBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import json.jayson.Soulslike;

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
                Block block1;
                if(block.block != null) {
                    block1 = block.block.getBlock();
                } else {
                    block1 = block.createBlock;
                }
                if(block.blockDataModel.modelFile == null) {
                    switch (block.blockDataModel.blockStateType) {
                        case SIMPLE_BLOCK_WITH_ITEM ->  simpleBlockWithItem(block1, cubeAll(block1));
                        //case SIMPLE_ITEM -> getVariantBuilder(block.block.getBlock()).partialState().setModels(new ConfiguredModel(ModItemModelProvider.INSTANCE.getBuilder(block.block.getItem().toString()).parent(new ModelFile.UncheckedModelFile("block/" + block.block.getItem().toString()))));
                        case SIMPLE_ITEM ->  getVariantBuilder(block1).partialState().setModels(new ConfiguredModel(ModItemModelProvider.INSTANCE.getBuilder(block.block.getItem().toString()).parent(new ModelFile.UncheckedModelFile("block/" + block.block.getItem().toString()))));
                        case SIMPLE_BLOCK -> simpleBlock(block1, cubeAll(block1));
                    }
                } else {
                    simpleBlockWithItem(block1, block.blockDataModel.modelFile);
                }
            }
        }
    }

}
