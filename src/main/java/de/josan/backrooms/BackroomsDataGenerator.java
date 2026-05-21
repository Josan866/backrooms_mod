package de.josan.backrooms;

import de.josan.backrooms.datagen.ModBlockLootGen;
import de.josan.backrooms.datagen.ModBlockTagGen;
import de.josan.backrooms.datagen.ModItemTagGen;
import de.josan.backrooms.datagen.ModModelGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BackroomsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        //pack.addProvider(ModBlockTagGen::new); // Minable with
        //pack.addProvider(ModItemTagGen::new); // ItemTags...IDK //NotWorking
        //pack.addProvider(ModBlockLootGen::new); // Lot/BlockDrops //NotWorking
        pack.addProvider(ModModelGen::new); // Model/Blockstate
	}
}
