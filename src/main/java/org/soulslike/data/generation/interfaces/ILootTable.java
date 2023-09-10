package org.soulslike.data.generation.interfaces;

import net.minecraft.world.level.ItemLike;

public interface ILootTable {

    default boolean dropSelf() { return false;}
    default ItemLike dropOther() { return null; }

    default boolean dropWhenSilktouch() { return false; }

    default boolean noDrop() {
        return !dropSelf() && dropOther() == null;
    }

}
