package de.josan.backrooms.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;

public class SunShyEffect extends StatusEffect {
    protected SunShyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (isAffectedByDaylight(entity) && !(entity.isInCreativeMode() || entity.isSpectator())) {
            entity.setOnFireFor(8.0F + amplifier * 2.5F);
            entity.damage(entity.getDamageSources().onFire(), amplifier * 1.75F);
        }
        return true;
    }

    /*
    private boolean skyLightAbove(LivingEntity entity) {
        return entity.getWorld().isDay() && !entity.getWorld().isRaining() && entity.getEntityWorld().isSkyVisible(entity.getBlockPos());
    }
     */

    protected boolean isAffectedByDaylight(LivingEntity entity) {
        if (entity.getWorld().isClient) { return false; }
        if (entity.getWorld().isDay() && !entity.getWorld().isClient) {
            float f = entity.getBrightnessAtEyes();
            BlockPos blockPos = BlockPos.ofFloored(entity.getX(), entity.getEyeY(), entity.getZ());
            boolean bl = entity.isWet() || entity.inPowderSnow || entity.wasInPowderSnow;
            return f > 0.5F && !bl && entity.getWorld().isSkyVisible(blockPos);
        }

        return false;
    }
}
