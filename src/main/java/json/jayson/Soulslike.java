package json.jayson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import json.jayson.common.objects.items.RandomItemColor;
import json.jayson.common.registries.*;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import json.jayson.client.ClientEvents;
import json.jayson.common.ModEvents;
import json.jayson.common.listener.json.SoulsMapReloadListener;
import json.jayson.common.objects.fluids.SoulsFluidTypes;
import json.jayson.common.registries.*;
import json.jayson.network.packet.ModMessages;

@Mod(Soulslike.MODID)
public class Soulslike {
    public static final String MODID = "soulslike";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public Soulslike() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(SoulsItems::addCreative);
        modEventBus.addListener(this::registerItemColors);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModEvents());
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new ClientEvents.ClientModBusEvents());

        SoulsItems.register(modEventBus);
        SoulsBlocks.register(modEventBus);
        SoulsBlockEntities.register(modEventBus);
        SoulsFluids.register(modEventBus);
        SoulsFluidTypes.register(modEventBus);
        SoulsCreativeTabs.register(modEventBus);
        SoulsLootCodecs.register(modEventBus);
        SoulsEntities.register(modEventBus);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void registerItemColors(RegisterColorHandlersEvent.Item event) {
        System.out.println("REGISTER ITEM COLORS");
        event.register(new RandomItemColor(), SoulsItems.THIGH_HIGHS.get());
    }

    @SubscribeEvent
    public void addReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new SoulsMapReloadListener(GSON, "souls"));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
        SoulsGameRules.RULE_KEEPSOULS = GameRules.register("keepSouls", GameRules.Category.PLAYER, GameRules.BooleanValue.create(false));
    }

}