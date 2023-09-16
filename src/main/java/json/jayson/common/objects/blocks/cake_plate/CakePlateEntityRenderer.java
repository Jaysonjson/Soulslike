package json.jayson.common.objects.blocks.cake_plate;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class CakePlateEntityRenderer implements BlockEntityRenderer<CakePlateEntity> {

    public CakePlateEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(CakePlateEntity cakePlateEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
            BlockRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
            poseStack.pushPose();
            poseStack.translate(0.27f, 0.23f, 0.27f);
            poseStack.scale(0.45f, 0.45f, 0.45f);
            if(cakePlateEntity.cakeBlock != null) {
                blockRenderDispatcher.renderSingleBlock(cakePlateEntity.cakeBlock.defaultBlockState(), poseStack, bufferSource, packedLight, packedOverlay);
            }
            poseStack.popPose();
    }
}
