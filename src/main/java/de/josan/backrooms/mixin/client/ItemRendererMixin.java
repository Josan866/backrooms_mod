
package de.josan.backrooms.mixin.client;

import de.josan.backrooms.client.render.CustomGlintRenderLayers;
import de.josan.backrooms.component.ModDataComponentTypes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.render.*;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayDeque;
import java.util.Deque;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Unique
    private static final ThreadLocal<Deque<ItemStack>> BACKROOMS$STACKS =
            ThreadLocal.withInitial(ArrayDeque::new);

    @Inject(method = "renderItem", at = @At("HEAD"))
    private void backrooms$pushStack(
            ItemStack stack,
            ModelTransformationMode renderMode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            BakedModel model,
            CallbackInfo ci
    ) {
        BACKROOMS$STACKS.get().push(stack);
    }

    @Inject(method = "renderItem", at = @At("RETURN"))
    private void backrooms$popStack(
            ItemStack stack,
            ModelTransformationMode renderMode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            int overlay,
            BakedModel model,
            CallbackInfo ci
    ) {
        Deque<ItemStack> stacks = BACKROOMS$STACKS.get();

        if (!stacks.isEmpty()) {
            stacks.pop();
        }

        if (stacks.isEmpty()) {
            BACKROOMS$STACKS.remove();
        }
    }

    @Unique
    private static int backrooms$getGlintId() {
        Deque<ItemStack> stacks = BACKROOMS$STACKS.get();

        if (stacks.isEmpty()) {
            return 0;
        }

        Integer id = stacks.peek().get(
                ModDataComponentTypes.GLINT_TYPE
        );

        return id == null ? 0 : id;
    }

    @Unique
    private static RenderLayer backrooms$getCustomItemLayer() {
        int id = backrooms$getGlintId();

        RenderLayer layer = id == 0
                ? null
                : CustomGlintRenderLayers.getItemLayer(id);

        return layer;
    }

    @Redirect(
            method = "renderItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;getItemGlintConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/render/RenderLayer;ZZ)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer backrooms$customItemGlint(
            VertexConsumerProvider provider,
            RenderLayer layer,
            boolean solid,
            boolean glint
    ) {
        if (!glint) {
            return provider.getBuffer(layer);
        }

        RenderLayer customLayer = backrooms$getCustomItemLayer();

        if (customLayer == null) {
            return ItemRenderer.getItemGlintConsumer(
                    provider,
                    layer,
                    solid,
                    true
            );
        }

        return VertexConsumers.union(
                provider.getBuffer(customLayer),
                provider.getBuffer(layer)
        );
    }

    @WrapOperation(
            method = "renderItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;getDirectItemGlintConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/render/RenderLayer;ZZ)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer backrooms$customDirectGlint(
            VertexConsumerProvider provider,
            RenderLayer layer,
            boolean solid,
            boolean glint,
            Operation<VertexConsumer> original
    ) {
        if (!glint) {
            return original.call(provider, layer, solid, false);
        }

        RenderLayer customLayer = backrooms$getCustomItemLayer();

        if (customLayer == null) {
            return original.call(provider, layer, solid, true);
        }

        return VertexConsumers.union(
                provider.getBuffer(customLayer),
                provider.getBuffer(layer)
        );
    }

    @Redirect(
            method = "renderItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;getDynamicDisplayGlintConsumer(Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/util/math/MatrixStack$Entry;)Lnet/minecraft/client/render/VertexConsumer;"
            )
    )
    private VertexConsumer backrooms$customDynamicGlint(
            VertexConsumerProvider provider,
            RenderLayer layer,
            MatrixStack.Entry entry
    ) {
        RenderLayer customLayer = backrooms$getCustomItemLayer();

        if (customLayer == null) {
            return ItemRenderer.getDynamicDisplayGlintConsumer(
                    provider, layer, entry
            );
        }

        return VertexConsumers.union(
                new OverlayVertexConsumer(
                        provider.getBuffer(customLayer),
                        entry,
                        0.0078125F
                ),
                provider.getBuffer(layer)
        );
    }
}
