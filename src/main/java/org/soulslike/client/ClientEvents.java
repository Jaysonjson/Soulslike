package org.soulslike.client;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.soulslike.client.overlay.DeathTextOverlay;
import org.soulslike.client.overlay.PlayerSoulsEntityOverlay;
import org.soulslike.client.overlay.SoulsHudOverlay;
import org.soulslike.client.renderer.PlayerSoulsEntityRenderer;
import org.soulslike.common.objects.blocks.cake_plate.CakePlateEntityRenderer;
import org.soulslike.common.objects.blocks.fire_altar.FireAltarEntityRenderer;
import org.soulslike.common.objects.blocks.vase.GenericVaseEntityRenderer;
import org.soulslike.common.objects.entities.PlayerSoulsEntity;
import org.soulslike.common.registries.SoulsBlockEntities;
import org.soulslike.common.registries.SoulsEntities;
import org.soulslike.common.registries.SoulsFluids;

import static org.soulslike.Soulslike.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("souls", SoulsHudOverlay.HUD_SOULS);
            event.registerAboveAll("death_text", DeathTextOverlay.HUD_DEATH_TEXT);
            event.registerAboveAll("player_souls_entity_overlay", PlayerSoulsEntityOverlay.HUD);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(SoulsFluids.SOURCE_BLOOD.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(SoulsFluids.FLOWING_BLOOD.get(), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer(SoulsBlocks.RUBY_ROSE.getBlock(), RenderType.translucent());
            //ItemBlockRenderTypes.setRenderLayer(SoulsBlocks.MANGROVE_CRAFTING_TABLE.getBlock(), RenderType.translucent());
        }
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(SoulsBlockEntities.GENERIC_VASE.get(), GenericVaseEntityRenderer::new);
            event.registerBlockEntityRenderer(SoulsBlockEntities.CAKE_PLATE.get(), CakePlateEntityRenderer::new);
            event.registerBlockEntityRenderer(SoulsBlockEntities.FIRE_ALTAR.get(), FireAltarEntityRenderer::new);
            event.registerEntityRenderer(SoulsEntities.PLAYER_SOULS.get(), PlayerSoulsEntityRenderer::new);
        }
    }

    @SubscribeEvent
    public void mouseMove(MovementInputUpdateEvent event) {
        EntityTrace entityTrace = new EntityTrace();
        Entity entity = entityTrace.getEntityInCrosshair(Minecraft.getInstance().getPartialTick(), 50.0);
        if(entity instanceof PlayerSoulsEntity playerSoulsEntity) {
            PlayerSoulsEntityOverlay.SOULS = playerSoulsEntity.getEntityData().get(PlayerSoulsEntity.SOULS);
        } else {
            PlayerSoulsEntityOverlay.SOULS = 0;
        }

    }


}
