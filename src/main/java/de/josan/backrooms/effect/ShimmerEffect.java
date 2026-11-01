package de.josan.backrooms.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

// MIT License!
public class ShimmerEffect extends StatusEffect {
        protected ShimmerEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient()) {
            DamageSource magicDamage = entity.getDamageSources().magic();
            entity.damage(magicDamage, 8.0F);
        }
    }
}
