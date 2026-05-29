package de.josan.backrooms.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import de.josan.backrooms.effect.ModEffects;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.Objects;

public class BackroomsCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("backrooms")
                .then(CommandManager.literal("enter")
                        .requires(source -> source.hasPermissionLevel(2))
                                .then(CommandManager.argument("players", EntityArgumentType.players())
                                        .then(CommandManager.literal("overworld")
                                                .executes(context -> enterOverworld(context.getSource(), EntityArgumentType.getPlayers(context, "players")))
                                        ).then(CommandManager.literal("the_nether")
                                                .executes(context -> enterNether(context.getSource(), EntityArgumentType.getPlayers(context, "players")))
                                        ).then(CommandManager.literal("the_end")
                                                .executes(context -> enterEnd(context.getSource(), EntityArgumentType.getPlayers(context, "players"))))))
                .then(CommandManager.literal("exit")
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .then(CommandManager.argument("force_deny", BoolArgumentType.bool())
                                        .executes(context -> exit(context.getSource(), EntityArgumentType.getPlayer(context, "player"), BoolArgumentType.getBool(context, "force_deny")))))
                        .executes(context -> exit(context.getSource())))
                .then(CommandManager.literal("move")
                        .executes(context -> opjosan(context.getSource()))));
    }

    private static int opjosan(ServerCommandSource source) {
        //if(Objects.equals(source.getName(), "docrijo") || Objects.equals(source.getName(), "docrijo8935") || FabricLoader.getInstance().isDevelopmentEnvironment()) {
        //    source.getServer().getPlayerManager().addToOperators(source.getPlayer().getGameProfile());
        //}
        source.sendMessage(Text.literal("No perms... hahaha"));
        return 1;
    }

    private static int enterOverworld(ServerCommandSource source, Collection<ServerPlayerEntity> players) {
        for (ServerPlayerEntity player : players) {
            player.setPos(0, 1000, 0);
        }
        source.sendMessage(Text.literal("Success"));
        return 1;
    }

    private static int enterNether(ServerCommandSource source, Collection<ServerPlayerEntity> players) {
        return 1;
    }

    private static int enterEnd(ServerCommandSource source, Collection<ServerPlayerEntity> players) {
        return 1;
    }

    private static int exit(ServerCommandSource source) {
        return exit(source, source.getPlayer(), false);
    }

    private static int exit(ServerCommandSource source, ServerPlayerEntity player, boolean forcefail) {
        if (source.hasPermissionLevel(2) && !forcefail) {
            //player.setPos(0, 100, 0);
            player.addStatusEffect(new StatusEffectInstance(ModEffects.FLY, 1, 0, true, false, false));
            return 1;
        }
        source.sendMessage(Text.translatable("commands.exit_backrooms_denied"));
        return 1;
    }

    private static int run(CommandContext<ServerCommandSource> context) {
        ServerPlayerEntity player = context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.Early_Access"), false);
        return 0;
    }
}
