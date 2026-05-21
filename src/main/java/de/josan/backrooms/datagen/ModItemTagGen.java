package de.josan.backrooms.datagen;

import de.josan.backrooms.block.ModBlocks;
import de.josan.backrooms.index.tag.ModItemTags;
import de.josan.backrooms.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.jukebox.JukeboxSongs;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGen extends FabricTagProvider.ItemTagProvider {
    public ModItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ModItemTags.ADVANCEMENTS)
                .add(ModItems.ADVANCEMENT_01)
                .add(ModItems.ADVANCEMENT_02)
                .add(ModItems.ADVANCEMENT_03)
                .add(ModItems.ADVANCEMENT_04)
                .add(ModItems.ADVANCEMENT_05)
                .add(ModItems.ADVANCEMENT_06)
                .add(ModItems.ADVANCEMENT_07)
                .add(ModItems.ADVANCEMENT_08)
                .add(ModItems.ADVANCEMENT_09);
    }
}
