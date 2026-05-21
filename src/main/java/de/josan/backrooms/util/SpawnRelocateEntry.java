package de.josan.backrooms.util;

import de.josan.backrooms.Backrooms;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class SpawnRelocateEntry {
    private final ItemStack stack;
    private final Type type;

    public enum Type {
        STANDARD("gui/shop_slot"),
        WEAPON("gui/shop_slot_weapon"),
        POISON("gui/shop_slot_poison"),
        TOOL("gui/shop_slot_tool");

        final Identifier texture;

        Type(String texture) {
            this.texture = Backrooms.id(texture);
        }

        public Identifier getTexture() {
            return texture;
        }
    }

    public SpawnRelocateEntry(ItemStack stack, Type type) {
        this.stack = stack;
        this.type = type;
    }

    public boolean onBuy(@NotNull PlayerEntity player, ServerPlayerEntity target) {
        return false;
    }

    public static boolean insertStackInFreeSlot(@NotNull PlayerEntity player, ItemStack stackToInsert) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isEmpty()) {
                player.getInventory().setStack(i, stackToInsert);
                return true;
            }
        }
        return false;
    }

    public ItemStack stack() {
        return this.stack;
    }

    public Type type() {
        return type;
    }
}
