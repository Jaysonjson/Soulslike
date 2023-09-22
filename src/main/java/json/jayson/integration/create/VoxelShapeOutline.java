package json.jayson.integration.create;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.outliner.AABBOutline;
import com.simibubi.create.foundation.outliner.Outline;
import com.simibubi.create.foundation.render.SuperRenderTypeBuffer;
import com.simibubi.create.foundation.utility.VoxelShaper;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Vector4f;

public class VoxelShapeOutline extends AABBOutline {
    public VoxelShape shape = Shapes.empty();

    public VoxelShapeOutline(AABB bb) {
        super(bb);
    }

    @Override
    public void render(PoseStack ms, SuperRenderTypeBuffer buffer, Vec3 camera, float pt) {
        if(shape != null) {
            for (AABB aabb : shape.toAabbs()) {
                bb = aabb;
                params.loadColor(colorTemp);
                Vector4f color = colorTemp;
                renderBox(ms, buffer, camera, aabb, color, LightTexture.FULL_BRIGHT, true);
            }
        }
    }
}
