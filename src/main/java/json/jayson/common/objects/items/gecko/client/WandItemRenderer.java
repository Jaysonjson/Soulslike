package json.jayson.common.objects.items.gecko.client;

import json.jayson.common.objects.items.gecko.WandItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WandItemRenderer extends GeoItemRenderer<WandItem> {
    public WandItemRenderer() {
        super(new WandItemModel());
    }
}
