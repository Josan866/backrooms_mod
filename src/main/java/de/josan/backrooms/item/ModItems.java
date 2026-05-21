package de.josan.backrooms.item;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.item.custom.Advancement;
import de.josan.backrooms.item.custom.AlmondWater;
import de.josan.backrooms.item.custom.Deer_Spawn_Egg_Item;
import de.josan.backrooms.item.custom.Spawn_relocate;
import de.josan.backrooms.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    //Items
    public static final Item BACKROOMS_ICON = registerItem("backrooms_icon", new Item(new Item.Settings()));
    public static final Item ALMOND_WATER = registerItem("almond_water", new AlmondWater(new Item.Settings().maxCount(1)));
    public static final Item SPAWNRELOCATE = registerItem("spawn_relocate", new Spawn_relocate(new Item.Settings().recipeRemainder(Items.NETHER_STAR).maxCount(49)));
    public static final Item DEER_SPAWN_EGG = registerItem("deer_spawn_egg", new Deer_Spawn_Egg_Item(ModEntities.DEER, new Item.Settings()));

    public static final Item MUSIC_DISC_ONE_DAY = registerItem("music_disc_one_day_some_day", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.ONEDAY_KEY)));
    public static final Item MUSIC_DISC_JANE = registerItem("music_disc_jane", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.JANE_KEY)));
    public static final Item MUSIC_DISC_MEMORY = registerItem("music_disc_memory", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.MEMORY_KEY)));
    public static final Item MUSIC_DISC_JUDAS = registerItem("music_disc_judas", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.JUDAS_KEY)));
    public static final Item MUSIC_DISC_BPE = registerItem("music_disc_bubble_pop_electric", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.BPE_KEY)));
    public static final Item MUSIC_DISC_AIZO = registerItem("music_disc_aizo", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.AIZO_KEY)));
    public static final Item MUSIC_DISC_SPEC = registerItem("music_disc_specialz", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.SPEC_KEY)));
    public static final Item MUSIC_DISC_MEET_AGAIN = registerItem("music_disc_meet_again", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.MEET_AGAIN_KEY)));
    public static final Item MUSIC_DISC_RICK = registerItem("music_disc_rick", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.RICK_KEY)));

    public static final Item ADVANCEMENT_01 = registerItem("advancement_01", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_02 = registerItem("advancement_02", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_03 = registerItem("advancement_03", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_04 = registerItem("advancement_04", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_05 = registerItem("advancement_05", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_06 = registerItem("advancement_06", new Advancement(new Item.Settings()));
    public static final Item ADVANCEMENT_07 = registerItem("advancement_07", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_08 = registerItem("advancement_08", new Item(new Item.Settings()));
    public static final Item ADVANCEMENT_09 = registerItem("advancement_09", new Item(new Item.Settings()));


    //Register Items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Backrooms.MOD_ID, name), item);
    }
    //Register and add to Creative-Mode-Tab
    public static void registerModItems() {
        Backrooms.LOGGER.info("Register Items for: " + Backrooms.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(DEER_SPAWN_EGG);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(MUSIC_DISC_ONE_DAY);
            entries.add(MUSIC_DISC_JANE);
            entries.add(MUSIC_DISC_MEMORY);
            entries.add(MUSIC_DISC_JUDAS);
            entries.add(MUSIC_DISC_BPE);
            entries.add(MUSIC_DISC_AIZO);
            entries.add(MUSIC_DISC_SPEC);
            entries.add(MUSIC_DISC_MEET_AGAIN);
            entries.add(MUSIC_DISC_RICK);
        });
    }
}
