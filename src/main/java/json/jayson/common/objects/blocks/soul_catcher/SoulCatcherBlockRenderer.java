package json.jayson.common.objects.blocks.soul_catcher;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

public class SoulCatcherBlockRenderer extends KineticBlockEntityRenderer<SoulCatcherBlockEntity> {

    public SoulCatcherBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRenderOffScreen(SoulCatcherBlockEntity be) {
        return false;
    }

    @Override
    protected void renderSafe(SoulCatcherBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {

        SmartFluidTankBehaviour tank = be.tank;
        if (tank == null)
            return;

        SmartFluidTankBehaviour.TankSegment primaryTank = tank.getPrimaryTank();
        FluidStack fluidStack = primaryTank.getRenderedFluid();
        float level = primaryTank.getFluidLevel()
                .getValue(partialTicks);

        if (!fluidStack.isEmpty() && level != 0) {
            boolean top = fluidStack.getFluid()
                    .getFluidType()
                    .isLighterThanAir();

            level = Math.max(level, 0.175f);
            float min = 2.5f / 16f;
            float max = min + (11 / 16f);
            float yOffset = (11 / 16f) * level;

            ms.pushPose();
            if (!top) ms.translate(0, yOffset, 0);
            else ms.translate(0, max - min, 0);

            FluidRenderer.renderFluidBox(fluidStack,
                    min, min - yOffset, min,
                    max, min, max,
                    buffer, ms, light, false);

            ms.popPose();
        }
    }

    @Override
    protected BlockState getRenderedBlockState(SoulCatcherBlockEntity be) {
        return shaft(getRotationAxisOf(be));
    }
}
