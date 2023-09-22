package json.jayson.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.equipment.zapper.terrainzapper.WorldshaperItem;
import com.simibubi.create.foundation.render.SuperRenderTypeBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import json.jayson.integration.create.VoxelShapeOutline;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class BlockOutlineHelper implements IGuiOverlay {

    public VoxelShapeOutline outline = new VoxelShapeOutline(new AABB(0, 0, 0, 0, 0, 0));

    @Override
    public void render(ForgeGui gui, GuiGraphics guiGraphics, float partialTick, int screenWidth, int screenHeight) {
        if(outline != null) {

        }
    }

    public void render(PoseStack ms, SuperRenderTypeBuffer buffer, Vec3 camera, float pt) {
        if(outline != null) {
            ms.pushPose();
            outline.render(ms, buffer, camera, pt);
            ms.popPose();
        }
    }
}
