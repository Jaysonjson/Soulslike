package org.soulslike.common.objects.blocks.cake_plate;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.soulslike.ModMessages;
import org.soulslike.common.SoulsNBTKeys;
import org.soulslike.common.registries.SoulsBlockEntities;
import org.soulslike.network.packet.CakePlateSyncS2CPacket;

public class CakePlateEntity extends BlockEntity {

    public String cakeString = "";
    public Block cakeBlock = null;

    public CakePlateEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(SoulsBlockEntities.CAKE_PLATE.get(), p_155229_, p_155230_);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if(tag.contains(SoulsNBTKeys.CAKE_PLATE_CAKE)) {
            cakeString = tag.getString(SoulsNBTKeys.CAKE_PLATE_CAKE);
        }
        if(cakeBlock == null) {
            reloadCakeBlock();
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        ModMessages.sendToClients(new CakePlateSyncS2CPacket(getBlockPos(), cakeString));
                    }
                },
                1000
        );

    }

    public void reloadCakeBlock() {
        if(ForgeRegistries.BLOCKS.containsKey(new ResourceLocation(cakeString))) {
            cakeBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(cakeString));
        }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString(SoulsNBTKeys.CAKE_PLATE_CAKE, cakeString);
    }


}
