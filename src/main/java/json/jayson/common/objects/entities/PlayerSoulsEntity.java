package json.jayson.common.objects.entities;

import json.jayson.client.overlay.player_souls.PlayerSoulsEntityEntries;
import json.jayson.common.SoulsNBTKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import json.jayson.client.overlay.EntityTextOverlay;
import json.jayson.common.capabilities.PlayerSoulsProvider;
import json.jayson.common.registries.SoulsEntities;

public class PlayerSoulsEntity extends Entity implements IEntityTextOverlay {

    long souls = 0;
    int time = 0;

    public static final EntityDataAccessor<Long> SOULS = SynchedEntityData.defineId(PlayerSoulsEntity.class, EntityDataSerializers.LONG);
    public static final EntityDataAccessor<String> PLAYER_NAME = SynchedEntityData.defineId(PlayerSoulsEntity.class, EntityDataSerializers.STRING);


    public PlayerSoulsEntity(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
        setNoGravity(false);
    }

    public PlayerSoulsEntity(Level level, double x, double y, double z, long souls, String playerName) {
        this(SoulsEntities.PLAYER_SOULS.get(), level);
        setPos(x, y, z);
        this.souls = souls;
        getEntityData().set(SOULS, souls);
        getEntityData().set(PLAYER_NAME, playerName);
        setNoGravity(false);
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand interactionHand) {
        System.out.println("INTERACT");
        player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
            playerSouls.increaseSouls(souls);
            if(player instanceof ServerPlayer serverPlayer) {
                playerSouls.sync(serverPlayer);
            }
            remove(RemovalReason.DISCARDED);
        });
        return super.interact(player, interactionHand);
    }



    @Override
    public void tick() {
        ++this.time;
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        if (!this.level().isClientSide) {
            BlockPos blockpos = this.blockPosition();
            double d0 = this.getDeltaMovement().lengthSqr();
            if (d0 > 1.0D) {
                BlockHitResult blockhitresult = this.level().clip(new ClipContext(new Vec3(this.xo, this.yo, this.zo), this.position(), ClipContext.Block.COLLIDER, ClipContext.Fluid.SOURCE_ONLY, this));
                if (blockhitresult.getType() != HitResult.Type.MISS) {
                    blockpos = blockhitresult.getBlockPos();
                }
            }

            if (!this.onGround()) {
                if (!this.level().isClientSide && (this.time > 100 && (blockpos.getY() <= this.level().getMinBuildHeight() || blockpos.getY() > this.level().getMaxBuildHeight()) || this.time > 600)) {
                    this.discard();
                }
            } else {
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
            }
        }
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
    }

    @Override
    protected void defineSynchedData() {
        getEntityData().define(SOULS, souls);
        getEntityData().define(PLAYER_NAME, "Unknown");
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        if(tag.contains(SoulsNBTKeys.ENTITY_PLAYER_SOULS_CONTAINED_SOULS)) {
            souls = tag.getLong(SoulsNBTKeys.ENTITY_PLAYER_SOULS_CONTAINED_SOULS);
            getEntityData().set(SOULS, souls);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putLong(SoulsNBTKeys.ENTITY_PLAYER_SOULS_CONTAINED_SOULS, souls);
    }

    public long getSouls() {
        return souls;
    }

    @Override
    public void alterEntityOverlayText() {
        PlayerSoulsEntityEntries.Souls souls1 = new PlayerSoulsEntityEntries.Souls();
        souls1.text = "Contained Souls: " + getEntityData().get(PlayerSoulsEntity.SOULS);
        EntityTextOverlay.TEXTS.add(souls1);

        PlayerSoulsEntityEntries.Player player = new PlayerSoulsEntityEntries.Player();
        player.text = getEntityData().get(PlayerSoulsEntity.PLAYER_NAME) + "`s Souls";
        EntityTextOverlay.TEXTS.add(player);
    }
}
