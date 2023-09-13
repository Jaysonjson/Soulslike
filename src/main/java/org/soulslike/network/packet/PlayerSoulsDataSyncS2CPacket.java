package org.soulslike.network.packet;


import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.soulslike.common.capabilities.PlayerSouls;
import org.soulslike.client.data.ClientSoulsData;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PlayerSoulsDataSyncS2CPacket {
    private final int souls;
    private ArrayList<BlockPos> beeStatues = new ArrayList<>();
    private int beeStatuesSize;
    private final int newPlayerSouls;

    private final int newPlayerSoulsTick;

    public PlayerSoulsDataSyncS2CPacket(PlayerSouls playerSouls) {
        this.souls = playerSouls.getSouls();
        this.newPlayerSouls = playerSouls.newSouls_;
        this.newPlayerSoulsTick = playerSouls.newSoulsTick;
        this.beeStatues = playerSouls.getBeeStatues();
        this.beeStatuesSize = playerSouls.getBeeStatues().size();
    }

    public PlayerSoulsDataSyncS2CPacket(int souls, int newPlayerSouls, int newPlayerSoulsTick, ArrayList<BlockPos> beeStatues) {
        this.souls = souls;
        this.newPlayerSouls = newPlayerSouls;
        this.newPlayerSoulsTick = newPlayerSoulsTick;
        this.beeStatues = beeStatues;
        this.beeStatuesSize = beeStatues.size();
    }

    public PlayerSoulsDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.souls = buf.readInt();
        this.newPlayerSouls = buf.readInt();
        this.newPlayerSoulsTick = buf.readInt();
        this.beeStatuesSize = buf.readInt();
        for (int i = 0; i < beeStatuesSize; i++) {
            this.beeStatues.add(buf.readBlockPos());
        }
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(souls);
        buf.writeInt(newPlayerSouls);
        buf.writeInt(newPlayerSoulsTick);
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
