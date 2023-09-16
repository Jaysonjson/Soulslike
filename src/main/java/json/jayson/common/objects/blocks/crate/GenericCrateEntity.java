package json.jayson.common.objects.blocks.crate;

import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GenericCrateEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);

    public GenericCrateEntity(BlockPos p_155630_, BlockState p_155631_) {
        super(SoulsBlockEntities.GENERIC_CRATE.get(), p_155630_, p_155631_);
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
    protected void saveAdditional(CompoundTag p_187489_) {
        super.saveAdditional(p_187489_);
        if (!this.trySaveLootTable(p_187489_)) {
            ContainerHelper.saveAllItems(p_187489_, this.items);
        }

    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Crate");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        sync();
        return ChestMenu.fourRows(p_58627_, p_58628_);
    }

    @Override
    public int getContainerSize() {
        return 36;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> p_59625_) {
        this.items = p_59625_;
    }

    public void sync() {
       /* int i = 0;
        for (ItemStack item : getItems()) {
            if(item.getItem() != Items.AIR) ModMessages.sendToClients(new CrateSyncS2CPacket(item, getBlockPos(), i));
            ++i;
        }*/
    }

}
