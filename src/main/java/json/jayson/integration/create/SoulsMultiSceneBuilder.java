package json.jayson.integration.create;

import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.foundation.ponder.PonderStoryBoardEntry;
import com.simibubi.create.foundation.ponder.PonderTag;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import json.jayson.Soulslike;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class SoulsMultiSceneBuilder {

    protected Iterable<? extends ItemLike> components;

    protected SoulsMultiSceneBuilder(Iterable<? extends ItemLike> components) {
        this.components = components;
    }

    public SoulsMultiSceneBuilder addStoryBoard(ResourceLocation schematicLocation, PonderStoryBoardEntry.PonderStoryBoard storyBoard) {
        return addStoryBoard(schematicLocation, storyBoard, $ -> {
        });
    }

    public SoulsMultiSceneBuilder addStoryBoard(ResourceLocation schematicLocation, PonderStoryBoardEntry.PonderStoryBoard storyBoard, PonderTag... tags) {
        return addStoryBoard(schematicLocation, storyBoard, sb -> sb.highlightTags(tags));
    }

    public SoulsMultiSceneBuilder addStoryBoard(ResourceLocation schematicLocation, PonderStoryBoardEntry.PonderStoryBoard storyBoard,
                                                                    Consumer<PonderStoryBoardEntry> extras) {
        components.forEach(c -> extras.accept(PonderIndex.addStoryBoard(c, schematicLocation.getPath(), storyBoard)));
        return this;
    }

    public SoulsMultiSceneBuilder addStoryBoard(String schematicPath, PonderStoryBoardEntry.PonderStoryBoard storyBoard) {
        return addStoryBoard(new ResourceLocation(Soulslike.MODID, schematicPath), storyBoard);
    }

    public SoulsMultiSceneBuilder addStoryBoard(String schematicPath, PonderStoryBoardEntry.PonderStoryBoard storyBoard, PonderTag... tags) {
        return addStoryBoard(new ResourceLocation(Soulslike.MODID, schematicPath), storyBoard, tags);
    }

    public SoulsMultiSceneBuilder addStoryBoard(String schematicPath, PonderStoryBoardEntry.PonderStoryBoard storyBoard,
                                                                    Consumer<PonderStoryBoardEntry> extras) {
        return addStoryBoard(new ResourceLocation(Soulslike.MODID, schematicPath), storyBoard, extras);
    }

}
