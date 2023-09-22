package json.jayson.integration.create.scenes;

import com.simibubi.create.content.contraptions.actors.harvester.HarvesterBlockEntity;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;
import com.simibubi.create.infrastructure.ponder.scenes.FunnelScenes;
import com.simibubi.create.infrastructure.ponder.scenes.fluid.PipeScenes;
import json.jayson.common.objects.blocks.cake_plate.CakePlateEntity;
import json.jayson.common.registries.SoulsItems;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class CakePlateScene {

    public static void spawn(SceneBuilder scene, SceneBuildingUtil util) {
        intro(scene, util);
    }


    private static void intro(SceneBuilder scene, SceneBuildingUtil util) {
        String id = "cake_plate";
        String title = "Using Cakeplates";

        scene.title(id, title);
        scene.showBasePlate();
        scene.idle(10);
        scene.world.showSection(util.select.layersFrom(1), Direction.DOWN);
        scene.idle(20);

        Vec3 topCenter = util.vector.centerOf(util.grid.at(2, 1, 2));
        scene.overlay.showText(80)
                .text("Right Click an empty Cake Plate to place a Block on it")
                .attachKeyFrame()
                .independent()
                .pointAt(topCenter)
                .placeNearTarget();

        scene.idle(40);

        scene.overlay.showControls(new InputWindowElement(util.vector.centerOf(2, 1, 2), Pointing.DOWN).rightClick()
                .withItem(new ItemStack(Blocks.CAKE)), 30);

        scene.world.modifyBlockEntity(util.grid.at(2, 1, 2), CakePlateEntity.class,
                cpt -> {
                    cpt.cakeString = "minecraft:cake";
                    cpt.cakeBlock = Blocks.CAKE;
                    cpt.reloadCakeBlock();
                });

        scene.idle(100);

        scene.overlay.showText(80)
                .text("Right Click again to remove the Block")
                .attachKeyFrame()
                .independent()
                .pointAt(topCenter)
                .placeNearTarget();
        scene.world.modifyBlockEntity(util.grid.at(2, 1, 2), CakePlateEntity.class,
                cpt -> {
                    cpt.cakeString = "minecraft:air";
                    cpt.cakeBlock = Blocks.AIR;
                    cpt.reloadCakeBlock();
                });
        ElementLink<EntityElement> firstCakeItem = scene.world.createItemEntity(util.grid.at(2, 1, 2).getCenter(), util.vector.of(0, 0, 0), new ItemStack(Blocks.CAKE));
        scene.idle(80);
        scene.world.modifyEntity(firstCakeItem, Entity::discard);
        scene.idle(20);
        scene.world.modifyBlockEntity(util.grid.at(2, 1, 2), CakePlateEntity.class,
                cpt -> {
                    cpt.cakeString = "minecraft:cake";
                    cpt.cakeBlock = Blocks.CAKE;
                    cpt.reloadCakeBlock();
                });
        scene.idle(40);
        scene.world.destroyBlock(util.grid.at(2, 1, 2));
        scene.overlay.showText(80)
                .text("Destroying the Cake Plate will also drop the Block")
                .attachKeyFrame()
                .independent()
                .pointAt(topCenter)
                .placeNearTarget();
        scene.world.createItemEntity(util.grid.at(2, 1, 2).getCenter(), util.vector.of(0, 0, 0), new ItemStack(Blocks.CAKE));
        scene.idle(80);
        scene.markAsFinished();
    }


}
