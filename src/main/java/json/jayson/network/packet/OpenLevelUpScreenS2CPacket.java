package json.jayson.network.packet;

import json.jayson.client.screens.LevelUpScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenLevelUpScreenS2CPacket {

    public OpenLevelUpScreenS2CPacket() {
    }

    public OpenLevelUpScreenS2CPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            NetworkEvent.Context context = supplier.get();
            context.enqueueWork(() -> {
                Minecraft.getInstance().setScreen(new LevelUpScreen());
            });
            context.setPacketHandled(true);
        }
        return true;
    }

}
