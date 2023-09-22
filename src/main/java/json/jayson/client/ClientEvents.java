package json.jayson.client;


import json.jayson.Soulslike;
import json.jayson.client.overlay.block_overlay.BlockTextOverlay;
import json.jayson.client.renderer.FireFlyEntityRenderer;
import json.jayson.common.objects.entities.FireFlyEntity;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.integration.create.PonderIndex;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import json.jayson.client.overlay.EntityTextOverlay;
import json.jayson.client.overlay.SoulsHudOverlay;
import json.jayson.client.renderer.PlayerSoulsEntityRenderer;
import json.jayson.common.objects.blocks.IBlockTextOverlay;
import json.jayson.common.objects.blocks.cake_plate.CakePlateEntityRenderer;
import json.jayson.common.objects.blocks.fire_altar.FireAltarEntityRenderer;
import json.jayson.common.objects.blocks.vase.GenericVaseEntityRenderer;
import json.jayson.common.objects.entities.IEntityTextOverlay;
import json.jayson.common.registries.SoulsBlockEntities;
import json.jayson.common.registries.SoulsEntities;
import json.jayson.common.registries.SoulsFluids;

@Mod.EventBusSubscriber(modid = Soulslike.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {

    @Mod.EventBusSubscriber(modid = Soulslike.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("souls", SoulsHudOverlay.HUD);
            //event.registerAboveAll("player_souls_entity_overlay", PlayerSoulsEntityOverlay.HUD);
            event.registerAboveAll("entity_text", EntityTextOverlay.HUD);
            event.registerAboveAll("block_text", BlockTextOverlay.HUD);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(SoulsFluids.SOURCE_BLOOD.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(SoulsFluids.FLOWING_BLOOD.get(), RenderType.translucent());
            PonderIndex.add();
            //ItemBlockRenderTypes.setRenderLayer(SoulsBlocks.MANGROVE_CRAFTING_TABLE.getBlock(), RenderType.translucent());
        }
        @SubscribeEvent
        public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(SoulsBlockEntities.GENERIC_VASE.get(), GenericVaseEntityRenderer::new);
            event.registerBlockEntityRenderer(SoulsBlockEntities.CAKE_PLATE.get(), CakePlateEntityRenderer::new);
            event.registerBlockEntityRenderer(SoulsBlockEntities.FIRE_ALTAR.get(), FireAltarEntityRenderer::new);
            event.registerEntityRenderer(SoulsEntities.PLAYER_SOULS.get(), PlayerSoulsEntityRenderer::new);
            event.registerEntityRenderer(SoulsEntities.FIRE_FLY.get(), FireFlyEntityRenderer::new);
        }
    }

    @SubscribeEvent
    public static void entityAttributCreation(EntityAttributeCreationEvent event) {
        System.out.println("ENTITY ATTRIBUTE");
        event.put(SoulsEntities.FIRE_FLY.get(), FireFlyEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void mouseMove(MovementInputUpdateEvent event) {
        EntityTrace entityTrace = new EntityTrace();
        Entity entity = entityTrace.getEntityInCrosshair(Minecraft.getInstance().getPartialTick(), 7.0f);
        BlockHitResult blockHitResult = entityTrace.getBlockInCrosshair(Minecraft.getInstance().getPartialTick(), 7.0f, ClipContext.Block.COLLIDER);
        if(blockHitResult != null) {
            Block block = Minecraft.getInstance().level.getBlockState(blockHitResult.getBlockPos()).getBlock();
            if(block instanceof IBlockTextOverlay textOverlay) {
                textOverlay.alterBlockOverlayText(blockHitResult.getBlockPos());
            } else {
                BlockTextOverlay.TEXTS.clear();
            }
        } else {
            BlockTextOverlay.TEXTS.clear();
        }

        /*if(entity instanceof PlayerSoulsEntity playerSoulsEntity) {
            PlayerSoulsEntityOverlay.SOULS = playerSoulsEntity.getEntityData().get(PlayerSoulsEntity.SOULS);
        } else {
            PlayerSoulsEntityOverlay.SOULS = 0;
        }*/

        /*if(entity instanceof IRayTracedEntity rayTracedEntity) {
            rayTracedEntity.onMouseRayTraceHit();
        } else {
            PlayerSoulsEntityOverlay.SOULS = 0;
        }*/

        if(entity instanceof IEntityTextOverlay entityTextOverlay) {
            entityTextOverlay.alterEntityOverlayText();
        } else {
            EntityTextOverlay.TEXTS.clear();
        }

    }


}
