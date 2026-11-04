package de.josan.backrooms.mixin;

import de.josan.backrooms.effect.ModEffects;
import de.josan.backrooms.entity.ModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEffectsMixin {

    @Shadow
    @Final
    private Map<RegistryEntry<StatusEffect>, StatusEffectInstance> activeStatusEffects;

    @Shadow
    protected abstract void onStatusEffectRemoved(StatusEffectInstance effect);

    @Unique
    private boolean myMod$shimmerCurse = false;

    @Inject(method = "clearStatusEffects", at = @At("HEAD"), cancellable = true)
    private void myMod$clearStatusEffects(CallbackInfoReturnable<Boolean> cir) {
        if (this.activeStatusEffects.isEmpty()) {
            return;
        }

        LivingEntity self = (LivingEntity)(Object)this;
        if (self.hasStatusEffect(ModEffects.SHIMMER) || self.hasStatusEffect(ModEffects.SHADOW_STANCE)) {
            cir.setReturnValue(false);

            Iterator<Map.Entry<RegistryEntry<StatusEffect>, StatusEffectInstance>> iterator = this.activeStatusEffects.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<RegistryEntry<StatusEffect>, StatusEffectInstance> entry = iterator.next();

                RegistryEntry<StatusEffect> effect = entry.getKey();

                if (effect.equals(ModEffects.SHIMMER) || effect.equals(ModEffects.SHADOW_STANCE)) {
                    continue;
                }

                this.onStatusEffectRemoved(entry.getValue());
                iterator.remove();
                cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "onStatusEffectRemoved", at = @At("HEAD"))
    private void myMod$onShimmerRemoved(StatusEffectInstance effect, CallbackInfo ci) {
        if (effect.getEffectType().equals(ModEffects.SHIMMER)) {
            LivingEntity self = (LivingEntity)(Object)this;

            if (!self.getWorld().isClient()) {
                this.myMod$shimmerCurse = true;
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void myMod$shimmerCurseTick(CallbackInfo ci) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (this.myMod$shimmerCurse && !self.getWorld().isClient() && self.isAlive()) {
            self.damage(self.getDamageSources().create(ModDamageTypes.SHIMMER_CURSE), 1.0F);
        }
    }
}
