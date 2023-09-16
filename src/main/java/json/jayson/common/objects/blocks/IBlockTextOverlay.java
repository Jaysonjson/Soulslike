package json.jayson.common.objects.blocks;

import json.jayson.client.overlay.block_overlay.BlockTextOverlay;
import net.minecraft.core.BlockPos;

public interface IBlockTextOverlay {

    void alterBlockOverlayText(BlockPos blockPos);

    default void addBlockOverlayEntry(BlockTextOverlay.Entry entry) {
        BlockTextOverlay.TEXTS.add(entry);
    }

}
