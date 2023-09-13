package org.soulslike.client.overlay;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class EntityTextOverlay {

    public static String TEXT = "";
    public static final IGuiOverlay HUD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        if(!TEXT.isEmpty()) {
            Minecraft minecraft = Minecraft.getInstance();
            guiGraphics.drawCenteredString(minecraft.font, TEXT, screenWidth / 2, (int)((float)screenHeight / 1.5f), 0xFFFFFFFF);
        }
    });

}
