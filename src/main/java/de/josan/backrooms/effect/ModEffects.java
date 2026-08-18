package de.josan.backrooms.effect;

import de.josan.backrooms.Backrooms;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {

    public static final RegistryEntry<StatusEffect> SANITY = registerStatusEffect("sanity",
            new BackroomsSanityEffect(StatusEffectCategory.NEUTRAL, 0xffffff)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(Backrooms.MOD_ID, "sanity"), +0.15f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> BLOODLOSS = registerStatusEffect("bloodloss",
            new BloodlossEffect(StatusEffectCategory.HARMFUL, 0xff0000)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(Backrooms.MOD_ID, "bloodloss"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> SHADOW_STANCE = registerStatusEffect("shadow_stance",
            new ShadowStanceEffect(StatusEffectCategory.NEUTRAL, 0x000000)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Backrooms.id("shadow_stance"), +Backrooms.speed_shadow,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            Backrooms.id("shadow_stance"), -4.0f,
                            EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_MAX_ABSORPTION,
                            Backrooms.id("shadow_stance"), +80.0f,
                            EntityAttributeModifier.Operation.ADD_VALUE));

    public static final RegistryEntry<StatusEffect> FLY = registerStatusEffect("fly",
            new FlyEffect(StatusEffectCategory.BENEFICIAL, 0xffffff)
                    .addAttributeModifier(EntityAttributes.GENERIC_FLYING_SPEED,
                            Backrooms.id("fly"), -6.6f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> SUNSHY = registerStatusEffect("sunshy",
            new SunShyEffect(StatusEffectCategory.HARMFUL, 0x000000)
                    .addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH,
                            Identifier.of(Backrooms.MOD_ID, "sunshy"), +0.5f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> SLIMEY = registerStatusEffect("slimey",
            new SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x00ff00)
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,
                            Identifier.of(Backrooms.MOD_ID, "slimey"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    //RegisterEffect
    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Backrooms.MOD_ID, name), statusEffect);
    }
    //Initialize
    public static void registerEffects() {
        Backrooms.LOGGER.info("Register Effects for: " + Backrooms.MOD_ID);
    }
}
