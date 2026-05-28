package de.josan.backrooms.item.custom;

import com.mojang.serialization.Codec;
import de.josan.backrooms.Backrooms;
import de.josan.backrooms.effect.ModEffects;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
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

    public static final AttachmentType<Integer> MOTHJELLY_OVERDOSE =
            AttachmentRegistry.create(
                    Backrooms.id("mothjelly_overdose"),
                    builder -> builder
                            .initializer(() -> 0)
                            .persistent(Codec.INT)
            );
    public static final AttachmentType<Integer> MOTHJELLY_DECREASE =
            AttachmentRegistry.create(
                    Backrooms.id("mothjelly_decrease"),
                    builder -> builder
                            .initializer(() -> 0)
                            .persistent(Codec.INT)
            );

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!user.isPlayer()) { return stack; }
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
        //int duration = Objects.requireNonNull(user.getStatusEffect(ModEffects.SANITY)).getDuration();
        user.addStatusEffect(new StatusEffectInstance(ModEffects.SANITY, 6000, 0, true, false, true));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 2, true, false, true));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1, true, false, true));
    }

    private void overdoseCheck(LivingEntity user) {
        if (user.getWorld().isClient) {
            return;
        }

        int current = user.getAttachedOrCreate(MOTHJELLY_OVERDOSE);
        if (current < 3) {
            user.setAttached(MOTHJELLY_OVERDOSE, (current + 1));
        }

        if (current >= 3) {
            user.damage(user.getDamageSources().magic(), 100.0F);
        }
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
