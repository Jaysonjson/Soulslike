package json.jayson.common.objects.blocks.soul_dispenser;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.KineticDebugger;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.deployer.DeployerRenderer;
import com.simibubi.create.content.kinetics.drill.DrillRenderer;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import com.simibubi.create.foundation.utility.Color;
import json.jayson.common.objects.blocks.soul_drain.SoulDrainBlockEntity;
import json.jayson.common.registries.SoulsPartialModels;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Supplier;

import static com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock.AXIS_ALONG_FIRST_COORDINATE;
import static com.simibubi.create.content.kinetics.base.DirectionalKineticBlock.FACING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class SoulDispenserRenderer extends SmartBlockEntityRenderer<SoulDispenserBlockEntity> {

    public SoulDispenserRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(SoulDispenserBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
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
            float max = min + (12 / 18f);
            float yOffset = (7 / 16f) * level;
            ms.pushPose();
            ms.translate(0, yOffset, 0);
            FluidRenderer.renderFluidBox(fluidStack, min, yMin - yOffset, min, max, yMin, max, buffer, ms, light,
                    false);
            ms.popPose();
        }

        float speed = 64;
        float time = AnimationTickHolder.getRenderTime(be.getLevel());
        float angle = ((time * speed * 6 / 10f) % 360) / 180 * (float) Math.PI;
        BlockState blockState = be.getBlockState();
        SuperByteBuffer chuteRender = CachedBufferer.partial(SoulsPartialModels.SOUL_DISPENSER_CHUTE, blockState);
        //rotateChute(chuteRender, be, angle, light).renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        transform(chuteRender, blockState).light(light).renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        //chuteRender.rotateCentered(rotateDir, angle)
                //.light(light)
                //.renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
    }


    public static SuperByteBuffer rotateChute(SuperByteBuffer buffer, SoulDispenserBlockEntity be, float angle, int light) {
        buffer.light(light);
        buffer.rotateCentered(Direction.get(Direction.AxisDirection.NEGATIVE, be.getBlockState().getValue(FACING).getAxis()), angle);
        return buffer;
    }


    private static SuperByteBuffer transform(SuperByteBuffer buffer, BlockState deployerState) {
        Direction facing = deployerState.getValue(FACING);
        float yRot = AngleHelper.horizontalAngle(facing);
        float xRot = facing == Direction.UP ? 270 : facing == Direction.DOWN ? 90 : 0;
        float zRot = 90;
        buffer.rotateCentered(Direction.UP, (float) ((yRot) / 180 * Math.PI));
        buffer.rotateCentered(Direction.EAST, (float) ((xRot) / 180 * Math.PI));
        buffer.rotateCentered(Direction.SOUTH, (float) ((zRot) / 180 * Math.PI));
        return buffer;
    }
}
