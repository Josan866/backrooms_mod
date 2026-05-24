package de.josan.backrooms.item.custom;

import de.josan.backrooms.component.ModDataComponentTypes;
import de.josan.backrooms.effect.ModEffects;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MothJelly extends Item {
    public MothJelly(Settings settings) {
        super(settings);
    }

    private static final int MAX_USE_TIME = 32;

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!world.isClient) {
            applyEffectsTo(user);
            overdoseCheck(user);
        }

        if (user instanceof PlayerEntity playerEntity) {
            return ItemUsage.exchangeStack(stack, playerEntity, new ItemStack(Items.AIR), false);
        } else {
            stack.decrementUnlessCreative(1, user);
            return stack;
        }
    }

    private static ServerPlayerEntity getServerPlayerEntity(PlayerEntity user) {
        return user.getServer().getPlayerManager().getPlayer(user.getUuid());
    }

    private void applyEffectsTo(LivingEntity user) {
        user.addStatusEffect(new StatusEffectInstance(ModEffects.SANITY, 6000, 0, true, false, true));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 2, true, false, true));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1, true, false, true));
    }

    private void overdoseCheck(LivingEntity user) {
        //user.writeCustomDataToNbt(ModDataComponentTypes.MOTHJELLY_OVERDOSE, 1);
        return;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
