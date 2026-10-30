package de.josan.backrooms.client;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.network.RansomClosePayload;
import de.josan.backrooms.network.RansomPopupPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShimmerHudRenderer {
    public static final List<RansomPopup> activePopups = new ArrayList<>();
    private static final Random RANDOM = new Random();

    // Variablen für den festen Countdown im Vordergrund
    public static boolean showCountdown = false;
    public static int countdownSeconds = 60;
    public static int currentGold = 0;

    public static final Identifier RANSOM_FACE_IDLE =
            Backrooms.id("textures/gui/ransom/face.png");
    public static final Identifier RANSOM_FACE_DISTORTED =
            Backrooms.id("textures/gui/ransom/face_distorted.png");
    public static final Identifier RANSOM_FACE_RAGE =
            Backrooms.id("textures/gui/ransom/face_rage.png");
    public static final Identifier RANSOM_FACE_RAGE_RED =
            Backrooms.id("textures/gui/ransom/face_rage_red.png");
    public static final Identifier RANSOM_STOP =
            Backrooms.id("textures/gui/ransom/stop.png");

    public static void register() {
        HudRenderCallback.EVENT.register(ShimmerHudRenderer::onHudRender);

        ClientPlayNetworking.registerGlobalReceiver(RansomPopupPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                // 1. Die Live-Werte für das Hauptfenster synchronisieren
                countdownSeconds = payload.secondsLeft();
                currentGold = payload.currentGold();
                showCountdown = true;

                // 2. Nur wenn der Server eine Nachricht mitgibt, spawnen wir ein neues, zufälliges Popup
                if (!payload.message().isEmpty()) {
                    int x = RANDOM.nextInt(320) - 160;
                    int y = RANDOM.nextInt(140) - 20;
                    activePopups.add(new RansomPopup(payload.message(), x, y, 100));
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(RansomClosePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                activePopups.clear();
                showCountdown = false;
            });
        });
    }

    private static void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;
        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        // --- 1. POPUPS AUTOMATISCH ABLAUFEN LASSEN ---
        for (int i = activePopups.size() - 1; i >= 0; i--) {
            RansomPopup popup = activePopups.get(i);
            popup.lifetime--;
            if (popup.lifetime <= 0) {
                activePopups.remove(i);
            }
        }

        // --- 2. ZUFÄLLIGE POPUPS KLEINER RENDERN ---
        int boxWidth = 180;  // Deutlich schmaler (vorher 240)
        int boxHeight = 45;  // Deutlich flacher (vorher 70)

        for (RansomPopup popup : activePopups) {
            int centerX = (screenWidth / 2) + popup.xOffset;
            int centerY = (screenHeight / 2) + popup.yOffset;
            int x = centerX - (boxWidth / 2);
            int y = centerY - (boxHeight / 2);

            drawRoundedRect(context, x, y, x + boxWidth, y + boxHeight, 2, 0xDD330000);
            context.fill(x + 1, y + 1, x + boxWidth - 1, y + boxHeight - 1, 0xF50A0000);

            context.drawCenteredTextWithShadow(textRenderer, "⚠ ERROR ⚠", centerX, y + 6, 0xFFFF3333);

            // Text herunterskalieren, damit er in das kleinere Fenster passt
            context.getMatrices().push();
            context.getMatrices().translate(centerX, y + 22, 0);
            context.getMatrices().scale(0.85f, 0.85f, 1.0f);
            context.drawCenteredTextWithShadow(textRenderer, popup.message, 0, 0, 0xFFFFFFFF);
            context.getMatrices().pop();
        }

        // --- 3. COUNTDOWN ALS FESTES FENSTER IMMER IM VORDERGRUND ---
        if (showCountdown) {
            int mainWidth = 150;
            int mainHeight = 105;
            int mainX = (screenWidth / 2) - (mainWidth / 2);
            int mainY = 10; // Leicht nach oben versetzt

            context.getMatrices().push();
            context.getMatrices().translate(0, 0, 301); // Drückt das X vor das Item

            // Markanter Rahmen für das Hauptfenster
            drawRoundedRect(context, mainX, mainY, mainX + mainWidth, mainY + mainHeight, 4, 0x44000000); // Schwarzer Außenrand
            context.fill(mainX + 2, mainY + 12, mainX + mainWidth - 2, mainY + mainHeight - 2, 0xFFFF0000); // Dunkelrot innen

            // GOLD
            context.fill(mainX + 3, mainY + mainHeight - 19, mainX + mainWidth / 2 - 18, mainY + mainHeight - 3, 0xFFFFCC00);
            context.fill(mainX + 4, mainY + mainHeight - 18, mainX + mainWidth / 2 - 19, mainY + mainHeight - 4, 0xFF000000);

            // TIMER
            context.fill(mainX + mainWidth / 2 - 13, mainY + mainHeight - 19, mainX + mainWidth - 3, mainY + mainHeight - 3, 0xFFFFFFFF);
            context.fill(mainX + mainWidth / 2 - 12, mainY + mainHeight - 18, mainX + mainWidth - 4, mainY + mainHeight - 4, 0xFFFF0000);

            // Infobox
            context.fill(mainX + 3, mainY + mainHeight / 2 - 5, mainX + mainWidth - 3, mainY + mainHeight - 23, 0xFFFFFFFF);
            context.fill(mainX + 4, mainY + mainHeight / 2 - 4, mainX + mainWidth - 4, mainY + mainHeight - 24, 0xFF000000);

            context.drawTextWithShadow(textRenderer, "RANS0M", screenWidth / 2 - mainWidth / 2 + 2, mainY + 3, 0xFFFFFFFF);
            context.drawTexture(RANSOM_FACE_IDLE, screenWidth / 2 - mainWidth / 2 + 12, mainY + 11, 0, 0, 45, 45, 45 , 45);
            context.drawCenteredTextWithShadow(textRenderer, "YOUR ITEMS", screenWidth / 2 + mainWidth / 6, mainY + 17, 0xFFFFFFFF);
            context.drawCenteredTextWithShadow(textRenderer, "HAVE BEEN", screenWidth / 2 + mainWidth / 6, mainY + 27, 0xFFFFFFFF);
            context.drawCenteredTextWithShadow(textRenderer, "ENCRYPTED", screenWidth / 2 + mainWidth / 6, mainY + 37, 0xFFFFFFFF);
            context.getMatrices().push();
            context.getMatrices().translate(screenWidth / 2, mainY + 55, 0);
            context.getMatrices().scale(0.65f, 0.65f, 1.0f);
            context.drawCenteredTextWithShadow(textRenderer, "IF YOU DO NOT PAY THIS RANSOM BEFORE", 0, 0, 0xFFFFFFFF);
            //context.drawCenteredTextWithShadow(textRenderer, "LEAVE BEFORE", 0, 8, 0xFFFFFFFF);
            context.drawCenteredTextWithShadow(textRenderer, "THE TIMER ENDS, YOUR ITEMS WILL BE", 0, 10, 0xFFFFFFFF);
            context.drawCenteredTextWithShadow(textRenderer, "UNRECOVERABLE BY ANY MEANS", 0, 20, 0xFFAA0000);
            context.getMatrices().pop();
            context.drawText(textRenderer, "TIME:          " + countdownSeconds, mainX + mainWidth / 2 - 10, mainY + mainHeight - 15, 0xFF000000, false);
            context.drawTextWithShadow(textRenderer, "GOLD: " + (500 - currentGold), mainX + 7, mainY + mainHeight - 15, 0xFFFFCC00);

            context.getMatrices().pop();
        }
    }

    private static void drawRoundedRect(DrawContext context, int x, int y, int width, int height, int radius, int color) {
        if (radius <= 0) {
            context.fill(x, y, x + width, y + height, color);
            return;
        }

        // Zentraler Hauptkörper des Rechtecks (ohne die Ecken)
        context.fill(x + radius, y, x + width - radius, y + height, color);
        context.fill(x, y + radius, x + radius, y + height - radius, color);
        context.fill(x + width - radius, y + radius, x + width, y + height - radius, color);

        // Pixelsprung-Berechnung für die Rundung der 4 Ecken (Radius = 4)
        for (int i = 0; i < radius; i++) {
            int offset = (int) Math.round(radius - Math.sqrt(radius * radius - (radius - i) * (radius - i)));

            // Top-Left Corner
            context.fill(x + i, y + offset, x + i + 1, y + radius, color);
            // Top-Right Corner
            context.fill(x + width - i - 1, y + offset, x + width - i, y + radius, color);
            // Bottom-Left Corner
            context.fill(x + i, y + height - radius, x + i + 1, y + height - offset, color);
            // Bottom-Right Corner
            context.fill(x + width - i - 1, y + height - radius, x + width - i, y + height - offset, color);
        }
    }
}
