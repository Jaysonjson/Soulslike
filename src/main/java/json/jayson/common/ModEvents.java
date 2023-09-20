package json.jayson.common;

import json.jayson.Soulslike;
import json.jayson.common.registries.SoulsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import json.jayson.common.capabilities.PlayerLevelProvider;
import json.jayson.common.capabilities.PlayerSoulsProvider;
import json.jayson.common.objects.entities.PlayerSoulsEntity;
import json.jayson.common.registries.SoulsGameRules;
import json.jayson.helpers.SoulsUtil;

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
    public static void soulsDisplayTick(TickEvent.PlayerTickEvent event) {
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
    public static void onItemPickup(EntityItemPickupEvent event) {
        Player player = event.getEntity();
        if(event.getItem().getItem().getItem().equals(SoulsItems.BEE_TEARS.get())) {
            if (player.getInventory().contains(event.getItem().getItem())) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if(event.getSource().getEntity() instanceof Player player) {
            player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                playerSouls.increaseSouls(SoulsUtil.getEntitySouls(event.getEntity()));
                playerSouls.sync((ServerPlayer) player);
            });
        }

        if(event.getSource().getEntity() instanceof Player attacker && event.getEntity() instanceof Player victim) {
            attacker.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(attackerSouls -> victim.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(victimSouls -> {
                attackerSouls.increaseSouls(victimSouls.getSouls());
                attackerSouls.sync((ServerPlayer) attacker);
            }));
        } else if(event.getEntity() instanceof Player player) {
            if(!player.level().getGameRules().getBoolean(SoulsGameRules.RULE_KEEPSOULS)) {
                player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                    if(playerSouls.getSouls() > 0) {
                        player.level().addFreshEntity(new PlayerSoulsEntity(player.level(), player.position().x, player.position().y + 1, player.position().z, playerSouls.getSouls(), player.getName().getString()));
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            if(event.getEntity().level().getGameRules().getBoolean(SoulsGameRules.RULE_KEEPSOULS)) {
                event.getOriginal().getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(oldStore -> event.getEntity().getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(newStore -> {
                    newStore.setSouls(oldStore.getSouls());
                }));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(oldStore -> {
            oldStore.sync((ServerPlayer) player);
        });
    }
}

