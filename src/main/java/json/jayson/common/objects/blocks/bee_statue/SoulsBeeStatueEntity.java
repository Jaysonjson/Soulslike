package json.jayson.common.objects.blocks.bee_statue;

import com.simibubi.create.Create;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlock;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.simibubi.create.content.kinetics.mixer.MechanicalMixerRenderer;
import com.simibubi.create.content.kinetics.mixer.MixerInstance;
import com.simibubi.create.content.kinetics.motor.CreativeMotorGenerator;
import com.simibubi.create.content.kinetics.press.MechanicalPressBlock;
import json.jayson.common.registries.SoulsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SoulsBeeStatueEntity extends BlockEntity {
    public boolean activated = false;
    public SoulsBeeStatueEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(SoulsBlockEntities.BEE_STATUE.get(), p_155229_, p_155230_);
    }
    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.putBoolean("activated", activated);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        activated = tag.getBoolean("activated");
    }
}
