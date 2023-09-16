package json.jayson.client.screens;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import json.jayson.Soulslike;
import json.jayson.client.data.ClientPlayerData;
import json.jayson.client.data.ClientSoulsData;
import json.jayson.common.capabilities.PlayerLevel;
import json.jayson.common.capabilities.PlayerLevelProvider;
import json.jayson.common.capabilities.PlayerSoulsProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.GiveCommand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import javax.annotation.Nullable;

import static net.minecraft.client.gui.screens.inventory.AbstractContainerScreen.INVENTORY_LOCATION;

public class LevelUpScreen extends Screen {
    public static final ResourceLocation BACKGROUND = new ResourceLocation(Soulslike.MODID, "textures/gui/container/level_up.png");
    int level_increase = 12;
    public LevelUpScreen() {
        super(Component.literal("Test"));
    }

    @Override
    public void render(@NotNull GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {
        drawHalfSizeString(p_281549_, "Level " + ClientPlayerData.getLevel() + " --> " + (ClientPlayerData.getLevel() + level_increase), -353, 15);
        drawHalfSizeString(p_281549_, "Souls Held " + ClientSoulsData.getSouls(), -353, 35);
        drawHalfSizeString(p_281549_, "Souls Needed " + PlayerLevel.requiredSouls(ClientPlayerData.getLevel() + level_increase), -353, 55);
        long newSouls = (ClientSoulsData.getSouls() - PlayerLevel.requiredSouls(ClientPlayerData.getLevel() + level_increase));
        if(newSouls > 0) {
            drawHalfSizeString(p_281549_, newSouls + "", -353, 75, 0x43ff64);
        } else {
            drawHalfSizeString(p_281549_,  newSouls + "", -353, 75, 0xff4364);
        }
        p_281549_.drawCenteredString(minecraft.font, this.minecraft.player.getName().getString(), (width + 50) / 2, (height - 150) / 2, 0xFFFFFFFF);
        p_281549_.blit(BACKGROUND, (width - 255) / 2, (height - 165) / 2,0,0, 255, 165);
        renderEntityInInventoryFollowsMouse(p_281549_, (width - 180) / 2, (height - 5) / 2, 30, (float)(width / 4) - p_281550_, (float)(height / 2 - 50) - p_282878_, this.minecraft.player);

    }

    public void drawHalfSizeString(GuiGraphics p_281549_, String text, int x, int y, int color ) {
        p_281549_.pose().pushPose();
        p_281549_.pose().scale(0.5f, 0.5f, 0.5f);
        int scaledWidth = width * 2;
        int scaledHeight = height * 2;
        p_281549_.drawCenteredString(minecraft.font, text, (scaledWidth + x) / 2, (scaledHeight + y) / 2, color);
        p_281549_.pose().popPose();
    }

    public void drawHalfSizeString(GuiGraphics p_281549_, String text, int x, int y) {
       drawHalfSizeString(p_281549_, text, x, y, 0xFFFFFFFF);
    }
    public void drawHalfSizeStringRight(GuiGraphics p_281549_, String text, int x, int y) {
        p_281549_.pose().pushPose();
        p_281549_.pose().scale(0.5f, 0.5f, 0.5f);
        int scaledWidth = width * 2;
        int scaledHeight = height * 2;
        p_281549_.drawCenteredString(minecraft.font, text, (scaledWidth + (x - minecraft.font.width(text))) / 2, (scaledHeight + y) / 2, 0xFFFFFFFF);
        p_281549_.pose().popPose();
    }

    public void drawHalfSizeStringLeft(GuiGraphics p_281549_, String text, int x, int y, int color) {
        p_281549_.pose().pushPose();
        p_281549_.pose().scale(0.5f, 0.5f, 0.5f);
        int scaledWidth = width * 2;
        int scaledHeight = height * 2;
        p_281549_.drawCenteredString(minecraft.font, text, (scaledWidth + (x + minecraft.font.width(text))) / 2, (scaledHeight + y) / 2, color);
        p_281549_.pose().popPose();
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
