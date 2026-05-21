package de.josan.backrooms;

import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.entity.client.DeerModel;
import de.josan.backrooms.entity.client.DeerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BackroomsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //MobEntitys
        EntityModelLayerRegistry.registerModelLayer(DeerModel.DEER, DeerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DEER, DeerRenderer::new);
    }
}
