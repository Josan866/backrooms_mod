package de.josan.backrooms.entity.client;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.entity.custom.DeerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DeerRenderer extends MobEntityRenderer<DeerEntity, DeerModel<DeerEntity>> {
    public DeerRenderer(EntityRendererFactory.Context context) {
        super(context, new DeerModel<>(context.getPart(DeerModel.DEER)), 0.75f);
    }

    @Override
    public Identifier getTexture(DeerEntity entity) {
        return Identifier.of(Backrooms.MOD_ID, "textures/entity/deer/deer.png");
    }

    @Override
    public void render(DeerEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(2.5f, 2.5f, 2.5f);
        } else {
            matrixStack.scale(1.2f, 1.2f, 1.2f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
