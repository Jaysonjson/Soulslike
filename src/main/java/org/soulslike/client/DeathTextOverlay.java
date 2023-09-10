package org.soulslike.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import org.soulslike.Soulslike;

public class DeathTextOverlay {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(Soulslike.MODID, "textures/hud/death/background.png");

    public static final IGuiOverlay HUD_DEATH_TEXT = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        boolean show = false;
        if(show) {
            int x = screenWidth;
            int y = screenHeight;
            Minecraft minecraft = Minecraft.getInstance();

       /* RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 0.75f);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        GuiComponent.blit(poseStack, x / 2, y / 2, 0, 0, 94, 16, 94, 16);*/
            guiGraphics.drawCenteredString(minecraft.font, "YOU DIED", x / 2, y / 2, 0x660000FF);
        }
    });
}
