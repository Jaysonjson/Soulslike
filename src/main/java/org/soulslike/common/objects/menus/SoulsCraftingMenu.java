package org.soulslike.common.objects.menus;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public class SoulsCraftingMenu extends CraftingMenu {
    public SoulsCraftingMenu(int p_39353_, Inventory p_39354_) {
        super(p_39353_, p_39354_);
    }

    public SoulsCraftingMenu(int p_39356_, Inventory p_39357_, ContainerLevelAccess p_39358_) {
        super(p_39356_, p_39357_, p_39358_);
    }

    @Override
    public boolean stillValid(Player p_39368_) {
        return true;
    }
}
