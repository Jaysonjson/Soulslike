package json.jayson.common.objects.blocks.bee_statue;

import json.jayson.client.overlay.block_overlay.BlockTextOverlay;

public class TestEntry {

    public static class Player extends BlockTextOverlay.Entry {
        @Override
        public void handle() {
            guiGraphics.drawCenteredString(minecraft.font, "Test", screenWidth / 2, (int)((float)screenHeight / 1.575f), 0xFFFFFFFF);
        }
    }

}
