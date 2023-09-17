package json.jayson.common.objects.items.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CloudStaff extends Item {
    int coolDown = 3;
    Vec3 force = new Vec3(1.75, 1.75, 1.75);
    String NBT_POWER = "power";
    public CloudStaff(Properties properties, int coolDown, Vec3 force) {
        super(properties);
        this.coolDown = coolDown;
        this.force = force;
    }

    public CloudStaff(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand interactionHand) {
        if(!player.isUnderWater()) {
            if (!level.isClientSide()) {
                player.getCooldowns().addCooldown(this, coolDown);
            }
            float power = 1;
            ItemStack itemStack = player.getItemInHand(interactionHand);
            if (itemStack.hasTag()) {
                if (itemStack.getTag().contains(NBT_POWER)) {
                    power = itemStack.getTag().getFloat(NBT_POWER);
                }
            }
            force = force.multiply(new Vec3(power, power, power));
            player.setDeltaMovement(player.getLookAngle().multiply(force));
            player.fallDistance = 0;
            if (!player.isCreative()) itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            if (itemStack.getMaxDamage() < itemStack.getDamageValue()) {
                itemStack.setCount(0);
                player.playSound(SoundEvents.ITEM_BREAK);
            }
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level p_41422_, @NotNull List<Component> componentList, @NotNull TooltipFlag p_41424_) {
        if(itemStack.hasTag()) {
            if(itemStack.getTag().contains(NBT_POWER)) {
                componentList.add(Component.literal("Power: " + itemStack.getTag().getFloat(NBT_POWER)));
            }
        }
        super.appendHoverText(itemStack, p_41422_, componentList, p_41424_);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack itemStack) {
        return true;
    }

}
