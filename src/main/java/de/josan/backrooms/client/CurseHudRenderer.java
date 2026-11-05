package de.josan.backrooms.client;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.network.CursePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class CurseHudRenderer {
    public static boolean show = false;

    public static final Identifier PENISFUCHS =
            Backrooms.id("textures/gui/curse/penisfuchs.png");

    public static void register() {
        HudRenderCallback.EVENT.register(CurseHudRenderer::onHudRender);

        ClientPlayNetworking.registerGlobalReceiver(CursePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                show = payload.show();
            });
        });
    }

    private static void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        if (show) {
            context.getMatrices().push();
            context.getMatrices().translate(0, 0, 302);

            context.setShaderColor(1f, 1f, 1f, 0.4f);

            context.drawTexture(PENISFUCHS, screenWidth / 3, 0, 0.5F, 0.0F, screenWidth / 3, screenHeight, screenWidth / 3 , screenHeight);

            context.setShaderColor(1f, 1f, 1f, 1f);

            context.getMatrices().pop();
        }
    }
}
