package de.josan.backrooms.mixin;

import de.josan.backrooms.client.render.CustomGlintRenderLayers;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.BufferAllocator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.SequencedMap;

@Mixin(BufferBuilderStorage.class)
public class BufferBuilderStorageMixin {
    @ModifyArg(
            method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumerProvider;immediate(Ljava/util/SequencedMap;Lnet/minecraft/client/util/BufferAllocator;)Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;"),
            index = 0
    )
    private SequencedMap<RenderLayer, BufferAllocator> backrooms$addGlintBuffers(
            SequencedMap<RenderLayer, BufferAllocator> map) {
        for (RenderLayer layer : CustomGlintRenderLayers.all()) {
            map.putIfAbsent(layer, new BufferAllocator(layer.getExpectedBufferSize()));
        }
        return map;
    }
}
