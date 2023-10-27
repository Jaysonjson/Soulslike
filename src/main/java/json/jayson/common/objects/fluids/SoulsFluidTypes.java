package json.jayson.common.objects.fluids;

import json.jayson.Soulslike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class SoulsFluidTypes {
    public static final ResourceLocation WATER_STILL_RESOURCE = new ResourceLocation("minecraft","block/water_still");
    public static final ResourceLocation WATER_FLOWING_RESOURCE = new ResourceLocation("minecraft","block/water_flow");

    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Soulslike.MODID);

    public static RegistryObject<FluidType> BLOOD_WATER_FLUID_TYPE = register("blood", FluidType.Properties.create().canDrown(true), 0xFFFFFFFF);
    public static RegistryObject<FluidType> SOUL_WATER_FLUID_TYPE = register("soul", FluidType.Properties.create().canDrown(true), 0xFFFFFFFF);

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties, int tintcolor) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RESOURCE, WATER_FLOWING_RESOURCE, WATER_STILL_RESOURCE, tintcolor, new Vector3f(224f / 255f, 56f / 255f, 208f/255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
