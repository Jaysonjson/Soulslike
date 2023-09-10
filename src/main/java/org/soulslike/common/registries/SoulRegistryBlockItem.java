package org.soulslike.common.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class SoulRegistryBlockItem<T> {

    private RegistryObject<Item> item;
    private RegistryObject<T> block;

    public SoulRegistryBlockItem() {

    }

    public SoulRegistryBlockItem(RegistryObject<Item> item, RegistryObject<T> block) {
        this.block = block;
        this.item = item;
    }

    public RegistryObject<Item> Item() {
        return this.item;
    }

    public RegistryObject<T> Block() {
        return this.block;
    }

    public Item getItem() {
        return Item().get();
    }

    public T getBlock() {
        return Block().get();
    }

}
