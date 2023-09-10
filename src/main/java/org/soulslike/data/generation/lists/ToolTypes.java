package org.soulslike.data.generation.lists;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.soulslike.data.generation.ModBlockTagProvider;
import org.soulslike.data.generation.interfaces.IToolType;

public class ToolTypes {

    public static IToolType NONE = () -> null;

    public static IToolType DEFAULT_PICKAXE = () -> ModBlockTagProvider.PICKAXE;

    public static IToolType DEFAULT_AXE = () -> ModBlockTagProvider.AXE;

    public static IToolType DEFAULT_HOE = () -> ModBlockTagProvider.HOE;

    public static IToolType DEFAULT_SHOVEL = () -> ModBlockTagProvider.SHOVEL;

}
