package org.soulslike.common.registries;

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
import org.soulslike.Soulslike;
import org.soulslike.common.objects.items.BeeTears;
import org.soulslike.common.objects.items.ItemWithDescription;
import org.soulslike.common.objects.items.SoulItem;
import org.soulslike.common.objects.items.staff.CloudStaff;
import org.soulslike.common.objects.items.staff.FeatherStaff;
import org.soulslike.common.objects.items.staff.RoseStaff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SoulsItems {

    public static HashMap< RegistryObject<Item>, RegistryObject<CreativeModeTab>> creativeTabs = new HashMap<>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Soulslike.MODID);

    public static final RegistryObject<Item> DWARF_COAL = registerItem("dwarf_coal", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB);
    public static final RegistryObject<Item> BEE_TEARS = registerItem("bee_tears", () -> new BeeTears(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB);
    public static final RegistryObject<Item> UNKNOWN_SOUL = registerItem("unknown_soul", () -> new SoulItem(new Item.Properties(), 50, "item.soulslike.unknown_soul.description"), SoulsCreativeTabs.OTHER_TAB);
    public static final RegistryObject<Item> SOUL_OF_A_GIANT = registerItem("soul_of_a_giant", () -> new SoulItem(new Item.Properties(), 1000, "item.soulslike.soul_of_a_giant.description"), SoulsCreativeTabs.OTHER_TAB);
    public static final RegistryObject<Item> TITANITOL_INGOT = registerItem("titanitol_ingot",
            () -> new ItemWithDescription(new Item.Properties(), Component.translatable("item.soulslike.titanitol_ingot.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC)), SoulsCreativeTabs.OTHER_TAB);
    public static final RegistryObject<Item> HARDENED_BLAZE = registerItem("hardened_blaze", () -> new ItemWithDescription(new Item.Properties(), Component.translatable("item.soulslike.hardened_blaze.description").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC)), SoulsCreativeTabs.OTHER_TAB);

    public static final RegistryObject<SwordItem> BIG_IRON_SWORD = registerSword("big_iron_sword", Tiers.IRON, 8, -3.5f);
    public static final RegistryObject<SwordItem> BIG_GOLD_SWORD = registerSword("big_gold_sword", Tiers.GOLD, 7, -3.5f);
    public static final RegistryObject<SwordItem> BIG_DIAMOND_SWORD = registerSword("big_diamond_sword", Tiers.DIAMOND, 8, -3.5f);
    public static final RegistryObject<SwordItem> BIG_STONE_SWORD = registerSword("big_stone_sword", Tiers.STONE, 8, -3.5f);
    public static final RegistryObject<SwordItem> BIG_WOODEN_SWORD = registerSword("big_wooden_sword", Tiers.WOOD, 6, -3.5f);
    public static final RegistryObject<SwordItem> BIG_BLAZE_SWORD = registerSword("big_blaze_sword", SoulsTier.BLAZE, 7, -3.5f);
    public static final RegistryObject<SwordItem> BIG_NETHERITE_SWORD = registerSword("big_netherite_sword", Tiers.NETHERITE, 9, -3.5f);
    public static final RegistryObject<SwordItem> BIG_SCRAPPED_SWORD = registerSword("big_scrapped_sword", SoulsTier.SCRAPPED, 10, -3.5f);
    public static final RegistryObject<SwordItem> BIG_TITANITOL_SWORD = registerSword("big_titanitol_sword", SoulsTier.BLAZE, 8, -3.5f);

    public static final RegistryObject<SwordItem> CLAYMORE = registerSword("claymore", Tiers.IRON, 10, -3.5f);

    public static final RegistryObject<SwordItem> WOODEN_DAGGER = registerSword("wooden_dagger", SoulsTier.WOOD_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> STONE_DAGGER = registerSword("stone_dagger", SoulsTier.STONE_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> IRON_DAGGER = registerSword("iron_dagger", SoulsTier.IRON_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> GOLDEN_DAGGER = registerSword("golden_dagger", SoulsTier.GOLD_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> DIAMOND_DAGGER = registerSword("diamond_dagger", SoulsTier.DIAMOND_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> NETHERITE_DAGGER = registerSword("netherite_dagger", SoulsTier.NETHERITE_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> TITANITOL_DAGGER = registerSword("titanitol_dagger", SoulsTier.IRON_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> SCRAPPED_DAGGER = registerSword("scrapped_dagger", SoulsTier.NETHERITE_SMALL, 1, -2.2f);
    public static final RegistryObject<SwordItem> BLAZE_DAGGER = registerSword("blaze_dagger", SoulsTier.IRON_SMALL, 1, -2.2f);


    public static final RegistryObject<Item> CLOUD_STAFF = registerItem("cloud_staff", () -> new CloudStaff(new Item.Properties().defaultDurability(450)), SoulsCreativeTabs.WEAPON_TAB);
    public static final RegistryObject<Item> CLOUD_WAND = registerItem("cloud_wand", () -> new CloudStaff(new Item.Properties().defaultDurability(325), 3, new Vec3(0.9, 0.9, 0.9)), SoulsCreativeTabs.WEAPON_TAB);

    public static final RegistryObject<Item> ROSE_WAND = registerItem("rose_wand", () -> new RoseStaff(new Item.Properties().defaultDurability(325), 2), SoulsCreativeTabs.WEAPON_TAB);
    public static final RegistryObject<Item> ROSE_STAFF = registerItem("rose_staff", () -> new RoseStaff(new Item.Properties().defaultDurability(450), 4), SoulsCreativeTabs.WEAPON_TAB);
    //public static final RegistryObject<Item> RUBY = registerItem("ruby", () -> new Item(new Item.Properties()), SoulsCreativeTabs.OTHER_TAB);

    public static final RegistryObject<Item> FEATHER_STAFF = registerItem("feather_staff", () -> new FeatherStaff(new Item.Properties().defaultDurability(450), 120), SoulsCreativeTabs.WEAPON_TAB);
    public static final RegistryObject<Item> FEATHER_WAND = registerItem("feather_wand", () -> new FeatherStaff(new Item.Properties().defaultDurability(325), 200), SoulsCreativeTabs.WEAPON_TAB);

    public static final RegistryObject<Item> BLOOD_BUCKET = registerItem("blood_bucket", () -> new BucketItem(SoulsFluids.SOURCE_BLOOD, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)), SoulsCreativeTabs.OTHER_TAB);


    private static  <T extends SwordItem> RegistryObject<T> registerSword(String name, Tier tier, int dmg, float speed) {
        return (RegistryObject<T>) registerItem(name, () -> new SwordItem(tier, dmg, speed, new Item.Properties()), SoulsCreativeTabs.WEAPON_TAB);
    }
    private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item, RegistryObject<CreativeModeTab> tab) {
        RegistryObject<T> registryObject = ITEMS.register(name, item);
        creativeTabs.put((RegistryObject<Item>) registryObject, tab);
        return registryObject;
    }

    public static ArrayList<RegistryObject<Item>> ENTITY_SOULS = new ArrayList<>();

    public static void createEntitySouls() {
        for (Map.Entry<String, Integer> stringIntegerEntry : Soulslike.SOULS.soulsMap.entrySet()) {
            RegistryObject<Item> placeHolder = ITEMS.register(stringIntegerEntry.getKey() + "_soul", () -> new SoulItem(new Item.Properties(), stringIntegerEntry.getValue(), "item.soulslike.soul_of_a_giant.description"));
            ENTITY_SOULS.add(placeHolder);
        }
    }

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        for (Map.Entry<RegistryObject<Item>, RegistryObject<CreativeModeTab>> entry : creativeTabs.entrySet()) {
            if(entry.getValue().getKey()  == event.getTabKey()) {
                event.accept(entry.getKey());
            }
        }
    }
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        //createEntitySouls();
    }
}