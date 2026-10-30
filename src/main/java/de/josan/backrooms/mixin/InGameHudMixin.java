package de.josan.backrooms.mixin;

import de.josan.backrooms.client.RansomItemRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    // Injiziert am Ende der HUD-Hotbar-Render-Methode
    @Inject(
            method = "renderHotbar",
            at = @At("TAIL")
    )
    private void onRenderHotbarOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        // Ruft unseren korrigierten Hotbar-Zeichner auf
        RansomItemRenderer.drawHotbarOverlay(context, tickCounter.getTickDelta(false));
    }
}
