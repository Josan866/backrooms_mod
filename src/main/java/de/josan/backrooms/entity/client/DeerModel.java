package de.josan.backrooms.entity.client;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.entity.custom.DeerEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class DeerModel<T extends DeerEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer DEER = new EntityModelLayer(Identifier.of(Backrooms.MOD_ID, "deer"), "main");

    private final ModelPart Body;
    private final ModelPart Head;

    public DeerModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = this.Body.getChild("Head");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -7.0F, -11.0F, 10.0F, 7.0F, 22.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.0F, 0.0F));

        ModelPartData Head = Body.addChild("Head", ModelPartBuilder.create().uv(0, 29).cuboid(-3.0F, -5.0F, -7.0F, 6.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, -11.0F));

        ModelPartData LLB = Body.addChild("LLB", ModelPartBuilder.create().uv(26, 29).cuboid(-3.0F, 0.0F, -2.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 0.0F, 10.0F));

        ModelPartData LRB = Body.addChild("LRB", ModelPartBuilder.create().uv(38, 29).cuboid(0.0F, 0.0F, -2.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, 10.0F));

        ModelPartData LRF = Body.addChild("LRF", ModelPartBuilder.create().uv(38, 42).cuboid(0.0F, 0.0F, -1.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 0.0F, -10.0F));

        ModelPartData LLF = Body.addChild("LLF", ModelPartBuilder.create().uv(26, 42).cuboid(-3.0F, 0.0F, -1.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 0.0F, -10.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(DeerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        //this.animateMovement(DeerAnimations.ANIM_DEER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        //this.updateAnimation(entity.idleAnimationState, DeerAnimations.ANIM_DEER_IDLE, ageInTicks, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch) {
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.Head.yaw = headYaw * 0.017453292F;
        this.Head.pitch = headPitch * 0.017453292F;
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Body;
    }
}
