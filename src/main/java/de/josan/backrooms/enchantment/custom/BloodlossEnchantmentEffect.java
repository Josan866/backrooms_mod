package de.josan.backrooms.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import de.josan.backrooms.Backrooms;
import de.josan.backrooms.sound.ModSounds;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import static java.lang.Math.round;

public record BloodlossEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<BloodlossEnchantmentEffect> CODEC = MapCodec.unit(BloodlossEnchantmentEffect::new);

    public static final AttachmentType<Integer> BLOODLOSS_ATTACK =
            AttachmentRegistry.create(
                    Backrooms.id("bloodloss_attack"),
                    builder -> builder
                            .initializer(() -> 0)
                            .persistent(Codec.INT)
                            .copyOnDeath()
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        int current = user.getAttachedOrCreate(BLOODLOSS_ATTACK);
        if (current < (7 - level)) {
            user.setAttached(BLOODLOSS_ATTACK, (current + 1));
            return;
        }
        user.setAttached(BLOODLOSS_ATTACK, 0);
        if (level == 255) {
            user.damage(user.getDamageSources().magic(), 999999999);
        } else {
            user.damage(user.getDamageSources().magic(), Math.round(level / 2.0f));
            user.playSound(ModSounds.BLOODLOSS, 1, 1);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
