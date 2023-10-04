package json.jayson.client.overlay.player_souls;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import json.jayson.client.overlay.EntityTextOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;

public class PlayerSoulsEntityEntries {
    public static class Souls extends EntityTextOverlay.Entry {
        public String text = "";
        @Override
        public void handle() {
            guiGraphics.drawCenteredString(minecraft.font, text, screenWidth / 2, (int)((float)screenHeight / 1.5f), 0xFFFFFFFF);
        }
    }

    public static class Player extends EntityTextOverlay.Entry {
        public String text = "";
        @Override
        public void handle() {
            guiGraphics.drawCenteredString(minecraft.font, text, screenWidth / 2, (int)((float)screenHeight / 1.575f), 0xFFFFFFFF);
        }
    }

    public static class PlayerSkin extends EntityTextOverlay.Entry {
        public String text = "";
        public net.minecraft.world.entity.player.Player player = null;
        @Override
        public void handle() {
            guiGraphics.drawCenteredString(minecraft.font, text, screenWidth / 2, (int)((float)screenHeight / 1.575f), 0xFFFFFFFF);
            if(player != null) {
                int x = (screenWidth + 153) / 2;
                int y = (screenHeight + 130) / 2;
                renderEntityInInventoryFollowsMouse(guiGraphics, x, y, 20, x / 8, y / 8, player);
            }
        }

        public static void renderEntityInInventoryFollowsMouse(GuiGraphics p_282802_, int p_275688_, int p_275245_, int p_275535_, float p_275604_, float p_275546_, LivingEntity p_275689_) {
            float f = (float)Math.atan((double)(p_275604_ / 40.0F));
            float f1 = (float)Math.atan((double)(p_275546_ / 40.0F));
            // Forge: Allow passing in direct angle components instead of mouse position
            renderEntityInInventoryFollowsAngle(p_282802_, p_275688_, p_275245_, p_275535_, f, f1, p_275689_);
        }

        public static void renderEntityInInventoryFollowsAngle(GuiGraphics p_282802_, int p_275688_, int p_275245_, int p_275535_, float angleXComponent, float angleYComponent, LivingEntity p_275689_) {
            float f = angleXComponent;
            float f1 = angleYComponent;
            Quaternionf quaternionf = (new Quaternionf()).rotateZ((float)Math.PI);
            Quaternionf quaternionf1 = (new Quaternionf()).rotateX(f1 * 20.0F * ((float)Math.PI / 180F));
            quaternionf.mul(quaternionf1);
            float f2 = p_275689_.yBodyRot;
            float f3 = p_275689_.getYRot();
            float f4 = p_275689_.getXRot();
            float f5 = p_275689_.yHeadRotO;
            float f6 = p_275689_.yHeadRot;
            p_275689_.yBodyRot = 180.0F + f * 20.0F;
            p_275689_.setYRot(180.0F + f * 40.0F);
            p_275689_.setXRot(-f1 * 20.0F);
            p_275689_.yHeadRot = p_275689_.getYRot();
            p_275689_.yHeadRotO = p_275689_.getYRot();
            renderEntityInInventory(p_282802_, p_275688_, p_275245_, p_275535_, quaternionf, quaternionf1, p_275689_);
            p_275689_.yBodyRot = f2;
            p_275689_.setYRot(f3);
            p_275689_.setXRot(f4);
            p_275689_.yHeadRotO = f5;
            p_275689_.yHeadRot = f6;
        }

        public static void renderEntityInInventory(GuiGraphics p_282665_, int p_283622_, int p_283401_, int p_281360_, Quaternionf p_281880_, @Nullable Quaternionf p_282882_, LivingEntity p_282466_) {
            p_282665_.pose().pushPose();
            p_282665_.pose().translate((double)p_283622_, (double)p_283401_, 50.0D);
            p_282665_.pose().mulPoseMatrix((new Matrix4f()).scaling((float)p_281360_, (float)p_281360_, (float)(-p_281360_)));
            p_282665_.pose().mulPose(p_281880_);
            Lighting.setupForEntityInInventory();
            EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
            if (p_282882_ != null) {
                p_282882_.conjugate();
                entityrenderdispatcher.overrideCameraOrientation(p_282882_);
            }

            entityrenderdispatcher.setRenderShadow(false);
            RenderSystem.runAsFancy(() -> {
                entityrenderdispatcher.render(p_282466_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, p_282665_.pose(), p_282665_.bufferSource(), 15728880);
            });
            p_282665_.flush();
            entityrenderdispatcher.setRenderShadow(true);
            p_282665_.pose().popPose();
            Lighting.setupFor3DItems();
        }

    }
}
