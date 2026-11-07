package de.josan.backrooms.item;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.client.render.CustomGlintRenderLayers;
import de.josan.backrooms.component.ModDataComponentTypes;
import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.item.custom.*;
import de.josan.backrooms.sound.ModSounds;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    //Items
    public static final Item BACKROOMS_ICON = registerItem("backrooms_icon", new Item(new Item.Settings()));
    public static final Item ALMOND_WATER = registerItem("almond_water", new AlmondWater(new Item.Settings().maxCount(1)));
    public static final Item MOTH_JELLY = registerItem("moth_jelly", new MothJelly(new Item.Settings().maxCount(3)));
    public static final Item SHIMMER = registerItem("shimmer", new Shimmer(new Item.Settings().maxCount(1).component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.PINK.ordinal())));
    public static final Item SHADOW_STANCE = registerItem("shadow_stance", new Shadow_Stance(new Item.Settings().maxCount(1)));
    public static final Item DEER_SPAWN_EGG = registerItem("deer_spawn_egg", new Deer_Spawn_Egg_Item(ModEntities.DEER, new Item.Settings()));
    public static final Item SPAWNRELOCATE = registerItem("spawn_relocate", new Spawn_relocate(new Item.Settings().recipeRemainder(Items.NETHER_STAR).maxCount(49)));

    public static final Item HYPERION = registerItem("hyperion", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item CONJURING = registerItem("conjuring", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item GIANT_SWORD = registerItem("giant_sword", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof().component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.GOLD.ordinal())));
    public static final Item ASTRAEA = registerItem("astraea", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item VORPAL_KATANA = registerItem("vorpal_katana", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item VALKYRIE = registerItem("valkyrie", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item BLASPHEMOUS_BLADE = registerItem("blasphemous_blade", new SwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 7, -0.0F)).fireproof()));
    public static final Item CURSED_SWORD = registerItem("cursed_sword", new SwordItem(ToolMaterials.DIAMOND, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 7, -0.0F))));
    public static final Item GREAT_SWORD = registerItem("great_sword", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -1.6F)).component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.RED.ordinal()).maxDamage(4349).fireproof()));
    public static final Item EXECUTIONER_SWORD = registerItem("executioner_sword", new SwordItem(ToolMaterials.GOLD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 20, -1.6F)).component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.GOLD.ordinal()).fireproof()));
    public static final Item DEATHRIPPER_DAGGER = registerItem("deathripper_dagger", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item TERMINATOR = registerItem("terminator", new BowItem(new Item.Settings().component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.RED.ordinal()).fireproof()));
    public static final Item REVENANT_AXE = registerItem("revenant_axe", new AxeItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5.0F, -3.0F))));
    public static final Item SCYLLA = registerItem("scylla", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item RUNAAN_BOW = registerItem("runaan_bow", new BowItem(new Item.Settings().component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.RED.ordinal()).maxDamage(384).fireproof()));
    public static final Item MIDAS_STAFF = registerItem("midas_staff", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    public static final Item DAEDALUS_AXE = registerItem("daedalus_axe", new AxeItem(ToolMaterials.NETHERITE, new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 5.0F, -3.0F))));
    public static final Item AOTV_SWORD = registerItem("aotv_sword", new SwordItem(ToolMaterials.NETHERITE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 9, -0.0F)).fireproof()));
    //public static final Item TRIDENT = registerItem("trident", new TridentItem(new Item.Settings().rarity(Rarity.EPIC).maxDamage(250).attributeModifiers(TridentItem.createAttributeModifiers()).component(DataComponentTypes.TOOL, TridentItem.createToolComponent())));

    public static final Item MACE = registerItem("mace", new MaceItem(new Item.Settings().rarity(Rarity.EPIC).maxDamage(500).component(DataComponentTypes.TOOL, MaceItem.createToolComponent()).attributeModifiers(MaceItem.createAttributeModifiers()).component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.FAINT_WHITE.ordinal())));

    public static final Item MUSIC_DISC_ONE_DAY = registerItem("music_disc_one_day_some_day", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModSounds.ONEDAY_KEY)));
    public static final Item MUSIC_DISC_JANE = registerItem("music_disc_jane", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModSounds.JANE_KEY)));
    public static final Item MUSIC_DISC_MEMORY = registerItem("music_disc_memory", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModSounds.MEMORY_KEY)));
    public static final Item MUSIC_DISC_JUDAS = registerItem("music_disc_judas", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModSounds.JUDAS_KEY)));
    public static final Item MUSIC_DISC_BPE = registerItem("music_disc_bubble_pop_electric", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.BPE_KEY)));
    public static final Item MUSIC_DISC_AIZO = registerItem("music_disc_aizo", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.AIZO_KEY)));
    public static final Item MUSIC_DISC_SPEC = registerItem("music_disc_specialz", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.SPEC_KEY)));
    public static final Item MUSIC_DISC_MEET_AGAIN = registerItem("music_disc_meet_again", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.MEET_AGAIN_KEY)));
    public static final Item MUSIC_DISC_LOOSE_CANNON = registerItem("music_disc_loose_cannon", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.LOOSE_CANNON_KEY)));
    public static final Item MUSIC_DISC_BERNADETTE = registerItem("music_disc_bernadette", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.BERNADETTE_KEY)));
    public static final Item MUSIC_DISC_PIWGO = registerItem("music_disc_places_i_would_go", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.PIWGO_KEY)));
    public static final Item MUSIC_DISC_THE_WORLD = registerItem("music_disc_the_world", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.THE_WORLD_KEY)));
    public static final Item MUSIC_DISC_MY_WAY = registerItem("music_disc_my_way", new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ModSounds.MY_WAY_KEY)));
    public static final Item MUSIC_DISC_SUNSHINE = registerItem("music_disc_sunshine", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.SUNSHINE_KEY)));
    public static final Item MUSIC_DISC_SEA = registerItem("music_disc_sea", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.SEA_KEY)));
    public static final Item MUSIC_DISC_ORDER = registerItem("music_disc_order", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.ORDER_KEY)));
    public static final Item MUSIC_DISC_ROSSO = registerItem("music_disc_rosso", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.ROSSO_KEY)));
    public static final Item MUSIC_DISC_WILLI = registerItem("music_disc_willi", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.WILLI_KEY)));
    public static final Item MUSIC_DISC_LIFE = registerItem("music_disc_life", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.LIFE_KEY)));
    public static final Item MUSIC_DISC_BUMMS = registerItem("music_disc_bumms", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.BUMMS_KEY)));
    public static final Item MUSIC_DISC_JACKPOT = registerItem("music_disc_jackpot", new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).jukeboxPlayable(ModSounds.JACKPOT_KEY).component(ModDataComponentTypes.GLINT_TYPE, CustomGlintRenderLayers.GLINT.GREEN.ordinal())));
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
            entries.add(MUSIC_DISC_LOOSE_CANNON);
            entries.add(MUSIC_DISC_BERNADETTE);
            entries.add(MUSIC_DISC_PIWGO);
            entries.add(MUSIC_DISC_THE_WORLD);
            entries.add(MUSIC_DISC_MY_WAY);
            entries.add(MUSIC_DISC_SUNSHINE);
            entries.add(MUSIC_DISC_SEA);
            entries.add(MUSIC_DISC_ORDER);
            entries.add(MUSIC_DISC_ROSSO);
            entries.add(MUSIC_DISC_WILLI);
            entries.add(MUSIC_DISC_LIFE);
            entries.add(MUSIC_DISC_BUMMS);
            entries.add(MUSIC_DISC_JACKPOT);
            entries.add(MUSIC_DISC_RICK);
        });
    }
}
