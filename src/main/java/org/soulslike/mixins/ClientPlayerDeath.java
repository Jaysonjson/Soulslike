package org.soulslike.mixins;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
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
