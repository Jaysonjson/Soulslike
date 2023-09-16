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


    public static final ForgeFlowingFluid.Properties BLOOD_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            SoulsFluidTypes.BLOOD_WATER_FLUID_TYPE, SOURCE_BLOOD, FLOWING_BLOOD)
            .slopeFindDistance(2).levelDecreasePerBlock(2).block(SoulsBlocks.BLOOD_BLOCK.Block())
            .bucket(SoulsItems.BLOOD_BUCKET);


    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
