package json.jayson.common.objects.entities;

import json.jayson.common.capabilities.providers.PlayerSoulsProvider;
import json.jayson.common.registries.SoulsEntities;
import net.minecraft.client.renderer.entity.ExperienceOrbRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SoulOrbEntity extends Entity {

    public int souls = 0;
    private Player followingPlayer;
    private int age;
    private int health = 5;
    private int count = 1;

    public SoulOrbEntity(Level p_19871_, double p_20777_, double p_20778_, double p_20779_, int souls) {
        super(SoulsEntities.SOUL_ORB.get(), p_19871_);
        this.setPos(p_20777_, p_20778_, p_20779_);
        this.setYRot((float)(this.random.nextDouble() * 360.0D));
        this.setDeltaMovement((this.random.nextDouble() * (double)0.2F - (double)0.1F) * 2.0D, this.random.nextDouble() * 0.2D * 2.0D, (this.random.nextDouble() * (double)0.2F - (double)0.1F) * 2.0D);
        this.souls = souls;
    }

    public SoulOrbEntity(EntityType<? extends SoulOrbEntity> p_20773_, Level p_20774_) {
        super(p_20773_, p_20774_);
    }

    public void tick() {
        super.tick();
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        if (this.isEyeInFluid(FluidTags.WATER)) {
            this.setUnderwaterMovement();
        } else if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
        }

        if (this.level().getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
            this.setDeltaMovement((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), (double)0.2F, (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F));
        }

        if (!this.level().noCollision(this.getBoundingBox())) {
            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
        }

        if (this.tickCount % 20 == 1) {
            this.scanForEntities();
        }

        if (this.followingPlayer != null && (this.followingPlayer.isSpectator() || this.followingPlayer.isDeadOrDying())) {
            this.followingPlayer = null;
        }

        if (this.followingPlayer != null) {
            Vec3 vec3 = new Vec3(this.followingPlayer.getX() - this.getX(), this.followingPlayer.getY() + (double)this.followingPlayer.getEyeHeight() / 2.0D - this.getY(), this.followingPlayer.getZ() - this.getZ());
            double d0 = vec3.lengthSqr();
            if (d0 < 64.0D) {
                double d1 = 1.0D - Math.sqrt(d0) / 8.0D;
                this.setDeltaMovement(this.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * 0.1D)));
            }
        }

        this.move(MoverType.SELF, this.getDeltaMovement());
        float f = 0.98F;
        if (this.onGround()) {
            BlockPos pos = getBlockPosBelowThatAffectsMyMovement();
            f = this.level().getBlockState(pos).getFriction(this.level(), pos, this) * 0.98F;
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply((double)f, 0.98D, (double)f));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, -0.9D, 1.0D));
        }

        ++this.age;
        if (this.age >= 6000) {
            this.discard();
        }

        if (this.level() instanceof ServerLevel) {
            for(SoulOrbEntity experienceorb : this.level().getEntities(EntityTypeTest.forClass(SoulOrbEntity.class), this.getBoundingBox().inflate(2D), this::canMerge)) {
                this.merge(experienceorb);
            }
        }
    }

    private void setUnderwaterMovement() {
        Vec3 vec3 = this.getDeltaMovement();
        this.setDeltaMovement(vec3.x * (double)0.99F, Math.min(vec3.y + (double)5.0E-4F, (double)0.06F), vec3.z * (double)0.99F);
    }

    private void scanForEntities() {
        if (this.followingPlayer == null || this.followingPlayer.distanceToSqr(this) > 64.0D) {
            this.followingPlayer = this.level().getNearestPlayer(this, 8.0D);
        }

        if (this.level() instanceof ServerLevel) {
            for(SoulOrbEntity experienceorb : this.level().getEntities(EntityTypeTest.forClass(SoulOrbEntity.class), this.getBoundingBox().inflate(2D), this::canMerge)) {
                this.merge(experienceorb);
            }
        }
    }

    public static void award(ServerLevel p_147083_, Vec3 p_147084_, int p_147085_) {
        for (int i = 0; i < p_147085_; i++) {
            if (!tryMergeToExisting(p_147083_, p_147084_, 1)) {
                p_147083_.addFreshEntity(new SoulOrbEntity(p_147083_, p_147084_.x(), p_147084_.y(), p_147084_.z(), 1));
            }
        }
    }

    private static boolean tryMergeToExisting(ServerLevel p_147097_, Vec3 p_147098_, int p_147099_) {
        AABB aabb = AABB.ofSize(p_147098_, 1.0D, 1.0D, 1.0D);
        int i = p_147097_.getRandom().nextInt(40);
        List<SoulOrbEntity> list = p_147097_.getEntities(EntityTypeTest.forClass(SoulOrbEntity.class), aabb, (p_147081_) -> {
            return canMerge(p_147081_, i, p_147099_);
        });
        if (!list.isEmpty()) {
            SoulOrbEntity experienceorb = list.get(0);
            ++experienceorb.count;
            experienceorb.age = 0;
            return true;
        } else {
            return false;
        }
    }

    private boolean canMerge(SoulOrbEntity p_147087_) {
        return p_147087_ != this && canMerge(p_147087_, this.getId(), this.souls);
    }

    private static boolean canMerge(SoulOrbEntity p_147089_, int p_147090_, int p_147091_) {
        return !p_147089_.isRemoved() && p_147089_.souls == p_147091_ && 30 > p_147089_.count + p_147091_;
    }

    private void merge(SoulOrbEntity p_147101_) {
        this.count += p_147101_.count;
        this.age = Math.min(this.age, p_147101_.age);
        p_147101_.discard();
    }

    public boolean hurt(DamageSource p_20785_, float p_20786_) {
        if (this.level().isClientSide || this.isRemoved()) return false;
        if (this.isInvulnerableTo(p_20785_)) {
            return false;
        } else if (this.level().isClientSide) {
            return true;
        } else {
            this.markHurt();
            this.health = (int)((float)this.health - p_20786_);
            if (this.health <= 0) {
                this.discard();
            }

            return true;
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    public void playerTouch(Player p_20792_) {
        if (!this.level().isClientSide) {
            if (p_20792_.takeXpDelay == 0) {
                p_20792_.takeXpDelay = 2;
                p_20792_.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                    playerSouls.increaseSouls((long) count * souls);
                    //if(p_20792_ instanceof ServerPlayer serverPlayer) playerSouls.sync(serverPlayer);
                });
                this.discard();
            }

        }
    }

    public int getIcon() {
        if (this.souls >= 99) {
            return 10;
        } else if (this.souls >= 74) {
            return 9;
        } else if (this.souls >= 67) {
            return 8;
        } else if (this.souls >= 56) {
            return 7;
        } else if (this.souls >= 45) {
            return 6;
        } else if (this.souls >= 33) {
            return 5;
        } else if (this.souls >= 27) {
            return 4;
        } else if (this.souls >= 17) {
            return 3;
        } else if (this.souls >= 7) {
            return 2;
        } else {
            return this.souls >= 3 ? 1 : 0;
        }
    }

    public void addAdditionalSaveData(CompoundTag p_20796_) {
        p_20796_.putShort("Health", (short)this.health);
        p_20796_.putShort("Age", (short)this.age);
        p_20796_.putShort("Souls", (short)this.souls);
        p_20796_.putInt("Count", this.count);
    }

    public void readAdditionalSaveData(CompoundTag p_20788_) {
        this.health = p_20788_.getShort("Health");
        this.age = p_20788_.getShort("Age");
        this.souls = p_20788_.getShort("Souls");
        this.count = Math.max(p_20788_.getInt("Count"), 1);
    }
}
