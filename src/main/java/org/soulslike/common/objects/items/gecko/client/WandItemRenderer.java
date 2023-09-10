package org.soulslike.common.objects.items.gecko.client;

import org.soulslike.common.objects.items.gecko.WandItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WandItemRenderer extends GeoItemRenderer<WandItem> {
    public WandItemRenderer() {
        super(new WandItemModel());
    }
}
