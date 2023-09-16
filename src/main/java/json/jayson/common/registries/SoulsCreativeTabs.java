package json.jayson.common.registries;

import json.jayson.Soulslike;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Soulslike.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class SoulsCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Soulslike.MODID);

    /*@Deprecated
    public static SoulsCreativeTabProvider COAL_TAB = new SoulsCreativeTabProvider("creativemodetab.coal_tab");
    @Deprecated
    public static SoulsCreativeTabProvider SOUL_TAB = new SoulsCreativeTabProvider("creativemodetab.soul_tab");*/
    public static RegistryObject<CreativeModeTab> OTHER_TAB = TABS.register("other_tab", () -> CreativeModeTab.builder()
            .icon(() -> SoulsItems.DWARF_COAL.get().getDefaultInstance()).title(Component.translatable("creativemodetab.other_tab")).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());

    public static RegistryObject<CreativeModeTab> WEAPON_TAB = TABS.register("weapon_tab", () -> CreativeModeTab.builder()
            .icon(() -> SoulsItems.BIG_IRON_SWORD.get().getDefaultInstance()).title(Component.translatable("creativemodetab.weapon_tab")).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());

    /*public static RegistryObject<CreativeModeTab> BLOCK_TAB = TABS.register("block_tab", () -> CreativeModeTab.builder()
            .icon(() -> SoulsBlocks.BEE_STATUE.getItem().getDefaultInstance()).title(Component.translatable("creativemodetab.block_tab")).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());*/

    public static RegistryObject<CreativeModeTab> BUILDING_BLOCK_TAB = TABS.register("building_block_tab", () -> CreativeModeTab.builder()
            .icon(() -> SoulsBlocks.MUD_VASE.getItem().getDefaultInstance()).title(Component.translatable("creativemodetab.block_tab")).withTabsBefore(CreativeModeTabs.SPAWN_EGGS).build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
