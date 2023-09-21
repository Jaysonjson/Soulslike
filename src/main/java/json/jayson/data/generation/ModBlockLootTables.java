package json.jayson.data.generation;

import json.jayson.common.registries.SoulsBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (ModBlockDataGeneration.ModBlockDataGenHolder modBlockDataGenHolder : ModBlockDataGeneration.blocks) {
            Block block1;
            if(modBlockDataGenHolder.block != null) {
                block1 = modBlockDataGenHolder.block.getBlock();
            } else {
                block1 = modBlockDataGenHolder.createBlock;
            }
            if(modBlockDataGenHolder.lootTable.dropSelf()) {
                dropSelf(block1);
            }
            if(modBlockDataGenHolder.lootTable.dropOther() != null) {
                dropOther(block1, modBlockDataGenHolder.lootTable.dropOther());
            }
            if(modBlockDataGenHolder.lootTable.dropWhenSilktouch()) {
                dropWhenSilkTouch(block1);
            }
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (ModBlockDataGeneration.ModBlockDataGenHolder block : ModBlockDataGeneration.blocks) {
            Block block1;
            if(block.block != null) {
                block1 = block.block.getBlock();
            } else {
                block1 = block.createBlock;
            }
            if(block.lootTable.dropSelf()) {
                blocks.add(block1);
            }
            if(block.lootTable.dropOther() != null) {
                blocks.add(block1);
            }
        }
        return blocks;
    }
}
