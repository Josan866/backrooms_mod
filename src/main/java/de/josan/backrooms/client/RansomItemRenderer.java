package de.josan.backrooms.client;

import de.josan.backrooms.Backrooms;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class RansomItemRenderer {
    public static void drawItemOverlay(DrawContext context, Slot slot, HandledScreen<?> screen) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Prüfen, ob der lokale Spieler gerade unter dem Ransom-Effekt leidet
        // Da 'activeAttackers' auf dem Server liegt, nutzen wir die Client-Warteschlange oder ein lokales Flag
        if (RansomHudRenderer.showCountdown) {
            ItemStack stack = slot.getStack();

            // Wenn das Item korrumpiert ist, zeichnen wir den visuellen Effekt
            if (Backrooms.hasCorruption(stack)) {
                renderOverlayAt(context, slot.x, slot.y);
            }
        }
    }

    public static void drawHotbarOverlay(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || !RansomHudRenderer.showCountdown) return;

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        // Alle 9 Hotbar-Slots prüfen
        for (int i = 0; i < 9; i++) {
            ItemStack stack = client.player.getInventory().getStack(i);

            if (Backrooms.hasCorruption(stack)) {
                // Mathematisch exakte Positionierung der Hotbar-Slots berechnen
                int slotX = (screenWidth / 2) - 90 + (i * 20) + 2;
                int slotY = screenHeight - 16 - 3;

                renderOverlayAt(context, slotX, slotY);
            }
        }
    }

    private static void renderOverlayAt(DrawContext context, int x, int y) {
        MinecraftClient client = MinecraftClient.getInstance();

        // 1. Ein kleines, fettes rotes "X" als visuellen Indikator in die Mitte zeichnen
        context.getMatrices().push();
        context.getMatrices().translate(0, 0, 300); // Schiebt das Overlay VOR das Item-Modell

        // 2. Rote, halbtransparente Hinterlegung über den gesamten 16x16 Slot legen
        // Farbcode: 0x77FF0000 (77 = Transparenz Alpha, FF0000 = Reines Rot)
        context.fill(x, y, x + 16, y + 16, 0x66FF0000);
        context.drawTexture(RansomHudRenderer.RANSOM_STOP, x + 1, y + 1, 0, 0, 14, 14, 14, 14);

        // Wir zeichnen ein stilisiertes X mit Linien (oder Text)
                /*client.textRenderer.draw("❌", x + 3, y + 4, 0x000000, true,
                        context.getMatrices().peek().getPositionMatrix(),
                        context.getVertexConsumers(),
                        net.minecraft.client.font.TextRenderer.TextLayerType.NORMAL, 0, 15728880);*/

        context.getMatrices().pop();
    }
}
