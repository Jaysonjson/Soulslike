package org.soulslike.common.objects.items;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class RandomItemColor implements ItemColor {
    @Override
    public int getColor(ItemStack itemStack, int index) {
       /* if(index == 0) {
            return 0xCBFCEFFF;
        }
        if(index == 1) {
            return 0xACFCAFFF;
        }
        return 0xABFCFFFF;*/
        return new Random().nextInt(429496729);
    }
}
