package org.soulslike.data.generation.interfaces;

import net.minecraft.world.level.ItemLike;

public class SoulsLootTable implements ILootTable {

    ItemLike other;

    public SoulsLootTable() {

    }

    public SoulsLootTable(ItemLike other) {
        this.other = other;
    }

    @Override
    public ItemLike dropOther() {
        return this.other;
    }
}
