package json.jayson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.simibubi.create.content.fluids.drain.ItemDrainBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import json.jayson.common.objects.items.RandomItemColor;
import json.jayson.common.registries.*;
import json.jayson.integration.create.PonderIndex;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.BarrierBlock;
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
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import json.jayson.client.ClientEvents;
import json.jayson.common.ModEvents;
import json.jayson.common.listener.json.SoulsMapReloadListener;
import json.jayson.common.objects.fluids.SoulsFluidTypes;
import json.jayson.common.registries.*;
import json.jayson.network.packet.ModMessages;

import static com.simibubi.create.Create.REGISTRATE;

@Mod(Soulslike.MODID)
public class Soulslike {
    public static final String MODID = "soulslike";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final CreateRegistrate SOULS_REGISTRATE = CreateRegistrate.create(MODID).setCreativeTab(SoulsCreativeTabs.BUILDING_BLOCK_TAB);
    public Soulslike() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        SOULS_REGISTRATE.setCreativeTab(SoulsCreativeTabs.BUILDING_BLOCK_TAB);
        SOULS_REGISTRATE.registerEventListeners(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(SoulsItems::addCreative);
        //modEventBus.addListener(this::registerItemColors);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModEvents());
        if (FMLEnvironment.dist == Dist.CLIENT) {
            MinecraftForge.EVENT_BUS.register(new ClientEvents());
            MinecraftForge.EVENT_BUS.register(new ClientEvents.ClientModBusEvents());
            modEventBus.addListener(ClientEvents::entityAttributCreation);
            modEventBus.addListener(this::registerItemColors);
        }
        SoulsItems.register(modEventBus);
        SoulsBlocks.register(modEventBus);
        SoulsBlockEntities.register(modEventBus);
        SoulsFluids.register(modEventBus);
        SoulsFluidTypes.register(modEventBus);
        SoulsCreativeTabs.register(modEventBus);
        SoulsLootCodecs.register(modEventBus);
        SoulsEntities.register(modEventBus);
        SoulsFeatures.registerBus(modEventBus);
        SoulsVillagers.registerBus(modEventBus);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void registerItemColors(RegisterColorHandlersEvent.Item event) {
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
