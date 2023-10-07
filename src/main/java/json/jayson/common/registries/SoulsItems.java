package json.jayson.common.registries;

import json.jayson.Soulslike;
import json.jayson.common.Data;
import json.jayson.common.objects.items.*;
import json.jayson.common.objects.items.gecko.WandItem;
import json.jayson.common.objects.items.staff.LoveStaff;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import json.jayson.common.objects.items.staff.CloudStaff;
import json.jayson.common.objects.items.staff.FeatherStaff;
import json.jayson.common.objects.items.staff.RoseStaff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SoulsItems {

    public static HashMap< RegistryObject<Item>, RegistryObject<CreativeModeTab>> creativeTabs = new HashMap<>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Soulslike.MODID);

    public static final RegistryObject<Item>
    DWARF_COAL = registerItem("dwarf_coal", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    BEE_TEARS = registerItem("bee_tears", () -> new BeeTears(new Item.Properties().stacksTo(1)), SoulsCreativeTabs.OTHER_TAB),
    UNKNOWN_SOUL = registerItem("unknown_soul", () -> new SoulItem(new Item.Properties(), 50, "item.soulslike.unknown_soul.description"), SoulsCreativeTabs.SOULS_TAB),
    SOUL_OF_A_GIANT = registerItem("soul_of_a_giant", () -> new SoulItem(new Item.Properties(), 375, "item.soulslike.soul_of_a_giant.description"), SoulsCreativeTabs.SOULS_TAB),

    DIMENSIONALS_SOUL = registerItem("dimensionals_soul", () -> new SoulItem(new Item.Properties(), 1000, "item.soulslike.soul_of_a_giant.description"), SoulsCreativeTabs.SOULS_TAB),
    EXPLORERS_SOUL = registerItem("explorers_soul", () -> new SoulItem(new Item.Properties(), 125, "item.soulslike.soul_of_a_giant.description"), SoulsCreativeTabs.SOULS_TAB),

    WTF_SOUL = registerItem("wtf_souls", () -> new SoulItem(new Item.Properties(), 999999999, "item.soulslike.soul_of_a_giant.description")),
    TITANITOL_INGOT = registerItem("titanitol_ingot",
            () -> new ItemWithDescription(new Item.Properties(), Component.translatable("item.soulslike.titanitol_ingot.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC)), SoulsCreativeTabs.OTHER_TAB),
    HARDENED_BLAZE = registerItem("hardened_blaze", () -> new ItemWithDescription(new Item.Properties(), Component.translatable("item.soulslike.hardened_blaze.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC)), SoulsCreativeTabs.OTHER_TAB),
    CLOUD_STAFF = registerItem("cloud_staff", () -> new CloudStaff(new Item.Properties().defaultDurability(450)), SoulsCreativeTabs.WEAPON_TAB),
    CLOUD_WAND = registerItem("cloud_wand", () -> new CloudStaff(new Item.Properties().defaultDurability(325), 3, new Vec3(0.9, 0.9, 0.9)), SoulsCreativeTabs.WEAPON_TAB),
    ROSE_WAND = registerItem("rose_wand", () -> new RoseStaff(new Item.Properties().defaultDurability(325), 2), SoulsCreativeTabs.WEAPON_TAB),
    ROSE_STAFF = registerItem("rose_staff", () -> new RoseStaff(new Item.Properties().defaultDurability(450), 4), SoulsCreativeTabs.WEAPON_TAB),
    RUBY = registerItem("ruby", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    RUBY_SHARD = registerItem("ruby_shard", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    SAPPHIRE = registerItem("sapphire", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    SAPPHIRE_SHARD = registerItem("sapphire_shard", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    FEATHER_STAFF = registerItem("feather_staff", () -> new FeatherStaff(new Item.Properties().defaultDurability(450), 120), SoulsCreativeTabs.WEAPON_TAB),
    FEATHER_WAND = registerItem("feather_wand", () -> new FeatherStaff(new Item.Properties().defaultDurability(325), 200), SoulsCreativeTabs.WEAPON_TAB),
    BLOOD_BUCKET = registerItem("blood_bucket", () -> new BucketItem(SoulsFluids.SOURCE_BLOOD, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))),
    SOUL_BUCKET = registerItem("soul_bucket", () -> new BucketItem(SoulsFluids.SOURCE_SOUL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1))),

    SCROLL = registerItem("scroll", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    OAK_BARK = registerItem("oak_bark", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    OAK_WAND = registerItem("oak_wand", () -> new WandItem(new Item.Properties()), SoulsCreativeTabs.WEAPON_TAB),

    LOVE_STAFF = registerItem("love_staff", () -> new LoveStaff(new Item.Properties().defaultDurability(100)), SoulsCreativeTabs.WEAPON_TAB),


    THIGH_HIGHS = registerItem("thigh_highs", () -> new ThighHighs(new Item.Properties())),
    BRYN = registerItem("bryn", () -> new Item(new Item.Properties())),
    CALIDA = registerItem("calida", () -> new Item(new Item.Properties())),
    PURA = registerItem("pura", () -> new Item(new Item.Properties())),
    MORTIS = registerItem("mortis", () -> new Item(new Item.Properties())),
    VITA = registerItem("vita", () -> new Item(new Item.Properties())),
    RUBY_DUST = registerItem("ruby_dust", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    SAPPHIRE_DUST = registerItem("sapphire_dust", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    POLISHED_RUBY = registerItem("polished_ruby", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
   SOUL_INGOT = registerItem("soul_ingot", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
   VIBRANT_SOUL_ALLOY = registerItem("vibrant_soul_alloy", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    BOTTLE_OF_SOULS = registerItem("bottle_of_souls", () -> new BottleOfSoulsItem(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    VIBRANT_SOUL_SHEET = registerItem("vibrant_soul_sheet", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB),
    SOUL_SPAWNER_CORE = registerItem("soul_spawner_core", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB)
                    ;

    public static final RegistryObject<SwordItem>
    BIG_IRON_SWORD = registerSword("big_iron_sword", Tiers.IRON, 8, -3.5f),
    BIG_GOLD_SWORD = registerSword("big_gold_sword", Tiers.GOLD, 7, -3.5f),
    BIG_DIAMOND_SWORD = registerSword("big_diamond_sword", Tiers.DIAMOND, 8, -3.5f),
    BIG_STONE_SWORD = registerSword("big_stone_sword", Tiers.STONE, 8, -3.5f),
    BIG_WOODEN_SWORD = registerSword("big_wooden_sword", Tiers.WOOD, 6, -3.5f),
    BIG_BLAZE_SWORD = registerSword("big_blaze_sword", SoulsTier.BLAZE, 7, -3.5f),
    BIG_NETHERITE_SWORD = registerSword("big_netherite_sword", Tiers.NETHERITE, 9, -3.5f),
    BIG_SCRAPPED_SWORD = registerSword("big_scrapped_sword", SoulsTier.SCRAPPED, 10, -3.5f),
    BIG_TITANITOL_SWORD = registerSword("big_titanitol_sword", SoulsTier.BLAZE, 8, -3.5f),

    RAPIER = registerSword("rapier", Tiers.IRON, 8, -3.5f),
    CUTLASS = registerSword("cutlass", Tiers.IRON, 8, -3.5f),
    GOLDEN_RAPIER = registerSword("golden_rapier", Tiers.IRON, 8, -3.5f),
    DIAMOND_RAPIER = registerSword("diamond_rapier", Tiers.IRON, 8, -3.5f),
    DIAMOND_CUTLASS = registerSword("diamond_cutlass", Tiers.IRON, 8, -3.5f),
    NETHERITE_RAPIER = registerSword("netherite_rapier", Tiers.IRON, 8, -3.5f),
    NETHERITE_CUTLASS = registerSword("netherite_cutlass", Tiers.IRON, 8, -3.5f),

    CLAYMORE = registerSword("claymore", Tiers.IRON, 10, -3.5f),

    WOODEN_DAGGER = registerSword("wooden_dagger", SoulsTier.WOOD_SMALL, 1, -2.2f),
    STONE_DAGGER = registerSword("stone_dagger", SoulsTier.STONE_SMALL, 1, -2.2f),
    IRON_DAGGER = registerSword("iron_dagger", SoulsTier.IRON_SMALL, 1, -2.2f),
    GOLDEN_DAGGER = registerSword("golden_dagger", SoulsTier.GOLD_SMALL, 1, -2.2f),
    DIAMOND_DAGGER = registerSword("diamond_dagger", SoulsTier.DIAMOND_SMALL, 1, -2.2f),
    NETHERITE_DAGGER = registerSword("netherite_dagger", SoulsTier.NETHERITE_SMALL, 1, -2.2f),
    TITANITOL_DAGGER = registerSword("titanitol_dagger", SoulsTier.IRON_SMALL, 1, -2.2f),
    SCRAPPED_DAGGER = registerSword("scrapped_dagger", SoulsTier.NETHERITE_SMALL, 1, -2.2f),
    BLAZE_DAGGER = registerSword("blaze_dagger", SoulsTier.IRON_SMALL, 1, -2.2f)
            ;


    private static  <T extends SwordItem> RegistryObject<T> registerSword(String name, Tier tier, int dmg, float speed) {
        return (RegistryObject<T>) registerItem(name, () -> new SwordItem(tier, dmg, speed, new Item.Properties()), SoulsCreativeTabs.WEAPON_TAB);
    }
    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item, RegistryObject<CreativeModeTab> tab) {
        RegistryObject<T> registryObject = ITEMS.register(name, item);
        creativeTabs.put((RegistryObject<Item>) registryObject, tab);
        return registryObject;
    }

    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
        return registerItem(name, item, null);
    }

    public static ArrayList<RegistryObject<Item>> ENTITY_SOULS = new ArrayList<>();

    public static void createEntitySouls() {
        for (Map.Entry<String, Integer> stringIntegerEntry : Data.SOULS_MAP.entrySet()) {
            RegistryObject<Item> placeHolder = ITEMS.register(stringIntegerEntry.getKey() + "_soul", () -> new SoulItem(new Item.Properties(), stringIntegerEntry.getValue(), "item.soulslike.soul_of_a_giant.description"));
            ENTITY_SOULS.add(placeHolder);
        }
    }

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        for (Map.Entry<RegistryObject<Item>, RegistryObject<CreativeModeTab>> entry : creativeTabs.entrySet()) {
            if(entry.getValue() != null) {
                if (entry.getValue().getKey() == event.getTabKey()) {
                    event.accept(entry.getKey());
                }
            }
        }
        if(event.getTab() == SoulsCreativeTabs.BUILDING_BLOCK_TAB.get()) {
            event.accept(SoulsBlocks.SOUL_CASING.get());
            event.accept(SoulsBlocks.SOUL_DRAIN.get());
            event.accept(SoulsBlocks.SOUL_ENTITY_SPAWNER.get());
            event.accept(SoulsBlocks.SOUL_DISPENSER.get());
        }
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        //createEntitySouls();
    }
}
