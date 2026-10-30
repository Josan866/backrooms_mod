package de.josan.backrooms.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

// Record um zwei Integer erweitert
public record RansomPopupPayload(String message, int secondsLeft, int currentGold) implements CustomPayload {

    public static final CustomPayload.Id<RansomPopupPayload> ID =
            new CustomPayload.Id<>(Identifier.of("backrooms", "ransom_popup"));

    // Codec für drei Werte angepasst
    public static final PacketCodec<RegistryByteBuf, RansomPopupPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, RansomPopupPayload::message,
            PacketCodecs.INTEGER, RansomPopupPayload::secondsLeft,
            PacketCodecs.INTEGER, RansomPopupPayload::currentGold,
            RansomPopupPayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
