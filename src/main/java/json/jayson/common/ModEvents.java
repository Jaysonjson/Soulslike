package json.jayson.common;

import java.util.List;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import json.jayson.Soulslike;
import json.jayson.common.capabilities.providers.EntitySoulsProvider;
import json.jayson.common.objects.items.SoulVialItem;
import json.jayson.common.registries.SoulsItems;
import json.jayson.common.registries.SoulsVillagers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import json.jayson.common.capabilities.providers.PlayerLevelProvider;
import json.jayson.common.capabilities.providers.PlayerSoulsProvider;
import json.jayson.common.objects.entities.PlayerSoulsEntity;
import json.jayson.common.registries.SoulsGameRules;
import json.jayson.helpers.SoulsUtil;

@Mod.EventBusSubscriber(modid = Soulslike.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if(event.getObject() instanceof Player player) {
            if(!player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).isPresent()) {
                event.addCapability(new ResourceLocation(Soulslike.MODID, "souls"), new PlayerSoulsProvider());
            }
            if(!player.getCapability(PlayerLevelProvider.PLAYER_LEVEL).isPresent()) {
                event.addCapability(new ResourceLocation(Soulslike.MODID, "levelcap"), new PlayerLevelProvider());
            }
        }
        if(SoulsUtil.entitySoulsExist(entity)) {
            if(!entity.getCapability(EntitySoulsProvider.ENTITY_SOULS).isPresent()) {
                event.addCapability(new ResourceLocation(Soulslike.MODID, "entity_souls"), new EntitySoulsProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinLevelEvent event) {
        event.getEntity().getCapability(EntitySoulsProvider.ENTITY_SOULS).ifPresent(entitySouls -> {
            entitySouls.setSouls(SoulsUtil.getEntitySouls(event.getEntity()));
        });
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
                event.getEntity().getCapability(EntitySoulsProvider.ENTITY_SOULS).ifPresent(entitySouls -> {
                    playerSouls.increaseSouls(entitySouls.getSouls());
                    playerSouls.sync((ServerPlayer) player);
                });
            });
            if(SoulsUtil.entitySoulsExist(event.getEntity())) {
                for (ItemStack item : player.getInventory().items) {
                    if (item.is(SoulsItems.SOUL_VIAL.get())) {
                        CompoundTag tag = item.getTag();
                        if (tag.contains("entity")) {
                            if (tag.getInt("amount") >= SoulVialItem.MAX) {
                                continue;
                            }
                            if (tag.getString("entity").isEmpty()) {
                                tag.putString("entity", event.getEntity().getType().getDescriptionId());
                                //if(tag.getInt("amount") >= SoulVialItem.MAX) {
                                //}
                            }
                            if (tag.getString("entity").equals(event.getEntity().getType().getDescriptionId())) {
                                tag.putInt("amount", tag.getInt("amount") + 1);
                            }
                            item.setTag(tag);
                            break;
                        }
                    }
                }
            }
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
                        player.level().addFreshEntity(new PlayerSoulsEntity(player.level(), player.position().x, player.position().y + 1, player.position().z, playerSouls.getSouls(), player));
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

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == SoulsVillagers.GEM_CUTTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, random.nextInt(4, 8)), new ItemStack(SoulsItems.RUBY_SHARD.get(), 4), 2, 8, 0.02f));
            trades.get(1).add((trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, random.nextInt(4, 8)), new ItemStack(SoulsItems.SAPPHIRE_SHARD.get(), 4), 2, 8, 0.02f));
            trades.get(2).add((trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, random.nextInt(4, 9)), new ItemStack(SoulsItems.RUBY.get(), 3), 2, 8, 0.05f));
            trades.get(2).add((trader, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, random.nextInt(4, 9)), new ItemStack(SoulsItems.SAPPHIRE.get(), 3), 2, 8, 0.05f));
        }
    }
}

