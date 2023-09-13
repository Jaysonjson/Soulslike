package org.soulslike.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.soulslike.Soulslike;
import org.soulslike.common.objects.entities.PlayerSoulsEntity;


public class SoulsEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Soulslike.MODID);

    public static final RegistryObject<EntityType<PlayerSoulsEntity>> PLAYER_SOULS = ENTITIES.register("player_souls",
            () -> EntityType.Builder.<PlayerSoulsEntity>of(PlayerSoulsEntity::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(Soulslike.MODID, "player_souls").toString())
    );

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
