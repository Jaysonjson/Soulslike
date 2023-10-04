package json.jayson.common.objects.blocks.soul_entity_spawner;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

public class SoulEntitySpawnerBlockRenderer extends KineticBlockEntityRenderer<SoulEntitySpawnerBlockEntity> {

    public SoulEntitySpawnerBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRenderOffScreen(SoulEntitySpawnerBlockEntity be) {
        return true;
    }

    @Override
    protected void renderSafe(SoulEntitySpawnerBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
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

        if (Backend.canUseInstancing(be.getLevel())) return;

        BlockState blockState = be.getBlockState();

        VertexConsumer vb = buffer.getBuffer(RenderType.solid());

        SuperByteBuffer superBuffer = CachedBufferer.partial(AllPartialModels.SHAFTLESS_COGWHEEL, blockState);
        standardKineticRotationTransform(superBuffer, be, light).renderInto(ms, vb);
    }
}
