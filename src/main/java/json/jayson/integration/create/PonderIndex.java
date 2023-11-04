package json.jayson.integration.create;

import java.util.Arrays;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderRegistry;
import com.simibubi.create.foundation.ponder.PonderStoryBoardEntry;
import com.simibubi.create.foundation.ponder.PonderTag;

import json.jayson.Soulslike;
import json.jayson.common.registries.SoulsBlocks;
import json.jayson.common.registries.SoulsFluids;
import json.jayson.common.registries.SoulsItems;
import json.jayson.integration.create.scenes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public class PonderIndex {

    public static PonderRegistrationHelper HELPER = new PonderRegistrationHelper(Soulslike.MODID);

    public static void add() {
        addStoryBoard(SoulsItems.SAPPHIRE_SHARD.get(), "sapphire_seagrass/spawn", SapphireSeaGrassScene::spawn);
        addStoryBoard(SoulsBlocks.SAPPHIRE_SEAGRASS.getItem(), "sapphire_seagrass/spawn", SapphireSeaGrassScene::spawn);
        addStoryBoard(SoulsBlocks.CAKE_PLATE.getItem(), "cake_plate/intro", CakePlateScene::spawn);
        addStoryBoard(SoulsBlocks.QUARTZ_CAKE_PLATE.getItem(), "cake_plate/intro", CakePlateScene::spawn);
        addStoryBoard(SoulsBlocks.GEM_STATION.getItem(), "gem_station/gemcutter_villager", GemStationScene::villager);
        addStoryBoard(SoulsBlocks.VIBRANT_SOUL_DRAIN.get(), "soul_drain", SoulDrainScene::intro);
        addStoryBoard(SoulsBlocks.SOUL_DRAIN.get(), "soul_drain", SoulDrainScene::intro);
        addStoryBoard(SoulsBlocks.SOUL_BLOCK.getItem(), "soul_drain", SoulDrainScene::intro);
        addStoryBoard(SoulsItems.SOUL_BUCKET.get(), "soul_drain", SoulDrainScene::intro);
        addStoryBoard(SoulsBlocks.VIBRANT_SOUL_ENTITY_SPAWNER.get(), "soul_spawner", SoulSpawnerScene::intro);
        forComponents(SoulsBlocks.RUBY_ROSE.getItem())
                .addStoryBoard("ruby_rose/spawn", RubyRoseScene::spawn)
                .addStoryBoard("ruby_rose/temple_0", RubyRoseScene::spawn)
                .addStoryBoard("gem_station/gemcutter_villager", GemStationScene::villager)
        ;
        forComponents(SoulsItems.RUBY_SHARD.get())
                .addStoryBoard("ruby_rose/spawn", RubyRoseScene::spawn)
                .addStoryBoard("ruby_rose/temple_0", RubyRoseScene::spawn)
                .addStoryBoard("gem_station/gemcutter_villager", GemStationScene::villager)
        ;
    }

    public static SoulsMultiSceneBuilder forComponents(ItemLike... components) {
        return new SoulsMultiSceneBuilder(Arrays.asList(components));
    }

    public static PonderStoryBoardEntry addStoryBoard(ItemLike component,
                                               String schematicLocation, PonderStoryBoardEntry.PonderStoryBoard storyBoard, PonderTag... tags) {
        PonderStoryBoardEntry entry = HELPER.createStoryBoardEntry(storyBoard, new ResourceLocation(Soulslike.MODID,schematicLocation), component.asItem().builtInRegistryHolder().key().location());
        entry.highlightTags(tags);
        PonderRegistry.addStoryBoard(entry);
        return entry;
    }

}
