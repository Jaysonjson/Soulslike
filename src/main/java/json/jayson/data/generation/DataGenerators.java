package json.jayson.data.generation;

import json.jayson.Soulslike;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Soulslike.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        ModItemModelProvider modItemModelProvider = new ModItemModelProvider(packOutput, existingFileHelper);
        ModBlockStateProvider blockStateProvider = new ModBlockStateProvider(packOutput, existingFileHelper);
        ModBlockTagProvider blockTagProvider = new ModBlockTagProvider(packOutput, event.getLookupProvider(), existingFileHelper);
        ModBlockDataGeneration.register();

        generator.addProvider(true, modItemModelProvider);
        generator.addProvider(true, blockStateProvider);
        generator.addProvider(true, ModLootTableProvider.create(packOutput));
        generator.addProvider(true, blockTagProvider);
        generator.addProvider(true, new ModRecipeProvider(packOutput));
        generator.addProvider(true, new ModItemTagProvider(packOutput, event.getLookupProvider(), blockTagProvider.contentsGetter(), existingFileHelper));

       /* try {
            Path source = Paths.get("src/generated/resources/data/minecraft/tags/blocks/pickaxe.json");
            Path dest = Paths.get("src/generated/resources/data/minecraft/tags/blocks/mineable/pickaxe.json");
            Files.move(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
