package org.soulslike.common.objects.blocks.vase;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.soulslike.common.registries.SoulsBlockEntities;

import java.util.ArrayList;

public class GenericVaseEntity extends RandomizableContainerBlockEntity {

    public static int SIZE = 6;
    public Vec3 offset;
    public NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    public ArrayList<ItemStack> renderItems = new ArrayList<>();

    public GenericVaseEntity(BlockEntityType<?> entityType, BlockPos p_155229_, BlockState p_155230_) {
        super(entityType, p_155229_, p_155230_);
    }

    public GenericVaseEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(SoulsBlockEntities.GENERIC_VASE.get(), p_155229_, p_155230_);

    }

    public void setHandler(ArrayList<ItemStack> items) {
        items.addAll(items);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_59625_) {
        this.items = p_59625_;
    }
    @Override
    public void load(CompoundTag p_155349_) {
        super.load(p_155349_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(p_155349_)) {
            ContainerHelper.loadAllItems(p_155349_, this.items);
        }
    }

    @Override
    public void saveAdditional(CompoundTag p_187489_) {
        super.saveAdditional(p_187489_);
        if (!this.trySaveLootTable(p_187489_)) {
            ContainerHelper.saveAllItems(p_187489_, this.items);
        }
        //ArrayList<ItemStack> itemStackArrayList = new ArrayList(items);
    }


    @Override
    protected Component getDefaultName() {
        return Component.literal("Mud Vase");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return ChestMenu.oneRow(p_58627_, p_58628_);
    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

}
