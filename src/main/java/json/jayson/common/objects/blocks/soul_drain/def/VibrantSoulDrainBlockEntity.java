package json.jayson.common.objects.blocks.soul_drain.def;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import json.jayson.common.capabilities.providers.EntitySoulsProvider;
import json.jayson.common.capabilities.providers.PlayerSoulsProvider;
import json.jayson.common.registries.SoulsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class VibrantSoulDrainBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {
    public static final int cap = 2000;

    SmartFluidTankBehaviour internalTank;
    public VibrantSoulDrainBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(internalTank = SmartFluidTankBehaviour.single(this, cap)
                .allowExtraction()
                .forbidInsertion());
        registerAwardables(behaviours, AllAdvancements.DRAIN, AllAdvancements.CHAINED_DRAIN);
    }

    @Override
    public void tick() {
        super.tick();

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(this.worldPosition.above()), LivingEntity::isAlive);
        if (!entities.isEmpty()) {
            int fluidAmount = getCapability(ForgeCapabilities.FLUID_HANDLER).resolve().get().getFluidInTank(0).getAmount();
            AtomicLong souls = new AtomicLong();
            internalTank.allowInsertion();
            for (LivingEntity entity : entities) {
                if(entity instanceof Player player) {
                    player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                        if (cap - 39 > fluidAmount) {
                            if (playerSouls.getSouls() > 39) {
                                souls.addAndGet(40);
                                playerSouls.setSouls(playerSouls.getSouls() - 40);
                            } else {
                                souls.addAndGet(playerSouls.getSouls());
                                playerSouls.setSouls(0);
                            }
                        }
                    });
                } else {
                    entity.getCapability(EntitySoulsProvider.ENTITY_SOULS).ifPresent(entitySouls -> {
                        if (cap - 39 > fluidAmount) {
                            if (entitySouls.getSouls() > 0) {
                                souls.addAndGet(1);
                                entitySouls.setSouls(entitySouls.getSouls() - 1);
                            } else {
                                entity.kill();
                            }
                        }
                    });
                }
            }
            if(souls.get() != 0) {
                internalTank.getPrimaryHandler().fill(new FluidStack(SoulsBlocks.SOUL_BLOCK.getBlock().getFluid(), (int) souls.get()), IFluidHandler.FluidAction.EXECUTE);
            }
            internalTank.forbidInsertion();
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (side != Direction.UP && isFluidHandlerCap(cap))
            return internalTank.getCapability()
                    .cast();

        return super.getCapability(cap, side);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        return containedFluidTooltip(tooltip, isPlayerSneaking, getCapability(ForgeCapabilities.FLUID_HANDLER));
    }
}
