package json.jayson.integration.create.scenes;

import com.simibubi.create.content.fluids.drain.ItemDrainBlockEntity;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.utility.Pointing;
import com.simibubi.create.infrastructure.ponder.scenes.GantryScenes;
import com.simibubi.create.infrastructure.ponder.scenes.fluid.DrainScenes;
import com.simibubi.create.infrastructure.ponder.scenes.fluid.PipeScenes;
import json.jayson.common.objects.blocks.cake_plate.CakePlateEntity;
import json.jayson.common.objects.blocks.soul_drain.SoulDrainBlockEntity;
import json.jayson.common.registries.SoulsFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class SoulDrainScene {

    public static void intro(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("soul_drain", "Getting liquified Souls");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(5);

        Selection drain = util.select.position(2, 1, 2);
        BlockPos drainPos = util.grid.at(2, 1, 2);
        Selection pipes = util.select.position(2, 1, 3).add(util.select.position(2, 1, 4));
        Selection tank = util.select.fromTo(1, 1, 4, 1, 3, 4);
        Selection largeCog = util.select.position(2, 0, 5);
        Selection kinetics = util.select.fromTo(3, 1, 3, 3, 1, 5);

        ElementLink<WorldSectionElement> drainLink = scene.world.showIndependentSection(drain, Direction.DOWN);
        scene.world.moveSection(drainLink, util.vector.of(0, 0, 0), 0);
        scene.idle(10);

        scene.overlay.showText(40)
                .text("Soul Drains can extract Souls from Players")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(drainPos.getCenter());
        scene.idle(50);
        ElementLink<EntityElement> playerLink = scene.world.createEntity(w -> {
            Villager player = EntityType.VILLAGER.create(w);
            //if(Minecraft.getInstance() != null && Minecraft.getInstance().player != null) {
               // player = Minecraft.getInstance().player;
           // } else {
            //}
            Vec3 p = util.vector.topOf(util.grid.at(2, 1, 2));
            player.setPos(p.x, p.y, p.z);
            player.xo = p.x;
            player.yo = p.y;
            player.zo = p.z;
            player.yRotO = 210;
            player.setYRot(210);
            player.yHeadRotO = 210;
            player.yHeadRot = 210;
            return player;
        });

        scene.idle(7);
        scene.world.modifyBlockEntity(drainPos, SoulDrainBlockEntity.class, be -> {
            be.getBehaviour(SmartFluidTankBehaviour.TYPE)
                    .allowInsertion();
            be.getCapability(ForgeCapabilities.FLUID_HANDLER)
                    .ifPresent(fh -> fh.fill(new FluidStack(SoulsFluids.SOURCE_SOUL.get(), 5000), IFluidHandler.FluidAction.EXECUTE));
        });
        scene.idle(30);
        //scene.world.modifyEntity(playerLink, Entity::discard);
        scene.world.showSection(largeCog, Direction.UP);
        scene.idle(3);
        scene.world.showSection(kinetics, Direction.NORTH);
        scene.world.showSection(tank, Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(pipes, Direction.NORTH);
        scene.world.setKineticSpeed(util.select.everywhere(), 64);
        scene.world.propagatePipeChange(util.grid.at(2, 1, 3));
        scene.world.propagatePipeChange(util.grid.at(2, 1, 4));
        scene.idle(20);

        scene.overlay.showText(90)
                .text("Pipe Networks can now pull the fluid from the drains' internal buffer")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(util.vector.topOf(util.grid.at(2, 1, 3)));
        scene.idle(50);
        scene.markAsFinished();
        scene.world.modifyBlockEntity(util.grid.at(1, 1, 4), FluidTankBlockEntity.class, be -> be.getTankInventory()
                .fill(new FluidStack(SoulsFluids.SOURCE_SOUL.get(), 10000), IFluidHandler.FluidAction.EXECUTE));
        scene.idle(50);
    }

}
