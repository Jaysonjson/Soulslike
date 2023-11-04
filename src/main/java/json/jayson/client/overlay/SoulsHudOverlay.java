package json.jayson.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import json.jayson.Soulslike;
import json.jayson.client.data.ClientSoulsData;
import json.jayson.common.registries.SoulsItems;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class SoulsHudOverlay {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(Soulslike.MODID, "textures/hud/souls/background.png");
    //private static final ResourceLocation SOULS = new ResourceLocation(Soulslike.MODID, "textures/hud/souls/souls.png");
    private static final ResourceLocation SOULS = new ResourceLocation(Soulslike.MODID, "textures/item/status/mortis.png");

    public static final IGuiOverlay HUD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth;
        int y = screenHeight;
        long souls = ClientSoulsData.getSouls();
        Minecraft minecraft = Minecraft.getInstance();
        int textWidth = minecraft.font.width(String.valueOf(souls));
        int textWidth2 = minecraft.font.width("+" + ClientSoulsData.getNewPlayerSouls());
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.75f);
        guiGraphics.blit(BACKGROUND, x - 80, y - 20, 0, 0, 74, 16, 74, 16);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.75f);
        guiGraphics.blit(SOULS, x - 77, y - 18, 0, 0, 12, 12, 12, 12);
        guiGraphics.drawString(minecraft.font, String.valueOf(souls), x - 9 - textWidth, y - 16, 0xFFFFFFFF);
        if(ClientSoulsData.getNewPlayerSouls() > 0) {
            guiGraphics.drawString(minecraft.font, "+" + ClientSoulsData.getNewPlayerSouls(), x - 9 - textWidth2, y - 29, 0xFFFFFFFF);
        }
    });

}
