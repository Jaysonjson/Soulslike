package json.jayson.common.objects.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulVialItem extends Item {

    public static int MAX = 32;
    public static String NBT_CUSTOM_MODEL_DATA = "CustomModelData";

    public SoulVialItem(Properties p_41383_) {
        super(p_41383_);
    }

    public void createTag(ItemStack itemStack) {
        if (!itemStack.hasTag()) {
            CompoundTag tag = new CompoundTag();
            tag.putString("entity", "");
            tag.putInt("amount", 0);
            checkModelData(tag);
            itemStack.setTag(tag);
        }
    }

    public void checkModelData(CompoundTag tag) {
        float percentage = ((float) tag.getInt("amount") / MAX) * 5;
        tag.putInt(NBT_CUSTOM_MODEL_DATA, (int) percentage);
        if((int)percentage == 0) tag.putInt(NBT_CUSTOM_MODEL_DATA, 1);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        createTag(p_41404_);
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        createTag(itemStack);
        if(!itemStack.getTag().getString("entity").isEmpty()) {
            components.add(Component.literal("Entity: " + Component.translatable(itemStack.getTag().getString("entity")).getString()).withStyle(ChatFormatting.GRAY));
            components.add(Component.literal("Amount: " + itemStack.getTag().getInt("amount") + " / " + MAX).withStyle(ChatFormatting.GRAY));
            super.appendHoverText(itemStack, level, components, tooltipFlag);
        }
    }

}
