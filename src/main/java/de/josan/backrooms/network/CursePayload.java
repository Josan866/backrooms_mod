package de.josan.backrooms.network;

import de.josan.backrooms.Backrooms;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record CursePayload(Boolean show) implements CustomPayload {

    public static final CustomPayload.Id<CursePayload> ID =
            new CustomPayload.Id<>(Backrooms.id("curse_popup"));

    public static final PacketCodec<RegistryByteBuf, CursePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, CursePayload::show,
            CursePayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
