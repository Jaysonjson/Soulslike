package org.soulslike.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.soulslike.client.data.ClientSoulsData;

@OnlyIn(Dist.CLIENT)
public class SoulsDeathScreen extends DeathScreen {
    int souls = 0;
    public SoulsDeathScreen(@Nullable Component p_95911_, boolean p_95912_) {
        super(p_95911_, p_95912_);
        souls = ClientSoulsData.getSouls();
    }

    public void render(GuiGraphics p_283488_, int p_283551_, int p_283002_, float p_281981_) {
        p_283488_.fillGradient(0, 0, this.width, this.height, 1615855616, -1602211792);
        p_283488_.pose().pushPose();
        p_283488_.pose().scale(2.0F, 2.0F, 2.0F);
        p_283488_.drawCenteredString(this.font, this.title, this.width / 2 / 2, 30, 16777215);
        p_283488_.pose().popPose();
        if (this.causeOfDeath != null) {
            p_283488_.drawCenteredString(this.font, this.causeOfDeath, this.width / 2, 85, 16777215);
            p_283488_.drawCenteredString(this.font, "Souls: " + this.souls, this.width / 2, 125, 16777215);
        } else {
            p_283488_.drawCenteredString(this.font, "Souls:" + this.souls, this.width / 2, 85, 16777215);
        }

        p_283488_.drawCenteredString(this.font, this.deathScore, this.width / 2, 100, 16777215);
        if (this.causeOfDeath != null && p_283002_ > 85 && p_283002_ < 85 + 9) {
            Style style = this.getClickedComponentStyleAt(p_283551_);
            p_283488_.renderComponentHoverEffect(this.font, style, p_283551_, p_283002_);
        }

        super.render(p_283488_, p_283551_, p_283002_, p_281981_);
        if (this.exitToTitleButton != null && this.minecraft.getReportingContext().hasDraftReport()) {
            p_283488_.blit(AbstractWidget.WIDGETS_LOCATION, this.exitToTitleButton.getX() + this.exitToTitleButton.getWidth() - 17, this.exitToTitleButton.getY() + 3, 182, 24, 15, 15);
        }

    }

}
