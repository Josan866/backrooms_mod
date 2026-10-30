package de.josan.backrooms.client;

public class RansomPopup {
    public final String message;
    public final int xOffset;
    public final int yOffset;
    public int lifetime; // Verbleibende Anzeigedauer in Ticks (z.B. 100 Ticks = 5 Sekunden)

    public RansomPopup(String message, int xOffset, int yOffset, int lifetime) {
        this.message = message;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.lifetime = lifetime;
    }
}
