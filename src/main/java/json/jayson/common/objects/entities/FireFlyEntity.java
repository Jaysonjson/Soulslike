package json.jayson.common.objects.entities;

import json.jayson.common.registries.SoulsEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FireFlyEntity extends Animal implements NeutralMob, FlyingAnimal {


    List<BlockPos> lights = new ArrayList<>();
    int timer = 0;

    public FireFlyEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = new FireFlyLookControl(this);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.6F).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
    }


    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(ItemTags.FLOWERS), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(9, new FloatGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1));
        //this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void tick() {
        BlockState blockState = level().getBlockState(blockPosition());
        Block block = blockState.getBlock();
        if(block.equals(Blocks.AIR)) {
            level().setBlockAndUpdate(blockPosition(), Blocks.LIGHT.defaultBlockState());
            lights.add(blockPosition());
        }
        ++timer;
        if(timer > 3) {
            if(!lights.isEmpty()) {
                if(lights.get(0) != blockPosition()) {
                    level().setBlockAndUpdate(lights.get(0), Blocks.AIR.defaultBlockState());
                    lights.remove(0);
                }
            }
            timer = 0;
        }
        super.tick();
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    public void onRemovedFromWorld() {
        for (BlockPos light : lights) {
            level().setBlockAndUpdate(light, Blocks.AIR.defaultBlockState());
        }
        super.onRemovedFromWorld();
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return SoulsEntities.FIRE_FLY.get().create(p_146743_);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 0;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {

    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_21672_) {

    }

    @Override
    public void startPersistentAngerTimer() {

    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    class FireFlyLookControl extends LookControl {
        FireFlyLookControl(Mob p_28059_) {
            super(p_28059_);
        }

        public void tick() {
            if (!FireFlyEntity.this.isAngry()) {
                super.tick();
            }
        }

        protected boolean resetXRotOnTick() {
            return false;
        }
    }
}
