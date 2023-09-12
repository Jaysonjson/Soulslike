package org.soulslike.mixins;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket;
import net.minecraft.world.entity.Entity;
import org.soulslike.client.screens.SoulsDeathScreen;
import org.soulslike.common.capabilities.PlayerSoulsProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

/*@Mixin(Minecraft.class)
public abstract class ClientPlayerDeath {


    @Shadow @Nullable public LocalPlayer player;

    @Shadow @Nullable public ClientLevel level;

    @Shadow @Final private Window window;

    @Inject(method = "setScreen", at = @At("HEAD"))
    public void setScreen(Screen p_91153_, CallbackInfo ci) {
        if (player.shouldShowDeathScreen()) {
            p_91153_ = new DeathScreen(null, this.level.getLevelData().isHardcore());
        }
        System.out.println("CALLED MIXIN");
       // p_91153_.init(((Minecraft) this), this.window.getGuiScaledWidth(), this.window.getGuiScaledHeight());
    }

}
*/
@Mixin(ClientPacketListener.class)
public class ClientPlayerDeath {
    @Shadow private ClientLevel level;

    @Shadow @Final private Minecraft minecraft;

    /*@Inject(method = "handlePlayerCombatKill", at = @At(value = "HEAD", shift = At.Shift.BY, by = 5))
    public void setScreen(ClientboundPlayerCombatKillPacket p_171775_, CallbackInfo ci) {
        System.out.println("MIXIN");
        this.minecraft.setScreen(new SoulsDeathScreen(p_171775_.getMessage(), this.level.getLevelData().isHardcore()));
    }*/

    @Inject(method = "handlePlayerCombatKill", at = @At(value = "TAIL"))
    public void setScreen(ClientboundPlayerCombatKillPacket p_171775_, CallbackInfo ci) {
        Entity entity = this.level.getEntity(p_171775_.getPlayerId());
        if (entity == this.minecraft.player) {
            if (this.minecraft.player.shouldShowDeathScreen()) {
                this.minecraft.setScreen(new SoulsDeathScreen(p_171775_.getMessage(), this.level.getLevelData().isHardcore()));
            } else {
                this.minecraft.player.respawn();
            }
        }
    }

}