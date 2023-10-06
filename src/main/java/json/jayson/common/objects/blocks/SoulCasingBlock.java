package json.jayson.common.objects.blocks;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class SoulCasingBlock extends CasingBlock {
    public SoulCasingBlock(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 4;
    }
}
