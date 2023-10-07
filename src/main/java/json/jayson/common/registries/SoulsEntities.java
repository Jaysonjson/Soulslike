package json.jayson.common.registries;

import json.jayson.Soulslike;
import json.jayson.common.objects.entities.FireFlyEntity;
import json.jayson.common.objects.entities.PlayerSoulsEntity;
import json.jayson.common.objects.entities.SoulOrbEntity;
import json.jayson.common.objects.entities.ThrownSoulBottle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class SoulsEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Soulslike.MODID);

    public static final RegistryObject<EntityType<PlayerSoulsEntity>> PLAYER_SOULS = ENTITIES.register("player_souls",
            () -> EntityType.Builder.<PlayerSoulsEntity>of(PlayerSoulsEntity::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(Soulslike.MODID, "player_souls").toString())
    );

    public static final RegistryObject<EntityType<FireFlyEntity>> FIRE_FLY = ENTITIES.register("fire_fly",
            () -> EntityType.Builder.<FireFlyEntity>of(FireFlyEntity::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(Soulslike.MODID, "fire_fly").toString())
    );

    public static final RegistryObject<EntityType<SoulOrbEntity>> SOUL_ORB = ENTITIES.register("soul_orb",
            () -> EntityType.Builder.<SoulOrbEntity>of(SoulOrbEntity::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(Soulslike.MODID, "soul_orb").toString())
    );

    public static final RegistryObject<EntityType<ThrownSoulBottle>> SOUL_BOTTLE = ENTITIES.register("soul_bottle",
            () -> EntityType.Builder.<ThrownSoulBottle>of(ThrownSoulBottle::new, MobCategory.MISC)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(Soulslike.MODID, "soul_bottle").toString())
    );

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
