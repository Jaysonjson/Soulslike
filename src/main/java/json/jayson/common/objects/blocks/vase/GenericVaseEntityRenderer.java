package json.jayson.common.objects.blocks.vase;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GenericVaseEntityRenderer implements BlockEntityRenderer<GenericVaseEntity> {

    public GenericVaseEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(GenericVaseEntity mudVaseEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        mudVaseEntity.unpackLootTable(null);

        int i = 1;
        for (ItemStack renderItem : mudVaseEntity.renderItems) {
            poseStack.pushPose();
            poseStack.translate(0.5f, 0.1f * i, 0.5f);
            poseStack.translate(mudVaseEntity.offset.x, mudVaseEntity.offset.y, mudVaseEntity.offset.z);
            poseStack.scale(0.37f, 0.37f, 0.37f);
            poseStack.mulPose(Axis.XP.rotationDegrees(90));
            itemRenderer.renderStatic(renderItem, ItemDisplayContext.GUI, packedLight, packedOverlay, poseStack, bufferSource, mudVaseEntity.getLevel(), 1);
            poseStack.popPose();
            ++i;
            //if(i == mudVaseEntity.getContainerSize()) break;
        }
    }
}
