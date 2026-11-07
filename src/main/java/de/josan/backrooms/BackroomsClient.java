package de.josan.backrooms;

import de.josan.backrooms.block.entity.ModBlockEntities;
import de.josan.backrooms.block.entity.renderer.PedestalBlockEntityRenderer;
import de.josan.backrooms.client.CurseHudRenderer;
import de.josan.backrooms.client.RansomHudRenderer;
import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.entity.client.DeerModel;
import de.josan.backrooms.entity.client.DeerRenderer;
import de.josan.backrooms.item.ModItems;
import de.josan.backrooms.screen.ModScreenHandlers;
import de.josan.backrooms.screen.custom.PedestalScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;

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

        /*FabricModelPredicateProviderRegistry.register(
                ModItems.TRIDENT,
                Identifier.ofVanilla("throwing"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }

                    return entity.isUsingItem()
                            && entity.getActiveItem() == stack
                            && stack.getUseAction() == UseAction.SPEAR
                            ? 1.0F : 0.0F;
                }
        );*/

        FabricModelPredicateProviderRegistry.register(
                ModItems.RUNAAN_BOW,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> {
                    return entity != null
                            && entity.isUsingItem()
                            && entity.getActiveItem() == stack ? 1.0F : 0.0F;
                }
        );

        FabricModelPredicateProviderRegistry.register(
                ModItems.RUNAAN_BOW,
                Identifier.ofVanilla("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null || !entity.isUsingItem()
                            || entity.getActiveItem() != stack) {
                        return 0.0F;
                    }

                    return (stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft())
                            / 20.0F;
                }
        );
    }
}
