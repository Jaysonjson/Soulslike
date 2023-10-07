package json.jayson.common.capabilities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;

import java.util.ArrayList;

public class EntitySouls {
    long souls_ = 0;

    public long getSouls() {
        return souls_;
    }

    public void setSouls(long souls) {
        souls_ = Math.min(souls, PlayerSouls.MAX_SOULS);
    }

    public void saveData(CompoundTag tag) {
        tag.putLong(PlayerSouls.NBT_TAG_SOULS, souls_);
    }

    public void loadData(CompoundTag tag) {
        souls_ = tag.getLong(PlayerSouls.NBT_TAG_SOULS);
    }

}
