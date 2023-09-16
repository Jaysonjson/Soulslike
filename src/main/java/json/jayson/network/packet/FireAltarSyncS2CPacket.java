package json.jayson.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import json.jayson.common.objects.blocks.fire_altar.FireAltarEntity;

import java.util.function.Supplier;

public class FireAltarSyncS2CPacket {
    private final BlockPos pos;

    private final boolean soulFlame;

    public FireAltarSyncS2CPacket(BlockPos pos, boolean soulFlame) {
        this.pos = pos;
        this.soulFlame = soulFlame;
    }

    public FireAltarSyncS2CPacket(BlockPos pos) {
        this.pos = pos;
        soulFlame = false;
    }

    public FireAltarSyncS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.soulFlame = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeBoolean(soulFlame);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof FireAltarEntity blockEntity) {
                blockEntity.soulFlame = soulFlame;
            }
        });
        return true;
    }
}
