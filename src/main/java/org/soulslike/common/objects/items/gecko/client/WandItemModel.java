package org.soulslike.common.objects.items.gecko.client;

import net.minecraft.resources.ResourceLocation;
import org.soulslike.Soulslike;
import org.soulslike.common.objects.items.gecko.WandItem;
import software.bernie.geckolib.model.GeoModel;

public class WandItemModel extends GeoModel<WandItem> {
    @Override
    public ResourceLocation getModelResource(WandItem animatable) {
        return new ResourceLocation(Soulslike.MODID, "geo/oak_wand.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WandItem animatable) {
        return new ResourceLocation(Soulslike.MODID, "textures/item/oak_wand.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WandItem animatable) {
        return new ResourceLocation(Soulslike.MODID, "animations/oak_wand.animation.json");
    }
}
