package de.josan.backrooms.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Spawn_relocate extends Item {
    public Spawn_relocate(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        for (String player : user.getServer().getPlayerNames()) {
            user.sendMessage(Text.translatable("commands.player", player));
        }

        setSpawnPoint(getServerPlayerEntity(user), user);
        if (!user.isCreative()) {
            user.getStackInHand(hand).setCount(user.getStackInHand(hand).getCount() - 1);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private static ServerPlayerEntity getServerPlayerEntity(PlayerEntity user) {
        return user.getServer().getPlayerManager().getPlayer(user.getUuid());
    }

    private static void setSpawnPoint(ServerPlayerEntity player, PlayerEntity user) {
        RegistryKey<World> registryKey = user.getWorld().getRegistryKey();
        BlockPos pos = user.getBlockPos();
        player.setSpawnPoint(registryKey, pos, 0f, true, false);
    }
}
