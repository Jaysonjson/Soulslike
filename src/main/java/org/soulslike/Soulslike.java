package org.soulslike;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.soulslike.common.objects.fluids.SoulsFluidTypes;
import org.soulslike.common.registries.*;

@Mod(Soulslike.MODID)
public class Soulslike {
    public static final String MODID = "soulslike";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static Souls SOULS = new Souls();
    public Soulslike() {
        SOULS = SoulsHandler.loadObject(SoulsHandler.loadJson());
        SOULS = SoulsHandler.loadObject(SoulsHandler.loadJson());
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(SoulsItems::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModEvents());
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new ClientEvents.ClientModBusEvents());
        MinecraftForge.EVENT_BUS.register(new SoulsCreativeTabs());

        SoulsItems.register(modEventBus);
        SoulsBlocks.register(modEventBus);
        SoulsBlockEntities.register(modEventBus);
        SoulsFluids.register(modEventBus);
        SoulsFluidTypes.register(modEventBus);
        SoulsCreativeTabs.register(modEventBus);
        SoulsLootCodecs.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

}
