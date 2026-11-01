package de.josan.backrooms;

import de.josan.backrooms.block.entity.ModBlockEntities;
import de.josan.backrooms.command.BackroomsCommand;
import de.josan.backrooms.command.GlintCommand;
import de.josan.backrooms.command.RansomCommand;
import de.josan.backrooms.command.SpeedCommand;
import de.josan.backrooms.component.ModDataComponentTypes;
import de.josan.backrooms.enchantment.ModEnchantmentEffects;
import de.josan.backrooms.event.RansomTicker;
import de.josan.backrooms.network.RansomClosePayload;
import de.josan.backrooms.network.RansomPopupPayload;
import de.josan.backrooms.potion.ModPotions;
import de.josan.backrooms.screen.ModScreenHandlers;
import de.josan.backrooms.sound.ModSounds;
import de.josan.backrooms.block.ModBlocks;
import de.josan.backrooms.effect.ModEffects;
import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.entity.custom.DeerEntity;
import de.josan.backrooms.item.ModItemGroups;
import de.josan.backrooms.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Backrooms implements ModInitializer {
	public static final String MOD_ID = "backrooms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //private static void registerCommands() {
    //    CommandRegistrationCallback.EVENT.register(BackroomsCommand::register);
    //}

    public static Identifier id (String name) {
        return Identifier.of(MOD_ID, name);
    }

    public static boolean AccessAllowed(PlayerEntity player) {
        if (player == null || !player.getClass().equals(ServerPlayerEntity.class)) return false;

        if (isEarlyAccess(player) || FabricLoader.getInstance().isDevelopmentEnvironment()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasCorruption(net.minecraft.item.ItemStack stack) {
        if (stack == null || stack.isEmpty()) return false;
        var nbtComponent = stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent != null) {
            return nbtComponent.copyNbt().getBoolean("RansomCorrupted");
        }
        return false;
    }

    private static boolean isEarlyAccess(PlayerEntity player) {
        return false;
    }

    public static float speed_shadow = 5.5f;

	@Override
	public void onInitialize() {


        //Items
        ModItems.registerModItems();

        //ItemGroups
        ModItemGroups.registerModItemGroups();

        //Blocks
        ModBlocks.registerModBlocks();

        //Entities
        ModEntities.registerModEntities();
        FabricDefaultAttributeRegistry.register(ModEntities.DEER, DeerEntity.createAttributes());

        //Effects
        ModEffects.registerEffects();

        //Sounds
        ModSounds.registerSounds();

        //EnchantmentEffects
        ModEnchantmentEffects.registerModEnchantmentEffects();

        //BlockEntities
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();

        //Event
        RansomTicker.register();
        PayloadTypeRegistry.playS2C().register(RansomClosePayload.ID, RansomClosePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(RansomPopupPayload.ID, RansomPopupPayload.CODEC);

        //Potions
        ModPotions.registerModPotions();

        //Components
        ModDataComponentTypes.registerDataComponentTypes();

        //Potion_recipe
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Items.SLIME_BALL, ModPotions.SLIMEY_POTION);
            builder.registerPotionRecipe(Potions.WATER, ModItems.MOTH_JELLY, ModPotions.SANITY_POTION);
            //builder.registerPotionRecipe(ModPotions.SANITY_POTION, Items.NETHER_STAR, ModPotions.SHIMMER);
        });

        //Commands
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            BackroomsCommand.register(dispatcher);
            SpeedCommand.register(dispatcher);
            RansomCommand.register(dispatcher);
            GlintCommand.register(dispatcher);
        }));

        // 1. EVENT: Wenn ein Spieler den Server verlässt (Disconnect)
        net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            net.minecraft.server.network.ServerPlayerEntity player = handler.getPlayer();

            // Prüfen, ob der Spieler im Erpresser-Modus war
            if (RansomCommand.activeAttackers.containsKey(player.getUuid())) {

                // OPTION A: Items säubern (Gnade)
                for (int i = 0; i < player.getInventory().size(); i++) {
                    var stack = player.getInventory().getStack(i);
                    if (hasCorruption(stack)) {
                        stack.remove(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
                        stack.remove(net.minecraft.component.DataComponentTypes.ITEM_NAME);
                    }
                }

                // Aus der aktiven Angriffsliste entfernen
                RansomCommand.activeAttackers.remove(player.getUuid());

                // OPTION B: Bestrafung (Falls du ihn für das Verlassen töten willst,
                // müsstest du seinen Status speichern, da player.kill() beim Disconnect nicht persistent ist)
            }
        });

        // 2. EVENT: Wenn ein Spieler dem Server beitritt (Join / Rejoin)
        net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            net.minecraft.server.network.ServerPlayerEntity player = handler.getPlayer();

            // Falls der Spieler nicht aktiv im Server-Ticker als "im Kampf" gelistet ist,
            // räumen wir die übrig gebliebenen korrumpierten Items auf.
            if (!RansomCommand.activeAttackers.containsKey(player.getUuid())) {
                boolean itemsDestroyed = false;

                for (int i = 0; i < player.getInventory().size(); i++) {
                    var stack = player.getInventory().getStack(i);

                    // Nutzt deine bestehende Hilfsmethode 'hasCorruption'
                    if (hasCorruption(stack)) {
                        // STRAFE FÜR COMBAT LOGGING: Das Item wird komplett gelöscht!
                        player.getInventory().setStack(i, net.minecraft.item.ItemStack.EMPTY);
                        itemsDestroyed = true;
                    }
                }

                // Wenn Items vernichtet wurden, senden wir das Schließ-Signal an den Client,
                // damit auch das HUD (Countdown, ❌-Overlays, Popups) sofort verschwindet.
                if (itemsDestroyed) {
                    player.sendMessage(net.minecraft.text.Text.translatable("ransom.info.leave"), false);

                    server.execute(() -> {
                        net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.send(
                                player,
                                new de.josan.backrooms.network.RansomClosePayload()
                        );
                    });
                }
            }
        });


        // 3. Kisten-Interaktion und Cursor-Kontrolle bei Server-Tick
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (net.minecraft.server.network.ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (RansomCommand.activeAttackers.containsKey(player.getUuid())) {
                    var screenHandler = player.currentScreenHandler;
                    if (screenHandler != null) {

                        // Cursor-Stack-Sicherung (bereits eingebaut)
                        var cursorStack = screenHandler.getCursorStack();
                        if (hasCorruption(cursorStack)) {
                            player.getInventory().offerOrDrop(cursorStack);
                            screenHandler.setCursorStack(net.minecraft.item.ItemStack.EMPTY);
                        }

                        // --- NEU: SHIFT-KLICK IN KISTEN BLOCKIEREN ---
                        // Wir loopen durch alle Slots des geöffneten Containers (z.B. Truhe, Fass)
                        // Kisten-Slots fangen meistens bei Slot 0 an. Spieler-Inventar-Slots liegen am Ende.
                        for (int i = 0; i < screenHandler.slots.size(); i++) {
                            var slot = screenHandler.getSlot(i);
                            // Prüfen, ob der Slot zu einem externen Container gehört (nicht zum Spieler-Inventar selbst)
                            if (slot.inventory != player.getInventory()) {
                                var stackInChest = slot.getStack();
                                if (hasCorruption(stackInChest)) {
                                    // Das Item wurde per Shift-Klick reingeschmuggelt!
                                    // Wir nehmen es aus der Kiste und zwingen es zurück in das Inventar des Spielers
                                    player.getInventory().offerOrDrop(stackInChest.copy());
                                    slot.setStack(net.minecraft.item.ItemStack.EMPTY);
                                    player.sendMessage(net.minecraft.text.Text.translatable("ransom.deny.quickmove"), true);
                                }
                            }
                        }
                        // ----------------------------------------------
                    }
                }
            }
        });

        // 4. Auf-den-Boden-Werfen (Q-Taste) blockieren
        net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof net.minecraft.entity.ItemEntity itemEntity) {
                net.minecraft.item.ItemStack stack = itemEntity.getStack();
                if (hasCorruption(stack) && itemEntity.getOwner() instanceof net.minecraft.server.network.ServerPlayerEntity player) {
                    if (RansomCommand.activeAttackers.containsKey(player.getUuid())) {
                        player.getInventory().offerOrDrop(stack);
                        itemEntity.discard(); // Zerstört das Entity auf dem Boden
                        player.sendMessage(net.minecraft.text.Text.translatable("ransom.deny.drop"), true);
                    }
                }
            }
        });

        // 5. Registriert die Sperre für das Platzieren von korrumpierten Blöcken
        net.fabricmc.fabric.api.event.player.UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            // Wir prüfen das auf dem Server, um Desynchronisationen zu verhindern
            if (!world.isClient()) {
                net.minecraft.item.ItemStack stackInHand = player.getStackInHand(hand);

                // Nutzt deine bestehende Hilfsmethode 'hasCorruption'
                if (hasCorruption(stackInHand)) {
                    // Sende eine Warnung in die Actionbar des Spielers
                    player.sendMessage(net.minecraft.text.Text.translatable("ransom.deny.place"), true);

                    // FAIL signalisiert Minecraft, dass die Aktion abgebrochen und blockiert wird
                    return net.minecraft.util.ActionResult.FAIL;
                }
            }
            // PASS erlaubt die normale Ausführung, wenn das Item nicht korrumpiert ist
            return net.minecraft.util.ActionResult.PASS;
        });
    }
}