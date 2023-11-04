package json.jayson.common.objects.blocks.soul_drain.def;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.fluids.FluidStack;

public class VibrantSoulDrainRenderer extends SmartBlockEntityRenderer<VibrantSoulDrainBlockEntity> {

    public VibrantSoulDrainRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(VibrantSoulDrainBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
        SmartFluidTankBehaviour tank = be.internalTank;
        if (tank == null)
            return;

        SmartFluidTankBehaviour.TankSegment primaryTank = tank.getPrimaryTank();
        FluidStack fluidStack = primaryTank.getRenderedFluid();
        float level = primaryTank.getFluidLevel()
                .getValue(partialTicks);

        if (!fluidStack.isEmpty() && level != 0) {
            float yMin = 5f / 16f;
            float min = 2f / 16f;
            float max = min + (12 / 16f);
            float yOffset = (7 / 16f) * level;
            ms.pushPose();
            ms.translate(0, yOffset, 0);
            FluidRenderer.renderFluidBox(fluidStack, min, yMin - yOffset, min, max, yMin, max, buffer, ms, light,
                    false);
            ms.popPose();
        }
    }

}
