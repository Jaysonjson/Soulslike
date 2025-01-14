package json.jayson.common.objects.blocks.bee_statue;

import json.jayson.client.screens.LevelUpScreen;
import json.jayson.common.capabilities.PlayerLevel;
import json.jayson.common.capabilities.providers.PlayerLevelProvider;
import json.jayson.common.capabilities.providers.PlayerSoulsProvider;
import json.jayson.common.objects.blocks.IBlockTextOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;

public class SoulsBeeStatue extends BaseEntityBlock implements IBlockTextOverlay {
    public SoulsBeeStatue(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new SoulsBeeStatueEntity(p_153215_, p_153216_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            BlockEntity block = pLevel.getBlockEntity(pPos);
            if(block instanceof SoulsBeeStatueEntity soulsBeeStatueEntity) {
                //ModMessages.sendToPlayer(new OpenLevelUpScreenS2CPacket(), (ServerPlayer) pPlayer);
                pPlayer.sendSystemMessage(Component.literal(String.valueOf(soulsBeeStatueEntity.activated)));
                soulsBeeStatueEntity.activated = true;
                pPlayer.getCapability(PlayerLevelProvider.PLAYER_LEVEL).ifPresent(playerLevel -> {
                    pPlayer.sendSystemMessage(Component.literal("Vitality: " + playerLevel.getAttributeLevel(PlayerLevel.ATTRIBUTE_VITALITY)));
                });
                pPlayer.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                    boolean has = false;
                    for (BlockPos beeStatue : playerSouls.getBeeStatues()) {
                        if (beeStatue.toShortString().equalsIgnoreCase(pPos.toShortString())) {
                            has = true;
                            break;
                        }
                    }
                    if(!has) {
                        playerSouls.getBeeStatues().add(pPos);
                        playerSouls.beeStatuesSize = playerSouls.getBeeStatues().size();
                    }
                    for (BlockPos beeStatue : playerSouls.getBeeStatues()) {
                        pPlayer.sendSystemMessage(Component.literal(beeStatue.toShortString()));
                    }
                });
            }
        } else {
            if (FMLEnvironment.dist == Dist.CLIENT) {
                Minecraft.getInstance().setScreen(new LevelUpScreen());
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public boolean canEntityDestroy(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
       return false;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        return false;
    }

    @Override
    public void alterBlockOverlayText(BlockPos blockPos) {
        addBlockOverlayEntry(new TestEntry.Player());
    }
}
