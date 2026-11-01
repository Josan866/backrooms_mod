
package de.josan.backrooms.client.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;

import java.util.List;

public final class CustomGlintRenderLayers {

    public static final RenderLayer RED_ITEM =
            BackroomsGlintLayerFactory.createItemLayer(
                    "backrooms_red_glint",
                    Identifier.of("backrooms", "textures/misc/glint/item/red.png")
            );

    public static final RenderLayer GREEN_ITEM =
            BackroomsGlintLayerFactory.createItemLayer(
                    "backrooms_green_glint",
                    Identifier.of("backrooms", "textures/misc/glint/item/green.png")
            );

    public static final RenderLayer PINK_ITEM =
            BackroomsGlintLayerFactory.createItemLayer(
                    "backrooms_pink_glint",
                    Identifier.of("backrooms", "textures/misc/glint/item/pink.png")
            );

    public static final RenderLayer GOLD_ITEM =
            BackroomsGlintLayerFactory.createItemLayer(
                    "backrooms_gold_glint",
                    Identifier.of("backrooms", "textures/misc/glint/item/gold.png")
            );

    public static final RenderLayer WHITE_ITEM =
            BackroomsGlintLayerFactory.createItemLayer(
                    "backrooms_white_glint",
                    Identifier.of("backrooms", "textures/misc/glint/item/white.png")
            );

    public static final RenderLayer RED_ARMOR =
            BackroomsGlintLayerFactory.createArmorLayer(
                    "backrooms_red_armor_glint",
                    Identifier.of("backrooms", "textures/misc/glint/entity/red.png")
            );

    public static final RenderLayer GREEN_ARMOR =
            BackroomsGlintLayerFactory.createArmorLayer(
                    "backrooms_green_armor_glint",
                    Identifier.of("backrooms", "textures/misc/glint/entity/green.png")
            );

    public static final RenderLayer PINK_ARMOR =
            BackroomsGlintLayerFactory.createArmorLayer(
                    "backrooms_pink_armor_glint",
                    Identifier.of("backrooms", "textures/misc/glint/entity/pink.png")
            );

    public static final RenderLayer GOLD_ARMOR =
            BackroomsGlintLayerFactory.createArmorLayer(
                    "backrooms_gold_armor_glint",
                    Identifier.of("backrooms", "textures/misc/glint/entity/gold.png")
            );

    public static final RenderLayer WHITE_ARMOR =
            BackroomsGlintLayerFactory.createArmorLayer(
                    "backrooms_white_armor_glint",
                    Identifier.of("backrooms", "textures/misc/glint/entity/white.png")
            );

    private CustomGlintRenderLayers() {
    }

    public static List<RenderLayer> all() {
        return List.of(
                RED_ITEM,
                GREEN_ITEM,
                PINK_ITEM,
                GOLD_ITEM,
                WHITE_ITEM,
                RED_ARMOR,
                GREEN_ARMOR,
                PINK_ARMOR,
                GOLD_ARMOR,
                WHITE_ARMOR
        );
    }

    public static RenderLayer getItemLayer(int glintId) {
        return switch (glintId) {
            case 1 -> RED_ITEM;
            case 2 -> GREEN_ITEM;
            case 3 -> PINK_ITEM;
            case 4 -> GOLD_ITEM;
            case 5 -> WHITE_ITEM;
            default -> null;
        };
    }

    public static RenderLayer getArmorLayer(int glintId) {
        return switch (glintId) {
            case 1 -> RED_ARMOR;
            case 2 -> GREEN_ARMOR;
            case 3 -> PINK_ARMOR;
            case 4 -> GOLD_ARMOR;
            case 5 -> WHITE_ARMOR;
            default -> null;
        };
    }
}
