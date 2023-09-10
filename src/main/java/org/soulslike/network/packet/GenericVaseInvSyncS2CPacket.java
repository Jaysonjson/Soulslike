package org.soulslike.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import org.soulslike.common.objects.blocks.vase.GenericVaseEntity;

import java.util.function.Supplier;

public class GenericVaseInvSyncS2CPacket {
    private final BlockPos pos;
    private final ItemStack itemStack;

    private final boolean clear;

    public GenericVaseInvSyncS2CPacket(ItemStack itemStackHandler, BlockPos pos, boolean clear) {
        this.itemStack = itemStackHandler;
        this.pos = pos;
        this.clear = clear;
    }

    public GenericVaseInvSyncS2CPacket(ItemStack itemStackHandler, BlockPos pos) {
        this.itemStack = itemStackHandler;
        this.pos = pos;
        clear = false;
    }

    public GenericVaseInvSyncS2CPacket(FriendlyByteBuf buf) {
        this.itemStack = buf.readItem();
        this.pos = buf.readBlockPos();
        this.clear = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(itemStack);
        buf.writeBlockPos(pos);
        buf.writeBoolean(clear);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof GenericVaseEntity blockEntity) {
                boolean has = false;
                for (ItemStack renderItem : blockEntity.renderItems) {
                    if(ItemStack.isSameItem(renderItem, itemStack)) {
                        has = true;
                    }
                }
                if(!has && !itemStack.is(Items.AIR)) blockEntity.renderItems.add(itemStack);
                if(clear) {
                    blockEntity.renderItems.clear();
                }
            }
        });
        return true;
    }
}