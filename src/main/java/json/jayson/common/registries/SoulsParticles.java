package json.jayson.common.registries;

import json.jayson.Soulslike;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulsParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Soulslike.MODID);

    public static final RegistryObject<SimpleParticleType> BLOSSOM_ROD = PARTICLES.register("blossom_rod", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }

}
