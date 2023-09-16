package json.jayson.common.objects.items.staff;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FeatherStaff extends Item {
    int cooldown;
    public FeatherStaff(Properties properties, int cooldown) {
        super(properties);
        this.cooldown = cooldown;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (!level.isClientSide()) {
            player.getCooldowns().addCooldown(this, cooldown);
        }
        player.fallDistance = 0;
        player.setDeltaMovement(0, 0, 0);
        return super.use(level, player, interactionHand);
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }
}
