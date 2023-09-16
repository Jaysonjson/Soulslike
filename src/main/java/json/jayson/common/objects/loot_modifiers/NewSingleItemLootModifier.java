package json.jayson.common.objects.loot_modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import json.jayson.common.registries.SoulsCodecs;
import json.jayson.common.registries.SoulsExtraCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import json.jayson.common.objects.codecs.EnchantmentCodec;
import json.jayson.common.objects.codecs.RandomAmountCodec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class NewSingleItemLootModifier extends LootModifier {
    public static final Supplier<Codec<NewSingleItemLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
            .and(ExtraCodecs.NON_EMPTY_STRING.optionalFieldOf("item", "minecraft:air").forGetter(m -> m.item))
            .and(SoulsCodecs.RANDOM_AMOUNT.get().optionalFieldOf("randomAmount", new RandomAmountCodec(0, 1)).forGetter(m -> m.randomAmountCodec))
            .and(SoulsExtraCodecs.ENCHANTMENT_ARRAY.optionalFieldOf("enchantments", new ArrayList<>()).forGetter(m -> m.enchantments))
            .and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("check_existing", 0).forGetter(m -> m.check_existing))
            .apply(inst, NewSingleItemLootModifier::new)
    ));

    private final String item;
    private final List<EnchantmentCodec> enchantments;

    private final RandomAmountCodec randomAmountCodec;

    private final int check_existing;

    public NewSingleItemLootModifier(final LootItemCondition[] conditionsIn, final String item, final RandomAmountCodec randomAmountCodec, final List<EnchantmentCodec> enchantments, final int check_existing) {
        super(conditionsIn);
        this.item = item;
        this.randomAmountCodec = randomAmountCodec;
        this.check_existing = check_existing;
        this.enchantments = enchantments;
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
            itemStack.setCount(ThreadLocalRandom.current().nextInt(randomAmountCodec.min, randomAmountCodec.max + 1));
            for (EnchantmentCodec enchantmentCodec : enchantments) {
                if(Math.random() < enchantmentCodec.chance) {
                    if (!enchantmentCodec.enchantment.isEmpty()) {
                        if (ForgeRegistries.ENCHANTMENTS.getDelegate(new ResourceLocation(enchantmentCodec.enchantment)).isPresent()) {
                            if (Math.random() < enchantmentCodec.upgrade_chance) {
                                itemStack.enchant(ForgeRegistries.ENCHANTMENTS.getDelegate(new ResourceLocation(enchantmentCodec.enchantment)).get().get(), enchantmentCodec.level + 1);
                            } else {
                                itemStack.enchant(ForgeRegistries.ENCHANTMENTS.getDelegate(new ResourceLocation(enchantmentCodec.enchantment)).get().get(), enchantmentCodec.level);
                            }
                        }
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
