package de.josan.backrooms.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import de.josan.backrooms.util.ShimmerCurseAccess;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class CurseCommand {
    public static Collection<UUID> penisfuchs = new HashSet<>();
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("curse")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("shimmer")
                        .then(CommandManager.argument("action", StringArgumentType.word())
                                .suggests((context, builder) ->
                                        CommandSource.suggestMatching(
                                                new String[]{"add", "remove"},
                                                builder
                                        )
                                )
                                .then(CommandManager.argument("players", EntityArgumentType.players())
                                        .executes(context -> shimmer(context.getSource(), StringArgumentType.getString(context, "action"), EntityArgumentType.getPlayers(context, "players"))))))
                .then(CommandManager.literal("penisfuchs")
                        .then(CommandManager.argument("action", StringArgumentType.word())
                                .suggests((context, builder) ->
                                        CommandSource.suggestMatching(
                                                new String[]{"add", "remove"},
                                                builder
                                        )
                                )
                                .then(CommandManager.argument("players", EntityArgumentType.players())
                                        .executes(context -> penisfuchs(context.getSource(), StringArgumentType.getString(context, "action"), EntityArgumentType.getPlayers(context, "players")))))));

    }

    private static int shimmer(ServerCommandSource source, String action, Collection<ServerPlayerEntity> players) {
        int num = 0;

        String act = null;
        String byverb = null;
        switch (action) {
            case "add" -> {
                act = "Added";
                byverb = "to";
            }
            case "remove" -> {
                act = "Removed";
                byverb = "from";
            }
        }

        if (act == null) {
            source.sendError(Text.translatable("commands.curse.fail.argument", action));
            return 0;
        }

        for (ServerPlayerEntity plr : players) {
            ShimmerCurseAccess curse = (ShimmerCurseAccess) plr;

            switch (action) {
                case "add" -> {
                    if (!curse.backrooms$hasShimmerCurse()) {
                        curse.backrooms$setShimmerCurse(true);
                        num++;
                    }
                }
                case "remove" -> {
                    if (curse.backrooms$hasShimmerCurse()) {
                        curse.backrooms$setShimmerCurse(false);
                        num++;
                    }
                }
            }
        }
        if (num == 0) {
            if (players.isEmpty()) {
                source.sendFeedback(() -> Text.translatable("commands.curse.fail"), false);
            } else {
                source.sendFeedback(() -> Text.translatable("commands.curse.fail.already"), false);
            }
            return 0;
        }
        final int count = num;

        final String act_fin = act;
        final String byverb_fin = byverb;

        if (num == 1) {
            source.sendFeedback(() -> Text.translatable("commands.curse.success", "ShimmerCurse", act_fin, byverb_fin), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.curse.success.multiple", "ShimmerCurse", act_fin, byverb_fin, count), true);
        }
        return count;
    }

    private static int penisfuchs(ServerCommandSource source, String action, Collection<ServerPlayerEntity> players) {
        int num = 0;

        String act = null;
        String byverb = null;
        switch (action) {
            case "add" -> {
                act = "Added";
                byverb = "to";
            }
            case "remove" -> {
                act = "Removed";
                byverb = "from";
            }
        }

        if (act == null) {
            source.sendError(Text.translatable("commands.curse.fail.argument", action));
            return 0;
        }

        for (ServerPlayerEntity plr : players) {
            UUID uuid = plr.getUuid();

            switch (action) {
                case "add" -> {
                    if (penisfuchs.add(uuid)) {
                        num++;
                    }
                }
                case "remove" -> {
                    if (penisfuchs.remove(uuid)) {
                        num++;
                    }
                }
            }
        }
        if (num == 0) {
            if (players.isEmpty()) {
                source.sendFeedback(() -> Text.translatable("commands.curse.fail"), false);
            } else {
                source.sendFeedback(() -> Text.translatable("commands.curse.fail.already"), false);
            }
            return 0;
        }
        final int count = num;

        final String act_fin = act;
        final String byverb_fin = byverb;

        if (num == 1) {
            source.sendFeedback(() -> Text.translatable("commands.curse.success", "PenisFuchs", act_fin, byverb_fin), true);
        } else {
            source.sendFeedback(() -> Text.translatable("commands.curse.success.multiple", "PenisFuchs", act_fin, byverb_fin, count), true);
        }
        return count;
    }
}
