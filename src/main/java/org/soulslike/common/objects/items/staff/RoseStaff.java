package org.soulslike.common.objects.items.staff;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class RoseStaff extends Item {

    public float healPower = 2f;
    public int maxDistance = 8;

    public RoseStaff(Properties properties, float healPower) {
        super(properties);
        this.healPower = healPower;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            player.getCooldowns().addCooldown(this, 12);
        }
        Random random = new Random();
        List<Entity> entities =  level.getEntities(player, player.getBoundingBox().expandTowards(player.getLookAngle().multiply(maxDistance, maxDistance, maxDistance)));
        for (Entity entity : entities) {
            if(entity instanceof LivingEntity livingEntity) {
                livingEntity.heal(healPower);
                int distance = (int) livingEntity.position().distanceTo(player.position());
                for (int i = 0; i < distance; i++) {
                    Vec3 f = player.position();
                    f = f.add(new Vec3(i, i, i).multiply(player.getLookAngle()));
                    level.addParticle(ParticleTypes.HEART, f.x, f.y + 0.2, f.z, 0.0D, 0.0D, 0.0D);
                }

                for (int i = 0; i < 4; i++) {
                    level.addParticle(ParticleTypes.HEART, livingEntity.position().x, livingEntity.getBoundingBox().maxY + random.nextDouble(), livingEntity.position().z, 0.0D, 0.0D, 0.0D);
                }

                //player.playSound(SoundEvents.DYE_USE);
            }
        }
        player.getItemInHand(interactionHand).setDamageValue(player.getItemInHand(interactionHand).getDamageValue() + entities.size());
        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
