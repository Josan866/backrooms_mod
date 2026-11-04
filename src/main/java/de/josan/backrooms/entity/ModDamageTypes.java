package de.josan.backrooms.entity;

import de.josan.backrooms.Backrooms;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface ModDamageTypes {
    RegistryKey<DamageType> SHIMMER_CURSE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Backrooms.id("shimmer_curse"));
}
