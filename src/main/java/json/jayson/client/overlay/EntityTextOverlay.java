package json.jayson.client.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.ArrayList;
import java.util.List;

public class EntityTextOverlay {

    public static List<Entry> TEXTS = new ArrayList<>();
    public static final IGuiOverlay HUD = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        /*if(!TEXT.isEmpty()) {
            Minecraft minecraft = Minecraft.getInstance();
            guiGraphics.drawCenteredString(minecraft.font, TEXT, screenWidth / 2, (int)((float)screenHeight / 1.5f), 0xFFFFFFFF);
        }*/
        for (Entry entry : TEXTS) {
            Minecraft minecraft = Minecraft.getInstance();
            entry.screenWidth = screenWidth;
            entry.screenHeight = screenHeight;
            entry.gui = gui;
            entry.guiGraphics = guiGraphics;
            entry.partialTick = partialTick;
            entry.minecraft = minecraft;
            entry.handle();
            //guiGraphics.drawCenteredString(minecraft.font, text.text, screenWidth / 2, (int)((float)screenHeight / 1.5f), text.color);
        }
    });

    public static class Entry {
        protected int screenWidth = 0;
        protected int screenHeight = 0;
        protected ForgeGui gui = null;
        protected GuiGraphics guiGraphics = null;
        protected float partialTick = 0;
        protected Minecraft minecraft = null;

        public void handle() {

        }
    }

}
