package json.jayson.common.objects.features;

import com.mojang.serialization.Codec;
import json.jayson.common.registries.SoulsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SeagrassFeature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class SapphireSeaGrassFeature extends Feature<ProbabilityFeatureConfiguration> {

    public SapphireSeaGrassFeature(Codec<ProbabilityFeatureConfiguration> p_66768_) {
        super(p_66768_);
    }

    public boolean place(FeaturePlaceContext<ProbabilityFeatureConfiguration> p_160318_) {
        boolean flag = false;
        RandomSource randomsource = p_160318_.random();
        WorldGenLevel worldgenlevel = p_160318_.level();
        BlockPos blockpos = p_160318_.origin();
        int i = randomsource.nextInt(8) - randomsource.nextInt(8);
        int j = randomsource.nextInt(8) - randomsource.nextInt(8);
        int k = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + i, blockpos.getZ() + j);
        BlockPos blockpos1 = new BlockPos(blockpos.getX() + i, k, blockpos.getZ() + j);
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER)) {
            BlockState blockstate = SoulsBlocks.SAPPHIRE_SEAGRASS.getBlock().defaultBlockState();
            if (blockstate.canSurvive(worldgenlevel, blockpos1)) {
                    worldgenlevel.setBlock(blockpos1, blockstate, 2);
                flag = true;
            }
        }
        return flag;
    }

}
