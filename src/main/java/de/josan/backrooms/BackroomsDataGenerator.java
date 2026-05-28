package de.josan.backrooms;

import de.josan.backrooms.datagen.*;
import de.josan.backrooms.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class BackroomsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        //pack.addProvider(ModBlockTagGen::new); // Minable with
        //pack.addProvider(ModItemTagGen::new); // ItemTags...IDK //NotWorking
        //pack.addProvider(ModBlockLootGen::new); // Lot/BlockDrops //NotWorking
        pack.addProvider(ModModelGen::new); // Model/Blockstate
        pack.addProvider(ModRegistryDataGen::new); // Registry oder so
	}

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantments::bootstrap);
    }
}
