package de.josan.backrooms.network;

import de.josan.backrooms.Backrooms;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record RansomClosePayload() implements CustomPayload {
    public static final CustomPayload.Id<RansomClosePayload> ID =
            new CustomPayload.Id<>(Backrooms.id("ransom_close"));

    // Ein leerer Codec, da keine Variablen übertragen werden müssen
    public static final PacketCodec<RegistryByteBuf, RansomClosePayload> CODEC =
            PacketCodec.unit(new RansomClosePayload());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
