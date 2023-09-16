package json.jayson.network.packet;


import json.jayson.client.data.ClientSoulsData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import json.jayson.common.capabilities.PlayerSouls;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PlayerSoulsDataSyncS2CPacket {
    private final long souls;
    private ArrayList<BlockPos> beeStatues = new ArrayList<>();
    private int beeStatuesSize;
    private final long newPlayerSouls;

    private final long newPlayerSoulsTick;

    public PlayerSoulsDataSyncS2CPacket(PlayerSouls playerSouls) {
        this.souls = playerSouls.getSouls();
        this.newPlayerSouls = playerSouls.newSouls_;
        this.newPlayerSoulsTick = playerSouls.newSoulsTick;
        this.beeStatues = playerSouls.getBeeStatues();
        this.beeStatuesSize = playerSouls.getBeeStatues().size();
    }

    public PlayerSoulsDataSyncS2CPacket(long souls, long newPlayerSouls, long newPlayerSoulsTick, ArrayList<BlockPos> beeStatues) {
        this.souls = souls;
        this.newPlayerSouls = newPlayerSouls;
        this.newPlayerSoulsTick = newPlayerSoulsTick;
        this.beeStatues = beeStatues;
        this.beeStatuesSize = beeStatues.size();
    }

    public PlayerSoulsDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.souls = buf.readLong();
        this.newPlayerSouls = buf.readLong();
        this.newPlayerSoulsTick = buf.readLong();
        this.beeStatuesSize = buf.readInt();
        for (int i = 0; i < beeStatuesSize; i++) {
            this.beeStatues.add(buf.readBlockPos());
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeLong(souls);
        buf.writeLong(newPlayerSouls);
        buf.writeLong(newPlayerSoulsTick);
        buf.writeInt(beeStatuesSize);
        for (BlockPos beeStatue : beeStatues) {
            buf.writeBlockPos(beeStatue);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> ClientSoulsData.set(souls, newPlayerSouls, newPlayerSoulsTick));
        return true;
    }
}
