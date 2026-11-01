
package de.josan.backrooms.mixin;

import de.josan.backrooms.client.render.CustomGlintRenderLayers;
import de.josan.backrooms.component.ModDataComponentTypes;
import de.josan.backrooms.effect.ModEffects;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayDeque;
import java.util.Deque;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin {

    @Unique
    private static final ThreadLocal<Deque<ItemStack>> BACKROOMS$ARMOR_STACKS =
            ThreadLocal.withInitial(ArrayDeque::new);

    // Bestehende SHADOW_STANCE-Funktion
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void backrooms$hideArmor(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            LivingEntity entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch,
            CallbackInfo ci
    ) {
        if (entity instanceof PlayerEntity &&
                entity.hasStatusEffect(ModEffects.SHADOW_STANCE)) {
            ci.cancel();
        }
    }

    // Aktuelles Rüstungsteil speichern
    @Inject(method = "renderArmor", at = @At("HEAD"))
    private void backrooms$pushArmorStack(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            LivingEntity entity,
            EquipmentSlot armorSlot,
            int light,
            BipedEntityModel<?> model,
            CallbackInfo ci
    ) {
        BACKROOMS$ARMOR_STACKS.get().push(
                entity.getEquippedStack(armorSlot)
        );
    }

    // Aktuelles Rüstungsteil wieder entfernen
    @Inject(method = "renderArmor", at = @At("RETURN"))
    private void backrooms$popArmorStack(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            LivingEntity entity,
            EquipmentSlot armorSlot,
            int light,
            BipedEntityModel<?> model,
            CallbackInfo ci
    ) {
        Deque<ItemStack> stacks = BACKROOMS$ARMOR_STACKS.get();

        if (!stacks.isEmpty()) {
            stacks.pop();
        }

        if (stacks.isEmpty()) {
            BACKROOMS$ARMOR_STACKS.remove();
        }
    }

    // Vanilla Armor-Glint durch unseren eigenen ersetzen
    @Redirect(
            method = "renderGlint",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/RenderLayer;getArmorEntityGlint()Lnet/minecraft/client/render/RenderLayer;"
            )
    )
    private RenderLayer backrooms$replaceArmorGlint() {

        Deque<ItemStack> stacks = BACKROOMS$ARMOR_STACKS.get();

        if (stacks.isEmpty()) {
            return RenderLayer.getArmorEntityGlint();
        }

        ItemStack stack = stacks.peek();

        Integer glintId = stack.get(
                ModDataComponentTypes.GLINT_TYPE
        );

        RenderLayer customLayer =
                glintId == null
                        ? null
                        : CustomGlintRenderLayers.getArmorLayer(glintId);

        return customLayer != null
                ? customLayer
                : RenderLayer.getArmorEntityGlint();
    }
}
