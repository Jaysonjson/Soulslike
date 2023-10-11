package json.jayson.common.objects.blocks.soul_dispenser;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.deployer.DeployerRenderer;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.simibubi.create.foundation.fluid.FluidRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import com.simibubi.create.foundation.utility.AngleHelper;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import json.jayson.common.objects.blocks.soul_drain.SoulDrainBlockEntity;
import json.jayson.common.registries.SoulsPartialModels;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
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
        //DeployerRenderer
        SuperByteBuffer chuteRender = CachedBufferer.partial(SoulsPartialModels.SOUL_DISPENSER_CHUTE, blockState);
        Direction rotateDir = Direction.UP;
        /*switch (blockState.getValue(WrenchableDirectionalBlock.FACING)) {
            case UP -> rotateDir = Direction.SOUTH;
            case DOWN -> rotateDir = Direction.DOWN;
            case EAST -> rotateDir = Direction.WEST;
            case WEST -> rotateDir = Direction.DOWN;
            case NORTH -> rotateDir = Direction.NORTH;
            case SOUTH -> rotateDir = Direction.SOUTH;
        }*/

        transform(chuteRender, blockState, true);
        System.out.println(blockState.getValue(FACING));

        chuteRender.rotateCentered(rotateDir, angle)
                .light(light)
                .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
    }


    private static SuperByteBuffer transform(SuperByteBuffer buffer, BlockState deployerState, boolean axisDirectionMatters) {
        Direction facing = deployerState.getValue(FACING);

           float yRot = AngleHelper.horizontalAngle(facing);
           float xRot =  AngleHelper.verticalAngle(facing);
           float zRot =  AngleHelper.verticalAngle(facing);
        buffer.rotateCentered(Direction.UP, (float) ((yRot) / 180 * Math.PI));
        buffer.rotateCentered(Direction.EAST, (float) ((xRot) / 180 * Math.PI));
        buffer.rotateCentered(Direction.SOUTH, (float) ((zRot) / 180 * Math.PI));
        return buffer;
    }
}
