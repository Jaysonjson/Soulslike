package json.jayson.helpers;

import json.jayson.common.Data;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class SoulsUtil {

    public static String getEntitySoulsId(Entity entity) {
        return entity.getType().getDescriptionId();
    }

    public static boolean entitySoulsExist(Entity entity) {
        return Data.SOULS_MAP.containsKey(getEntitySoulsId(entity));
    }

    public static boolean entitySoulsExist(String entity) {
        return Data.SOULS_MAP.containsKey(entity);
    }

    public static int getEntitySouls(Entity entity) {
        if(entitySoulsExist(entity)) {
            return Data.SOULS_MAP.get(getEntitySoulsId(entity));
        }
        return 0;
    }

    public static int getEntitySouls(String entity) {
        if(entitySoulsExist(entity)) {
            return Data.SOULS_MAP.get(entity);
        }
        return 0;
    }

    @Nullable
    public static EntityType<?> getEntity(String id) {
        for (EntityType<?> entityType : ForgeRegistries.ENTITY_TYPES) {
            if(entityType.getDescriptionId().equals(id)) return entityType;
        }
        return null;
    }


}
