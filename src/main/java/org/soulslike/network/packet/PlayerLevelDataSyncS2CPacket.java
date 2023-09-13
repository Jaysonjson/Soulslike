package org.soulslike.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.apache.commons.lang3.SerializationUtils;
import org.soulslike.common.capabilities.PlayerLevel;
import org.soulslike.client.data.ClientPlayerData;

import java.util.HashMap;
import java.util.function.Supplier;

public class PlayerLevelDataSyncS2CPacket {
    private final HashMap<String, Integer> attributes;
    private final int level;

    public PlayerLevelDataSyncS2CPacket(PlayerLevel playerLevel) {
        this.attributes = playerLevel.getAttributes();
        this.level = playerLevel.getLevel();
    }

    public PlayerLevelDataSyncS2CPacket(HashMap<String, Integer> attributes, int level) {
        this.attributes = attributes;
        this.level = level;
    }

    public PlayerLevelDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.attributes = SerializationUtils.deserialize(buf.readByteArray());
        this.level = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeByteArray(SerializationUtils.serialize(attributes));
        buf.writeInt(level);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> ClientPlayerData.set(attributes, level));
        return true;
    }
}
