package de.josan.backrooms.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Shadow public float nauseaIntensity;

    @Inject(method = "tick", at = @At("TAIL"))
    private void injectShimmerNausea(CallbackInfo ci) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        var shimmerEffectEntry = de.josan.backrooms.effect.ModEffects.SHIMMER;

        if (player.hasStatusEffect(shimmerEffectEntry)) {
            this.nauseaIntensity = 0.1F;
        }
    }
}
