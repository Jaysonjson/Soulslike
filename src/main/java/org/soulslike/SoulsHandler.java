package org.soulslike;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.FileUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SoulsHandler {
    public static String PATH = FMLPaths.MODSDIR.get().toString() + "/1.20.1/soulslike/souls" + ".json";

    public static String newObject(String json, EntityType entity, int souls) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Souls soulsClass = gson.fromJson(json, Souls.class);
        soulsClass.soulsMap.put(entity.getDescriptionId(), souls);
        return gson.toJson(soulsClass);
    }

    public static Souls loadObject(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Souls souls = gson.fromJson(json, Souls.class);
        return souls;
    }

    public static String loadJson() {
        String content = "{}";
        try {
            content = FileUtils.readFileToString(new File(PATH), "utf-8");
        } catch (IOException ioexc) {
            //System.out.println("IOException - file " + PATH + " not found!");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Souls soulsClass = gson.fromJson("{}", Souls.class);
            //UGLY BOILERPLATE
            soulsClass.soulsMap.put(EntityType.COW.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.PIG.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.CHICKEN.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.FOX.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.WARDEN.getDescriptionId(), 7500);
            soulsClass.soulsMap.put(EntityType.ENDER_DRAGON.getDescriptionId(), 3000);
            soulsClass.soulsMap.put(EntityType.WITHER.getDescriptionId(), 4000);
            soulsClass.soulsMap.put(EntityType.ZOMBIE.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.ZOMBIE_HORSE.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.ZOMBIE_VILLAGER.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.CREEPER.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.SKELETON.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.SKELETON_HORSE.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.WITHER_SKELETON.getDescriptionId(), 5);
            soulsClass.soulsMap.put(EntityType.WITCH.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.ALLAY.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.AXOLOTL.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.BAT.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.BEE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.BLAZE.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.CAMEL.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.CAT.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SPIDER.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.CAVE_SPIDER.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.COD.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.DOLPHIN.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.DONKEY.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.DROWNED.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.ELDER_GUARDIAN.getDescriptionId(), 5);
            soulsClass.soulsMap.put(EntityType.ENDERMAN.getDescriptionId(), 4);
            soulsClass.soulsMap.put(EntityType.ENDERMITE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.EVOKER.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.FROG.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.GHAST.getDescriptionId(), 10);
            soulsClass.soulsMap.put(EntityType.GLOW_SQUID.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.GOAT.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.GLOW_SQUID.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SQUID.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.HOGLIN.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.HUSK.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.HORSE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.ILLUSIONER.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.IRON_GOLEM.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.LLAMA.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.MAGMA_CUBE.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.MULE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.OCELOT.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.PANDA.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.PARROT.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.PIGLIN.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.PIGLIN_BRUTE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.ZOMBIFIED_PIGLIN.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.PILLAGER.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.POLAR_BEAR.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.ZOGLIN.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.WOLF.getDescriptionId(), 2);
            soulsClass.soulsMap.put(EntityType.WANDERING_TRADER.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.VILLAGER.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.VINDICATOR.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.TROPICAL_FISH.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.TRADER_LLAMA.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.TADPOLE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SNIFFER.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.PUFFERFISH.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.RAVAGER.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.RABBIT.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SALMON.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SHEEP.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SHULKER.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.SILVERFISH.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.SLIME.getDescriptionId(), 4);
            soulsClass.soulsMap.put(EntityType.SNOW_GOLEM.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.STRAY.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.STRIDER.getDescriptionId(),3);
            soulsClass.soulsMap.put(EntityType.TURTLE.getDescriptionId(), 1);
            soulsClass.soulsMap.put(EntityType.VEX.getDescriptionId(), 3);
            soulsClass.soulsMap.put(EntityType.PHANTOM.getDescriptionId(), 3);
            saveAsFile(gson.toJson(soulsClass));
        }
        return content;
    }

    public static void saveAsFile(String json) {
        try {
            new File(FMLPaths.MODSDIR.get().toString() + "/1.20.1/soulslike/").mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(PATH);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
            fileOutputStream.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

