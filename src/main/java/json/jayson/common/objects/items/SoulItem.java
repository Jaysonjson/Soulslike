package json.jayson.common.objects.items;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import json.jayson.common.capabilities.PlayerSoulsProvider;

import java.util.List;

public class SoulItem extends Item {
    public long souls;
    public String hoverText;
    public SoulItem(Properties p_41383_, long souls, String hoverText) {
        super(p_41383_);
        this.souls = souls;
        this.hoverText = hoverText;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable(hoverText));
        super.appendHoverText(stack, level, components, flag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide()) {
            player.getCooldowns().addCooldown(this, 5);
            if (hand == InteractionHand.MAIN_HAND) {
                player.getMainHandItem().shrink(1);
                player.getCapability(PlayerSoulsProvider.PLAYER_SOULS).ifPresent(playerSouls -> {
                    playerSouls.setSouls(playerSouls.getSouls() + souls);
                    playerSouls.sync((ServerPlayer) player);
                });
            }
        }
        return super.use(level, player, hand);
    }
}
