package org.soulslike.common;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.soulslike.Soulslike;
import org.soulslike.common.capabilities.PlayerLevelProvider;
import org.soulslike.common.capabilities.PlayerSoulsProvider;

@Mod.EventBusSubscriber(modid = Soulslike.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player player) {
            if(!player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).isPresent()) {
                event.addCapability(new ResourceLocation(Soulslike.MODID, "properties"), new PlayerSoulsProvider());
            }
            if(!player.getCapability(PlayerLevelProvider.PLAYER_LEVEL).isPresent()) {
                event.addCapability(new ResourceLocation(Soulslike.MODID, "levelcap"), new PlayerLevelProvider());
            }
        }
    }

    @SubscribeEvent
    public static void testTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                if(playerSouls.newSouls_ != 0) {
                    ++playerSouls.newSoulsTick;
                    playerSouls.sync((ServerPlayer) event.player);
                }
                if(playerSouls.newSoulsTick > 150) {
                    playerSouls.newSouls_ = 0;
                    playerSouls.newSoulsTick = 0;
                    playerSouls.sync((ServerPlayer) event.player);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                    playerSouls.sync(player);
                    for (BlockPos beeStatue : playerSouls.getBeeStatues()) {
                        player.sendSystemMessage(Component.literal(beeStatue.toShortString()));
                    }
                });
                player.getCapability(PlayerLevelProvider.PLAYER_LEVEL).ifPresent(playerLevel -> {
                    playerLevel.setPlayerAttributes(player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof Player player) {
            player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                if (Data.SOULS_MAP.containsKey(event.getEntity().getType().getDescriptionId())) {
                    playerSouls.setSouls(playerSouls.getSouls() + Data.SOULS_MAP.get(event.getEntity().getType().getDescriptionId()));
                    playerSouls.sync((ServerPlayer) player);
                }
            });
        }

        if(event.getSource().getEntity() instanceof Player attacker && event.getEntity() instanceof Player victim) {
            attacker.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(attackerSouls -> victim.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(victimSouls -> {
                attackerSouls.setSouls(attackerSouls.getSouls() + victimSouls.getSouls());
                attackerSouls.sync((ServerPlayer) attacker);
            }));
        }
    }

   /* @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(oldStore -> event.getEntity().getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(newStore -> newStore.setShowDeathText(true)));
        }
    }*/


}
