package json.jayson.mixins;

import json.jayson.client.screens.SoulsDeathScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class ClientPlayerDeathMinecraft {

    @Shadow @Nullable public ClientLevel level;

    @Shadow @Nullable public LocalPlayer player;

    @Inject(method = "setScreen", at = @At(value = "TAIL"))
    public void setScreen(Screen p_91153_, CallbackInfo ci) {
        if (p_91153_ == null && this.level == null) {
            p_91153_ = new TitleScreen();
        } else if (p_91153_ == null && this.player.isDeadOrDying()) {
            if (this.player.shouldShowDeathScreen()) {
                p_91153_ = new SoulsDeathScreen((Component)null, this.level.getLevelData().isHardcore());
            } else {
                this.player.respawn();
            }
        }
    }

}
