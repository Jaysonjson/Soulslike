package json.jayson.common.registries;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.Create;
import json.jayson.Soulslike;
import net.minecraft.resources.ResourceLocation;

public class SoulsPartialModels {

    public static final PartialModel
            SOUL_DISPENSER_CHUTE = block("soul_dispenser/chute");

    private static PartialModel block(String path) {
        return new PartialModel(new ResourceLocation(Soulslike.MODID, "block/" + path));
    }

    public static void init() {
    }
}
