package de.josan.backrooms.test;

import de.josan.backrooms.item.ModItems;
import de.josan.backrooms.util.SpawnRelocateEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public interface testings {

    List<SpawnRelocateEntry> SHOP_ENTRIES = Util.make(new ArrayList<>(), entries -> {
        entries.add(new SpawnRelocateEntry(ModItems.SPAWNRELOCATE.getDefaultStack(), SpawnRelocateEntry.Type.STANDARD));
        entries.add(new SpawnRelocateEntry(ModItems.ALMOND_WATER.getDefaultStack(), SpawnRelocateEntry.Type.STANDARD) {
            @Override
            public boolean onBuy(@NotNull PlayerEntity player, ServerPlayerEntity target) {
                return false;
            }
        });
    });
}
