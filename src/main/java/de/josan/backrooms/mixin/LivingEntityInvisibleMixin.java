package de.josan.backrooms.mixin;

import de.josan.backrooms.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityInvisibleMixin {

    @Shadow public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);
    @Shadow protected abstract void updatePotionSwirls();
    @Shadow protected abstract void clearPotionSwirls();
    @Shadow private Map<RegistryEntry<StatusEffect>, StatusEffectInstance> activeStatusEffects;

    @Inject(method = "updatePotionVisibility", at = @At("HEAD"), cancellable = true)
    private void myMod$forceInvisibleOnEffect(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (this.activeStatusEffects.isEmpty()) {
            this.clearPotionSwirls();
            self.setInvisible(false);
        } else {
            boolean invisible = this.hasStatusEffect(StatusEffects.INVISIBILITY)
                    || this.hasStatusEffect(ModEffects.SHADOW_STANCE);
            self.setInvisible(invisible);
            this.updatePotionSwirls();
        }
        ci.cancel();
    }
}
