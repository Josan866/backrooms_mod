package de.josan.backrooms.event;

import de.josan.backrooms.command.CurseCommand;
import de.josan.backrooms.network.CursePayload;
import de.josan.backrooms.sound.ModSounds;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class CurseTicker {
    public static final Random RANDOM = new Random();
    private static int cooldown = 0;

    private static final Map<UUID, Boolean> LAST_STATE = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(CurseTicker::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        cooldown--;
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            UUID uuid = player.getUuid();

            boolean cursed = CurseCommand.penisfuchs.contains(uuid);
            boolean lastState = LAST_STATE.getOrDefault(uuid, false);

            if (cursed != lastState) {
                ServerPlayNetworking.send(player, new CursePayload(cursed));
                LAST_STATE.put(uuid, cursed);
            }
        }

        if (cooldown <= 0) {
            for (UUID uuid : CurseCommand.penisfuchs) {
                ServerPlayerEntity player = server.getPlayerManager().getPlayer(uuid);

                if (player != null && uuid != null) {
                    Vec3d vec3d = player.getPos();
                    player.getServer().getPlayerManager().getPlayer(uuid).networkHandler.sendPacket(new PlaySoundS2CPacket(ModSounds.FETTARME_EV, SoundCategory.MASTER, vec3d.getX(), vec3d.getY(), vec3d.getZ(), 1, 1, 1));
                    StatusEffectInstance statusEffectInstance = new StatusEffectInstance(StatusEffects.LEVITATION, 1, 9, false, false);
                    player.addStatusEffect(statusEffectInstance, player);
                }
            }

            cooldown = 2 * 20;
        }
    }
}
