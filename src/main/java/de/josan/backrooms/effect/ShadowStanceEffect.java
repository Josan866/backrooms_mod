package de.josan.backrooms.effect;

import de.josan.backrooms.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.Map;

public class ShadowStanceEffect extends StatusEffect {
    protected ShadowStanceEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        entity.setHealth(entity.getHealth() + 20);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (entity.getWorld().isClient) {
            return true;
        }

        spawnParticle(entity.getWorld(), entity.getBlockPos().up().south().east());

        return true;
    }

    private static void spawnParticle(World world, BlockPos pos) {
        if (world.isClient) { return; }
        Random random = world.getRandom();
        SimpleParticleType simpleParticleType = ParticleTypes.SMOKE;
        if (world.getServer().getWorld(world.getRegistryKey()) == null) {
            world.addImportantParticle(
                    simpleParticleType,
                    true,
                    pos.getX() + random.nextDouble() - 0.5,
                    pos.getY() + random.nextDouble() * 2 - 1.0,
                    pos.getZ() + random.nextDouble() - 0.5,
                    0.01,
                    0.01,
                    0.01
            );
        } else {
            world.getServer().getWorld(world.getRegistryKey()).spawnParticles(
                    simpleParticleType,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    300,
                    0.5,
                    1.0,
                    0.5,
                    0.01

            );
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 2 == 0;
    }
}
