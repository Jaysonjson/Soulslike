package json.jayson.common.objects.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import json.jayson.common.objects.menus.SoulsCraftingMenu;

public class SoulsCraftingTable extends CraftingTableBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    public SoulsCraftingTable(BlockBehaviour.Properties p_52225_) {
        super(p_52225_);
    }

    @Override
    public MenuProvider getMenuProvider(BlockState p_52240_, Level p_52241_, BlockPos p_52242_) {
        return new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) -> new SoulsCraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(p_52241_, p_52242_)), CONTAINER_TITLE);
    }

}
