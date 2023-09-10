package org.soulslike.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.soulslike.common.objects.blocks.cake_plate.CakePlateEntity;

import java.util.function.Supplier;

public class CakePlateSyncS2CPacket {

    private final BlockPos pos;
    private final String cakeId;

    public CakePlateSyncS2CPacket(BlockPos pos, String cakeId) {
        this.pos = pos;
        this.cakeId = cakeId;
    }

    public CakePlateSyncS2CPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.cakeId = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeUtf(cakeId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if(Minecraft.getInstance().level.getBlockEntity(pos) instanceof CakePlateEntity cakePlateEntity) {
                cakePlateEntity.cakeString = cakeId;
                cakePlateEntity.reloadCakeBlock();
            }
        });
        return true;
    }

}
