package json.jayson.integration.create.scenes;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.utility.Pointing;

import json.jayson.common.registries.SoulsItems;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class SapphireSeaGrassScene {

    public static void spawn(SceneBuilder scene, SceneBuildingUtil util) {
        intro(scene, util);
    }


    private static void intro(SceneBuilder scene, SceneBuildingUtil util) {
        String id = "sapphire_seagrass";
        String title = "Getting Sapphire Shards";
        scene.title(id, title);
        scene.showBasePlate();
        scene.idle(10);
        scene.world.showSection(util.select.layersFrom(1), Direction.DOWN);
        scene.idle(20);
        Vec3 topCenter = util.vector.centerOf(util.grid.at(2, 1, 2));
        scene.overlay.showText(80)
                .text("Sapphire Seagrass will spawn Randomly in Water")
                .attachKeyFrame()
                .independent()
                .pointAt(topCenter)
                .placeNearTarget();
        scene.idle(100);
        scene.overlay.showText(80)
                .text("Breaking this Seagrass will have a chance of 10 Percentage to drop a Sapphire Shard")
                .attachKeyFrame()
                .independent()
                .pointAt(topCenter)
                .placeNearTarget();
        scene.overlay.showControls(new InputWindowElement(util.vector.centerOf(2, 1, 2), Pointing.DOWN).leftClick(), 15);

        scene.world.destroyBlock(util.grid.at(2, 1, 2));
        scene.world.createItemEntity(util.grid.at(2, 1, 2).getCenter(), util.vector.of(0, 0, 0), new ItemStack(SoulsItems.SAPPHIRE_SHARD.get()));
        scene.idle(100);
        scene.markAsFinished();
    }


}
