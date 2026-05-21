package de.josan.backrooms.worldgen.biome;

import de.josan.backrooms.Backrooms;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class ModBiomes {
    //public static final RegistryKey<Biome> RED_CITY = register()


    //private static Biome registerBiome(String name, Biome biome) {
    //    return Registry.register(Registries.BIOME_SOURCE, Identifier.of(Backrooms.MOD_ID, name), biome);
    //}

    public static void registerModBiomes() {
        Backrooms.LOGGER.info("Register Biomes For: " + Backrooms.MOD_ID);
    }
}
