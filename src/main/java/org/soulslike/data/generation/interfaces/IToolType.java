package org.soulslike.data.generation.interfaces;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public interface IToolType {

    TagKey<Block> toolTag();

}
