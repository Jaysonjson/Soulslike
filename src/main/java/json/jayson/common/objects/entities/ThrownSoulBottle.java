package json.jayson.common.objects.entities;

import json.jayson.common.registries.SoulsEntities;
import json.jayson.common.registries.SoulsItems;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class ThrownSoulBottle extends ThrowableItemProjectile {
    public ThrownSoulBottle(Level p_37443_) {
        super(SoulsEntities.SOUL_BOTTLE.get(), p_37443_);
    }

    public ThrownSoulBottle(Level p_37518_, LivingEntity p_37519_) {
        super(SoulsEntities.SOUL_BOTTLE.get(), p_37519_, p_37518_);
    }

    public ThrownSoulBottle(Level p_37513_, double p_37514_, double p_37515_, double p_37516_) {
        super(SoulsEntities.SOUL_BOTTLE.get(), p_37514_, p_37515_, p_37516_, p_37513_);
    }

    public ThrownSoulBottle(EntityType<? extends ThrownSoulBottle> p_20773_, Level p_20774_) {
        super(p_20773_, p_20774_);
    }


    protected float getGravity() {
        return 0.07F;
    }

    @Override
    protected Item getDefaultItem() {
        return SoulsItems.BOTTLE_OF_SOULS.get();
    }

    protected void onHit(HitResult p_37521_) {
        super.onHit(p_37521_);
        if (this.level() instanceof ServerLevel) {
            this.level().levelEvent(2002, this.blockPosition(), PotionUtils.getColor(Potions.WATER));
            SoulOrbEntity.award((ServerLevel)this.level(), this.position(), 250);
            this.discard();
        }
    }

}
