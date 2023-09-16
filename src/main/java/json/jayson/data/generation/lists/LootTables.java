package json.jayson.data.generation.lists;

import json.jayson.data.generation.interfaces.ILootTable;
import json.jayson.data.generation.interfaces.SoulsLootTable;
import net.minecraft.world.level.ItemLike;

public class LootTables {

    public static ILootTable DROP_SELF = new ILootTable() {
        @Override
        public boolean dropSelf() {
            return true;
        }
    };

    public static ILootTable NO_DROP = new ILootTable() {};

    public static ILootTable DROP_WHEN_SILKTOUCH = new ILootTable() {
        @Override
        public boolean dropWhenSilktouch() {
            return true;
        }
    };

    public static ILootTable DROP_OTHER(ItemLike other) {
        return new SoulsLootTable(other);
    }

}
