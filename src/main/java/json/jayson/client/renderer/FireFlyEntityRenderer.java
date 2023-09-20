package json.jayson.client.renderer;

import json.jayson.Soulslike;
import json.jayson.common.objects.entities.FireFlyEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FireFlyEntityRenderer extends EntityRenderer<FireFlyEntity> {
    public FireFlyEntityRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(FireFlyEntity p_114482_) {
        return new ResourceLocation(Soulslike.MODID, "textures/entity/player_souls.png");
    }
}
