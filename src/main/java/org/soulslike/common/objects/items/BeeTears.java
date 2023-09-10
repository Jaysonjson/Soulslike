package org.soulslike.common.objects.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/* Estus Flask */
public class BeeTears extends Item {

    public static String NBT_LEVEL = "level";
    public static String NBT_CHARGES = "charges";
    public static String NBT_MAX_CHARGES = "max_charges";
    public static String NBT_CUSTOM_MODEL_DATA = "CustomModelData";

    public BeeTears(Properties properties) {
        super(properties);
        properties.stacksTo(1);
        properties.fireResistant();
    }

    public void checkModelData(CompoundTag tag) {
        float percentage = ((float) tag.getInt(NBT_CHARGES) / tag.getInt(NBT_MAX_CHARGES)) * 100;
        if(percentage == 0) tag.putInt(NBT_CUSTOM_MODEL_DATA, 1);
        if(percentage > 24) tag.putInt(NBT_CUSTOM_MODEL_DATA, 2);
        if(percentage > 49) tag.putInt(NBT_CUSTOM_MODEL_DATA, 3);
        if(percentage > 74) tag.putInt(NBT_CUSTOM_MODEL_DATA, 4);
        if(percentage > 80) tag.putInt(NBT_CUSTOM_MODEL_DATA, 5);
    }

    public void createTag(ItemStack itemStack) {
        if(!itemStack.hasTag()) {
            CompoundTag tag = new CompoundTag();
            tag.putInt(NBT_LEVEL, 1);
            tag.putInt(NBT_CHARGES, 3);
            tag.putInt(NBT_MAX_CHARGES, 3);
            checkModelData(tag);
            itemStack.setTag(tag);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        createTag(itemStack);
        CompoundTag tag = itemStack.getOrCreateTag();
        int charges = tag.getInt(NBT_CHARGES);
        if(charges > 0) {
            player.getCooldowns().addCooldown(this, 35);
            player.setHealth(player.getHealth() + (4 * tag.getInt(NBT_LEVEL)));
            tag.putInt(NBT_CHARGES, charges - 1);
            checkModelData(tag);
            //itemStack.setTag(tag);
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        createTag(itemStack);
        components.add(Component.literal("Level: " + itemStack.getTag().getInt(NBT_LEVEL)));
        components.add(Component.literal("Charges: " + itemStack.getTag().getInt(NBT_CHARGES)));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
