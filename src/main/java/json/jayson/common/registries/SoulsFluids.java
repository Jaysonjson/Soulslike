package json.jayson.common.registries;

import json.jayson.Soulslike;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import json.jayson.common.objects.fluids.SoulsFluidTypes;

public class SoulsFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Soulslike.MODID);

    public static final RegistryObject<FlowingFluid> SOURCE_BLOOD = FLUIDS.register("blood_fluid",
            () -> new ForgeFlowingFluid.Source(SoulsFluids.BLOOD_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood",
            () -> new ForgeFlowingFluid.Flowing(SoulsFluids.BLOOD_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_SOUL = FLUIDS.register("soul",
            () -> new ForgeFlowingFluid.Source(SoulsFluids.SOULS_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_SOUL = FLUIDS.register("flowing_soul",
            () -> new ForgeFlowingFluid.Flowing(SoulsFluids.SOULS_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_VIBRANT_SOUL = FLUIDS.register("vibrant_soul",
            () -> new ForgeFlowingFluid.Source(SoulsFluids.VIBRANT_SOULS_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_VIBRANT_SOUL = FLUIDS.register("flowing_vibrant_soul",
            () -> new ForgeFlowingFluid.Flowing(SoulsFluids.VIBRANT_SOULS_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SOURCE_BEE_TEARS = FLUIDS.register("source_bee_tears",
            () -> new ForgeFlowingFluid.Source(SoulsFluids.BEE_TEARS_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_BEE_TEARS = FLUIDS.register("flowing_bee_tears",
            () -> new ForgeFlowingFluid.Flowing(SoulsFluids.BEE_TEARS_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            SoulsFluidTypes.BLOOD_WATER_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(SoulsBlocks.BLOOD_BLOCK.Block())
            .bucket(SoulsItems.BLOOD_BUCKET);

    public static final ForgeFlowingFluid.Properties SOULS_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            SoulsFluidTypes.SOUL_WATER_FLUID_TYPE, SOURCE_SOUL, FLOWING_SOUL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(SoulsBlocks.SOUL_BLOCK.Block())
            .bucket(SoulsItems.SOUL_BUCKET);

    public static final ForgeFlowingFluid.Properties VIBRANT_SOULS_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            SoulsFluidTypes.VIBRANT_SOUL_WATER_FLUID_TYPE, SOURCE_VIBRANT_SOUL, FLOWING_VIBRANT_SOUL)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(SoulsBlocks.VIBRANT_SOUL_BLOCK.Block())
            .bucket(SoulsItems.VIBRANT_SOUL_BUCKET);

    public static final ForgeFlowingFluid.Properties BEE_TEARS_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            SoulsFluidTypes.BEE_TEAR_FLUID_TYPE, SOURCE_BEE_TEARS, FLOWING_BEE_TEARS)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(SoulsBlocks.BEE_TEARS_BLOCK.Block())
            .bucket(SoulsItems.BEE_TEARS_BUCKET);



    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
