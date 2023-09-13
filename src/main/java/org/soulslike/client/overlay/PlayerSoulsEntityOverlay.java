package org.soulslike.client.overlay;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

@OnlyIn(Dist.CLIENT)
public class PlayerSoulsEntityOverlay {

    public static int SOULS = 0;
    public static final IGuiOverlay HUD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if(SOULS != 0) {
            Minecraft minecraft = Minecraft.getInstance();
            guiGraphics.drawCenteredString(minecraft.font, "Contained Souls: " + SOULS, screenWidth / 2, (int)((float)screenHeight / 1.5f), 0xFFFFFFFF);
        }
    });

}
