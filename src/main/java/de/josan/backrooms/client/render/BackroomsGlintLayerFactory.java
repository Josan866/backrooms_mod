
package de.josan.backrooms.client.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.Identifier;

public final class BackroomsGlintLayerFactory {

    private BackroomsGlintLayerFactory() {
    }

    public static RenderLayer createItemLayer(
            String name,
            Identifier texture
    ) {
        System.out.println("[GLINT] Creating layer: " + name);

        return RenderLayer.of(
                name,
                VertexFormats.POSITION_TEXTURE,
                VertexFormat.DrawMode.QUADS,
                1536,
                false,
                false,
                RenderLayer.MultiPhaseParameters.builder()
                        .program(RenderPhase.GLINT_PROGRAM)
                        .texture(new RenderPhase.Texture(texture, true, false))
                        .writeMaskState(RenderPhase.COLOR_MASK)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .depthTest(RenderPhase.EQUAL_DEPTH_TEST)
                        .transparency(RenderPhase.GLINT_TRANSPARENCY)
                        .texturing(RenderPhase.GLINT_TEXTURING)
                        .build(false)
        );
    }

    public static RenderLayer createArmorLayer(
            String name,
            Identifier texture
    ) {
        return RenderLayer.of(
                name,
                VertexFormats.POSITION_TEXTURE,
                VertexFormat.DrawMode.QUADS,
                1536,
                RenderLayer.MultiPhaseParameters.builder()
                        .program(RenderPhase.ARMOR_ENTITY_GLINT_PROGRAM)
                        .texture(new RenderPhase.Texture(texture, true, false))
                        .writeMaskState(RenderPhase.COLOR_MASK)
                        .cull(RenderPhase.DISABLE_CULLING)
                        .depthTest(RenderPhase.EQUAL_DEPTH_TEST)
                        .transparency(RenderPhase.GLINT_TRANSPARENCY)
                        .texturing(RenderPhase.ENTITY_GLINT_TEXTURING)
                        .layering(RenderPhase.VIEW_OFFSET_Z_LAYERING)
                        .build(false)
        );
    }
}
