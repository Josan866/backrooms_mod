package de.josan.backrooms.mixin;

import de.josan.backrooms.effect.ModEffects;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.EnumMap;
import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEquipmentMixin {

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Inject(method = "getEquipmentChanges", at = @At("RETURN"), cancellable = true)
    private void myMod$hideEquipmentOnEffect(CallbackInfoReturnable<Map<EquipmentSlot, ItemStack>> cir) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (!self.hasStatusEffect(ModEffects.SHADOW_STANCE)) return;

        Map<EquipmentSlot, ItemStack> original = cir.getReturnValue();
        Map<EquipmentSlot, ItemStack> filtered = new EnumMap<>(EquipmentSlot.class);
        if (original != null) filtered.putAll(original);

        // Force every non-empty slot to report as EMPTY in the outgoing diff
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (!this.getEquippedStack(slot).isEmpty()) {
                filtered.put(slot, ItemStack.EMPTY);
            }
        }

        cir.setReturnValue(filtered.isEmpty() ? null : filtered);
    }
}