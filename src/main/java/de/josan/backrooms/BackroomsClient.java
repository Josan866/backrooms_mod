package de.josan.backrooms;

import de.josan.backrooms.block.entity.ModBlockEntities;
import de.josan.backrooms.block.entity.renderer.PedestalBlockEntityRenderer;
import de.josan.backrooms.client.CurseHudRenderer;
import de.josan.backrooms.client.RansomHudRenderer;
import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.entity.client.DeerModel;
import de.josan.backrooms.entity.client.DeerRenderer;
import de.josan.backrooms.screen.ModScreenHandlers;
import de.josan.backrooms.screen.custom.PedestalScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BackroomsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //MobEntitys
        EntityModelLayerRegistry.registerModelLayer(DeerModel.DEER, DeerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DEER, DeerRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);
        HandledScreens.register(ModScreenHandlers.PEDESTAL_SCREEN_HANDLER, PedestalScreen::new);

        RansomHudRenderer.register();
        CurseHudRenderer.register();
    }
}
