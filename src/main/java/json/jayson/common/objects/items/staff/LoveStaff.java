package json.jayson.common.objects.items.staff;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public class LoveStaff extends Item {

    public int maxDistance = 8;

    public LoveStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            player.getCooldowns().addCooldown(this, 12);
        }
        Random random = new Random();
        List<Entity> entities =  level.getEntities(player, player.getBoundingBox().expandTowards(player.getLookAngle().multiply(maxDistance, maxDistance, maxDistance)));
        for (Entity entity : entities) {
            if(entity instanceof Animal animal) {
                animal.setInLove(player);
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
