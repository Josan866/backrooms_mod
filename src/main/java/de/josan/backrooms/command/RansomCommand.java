package de.josan.backrooms.command;

import com.mojang.brigadier.CommandDispatcher;
import de.josan.backrooms.util.RansomData;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.*;

public class RansomCommand {
    public static boolean ransomActive = false;
    // Liste aller Spieler, die für zukünftige zufällige Ransoms bereitstehen
    public static final List<UUID> ransomQueue = new ArrayList<>();

    // Speichert den aktuell angegriffenen Spieler und seine eingefrorenen Daten
    public static final Map<UUID, RansomData> activeAttackers = new HashMap<>();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("ransom")
                .requires(source -> source.hasPermissionLevel(1))
                        .then(CommandManager.literal("ransom")
                                .then(CommandManager.literal("start")
                                        .executes(context -> startRansom(context.getSource())))
                                .then(CommandManager.literal("stop")
                                        .executes(context -> stopRansom(context.getSource())))
                                .then(CommandManager.literal("get")
                                        .executes(context -> getRansom(context.getSource()))))
                        .then(CommandManager.literal("player")
                                .then(CommandManager.literal("add")
                                        .then(CommandManager.argument("players", EntityArgumentType.players())
                                                .executes(context -> addToQueue(context.getSource(), EntityArgumentType.getPlayers(context, "players")))))
                                .then(CommandManager.literal("remove")
                                        .then(CommandManager.argument("players", EntityArgumentType.players())
                                                .executes(context -> removeFromQueue(context.getSource(), EntityArgumentType.getPlayers(context, "players")))))
                                .then(CommandManager.literal("list")
                                        .executes(context -> getQueue(context.getSource())))));
    }

    private static int startRansom(ServerCommandSource source) {
        final boolean out = ransomActive;
        ransomActive = true;
        if(!out)
            source.sendFeedback(() -> Text.translatable("commands.ransom.ransom.start.success"), false);
        else
            source.sendFeedback(() -> Text.translatable("commands.ransom.ransom.start.failed"), false);
        return 1;
    }

    private static int stopRansom(ServerCommandSource source) {
        final boolean out = ransomActive;
        ransomActive = false;
        if(out)
            source.sendFeedback(() -> Text.translatable("commands.ransom.ransom.stop.success"), false);
        else
            source.sendFeedback(() -> Text.translatable("commands.ransom.ransom.stop.failed"), false);
        return 1;
    }

    private static int getRansom(ServerCommandSource source) {
        final boolean out = ransomActive;
        source.sendFeedback(() -> Text.translatable("commands.ransom.ransom.get", out), false);
        return 1;
    }

    private static int addToQueue(ServerCommandSource source, Collection<ServerPlayerEntity> players) {
        int count = 0;
        for (ServerPlayerEntity plr : players) {
            if (!ransomQueue.contains(plr.getUuid()) && !activeAttackers.containsKey(plr.getUuid())) {
                ransomQueue.add(plr.getUuid());
                count++;
            }
        }
        final int out = count;
        source.sendFeedback(() -> Text.translatable("commands.ransom.player.add", out), false);
        return 1;
    }

    private static int removeFromQueue(ServerCommandSource source, Collection<ServerPlayerEntity> players) {
        int count = 0;
        for (ServerPlayerEntity plr : players) {
            UUID uuid = plr.getUuid();
            if (ransomQueue.contains(uuid)) {
                ransomQueue.remove(uuid);
                count++;
            }
            if (activeAttackers.containsKey(uuid)) {
                activeAttackers.remove(uuid);
                count++;
            }
        }
        final int out = count;
        source.sendFeedback(() -> Text.translatable("commands.ransom.player.remove", out), false);
        return 1;
    }

    private static int getQueue(ServerCommandSource source) {
        StringBuilder plrS = new StringBuilder();

        for (UUID uuid : ransomQueue) {
            if (plrS.length() > 0) {
                plrS.append(", ");
            }
            // 3. Holt den echten Anzeigenamen des Spielers anstelle des Entity-Objekt-Strings
            plrS.append(source.getServer().getPlayerManager().getPlayer(uuid).getName().toString());
        }

        String out = plrS.length() == 0 ? "Niemand" : plrS.toString();
        source.sendFeedback(() -> Text.translatable("commands.ransom.player.list", out), false);
        return 1;
    }
}
