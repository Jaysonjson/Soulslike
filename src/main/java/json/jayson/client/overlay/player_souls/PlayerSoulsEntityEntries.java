package json.jayson.client.overlay.player_souls;

import json.jayson.client.overlay.EntityTextOverlay;

public class PlayerSoulsEntityEntries {
    public static class Souls extends EntityTextOverlay.Entry {
        public String text = "";
        @Override
        public void handle() {
            guiGraphics.drawCenteredString(minecraft.font, text, screenWidth / 2, (int)((float)screenHeight / 1.5f), 0xFFFFFFFF);
        }
    }

    public static class Player extends EntityTextOverlay.Entry {
        public String text = "";
        @Override
        public void handle() {
            guiGraphics.drawCenteredString(minecraft.font, text, screenWidth / 2, (int)((float)screenHeight / 1.575f), 0xFFFFFFFF);
        }
    }
}
