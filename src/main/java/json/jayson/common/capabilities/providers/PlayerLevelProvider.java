package json.jayson.common.capabilities.providers;

import json.jayson.common.capabilities.PlayerLevel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerLevelProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerLevel> PLAYER_LEVEL = CapabilityManager.get(new CapabilityToken<>() {
    });
    private PlayerLevel level_ = null;
    private final LazyOptional<PlayerLevel> optional = LazyOptional.of(this::createPlayerLevel);

    private PlayerLevel createPlayerLevel() {
        if(this.level_ == null) {
            this.level_ = new PlayerLevel();
        }
        return this.level_;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_LEVEL) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerLevel().saveData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerLevel().loadData(nbt);
    }
}
