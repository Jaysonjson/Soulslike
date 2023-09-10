package org.soulslike.common.objects.loot_modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Deprecated
public class SingleItemLootModifier extends LootModifier {
    public static final Supplier<Codec<SingleItemLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ExtraCodecs.NON_EMPTY_STRING.optionalFieldOf("item", "minecraft:air").forGetter(m -> m.item))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("min", 1).forGetter(m -> m.min))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("max", 1).forGetter(m -> m.max))
            .and(ExtraCodecs.NON_EMPTY_STRING.optionalFieldOf("enchantment", "").forGetter(m -> m.enchantment))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("enchantment_level", 0).forGetter(m -> m.enchantment_level))
            .and(ExtraCodecs.POSITIVE_FLOAT.optionalFieldOf("enchantment_chance", 0.0f).forGetter(m -> m.enchantment_chance))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("check_existing", 0).forGetter(m -> m.check_existing))
            .apply(inst, SingleItemLootModifier::new)
    ));

    private final String item;
    private final String enchantment;
    private final int enchantment_level;
    private final float enchantment_chance;
    private final int min;
    private final int max;

    private final int check_existing;

    public SingleItemLootModifier(final LootItemCondition[] conditionsIn, final String item, final int min, final int max, final String enchantment, final int enchantment_level, final float enchantment_chance, final int check_existing) {
        super(conditionsIn);
        this.item = item;
        this.min = min;
        this.max = max;
        this.enchantment = enchantment;
        this.enchantment_level = enchantment_level;
        this.enchantment_chance = enchantment_chance;
        this.check_existing = check_existing;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ItemStack itemStack = null;
        if(ForgeRegistries.ITEMS.getDelegate(new ResourceLocation(item)).isPresent()) {
            itemStack = ForgeRegistries.ITEMS.getDelegate(new ResourceLocation(item)).get().get().getDefaultInstance();
        } else if(ForgeRegistries.BLOCKS.getDelegate(new ResourceLocation(item)).isPresent()) {
            itemStack = ForgeRegistries.BLOCKS.getDelegate(new ResourceLocation(item)).get().get().asItem().getDefaultInstance();
        }
        if(itemStack != null) {
            itemStack.setCount(ThreadLocalRandom.current().nextInt(min, max + 1));
            if(Math.random() < enchantment_chance) {
                if (!enchantment.isEmpty()) {
                    if (ForgeRegistries.ENCHANTMENTS.getDelegate(new ResourceLocation(enchantment)).isPresent()) {
                        itemStack.enchant(ForgeRegistries.ENCHANTMENTS.getDelegate(new ResourceLocation(enchantment)).get().get(), enchantment_level);
                    }
                }
            }
            boolean add = true;
            if(check_existing == 1) {
                for (ItemStack stack : generatedLoot) {
                    if(stack.getItem().toString().equalsIgnoreCase(itemStack.getItem().toString()))  {
                        add = false;
                    }
                }
            }
            if(add) generatedLoot.add(itemStack);
        }
        return generatedLoot;
    }
    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
