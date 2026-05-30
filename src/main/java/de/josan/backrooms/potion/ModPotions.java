package de.josan.backrooms.potion;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModPotions {
    public static final RegistryEntry<Potion> SLIMEY_POTION = registerPotion("slimey_potion",
            new Potion(new StatusEffectInstance(ModEffects.SLIMEY, 1200, 0)));
    public static final RegistryEntry<Potion> SANITY_POTION = registerPotion("sanity_potion",
            new Potion(new StatusEffectInstance(ModEffects.SANITY, 1200, 0)));

    //register
    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Backrooms.id(name), potion);
    }
    //Initialize
    public static void registerModPotions() {
        Backrooms.LOGGER.info("Register Potions for: " + Backrooms.MOD_ID);
    }
}
