package json.jayson.integration.create.scenes;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.utility.Pointing;
import json.jayson.common.registries.SoulsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class SoulSpawnerScene {

    public static void intro(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("soul_spawner", "Spawning Entities");
        scene.configureBasePlate(0, 0, 5);
        scene.showBasePlate();
        scene.idle(5);

        Selection spawner = util.select.position(2, 1, 2);
        BlockPos spawnerPos = util.grid.at(2, 1, 2);
        Selection pipes = util.select.position(2, 2, 2).add(util.select.position(2, 3, 2)).add(util.select.position(2,3,3).add(util.select.position(2,3,4)));
        Selection tank = util.select.fromTo(1, 1, 4, 1, 3, 4);
        Selection largeCog = util.select.position(2, 0, 5);
        Selection kinetics = util.select.fromTo(3, 1, 3, 3, 4, 5);

        ElementLink<WorldSectionElement> drainLink = scene.world.showIndependentSection(spawner, Direction.DOWN);
        scene.world.moveSection(drainLink, util.vector.of(0, 0, 0), 0);
        scene.idle(10);

        scene.overlay.showText(70)
                .text("Soul Spawners can spawn Entities")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(spawnerPos.getCenter());
        scene.idle(80);

        scene.overlay.showText(60)
                .text("Right Click with a filled Soul Vial to set the Entity")
                .attachKeyFrame()
                .independent()
                .pointAt(spawnerPos.getCenter())
                .placeNearTarget();

        scene.idle(5);
        scene.overlay.showControls(new InputWindowElement(util.vector.centerOf(2, 1, 2), Pointing.DOWN).rightClick()
                .withItem(new ItemStack(SoulsItems.SOUL_VIAL.get())), 40);
        scene.idle(70);
        scene.overlay.showText(70)
                .text("Soul Vials can be filled by killing Entities while having the Vial in the Inventory")
                .attachKeyFrame()
                .independent()
                .pointAt(spawnerPos.getCenter())
                .placeNearTarget();
        scene.idle(80);
        scene.world.showSection(largeCog, Direction.UP);
        scene.idle(3);
        scene.world.showSection(kinetics, Direction.NORTH);
        scene.idle(3);
        scene.world.destroyBlock(new BlockPos(1,0, 1));
        scene.world.destroyBlock(new BlockPos(2,0, 1));
        scene.world.destroyBlock(new BlockPos(1,0, 2));
        scene.world.setKineticSpeed(util.select.everywhere(), 128);
        scene.overlay.showText(80)
                .text("Soul Spawners require Rotational Force in the Bottom to work")
                .attachKeyFrame()
                .independent()
                .pointAt(spawnerPos.getCenter())
                .placeNearTarget();
        scene.idle(90);

        scene.world.showSection(tank, Direction.DOWN);
        scene.idle(5);
        scene.world.showSection(pipes, Direction.NORTH);
        scene.overlay.showText(80)
                .text("Soul Spawners also require the Souls fluid in the Top to work")
                .attachKeyFrame()
                .independent()
                .pointAt(spawnerPos.getCenter())
                .placeNearTarget();

        scene.idle(90);
        scene.overlay.showText(50)
                .text("Now after some random Time, the set Entity will spawn")
                .attachKeyFrame()
                .placeNearTarget()
                .pointAt(util.vector.topOf(util.grid.at(2, 1, 2)));
        scene.idle(60);
        scene.markAsFinished();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            scene.idle(random.nextInt(20, 100));
            ElementLink<EntityElement> huskLink = scene.world.createEntity(w -> {
                Sheep player = EntityType.SHEEP.create(w);
                double x = 2.0 +  random.nextDouble() - random.nextDouble() * 2.5d;
                double y = 1.0;
                double z = 2.0 + random.nextDouble() - random.nextDouble() * 2.5d;
                player.setPos(x, y, z);
                player.xo = x;
                player.yo = y;
                player.zo = z;
                float rot = random.nextFloat(365);
                player.yRotO = rot;
                player.setYRot(rot);
                player.yHeadRotO = rot;
                player.yHeadRot = rot;
                return player;
            });
            scene.idle(50);
            scene.world.modifyEntity(huskLink, Entity::discard);
        }
    }

}
