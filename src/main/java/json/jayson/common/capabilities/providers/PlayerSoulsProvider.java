package json.jayson.common.capabilities.providers;

import json.jayson.common.capabilities.PlayerSouls;
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

public class PlayerSoulsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerSouls> PLAYER_SOULS = CapabilityManager.get(new CapabilityToken<PlayerSouls>() {});
    private PlayerSouls souls_ = null;
    private final LazyOptional<PlayerSouls> optional = LazyOptional.of(this::createPlayerSouls);

    private PlayerSouls createPlayerSouls() {
        if(this.souls_ == null) {
            this.souls_ = new PlayerSouls();
        }
        return this.souls_;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_SOULS) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerSouls().saveData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerSouls().loadData(nbt);
    }
}
