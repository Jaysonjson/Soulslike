package json.jayson.common.registries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.mixer.MixerInstance;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import json.jayson.Soulslike;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlockEntity;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlockInstance;
import json.jayson.common.objects.blocks.soul_entity_spawner.SoulEntitySpawnerBlockRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import json.jayson.common.objects.blocks.bee_statue.SoulsBeeStatueEntity;
import json.jayson.common.objects.blocks.cake_plate.CakePlateEntity;
import json.jayson.common.objects.blocks.crate.GenericCrateEntity;
import json.jayson.common.objects.blocks.fire_altar.FireAltarEntity;
import json.jayson.common.objects.blocks.fox_altar.SoulsFoxAltarEntity;
import json.jayson.common.objects.blocks.vase.GenericVaseEntity;

import static com.simibubi.create.Create.REGISTRATE;
import static json.jayson.Soulslike.SOULS_REGISTRATE;

public class SoulsBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Soulslike.MODID);

    public static final RegistryObject<BlockEntityType<SoulsBeeStatueEntity>> BEE_STATUE =
            BLOCK_ENTITIES.register("bee_statue_entity", () ->
                    BlockEntityType.Builder.of(SoulsBeeStatueEntity::new,
                            SoulsBlocks.BEE_STATUE.getBlock()).build(null));
    public static final RegistryObject<BlockEntityType<SoulsFoxAltarEntity>> FOX_ALTAR =
            BLOCK_ENTITIES.register("fox_altar_entity", () ->
                    BlockEntityType.Builder.of(SoulsFoxAltarEntity::new,
                            SoulsBlocks.FOX_ALTAR.getBlock()).build(null));
    public static final RegistryObject<BlockEntityType<GenericVaseEntity>> GENERIC_VASE =
            BLOCK_ENTITIES.register("generic_vase_entity", () ->
                    BlockEntityType.Builder.of(GenericVaseEntity::new,
                            SoulsBlocks.MUD_VASE.getBlock(), SoulsBlocks.NETHER_BRICKS_VASE.getBlock(), SoulsBlocks.STONE_VASE.getBlock(), SoulsBlocks.QUARTZ_VASE.getBlock()).build(null));
    public static final RegistryObject<BlockEntityType<GenericCrateEntity>> GENERIC_CRATE =
            BLOCK_ENTITIES.register("generic_crate_entity", () ->
                    BlockEntityType.Builder.of(GenericCrateEntity::new,
                            SoulsBlocks.OAK_CRATE.getBlock(), SoulsBlocks.BIRCH_CRATE.getBlock(), SoulsBlocks.JUNGLE_CRATE.getBlock(), SoulsBlocks.SPRUCE_CRATE.getBlock(),
                            SoulsBlocks.DARK_OAK_CRATE.getBlock(), SoulsBlocks.ACACIA_CRATE.getBlock(), SoulsBlocks.MANGROVE_CRATE.getBlock(), SoulsBlocks.CRIMSON_CRATE.getBlock(),
                            SoulsBlocks.WARPED_CRATE.getBlock(),
                            SoulsBlocks.OAK_LOG_CRATE.getBlock()).build(null));

    public static final RegistryObject<BlockEntityType<CakePlateEntity>> CAKE_PLATE =
            BLOCK_ENTITIES.register("cake_plate", () ->
                    BlockEntityType.Builder.of(CakePlateEntity::new,
                            SoulsBlocks.CAKE_PLATE.getBlock(), SoulsBlocks.QUARTZ_CAKE_PLATE.getBlock()).build(null));

    public static final RegistryObject<BlockEntityType<FireAltarEntity>> FIRE_ALTAR =
            BLOCK_ENTITIES.register("fire_altar", () ->
                    BlockEntityType.Builder.of(FireAltarEntity::new,
                            SoulsBlocks.FIRE_ALTAR.getBlock()).build(null));

    public static final BlockEntityEntry<SoulEntitySpawnerBlockEntity> SOUL_ENTITY_SPAWNER = SOULS_REGISTRATE
            .blockEntity("soul_entity_spawner", SoulEntitySpawnerBlockEntity::new)
            .instance(() -> SoulEntitySpawnerBlockInstance::new)
            .validBlocks(SoulsBlocks.SOUL_ENTITY_SPAWNER)
            .renderer(() -> SoulEntitySpawnerBlockRenderer::new)
            .register();

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
