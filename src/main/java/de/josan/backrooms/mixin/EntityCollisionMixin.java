package de.josan.backrooms.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityCollisionMixin {
    @Shadow
    @Final
    private static TrackedData<Boolean> NO_GRAVITY;

    @Inject(method = "move", at = @At("TAIL"), cancellable = true)
    public void movement(MovementType movementType, Vec3d movement, CallbackInfo ci) {
        /*if (this.horizontalCollision) {
            Vec3d vec3d2 = this.getVelocity();
            this.setVelocity(vec3d2.x, vec3d2.y, vec3d2.z);
        }*/
    }
}