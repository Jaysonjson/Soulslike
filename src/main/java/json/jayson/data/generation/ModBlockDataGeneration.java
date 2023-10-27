
package json.jayson.data.generation;

import com.tterrag.registrate.util.entry.BlockEntry;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlock;
import json.jayson.data.generation.interfaces.ILootTable;
import json.jayson.data.generation.interfaces.IToolType;
import json.jayson.data.generation.lists.LootTables;
import json.jayson.data.generation.lists.ToolTypes;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import json.jayson.common.registries.SoulRegistryBlockItem;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.common.registries.SoulsItems;

import java.util.ArrayList;

public class ModBlockDataGeneration {

    public static class ModBlockDataModel {
        /* When NULL, then cubeAll will be used */
        public final ModelFile modelFile;
        public final BlockStateType blockStateType;

        ModBlockDataModel(ModelFile modelFile, BlockStateType blockStateType) {
            this.modelFile = modelFile;
            this.blockStateType = blockStateType;
        }
    }

    public static class ModBlockDataGenHolder {
        public final IToolType toolType;
        public final ILootTable lootTable;

        public final SoulRegistryBlockItem<Block> block;
        public Block createBlock;

        public final ModBlockDataModel blockDataModel;

        ModBlockDataGenHolder(SoulRegistryBlockItem<Block> block, IToolType toolType, ILootTable lootTable, ModBlockDataModel blockDataModel) {
            this.toolType = toolType;
            this.lootTable = lootTable;
            this.block = block;
            this.blockDataModel = blockDataModel;
        }

        ModBlockDataGenHolder(Block block, IToolType toolType, ILootTable lootTable, ModBlockDataModel blockDataModel) {
            this.toolType = toolType;
            this.lootTable = lootTable;
            this.createBlock = block;
            this.blockDataModel = blockDataModel;
            this.block = null;
        }
    }

    public static ArrayList<ModBlockDataGenHolder> blocks = new ArrayList<>();

    public static void register() {
        add(SoulsBlocks.DARKENED_NETHER_BRICKS, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
       // add(SoulsBlocks.OAK_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.BEE_STATUE, ToolTypes.NONE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.FOX_ALTAR,ToolTypes.NONE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.MUD_VASE, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.NETHER_BRICKS_VASE, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.STONE_VASE, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.QUARTZ_VASE, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.TITANITOL_BLOCK, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
        add(SoulsBlocks.FIRE_ALTAR, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF);
        //add(SoulsBlocks.SPRUCE_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.BIRCH_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.JUNGLE_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.ACACIA_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.DARK_OAK_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.MANGROVE_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.CRIMSON_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.WARPED_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.OAK_LOG_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.CAKE_PLATE, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.QUARTZ_CAKE_PLATE, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.BURNING_COAL, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
        //add(SoulsBlocks.STEEL_BLOCK, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
        //add(SoulsBlocks.CHERRY_CRATE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        //add(SoulsBlocks.PILLORY, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.HARDENED_BLAZE_PIECE, ToolTypes.NONE, LootTables.DROP_OTHER(SoulsItems.HARDENED_BLAZE.get()), BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.CHERRY_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.ACACIA_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.MANGROVE_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.WARPED_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.CRIMSON_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.BAMBOO_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.SPRUCE_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.BIRCH_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.JUNGLE_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.DARK_OAK_CRAFTING_TABLE, ToolTypes.DEFAULT_AXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_ITEM);
        add(SoulsBlocks.RUBY_ROSE, ToolTypes.NONE, LootTables.DROP_OTHER(SoulsItems.RUBY_SHARD.get()), BlockStateType.NONE);
        add(SoulsBlocks.VIBRANT_SOUL_ENTITY_SPAWNER.get(), ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.NONE);
        add(SoulsBlocks.RUBY_BLOCK, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
        add(SoulsBlocks.SAPPHIRE_BLOCK, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
        add(SoulsBlocks.RUBY_SHARD_BLOCK, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);
        add(SoulsBlocks.SAPPHIRE_SHARD_BLOCK, ToolTypes.DEFAULT_PICKAXE, LootTables.DROP_SELF, BlockStateType.SIMPLE_BLOCK_WITH_ITEM);

    }

    public static void add(Block block, IToolType toolType, ILootTable lootTable) {
        add(block, toolType, lootTable, new ModBlockDataModel(null, BlockStateType.NONE));
    }

    public static void add(Block block, IToolType toolType, ILootTable lootTable, ModBlockDataModel blockDataModel) {
        blocks.add(new ModBlockDataGenHolder(block, toolType, lootTable, blockDataModel));
    }


    public static void add(Block block, IToolType toolType, ILootTable lootTable, BlockStateType blockStateType) {
        blocks.add(new ModBlockDataGenHolder(block, toolType, lootTable, new ModBlockDataModel(null, blockStateType)));
    }

    public static void add(SoulRegistryBlockItem<Block> block, IToolType toolType, ILootTable lootTable) {
        add(block, toolType, lootTable, new ModBlockDataModel(null, BlockStateType.NONE));
    }

    public static void add(SoulRegistryBlockItem<Block> block, IToolType toolType, ILootTable lootTable, ModBlockDataModel blockDataModel) {
        blocks.add(new ModBlockDataGenHolder(block, toolType, lootTable, blockDataModel));
    }


    public static void add(SoulRegistryBlockItem<Block> block, IToolType toolType, ILootTable lootTable, BlockStateType blockStateType) {
        blocks.add(new ModBlockDataGenHolder(block, toolType, lootTable, new ModBlockDataModel(null, blockStateType)));
    }


}
