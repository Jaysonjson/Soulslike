package json.jayson.common.registries;

import com.google.common.collect.ImmutableSet;

import json.jayson.Soulslike;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulsVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, Soulslike.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, Soulslike.MODID);
    
    public static final RegistryObject<PoiType>
    GEM_POI = POI_TYPES.register("gem_poi", () -> new PoiType(
    		ImmutableSet.copyOf(SoulsBlocks.RUBY_BLOCK.getBlock().getStateDefinition().getPossibleStates()), 1, 1));
    
    public static final RegistryObject<VillagerProfession> 
    GEM_CUTTER =  VILLAGER_PROFESSIONS.register("gemcutter", () -> 
        new VillagerProfession("gemcutter", holder -> holder.get() == GEM_POI.get(), holder -> holder.get() == GEM_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
    
    
    public static void registerBus(IEventBus bus) {
    	POI_TYPES.register(bus);
    	VILLAGER_PROFESSIONS.register(bus);
    }
}
