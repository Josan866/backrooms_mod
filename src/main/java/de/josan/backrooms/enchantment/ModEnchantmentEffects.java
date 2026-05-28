package de.josan.backrooms.enchantment;

import com.mojang.serialization.MapCodec;
import de.josan.backrooms.Backrooms;
import de.josan.backrooms.enchantment.custom.BloodlossEnchantmentEffect;
import de.josan.backrooms.enchantment.custom.YeetEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> BLOODLOSS =
            registerEntityEffect("bloodloss", BloodlossEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> YEET =
            registerEntityEffect("yeet", YeetEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Backrooms.id(name), codec);
    }

    public static void registerModEnchantmentEffects() {
        Backrooms.LOGGER.info("Register EnchantmentEffects for: " + Backrooms.MOD_ID);
    }
}
