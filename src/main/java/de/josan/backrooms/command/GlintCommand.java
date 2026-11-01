
package de.josan.backrooms.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import de.josan.backrooms.component.ModDataComponentTypes;
import net.minecraft.command.CommandSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.Map;

public class GlintCommand {

    private static final Map<String, Integer> GLINTS = Map.of(
            "red", 1,
            "green", 2,
            "pink", 3,
            "gold", 4,
            "white", 5
    );

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(
                CommandManager.literal("glint")

                        .then(CommandManager.literal("set")
                                .then(CommandManager.argument("type", StringArgumentType.word())
                                        .suggests((context, builder) ->
                                                CommandSource.suggestMatching(
                                                        new String[]{"normal", "red", "green", "pink", "gold", "white"},
                                                        builder
                                                )
                                        )
                                        .executes(context -> {

                                            ServerCommandSource source = context.getSource();

                                            var player = source.getPlayerOrThrow();
                                            ItemStack stack = player.getMainHandStack();

                                            if (stack.isEmpty()) {
                                                source.sendError(Text.literal(
                                                        "Du musst ein Item in der Haupthand halten!"
                                                ));
                                                return 0;
                                            }

                                            String type = StringArgumentType.getString(context, "type");

                                            if (type.equals("normal")) {
                                                stack.remove(ModDataComponentTypes.GLINT_TYPE);

                                                source.sendFeedback(() ->
                                                        Text.literal("Glint auf Vanilla zurückgesetzt!"), true);

                                                return 1;
                                            }

                                            Integer glintId = GLINTS.get(type);

                                            if (glintId == null) {
                                                source.sendError(Text.literal(
                                                        "Unbekannter Glint! Verfügbar: normal, red, green, pink"
                                                ));
                                                return 0;
                                            }

                                            stack.set(ModDataComponentTypes.GLINT_TYPE, glintId);

                                            source.sendFeedback(() ->
                                                    Text.literal("Glint geändert zu: " + type), true);

                                            return 1;
                                        })
                                )
                        )

                        .then(CommandManager.literal("remove")
                                .executes(context -> {

                                    var player = context.getSource().getPlayerOrThrow();
                                    ItemStack stack = player.getMainHandStack();

                                    if (stack.isEmpty()) {
                                        context.getSource().sendError(Text.literal(
                                                "Du musst ein Item in der Haupthand halten!"
                                        ));
                                        return 0;
                                    }

                                    stack.remove(ModDataComponentTypes.GLINT_TYPE);

                                    context.getSource().sendFeedback(() ->
                                            Text.literal("Eigener Glint entfernt!"), true);

                                    return 1;
                                })
                        )
        );
    }
}
