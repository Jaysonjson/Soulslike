package org.soulslike.common.objects.blocks;

import net.minecraft.core.BlockPos;
import org.soulslike.client.overlay.EntityTextOverlay;
import org.soulslike.client.overlay.block_overlay.BlockTextOverlay;

public interface IBlockTextOverlay {

    void alterBlockOverlayText(BlockPos blockPos);

    default void addBlockOverlayEntry(BlockTextOverlay.Entry entry) {
        BlockTextOverlay.TEXTS.add(entry);
    }

}
