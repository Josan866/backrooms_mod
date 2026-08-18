package de.josan.backrooms.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import de.josan.backrooms.Backrooms;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.security.Permission;

public class SpeedCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("speed").requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("set")
                        .then(CommandManager.argument("speed", IntegerArgumentType.integer())
                                .executes(context -> setSpeed(context.getSource(), IntegerArgumentType.getInteger(context, "speed")))))
                .then(CommandManager.literal("get")
                        .executes(context -> getSpeed(context.getSource()))));
    }

    private static int setSpeed(ServerCommandSource source, int speed) {
        Backrooms.speed_shadow = speed;
        source.sendMessage(Text.literal("Changed Speed to " + speed));
        return 1;
    }

    private static int getSpeed(ServerCommandSource source) {
        source.sendMessage(Text.literal("Speed: " + Backrooms.speed_shadow));
        return 1;
    }
}
