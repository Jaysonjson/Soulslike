package org.soulslike.common.objects.entities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IRayTracedEntity {

    @OnlyIn(Dist.CLIENT)
    void onMouseRayTraceHit();
}
