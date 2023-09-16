package json.jayson.common.listener.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import json.jayson.SoulsMap;
import json.jayson.Soulslike;
import json.jayson.common.Data;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class SoulsMapReloadListener extends SimpleJsonResourceReloadListener {

    public SoulsMapReloadListener(Gson gson, String path) {
        super(gson, path);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        for(Map.Entry<ResourceLocation, JsonElement> entry : jsonElementMap.entrySet()) {
            ResourceLocation resourcelocation = entry.getKey();
            SoulsMap item = Soulslike.GSON.fromJson(entry.getValue(), SoulsMap.class);
            if(acceptProtocol(item)) Data.SOULS_MAP.putAll(item.SOULS_MAP);
        }
    }

    public boolean acceptProtocol(SoulsMap soulsMap) {
        return true;
    }
}
