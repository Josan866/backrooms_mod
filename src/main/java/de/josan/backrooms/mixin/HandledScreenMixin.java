package de.josan.backrooms.mixin;

import de.josan.backrooms.client.RansomItemRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {

    // Wir injizieren am Ende (TAIL) der drawSlot-Methode.
    // Zu diesem Zeitpunkt hat Minecraft das Item bereits fertig gezeichnet.
    @Inject(
            method = "drawSlot",
            at = @At("TAIL")
    )
    private void onDrawSlotOverlay(DrawContext context, Slot slot, CallbackInfo ci) {
        // Ruft unseren Renderer auf, um das rote Overlay und das X darüberzulegen
        RansomItemRenderer.drawItemOverlay(context, slot, (HandledScreen<?>)(Object)this);
    }
}
