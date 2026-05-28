package de.josan.backrooms.effect;

import de.josan.backrooms.item.custom.MothJelly;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.text.Text;

public class BackroomsSanityEffect extends StatusEffect {
    protected BackroomsSanityEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        int current_over = entity.getAttachedOrCreate(MothJelly.MOTHJELLY_OVERDOSE);
        if (entity.isPlayer() && !entity.getWorld().isClient && current_over > 0) {
            int current_dec = entity.getAttachedOrCreate(MothJelly.MOTHJELLY_DECREASE);
            entity.setAttached(MothJelly.MOTHJELLY_DECREASE, (current_dec + 1));

            if (current_dec >= 200) {
                entity.setAttached(MothJelly.MOTHJELLY_DECREASE, 0);
                entity.setAttached(MothJelly.MOTHJELLY_OVERDOSE, (current_over - 1));
                entity.sendMessage(Text.literal("Diedanisd"));
            }
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 2 == 0;
    }
}
