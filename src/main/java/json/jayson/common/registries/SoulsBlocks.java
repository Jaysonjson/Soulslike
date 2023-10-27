package json.jayson.common.registries;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.utility.Couple;
import com.tterrag.registrate.util.entry.BlockEntry;
import json.jayson.Soulslike;
import json.jayson.common.objects.blocks.*;
import json.jayson.common.objects.blocks.simple_soul_generator.SoulGeneratorBlock;
import json.jayson.common.objects.blocks.simple_soul_generator.SoulGeneratorState;
import json.jayson.common.objects.blocks.soul_catcher.SoulCatcherBlock;
import json.jayson.common.objects.blocks.soul_dispenser.SoulDispenserBlock;
import json.jayson.common.objects.blocks.soul_drain.SoulDrainBlock;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import json.jayson.common.objects.blocks.fire_altar.FireAltar;
import json.jayson.common.objects.blocks.cake_plate.CakePlate;
import json.jayson.common.objects.blocks.vase.GenericVase;
import json.jayson.common.objects.blocks.bee_statue.SoulsBeeStatue;
import json.jayson.common.objects.blocks.fox_altar.SoulsFoxAltar;

import java.util.function.Supplier;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static json.jayson.Soulslike.SOULS_REGISTRATE;

public class SoulsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Soulslike.MODID);
    static { SOULS_REGISTRATE.setCreativeTab(SoulsCreativeTabs.BUILDING_BLOCK_TAB); }
    @SuppressWarnings("deprecation")
	public static final SoulRegistryBlockItem<Block>
            BEE_STATUE = registerBlock("bee_statue", () -> new SoulsBeeStatue(BlockBehaviour.Properties.of().strength(99999f).noOcclusion()), SoulsCreativeTabs.BLOCK_TAB),
            FOX_ALTAR = registerBlock("fox_altar", () -> new SoulsFoxAltar(BlockBehaviour.Properties.of().strength(6f).noOcclusion()), SoulsCreativeTabs.BLOCK_TAB),
            DARKENED_NETHER_BRICKS = registerBlock("darkened_nether_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
            MUD_VASE = registerBlock("mud_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
            NETHER_BRICKS_VASE = registerBlock("nether_bricks_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    STONE_VASE = registerBlock("stone_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    QUARTZ_VASE = registerBlock("quartz_vase", () -> new GenericVase(BlockBehaviour.Properties.of().strength(1f).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    TITANITOL_BLOCK = registerBlock("titanitol_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    FIRE_ALTAR = registerBlock("fire_altar", () -> new FireAltar(BlockBehaviour.Properties.of().strength(3f).lightLevel(state -> state.getValue(FireAltar.UNLIGHTED) ? 1 : 13).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
   /* OAK_CRATE = registerBlock("oak_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    SPRUCE_CRATE = registerBlock("spruce_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BIRCH_CRATE = registerBlock("birch_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    JUNGLE_CRATE = registerBlock("jungle_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    ACACIA_CRATE = registerBlock("acacia_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    DARK_OAK_CRATE = registerBlock("dark_oak_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    MANGROVE_CRATE = registerBlock("mangrove_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CRIMSON_CRATE = registerBlock("crimson_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    WARPED_CRATE = registerBlock("warped_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    CHERRY_CRATE = registerBlock("cherry_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    OAK_LOG_CRATE = registerBlock("oak_log_crate", () -> new GenericCrate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),*/
    CAKE_PLATE = registerBlock("cake_plate", () -> new CakePlate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    QUARTZ_CAKE_PLATE = registerBlock("quartz_cake_plate", () -> new CakePlate(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BURNING_COAL = registerBlock("burning_coal", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    //STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    //PILLORY = registerBlock("pillory", () -> new Block(BlockBehaviour.Properties.of().strength(3f).noOcclusion()), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
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
    SAPPHIRE_SEAGRASS = registerBlock("sapphire_seagrass", () -> new SeagrassBlock(BlockBehaviour.Properties.copy(Blocks.SEAGRASS)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    //SAPPHIRE_SEA_PICKLE = registerBlock("sapphire_sea_pickle", () -> new SeaPickleBlock(BlockBehaviour.Properties.copy(Blocks.SEA_PICKLE)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    RUBY_ROSE = registerBlock("ruby_rose", () -> new FlowerBlock(MobEffects.NIGHT_VISION, 5, BlockBehaviour.Properties.copy(Blocks.POPPY)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    RUBY_SHARD_BLOCK = registerBlock("ruby_shard_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    SAPPHIRE_SHARD_BLOCK = registerBlock("sapphire_shard_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    GEM_STATION = registerBlock("gem_station", () -> new Block(BlockBehaviour.Properties.of().strength(3f)), SoulsCreativeTabs.BLOCK_TAB),
    BLOSSOM_LANTERN = registerBlock("blossom_lantern", () -> new LanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB),
    BLOSSOM_ROD = registerBlock("blossom_rod", () -> new BlossomRod(BlockBehaviour.Properties.copy(Blocks.END_ROD).strength(3f)), SoulsCreativeTabs.BUILDING_BLOCK_TAB)

    ;

    public static final BlockEntry<SoulEntitySpawnerBlock> SOUL_ENTITY_SPAWNER =
            SOULS_REGISTRATE.block("soul_entity_spawner", SoulEntitySpawnerBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.SNOW))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                    .transform(BlockStressDefaults.setImpact(128.0))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<SoulCatcherBlock> SOUL_CATCHER =
            SOULS_REGISTRATE.block("soul_catcher", SoulCatcherBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.SNOW))
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .transform(axeOrPickaxe())
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
                    .transform(BlockStressDefaults.setImpact(32.0))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<SoulDrainBlock> SOUL_DRAIN =
            SOULS_REGISTRATE.block("soul_drain", SoulDrainBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .transform(pickaxeOnly())
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
                    .simpleItem()
                    .register();

    public static final BlockEntry<SoulDispenserBlock> SOUL_DISPENSER =
            SOULS_REGISTRATE.block("soul_dispenser", SoulDispenserBlock::new)
                    .initialProperties(SharedProperties::copperMetal)
                    .transform(pickaxeOnly())
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
                    .simpleItem()
                    .register();

    public static final BlockEntry<SoulCasingBlock> SOUL_CASING = SOULS_REGISTRATE.block("soul_casing", SoulCasingBlock::new)
            .properties(p -> p.mapColor(MapColor.SNOW))
            .transform(BuilderTransformers.casing(() -> SoulsSpriteShifts.SOUL_CASING))
            .blockstate((c, p) -> p.simpleBlock(c.get(), AssetLookup.standardModel(c, p)))
            .simpleItem()
            .register();

    public static final BlockEntry<SoulGeneratorBlock> SIMPLE_SOUL_GENERATOR =
            SOULS_REGISTRATE.block("simple_soul_generator", SoulGeneratorBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.SNOW).forceSolidOn())
                    .transform(pickaxeOnly())
                    .blockstate(new SoulGeneratorState()::generate)
                    .transform(BlockStressDefaults.setCapacity(5124.0))
                    .transform(BlockStressDefaults.setGeneratorSpeed(() -> Couple.create(64, 64)))
                    .item()
                    .properties(p -> p.rarity(Rarity.RARE))
                    .transform(customItemModel())
                    .register();

    public static final SoulRegistryBlockItem<SoulsLiquidBlock> BLOOD_BLOCK = registerBlock("blood", () -> new SoulsLiquidBlock(SoulsFluids.SOURCE_BLOOD, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final SoulRegistryBlockItem<SoulsLiquidBlock> SOUL_BLOCK = registerBlock("soul", () -> new SoulsLiquidBlock(SoulsFluids.SOURCE_SOUL, BlockBehaviour.Properties.copy(Blocks.WATER)));


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
        SOULS_REGISTRATE.setCreativeTab(SoulsCreativeTabs.BUILDING_BLOCK_TAB);
        BLOCKS.register(eventBus);
    }
}
