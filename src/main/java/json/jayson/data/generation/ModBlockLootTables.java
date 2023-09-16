package json.jayson.data.generation;

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
            if(modBlockDataGenHolder.lootTable.dropSelf()) {
                dropSelf(modBlockDataGenHolder.block.getBlock());
            }
            if(modBlockDataGenHolder.lootTable.dropOther() != null) {
                dropOther(modBlockDataGenHolder.block.getBlock(), modBlockDataGenHolder.lootTable.dropOther());
            }
            if(modBlockDataGenHolder.lootTable.dropWhenSilktouch()) {
                dropWhenSilkTouch(modBlockDataGenHolder.block.getBlock());
            }
        }
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (ModBlockDataGeneration.ModBlockDataGenHolder block : ModBlockDataGeneration.blocks) {
            if(block.lootTable.dropSelf()) {
                blocks.add(block.block.getBlock());
            }
            if(block.lootTable.dropOther() != null) {
                blocks.add(block.block.getBlock());
            }
        }
        return blocks;
    }
}
