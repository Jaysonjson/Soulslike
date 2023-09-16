package json.jayson.common.objects.blocks.fire_altar;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Blocks;

public class FireAltarEntityRenderer implements BlockEntityRenderer<FireAltarEntity> {

    public FireAltarEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(FireAltarEntity fireAltarEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if(!fireAltarEntity.getBlockState().getValue(FireAltar.UNLIGHTED)) {
            BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
            poseStack.pushPose();
            poseStack.translate(0.17f, 1f, 0.17f);
            poseStack.scale(0.65f, 0.65f, 0.65f);
            if(fireAltarEntity.soulFlame) {
                blockRenderDispatcher.renderSingleBlock(Blocks.SOUL_FIRE.defaultBlockState(), poseStack, bufferSource, packedLight, packedOverlay);
            } else {
                blockRenderDispatcher.renderSingleBlock(Blocks.FIRE.defaultBlockState(), poseStack, bufferSource, packedLight, packedOverlay);
            }
            poseStack.popPose();
        }
    }
}