package json.jayson.integration.create.scenes;

import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import com.simibubi.create.foundation.ponder.element.EntityElement;
import com.simibubi.create.foundation.ponder.element.InputWindowElement;
import com.simibubi.create.foundation.ponder.element.WorldSectionElement;
import com.simibubi.create.foundation.ponder.instruction.EmitParticlesInstruction;
import com.simibubi.create.foundation.utility.Pointing;

import com.simibubi.create.infrastructure.ponder.scenes.FunnelScenes;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.common.registries.SoulsItems;
import json.jayson.common.registries.SoulsVillagers;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.WalkAnimationState;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class GemStationScene {

    public static void villager(SceneBuilder scene, SceneBuildingUtil util) {
        intro(scene, util);
    }


    private static void intro(SceneBuilder scene, SceneBuildingUtil util) {
        String id = "gem_station_villager";
        String title = "Getting a Gem Cutter Villager";
        scene.title(id, title);
        scene.showBasePlate();
        ElementLink<EntityElement> villagerLink = scene.world.createEntity(w -> {
            Villager entity = EntityType.VILLAGER.create(w);
            Vec3 p = util.vector.topOf(util.grid.at(2, 0, 2));
            entity.setPos(p.x, p.y, p.z);
            entity.xo = p.x;
            entity.yo = p.y;
            entity.zo = p.z;
            entity.yRotO = 210;
            entity.setYRot(210);
            entity.yHeadRotO = 210;
            entity.yHeadRot = 210;
            return entity;
        });
        scene.idle(10);
        scene.world.showSection(util.select.layersFrom(1), Direction.DOWN);
        scene.idle(25);
        Vec3 topCenter = util.vector.centerOf(util.grid.at(2, 2, 2));
        scene.world.setBlock(util.grid.at(2, 1, 1), SoulsBlocks.GEM_STATION.getBlock().defaultBlockState(), true);

        scene.overlay.showText(80)
                .text("A Gem Station will turn a Villager into a Gem Cutter")
                .attachKeyFrame()
                .independent()
                .pointAt(util.vector.centerOf(util.grid.at(2, 1, 1)))
                .placeNearTarget();

        /*ElementLink<WorldSectionElement> independentSection =
                scene.world.showIndependentSection(util.select.fromTo(2, -1, 2, 2, 1, 2), Direction.UP);*/

        scene.idle(20);

        scene.world.modifyEntity(villagerLink, entity -> {
            entity.getEntityData().get(Villager.DATA_VILLAGER_DATA).setProfession(SoulsVillagers.GEM_CUTTER.get());
        });

        scene.effects.emitParticles(util.grid.at(2, 2, 1).getCenter(),EmitParticlesInstruction.Emitter.simple(ParticleTypes.GLOW, Vec3.ZERO), 3, 40);
        scene.idle(60);

        scene.overlay.showText(80)
                .text("This Gem Cutter will now trade you Gems")
                .attachKeyFrame()
                .independent()
                .pointAt(topCenter)
                .placeNearTarget();
        scene.idle(100);
        scene.markAsFinished();
    }

	
}
