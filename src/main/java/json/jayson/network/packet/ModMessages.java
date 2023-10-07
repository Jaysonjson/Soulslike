package json.jayson.network.packet;

import json.jayson.Soulslike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import json.jayson.network.packet.*;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return ++packetId;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Soulslike.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PlayerSoulsDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayerSoulsDataSyncS2CPacket::new)
                .encoder(PlayerSoulsDataSyncS2CPacket::toBytes)
                .consumerMainThread(PlayerSoulsDataSyncS2CPacket::handle)
                .add();


        net.messageBuilder(PlayerLevelDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayerLevelDataSyncS2CPacket::new)
                .encoder(PlayerLevelDataSyncS2CPacket::toBytes)
                .consumerMainThread(PlayerLevelDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(GenericVaseInvSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(GenericVaseInvSyncS2CPacket::new)
                .encoder(GenericVaseInvSyncS2CPacket::toBytes)
                .consumerMainThread(GenericVaseInvSyncS2CPacket::handle)
                .add();
        net.messageBuilder(FireAltarSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(FireAltarSyncS2CPacket::new)
                .encoder(FireAltarSyncS2CPacket::toBytes)
                .consumerMainThread(FireAltarSyncS2CPacket::handle)
                .add();
        net.messageBuilder(CrateSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CrateSyncS2CPacket::new)
                .encoder(CrateSyncS2CPacket::toBytes)
                .consumerMainThread(CrateSyncS2CPacket::handle)
                .add();

        net.messageBuilder(CakePlateSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CakePlateSyncS2CPacket::new)
                .encoder(CakePlateSyncS2CPacket::toBytes)
                .consumerMainThread(CakePlateSyncS2CPacket::handle)
                .add();
            //net.messageBuilder(OpenLevelUpScreenS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
              //      .decoder(OpenLevelUpScreenS2CPacket::new)
               //     .encoder(OpenLevelUpScreenS2CPacket::toBytes)
               //     .consumerMainThread(OpenLevelUpScreenS2CPacket::handle)
               //     .add();
        net.messageBuilder(SoulEntitySpawnerSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(SoulEntitySpawnerSyncS2CPacket::new)
                .encoder(SoulEntitySpawnerSyncS2CPacket::toBytes)
                .consumerMainThread(SoulEntitySpawnerSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }
}
