package org.soulslike.common.objects.fluids;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;
import org.soulslike.Soulslike;

public class SoulsFluidTypes {
    public static final ResourceLocation WATER_STILL_RESOURCE = new ResourceLocation("minecraft","block/water_still");
    public static final ResourceLocation WATER_FLOWING_RESOURCE = new ResourceLocation("minecraft","block/water_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Soulslike.MODID);

    public static RegistryObject<FluidType> BLOOD_WATER_FLUID_TYPE = register("blood", FluidType.Properties.create().canDrown(true));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RESOURCE, WATER_FLOWING_RESOURCE, WATER_STILL_RESOURCE, 0x1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f/255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}