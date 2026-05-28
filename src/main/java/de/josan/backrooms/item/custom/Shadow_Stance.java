package de.josan.backrooms.item.custom;

import de.josan.backrooms.effect.ModEffects;
import de.josan.backrooms.sound.ModSounds;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Objects;

public class Shadow_Stance extends Item {
    public Shadow_Stance(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(ModSounds.SHADOW_STANCE, 1, 1);
        if (!user.isPlayer()) { return TypedActionResult.pass(user.getStackInHand(hand)); }
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        if (user.hasStatusEffect(ModEffects.SHADOW_STANCE)) {
            user.setStatusEffect(new StatusEffectInstance(ModEffects.SHADOW_STANCE, 0, 0), user);
        } else {
            user.addStatusEffect(new StatusEffectInstance(ModEffects.SHADOW_STANCE, -1, 0, true, false, true));
        }

        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
