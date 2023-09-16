package json.jayson.data.generation.lists;

import json.jayson.data.generation.interfaces.IToolType;
import json.jayson.data.generation.ModBlockTagProvider;

public class ToolTypes {

    public static IToolType NONE = () -> null;

    public static IToolType DEFAULT_PICKAXE = () -> ModBlockTagProvider.PICKAXE;

    public static IToolType DEFAULT_AXE = () -> ModBlockTagProvider.AXE;

    public static IToolType DEFAULT_HOE = () -> ModBlockTagProvider.HOE;

    public static IToolType DEFAULT_SHOVEL = () -> ModBlockTagProvider.SHOVEL;

}
