package de.josan.backrooms.block.entity;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.block.ModBlocks;
import de.josan.backrooms.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Backrooms.id("pedestal_be"),
                    BlockEntityType.Builder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static void registerBlockEntities() {
        Backrooms.LOGGER.info("Register Blocks Entities For: " + Backrooms.MOD_ID);
    }
}
