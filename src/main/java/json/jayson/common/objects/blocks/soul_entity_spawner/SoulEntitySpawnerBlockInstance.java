package json.jayson.common.objects.blocks.soul_entity_spawner;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import com.simibubi.create.content.kinetics.mixer.MixerInstance;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogInstance;
import com.simibubi.create.foundation.render.AllMaterialSpecs;

public class SoulEntitySpawnerBlockInstance extends EncasedCogInstance {
    public SoulEntitySpawnerBlockInstance(MaterialManager modelManager, SoulEntitySpawnerBlockEntity blockEntity) {
        super(modelManager, blockEntity, false);
    }

    @Override
    protected Instancer<RotatingData> getCogModel() {
        return materialManager.defaultSolid()
                .material(AllMaterialSpecs.ROTATING)
                .getModel(AllPartialModels.SHAFTLESS_COGWHEEL, blockEntity.getBlockState());
    }
}
