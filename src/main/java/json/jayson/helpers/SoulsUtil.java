package json.jayson.helpers;

import json.jayson.common.Data;
import net.minecraft.world.entity.Entity;

public class SoulsUtil {

    public static String getEntitySoulsId(Entity entity) {
        return entity.getType().getDescriptionId();
    }

    public static boolean entitySoulsExist(Entity entity) {
        return Data.SOULS_MAP.containsKey(getEntitySoulsId(entity));
    }

    public static int getEntitySouls(Entity entity) {
        if(entitySoulsExist(entity)) {
            return Data.SOULS_MAP.get(getEntitySoulsId(entity));
        }
        return 0;
    }

}
