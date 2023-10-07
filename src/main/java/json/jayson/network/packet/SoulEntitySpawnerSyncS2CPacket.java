package json.jayson.network.packet;

import json.jayson.common.objects.blocks.cake_plate.CakePlateEntity;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SoulEntitySpawnerSyncS2CPacket {

    private final BlockPos pos;
    private final String entity;

    public SoulEntitySpawnerSyncS2CPacket(BlockPos pos, String entity) {
        this.pos = pos;
        this.entity = entity;
    }

    public SoulEntitySpawnerSyncS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.entity = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeUtf(entity);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof SoulEntitySpawnerBlockEntity te) {
                te.setEntity(entity);
            }
        });
        return true;
    }

}
