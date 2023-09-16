package json.jayson.client.renderer;

import json.jayson.Soulslike;
import json.jayson.common.objects.entities.PlayerSoulsEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PlayerSoulsEntityRenderer extends EntityRenderer<PlayerSoulsEntity> {
    public PlayerSoulsEntityRenderer(EntityRendererProvider.Context p_174008_) {
        super(p_174008_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(PlayerSoulsEntity p_114482_) {
        return new ResourceLocation(Soulslike.MODID, "textures/entity/player_souls.png");
    }
}
