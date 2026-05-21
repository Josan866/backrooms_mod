package de.josan.backrooms.datagen;

import de.josan.backrooms.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootGen extends FabricBlockLootTableProvider {
    protected ModBlockLootGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.WHITE_ABYSS);
        addDrop(ModBlocks.BLACK_ABYSS);
        addDrop(ModBlocks.WALL);

        addDrop(ModBlocks.BACKROOMS_DOOR);
        addDrop(ModBlocks.BACKROOMS_DOOR_LOCKED);
    }
}
