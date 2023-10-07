package json.jayson.common.capabilities.providers;

import json.jayson.common.capabilities.EntitySouls;
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

public class EntitySoulsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<EntitySouls> ENTITY_SOULS = CapabilityManager.get(new CapabilityToken<>() {});
    private EntitySouls souls_ = null;
    private final LazyOptional<EntitySouls> optional = LazyOptional.of(this::createEntitySouls);

    private EntitySouls createEntitySouls() {
        if(this.souls_ == null) {
            this.souls_ = new EntitySouls();
        }
        return this.souls_;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ENTITY_SOULS) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createEntitySouls().saveData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createEntitySouls().loadData(nbt);
    }
}
