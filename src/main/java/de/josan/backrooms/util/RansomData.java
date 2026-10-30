package de.josan.backrooms.util;

import net.minecraft.util.math.Vec3d;

public class RansomData {
    public final Vec3d pos;
    public final float yaw;
    public final float pitch;

    public boolean inDemandMode = false;
    public int remainingTicks = 1200; // 60 Sekunden * 20 Ticks = 1200 Ticks
    public int nextPopupTicks = 0;

    public RansomData(Vec3d pos, float yaw, float pitch) {
        this.pos = pos;
        this.yaw = yaw;
        this.pitch = pitch;
    }
}
