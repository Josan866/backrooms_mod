package de.josan.backrooms.index.tag;

import de.josan.backrooms.Backrooms;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface ModItemTags {

    TagKey<Item> ADVANCEMENTS = create("advancements");

    private static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, Backrooms.id(id));
    }
}
