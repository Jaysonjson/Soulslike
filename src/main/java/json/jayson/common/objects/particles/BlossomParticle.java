package json.jayson.common.objects.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlossomParticle extends TextureSheetParticle {

    protected BlossomParticle(ClientLevel p_108323_, double p_108324_, double p_108325_, double p_108326_) {
        super(p_108323_, p_108324_, p_108325_, p_108326_);
    }

    public BlossomParticle(ClientLevel p_108328_, double p_108329_, double p_108330_, double p_108331_, double p_108332_, double p_108333_, double p_108334_) {
        super(p_108328_, p_108329_, p_108330_, p_108331_, p_108332_, p_108333_, p_108334_);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public void move(double p_106550_, double p_106551_, double p_106552_) {
        this.setBoundingBox(this.getBoundingBox().move(p_106550_, p_106551_, p_106552_));
        this.setLocationFromBoundingbox();
    }

    public static Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z,
                                          double xd, double yd, double zd) {
        return new BlossomParticle(level,x,y,z,xd,yd,zd);
    }
}
