package de.josan.backrooms.effect;

import de.josan.backrooms.Backrooms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class TestEffect extends StatusEffect {
    protected TestEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        /*if (entity.isPlayer()) {
            getServerPlayerEntity(entity).sendMessage(Text.translatable("commands.backrooms_test", Backrooms.speed), true);
        }*/
        return true;
    }

    private ServerPlayerEntity getServerPlayerEntity(LivingEntity entity) {
        return entity.getServer().getPlayerManager().getPlayer(entity.getUuid());
    }
}
