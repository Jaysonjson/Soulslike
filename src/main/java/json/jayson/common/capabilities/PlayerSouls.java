package json.jayson.common.capabilities;

import json.jayson.network.packet.ModMessages;
import json.jayson.network.packet.PlayerSoulsDataSyncS2CPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;

public class PlayerSouls {
    public static String NBT_TAG_SOULS = "souls";
    public static String NBT_TAG_BEE_STATUES = "bee_statues";

    public static String NBT_TAG_SHOW_DEATH_TEXT = "death_text";

    int souls_ = 0;
    public int newSouls_ = 0;
    public int newSoulsTick = 0;
    ArrayList<BlockPos> beeStatues = new ArrayList<>();
    public int beeStatuesSize = 0;
    public static final int MAX_SOULS = 999999999;

    public int getSouls() {
        return souls_;
    }

    public ArrayList<BlockPos> getBeeStatues() {
        return beeStatues;
    }

    public void setSouls(int souls) {
        newSouls_ += souls - souls_;
        newSoulsTick = 0;
        souls_ = Math.min(souls, MAX_SOULS);
    }

    public void increaseSouls(int increment) {
        setSouls(getSouls() + increment);
    }

    public void saveData(CompoundTag tag) {
        tag.putInt(NBT_TAG_SOULS, souls_);
        ListTag blockPosList = new ListTag();
        for (BlockPos beeStatue : beeStatues) {
            blockPosList.add(NbtUtils.writeBlockPos(beeStatue));
        }
        tag.put(NBT_TAG_BEE_STATUES, blockPosList);
        tag.putInt("bee_statues_size", beeStatuesSize);
    }

    public void loadData(CompoundTag tag) {
        souls_ = tag.getInt(NBT_TAG_SOULS);
        beeStatues = new ArrayList<>();
        ListTag listTag = (ListTag) tag.get(NBT_TAG_BEE_STATUES);
        beeStatuesSize = tag.getInt("bee_statues_size");
        for (int i = 0; i < beeStatuesSize; i++) {
            beeStatues.add(NbtUtils.readBlockPos(listTag.getCompound(i)));
        }
    }

    public void sync(ServerPlayer player) {
        ModMessages.sendToPlayer(new PlayerSoulsDataSyncS2CPacket(this), player);
    }

}
