package de.josan.backrooms.event;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.command.RansomCommand;
import de.josan.backrooms.network.RansomPopupPayload;
import de.josan.backrooms.util.RansomData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.*;

public class RansomTicker {
    public static final Random RANDOM = new Random();
    private static int randomCooldown = 200;
    private static int attackDuration = 0;

    // Verschiedene gruselige Fehlermeldungen im Stil von Doors A-90 / Ransom
    private static final String[] POPUP_MESSAGES = {
            "§4SYSTEM ERROR: RANSOM REQUIRED§r",
            "§cPAY 500 GOLD TO UNLOCK YOUR SCREEN§r",
            "§4ACCESS DENIED§r",
            "§cYOUR TIME IS RUNNING OUT!§r",
            "§4[⚠] CRITICAL MALFUNCTION [⚠]§r",
            "§cPAY NOW! PAY NOW! PAY NOW!§r"
    };

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(RansomTicker::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        // --- TEIL 1: ZUFÄLLIGES SPAWNEN ---
        if (RansomCommand.activeAttackers.isEmpty() && !RansomCommand.ransomQueue.isEmpty()) {
            randomCooldown--;
            if (randomCooldown <= 0) {
                int randomIndex = RANDOM.nextInt(RansomCommand.ransomQueue.size());
                UUID targetUuid = RansomCommand.ransomQueue.get(randomIndex);
                ServerPlayerEntity player = server.getPlayerManager().getPlayer(targetUuid);

                if (player != null) {
                    RansomCommand.activeAttackers.put(targetUuid, new RansomData(
                            player.getPos(), player.getYaw(), player.getPitch()
                    ));
                    attackDuration = 100; // 5 Sekunden Stillstehen am Anfang

                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0, false, false));
                    player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.PLAYERS, 1.0f, 0.5f);
                    sendTitle(player, "§4RANSOM", "§cDON'T MOVE! DON'T TURN!");
                }
                randomCooldown = 300 + RANDOM.nextInt(600);
            }
        }

        // --- TEIL 2: KONTROLLE DES ANGRIFFS ODER COUNTDOWNS ---
        if (!RansomCommand.activeAttackers.isEmpty()) {
            List<UUID> toRemove = new ArrayList<>();
            if (attackDuration > 0) attackDuration--;

            for (Map.Entry<UUID, RansomData> entry : RansomCommand.activeAttackers.entrySet()) {
                UUID uuid = entry.getKey();
                RansomData data = entry.getValue();
                ServerPlayerEntity player = server.getPlayerManager().getPlayer(uuid);

                if (player == null) {
                    toRemove.add(uuid);
                    continue;
                }

                // MODUS A: Der Spieler wurde erwischt und muss jetzt bezahlen (1 Min Countdown)
                if (data.inDemandMode) {
                    data.remainingTicks--;

                    // 1. Gold-Prüfung: Hat der Spieler 500 Goldbarren?
                    int goldCount = player.getInventory().count(net.minecraft.item.Items.GOLD_INGOT);
                    if (goldCount >= 500) {
                        // Erfolgreich freigekauft! 500 Gold abziehen
                        player.getInventory().remove(stack -> stack.isOf(net.minecraft.item.Items.GOLD_INGOT), 500, player.getInventory());

                        // --- NEU: ITEMS ENT-KORRUMPIEREN ---
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            var stack = player.getInventory().getStack(i);
                            if (Backrooms.hasCorruption(stack)) {
                                // 1. Entferne das NBT-Tag
                                stack.remove(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);

                                // 2. Setze den Namen auf den Standardnamen des Items zurück
                                stack.remove(net.minecraft.component.DataComponentTypes.ITEM_NAME);
                            }
                        }
                        // ------------------------------------

                        // Schließ-Befehl an den Client senden
                        net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.send(
                                player,
                                new de.josan.backrooms.network.RansomClosePayload()
                        );

                        sendTitle(player, "§aSYSTEM UNLOCKED", "§7Thank You!");
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                                net.minecraft.sound.SoundEvents.ENTITY_PLAYER_LEVELUP, net.minecraft.sound.SoundCategory.PLAYERS, 1.0f, 1.0f);
                        toRemove.add(uuid);
                        continue;
                    }

                    // 2. Zeit abgelaufen? -> Bildschirm schließen und töten
                    if (data.remainingTicks <= 0) {
                        // Schließ-Signal an Client senden
                        net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.send(player, new de.josan.backrooms.network.RansomClosePayload());

                        // Alle korrumpierten Gegenstände endgültig vernichten
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            var stack = player.getInventory().getStack(i);
                            if (Backrooms.hasCorruption(stack)) {
                                player.getInventory().setStack(i, net.minecraft.item.ItemStack.EMPTY);
                            }
                        }

                        player.damage(player.getDamageSources().magic(), 1000.0f); // Spieler stirbt
                        toRemove.add(uuid);
                        continue;
                    }



                    // --- NEU & KORRIGIERT: DAUERHAFTES POPUP-SPAM-TIMING ---
                    data.nextPopupTicks--;
                    boolean shouldSpawnPopup = false;
                    String chosenMessage = "";

                    // Prüfen, ob ein neues Fehlerfenster spawnen soll
                    if (data.nextPopupTicks <= 0) {
                        shouldSpawnPopup = true;
                        chosenMessage = POPUP_MESSAGES[RANDOM.nextInt(POPUP_MESSAGES.length)];

                        // Sound abspielen, wenn ein echtes Popup erzeugt wird
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                                net.minecraft.sound.SoundEvents.BLOCK_NOTE_BLOCK_BIT, net.minecraft.sound.SoundCategory.PLAYERS, 0.8f, 0.5f);

                        // Setzt die Verzögerung für das nächste Fenster (zwischen 1 und 2 Sekunden)
                        data.nextPopupTicks = 20 + RANDOM.nextInt(20);
                    }

                    // Jede Sekunde (oder wenn ein neues Popup erscheint) das Paket an den Client feuern
                    int secondsLeft = data.remainingTicks / 20;
                    if (data.remainingTicks % 20 == 0 || shouldSpawnPopup) {
                        net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.send(
                                player,
                                new de.josan.backrooms.network.RansomPopupPayload(chosenMessage, secondsLeft, goldCount)
                        );
                    }
                }
                // MODUS B: Das normale Stillstehen läuft noch
                else {
                    boolean movedPos = player.getPos().distanceTo(data.pos) > 0.05;
                    boolean movedYaw = Math.abs(player.getYaw() - data.yaw) > 1.0f;
                    boolean movedPitch = Math.abs(player.getPitch() - data.pitch) > 1.0f;

                    if (movedPos || movedYaw || movedPitch) {
                        // ERWISCHT! Wechsel in den Erpresser-Modus (1 Min Countdown startet)
                        data.inDemandMode = true;

                        // Alle aktuellen Items im Inventar korrumpieren
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            var stack = player.getInventory().getStack(i);
                            if (!stack.isEmpty() && !stack.isOf(net.minecraft.item.Items.GOLD_INGOT)) {
                                // NBT-Datenkomponente für Korruption setzen (1.21.1 Syntax via custom_data)
                                var nbt = new net.minecraft.nbt.NbtCompound();
                                nbt.putBoolean("RansomCorrupted", true);
                                stack.set(net.minecraft.component.DataComponentTypes.CUSTOM_DATA,
                                        net.minecraft.component.type.NbtComponent.of(nbt));

                                // Rotes Namens-Overlay / Lore hinzufügen
                                stack.set(net.minecraft.component.DataComponentTypes.ITEM_NAME,
                                        net.minecraft.text.Text.literal("§4[ENCRYPTED] §r" + stack.getName().getString()));
                            }
                        }
                        // (Bisherige Effekte wie Sound und Slowness beibehalten...)

                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.PLAYERS, 1.5f, 0.8f);

                        // Verlangsamung und Blindheit, um das Suchen nach Gold zu erschweren
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 1200, 1, false, false));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0, false, false));
                    }
                    // Erfolgreich stillgehalten (5 Sekunden vorbei)
                    else if (attackDuration <= 0) {
                        sendTitle(player, "§aSAFE", "§7Du hast stillgehalten.");
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                                SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        toRemove.add(uuid);
                    }
                }
            }

            for (UUID uuid : toRemove) {
                RansomCommand.activeAttackers.remove(uuid);
            }
        }
    }

    private static void sendTitle(ServerPlayerEntity player, String title, String subtitle) {
        player.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.TitleS2CPacket(Text.literal(title)));
        player.networkHandler.sendPacket(new net.minecraft.network.packet.s2c.play.SubtitleS2CPacket(Text.literal(subtitle)));
    }
}
