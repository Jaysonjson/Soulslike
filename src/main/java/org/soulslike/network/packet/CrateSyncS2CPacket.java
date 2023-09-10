package org.soulslike.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import org.soulslike.common.objects.blocks.crate.GenericCrateEntity;

import java.util.function.Supplier;

public class CrateSyncS2CPacket {
    private final BlockPos pos;
    private final ItemStack itemStack;

    private final int slot;

    public CrateSyncS2CPacket(ItemStack itemStackHandler, BlockPos pos, int clear) {
        this.itemStack = itemStackHandler;
        this.pos = pos;
        this.slot = clear;
    }

    public CrateSyncS2CPacket(FriendlyByteBuf buf) {
        this.itemStack = buf.readItem();
        this.pos = buf.readBlockPos();
        this.slot = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(itemStack);
        buf.writeBlockPos(pos);
        buf.writeInt(slot);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof GenericCrateEntity blockEntity) {
                blockEntity.setItem(slot, itemStack);
            }
        });
        return true;
    }
}
