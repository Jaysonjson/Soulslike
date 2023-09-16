package org.soulslike.common.registries;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.soulslike.Soulslike;
import org.soulslike.common.objects.blocks.HardenedBlazePiece;
import org.soulslike.common.objects.blocks.SoulsCraftingTable;
import org.soulslike.common.objects.blocks.SoulsLiquidBlock;
import org.soulslike.common.objects.blocks.fire_altar.FireAltar;
import org.soulslike.common.objects.blocks.cake_plate.CakePlate;
import org.soulslike.common.objects.blocks.crate.GenericCrate;
import org.soulslike.common.objects.blocks.vase.GenericVase;
import org.soulslike.common.objects.blocks.bee_statue.SoulsBeeStatue;
import org.soulslike.common.objects.blocks.fox_altar.SoulsFoxAltar;

import java.util.function.Supplier;

public class SoulsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Soulslike.MODID);

    public static final SoulRegistryBlockItem<Block>
            BEE_STATUE = registerBlock("bee_statue", () -> new SoulsBeeStatue(BlockBehaviour.Properties.of().strength(99999f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
            FOX_ALTAR = registerBlock("fox_altar", () -> new SoulsFoxAltar(BlockBehaviour.Properties.of().strength(6f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
            DARKENED_NETHER_BRICKS = registerBlock("darkened_nether_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
            MUD_VASE = registerBlock("mud_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
            NETHER_BRICKS_VASE = registerBlock("nether_bricks_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    STONE_VASE = registerBlock("stone_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    QUARTZ_VASE = registerBlock("quartz_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    TITANITOL_BLOCK = registerBlock("titanitol_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    FIRE_ALTAR = registerBlock("fire_altar", () -> new FireAltar(BlockBehaviour.Properties.of().strength(3f).lightLevel(state -> state.getValue(FireAltar.UNLIGHTED) ? 1 : 13).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    OAK_CRATE = registerBlock("oak_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    SPRUCE_CRATE = registerBlock("spruce_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BIRCH_CRATE = registerBlock("birch_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    JUNGLE_CRATE = registerBlock("jungle_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    ACACIA_CRATE = registerBlock("acacia_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    DARK_OAK_CRATE = registerBlock("dark_oak_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    MANGROVE_CRATE = registerBlock("mangrove_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CRIMSON_CRATE = registerBlock("crimson_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    WARPED_CRATE = registerBlock("warped_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CHERRY_CRATE = registerBlock("cherry_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    OAK_LOG_CRATE = registerBlock("oak_log_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CAKE_PLATE = registerBlock("cake_plate", () -> new CakePlate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    QUARTZ_CAKE_PLATE = registerBlock("quartz_cake_plate", () -> new CakePlate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BURNING_COAL = registerBlock("burning_coal", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    PILLORY = registerBlock("pillory", () -> new Block(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    HARDENED_BLAZE_PIECE = registerBlock("hardened_blaze_piece", () -> new HardenedBlazePiece(BlockBehaviour.Properties.of().noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CHERRY_CRAFTING_TABLE = registerBlock("cherry_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    ACACIA_CRAFTING_TABLE = registerBlock("acacia_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    MANGROVE_CRAFTING_TABLE = registerBlock("mangrove_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    WARPED_CRAFTING_TABLE = registerBlock("warped_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CRIMSON_CRAFTING_TABLE = registerBlock("crimson_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BAMBOO_CRAFTING_TABLE = registerBlock("bamboo_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    SPRUCE_CRAFTING_TABLE = registerBlock("spruce_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BIRCH_CRAFTING_TABLE = registerBlock("birch_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    JUNGLE_CRAFTING_TABLE = registerBlock("jungle_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    DARK_OAK_CRAFTING_TABLE = registerBlock("dark_oak_crafting_table", () -> new SoulsCraftingTable(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    RUBY_ROSE = registerBlock("ruby_rose", () -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.copy(Blocks.POPPY)), SoulsCreativeTabs.OTHER_TAB)
            ;


    public static final SoulRegistryBlockItem<SoulsLiquidBlock> BLOOD_BLOCK = registerBlock("blood_block", () -> new SoulsLiquidBlock(SoulsFluids.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER)));


    private static <T extends Block> SoulRegistryBlockItem<T> registerBlock(String name, Supplier<T> block, RegistryObject<CreativeModeTab> tabProvider) {
        RegistryObject<T> blockRegistry = BLOCKS.register(name, block);
        RegistryObject<Item> itemRegistry = SoulsItems.ITEMS.register(name, () -> new BlockItem(blockRegistry.get(), new Item.Properties()));
        SoulsItems.creativeTabs.put(itemRegistry, tabProvider);
        return new SoulRegistryBlockItem<>(itemRegistry, blockRegistry);
    }

    private static <T extends Block> SoulRegistryBlockItem<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, null);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
