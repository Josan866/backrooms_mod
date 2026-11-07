package de.josan.backrooms.item;

import de.josan.backrooms.Backrooms;
import de.josan.backrooms.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    //Groups
    public static final ItemGroup BACKROOMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Backrooms.MOD_ID, "backrooms"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.backrooms"))
                    .icon(() -> new ItemStack(ModItems.BACKROOMS_ICON)).entries((displayContext, entries) -> {
                        entries.add(ModBlocks.BLACK_ABYSS);
                        entries.add(ModBlocks.WHITE_ABYSS);
                        entries.add(ModBlocks.WALL);

                        entries.add(ModBlocks.BACKROOMS_DOOR_LOCKED);
                        entries.add(ModBlocks.BACKROOMS_DOOR);

                        entries.add(ModItems.BLASPHEMOUS_BLADE);
                        entries.add(ModItems.CURSED_SWORD);
                        entries.add(ModItems.GREAT_SWORD);
                        entries.add(ModItems.EXECUTIONER_SWORD);
                        entries.add(ModItems.MACE);

                        entries.add(ModItems.ALMOND_WATER);
                        entries.add(ModItems.MOTH_JELLY);
                        entries.add(ModItems.SHIMMER);
                        entries.add(ModItems.SHADOW_STANCE);
                        entries.add(ModItems.DEER_SPAWN_EGG);
                        entries.add(ModItems.SPAWNRELOCATE);
                        entries.add(ModItems.HYPERION);
                        entries.add(ModItems.CONJURING);
                        entries.add(ModItems.GIANT_SWORD);
                        entries.add(ModItems.ASTRAEA);
                        entries.add(ModItems.VORPAL_KATANA);
                        entries.add(ModItems.VALKYRIE);
                        entries.add(ModItems.DEATHRIPPER_DAGGER);
                        entries.add(ModItems.TERMINATOR);
                        entries.add(ModItems.REVENANT_AXE);
                        entries.add(ModItems.SCYLLA);
                        entries.add(ModItems.RUNAAN_BOW);
                        entries.add(ModItems.MIDAS_STAFF);
                        entries.add(ModItems.DAEDALUS_AXE);
                        entries.add(ModItems.AOTV_SWORD);
                        //entries.add(ModItems.TRIDENT);

                        entries.add(ModItems.MUSIC_DISC_ONE_DAY);
                        entries.add(ModItems.MUSIC_DISC_JANE);
                        entries.add(ModItems.MUSIC_DISC_MEMORY);
                        entries.add(ModItems.MUSIC_DISC_JUDAS);
                        entries.add(ModItems.MUSIC_DISC_BPE);
                        entries.add(ModItems.MUSIC_DISC_AIZO);
                        entries.add(ModItems.MUSIC_DISC_SPEC);
                        entries.add(ModItems.MUSIC_DISC_MEET_AGAIN);
                        entries.add(ModItems.MUSIC_DISC_LOOSE_CANNON);
                        entries.add(ModItems.MUSIC_DISC_BERNADETTE);
                        entries.add(ModItems.MUSIC_DISC_PIWGO);
                        entries.add(ModItems.MUSIC_DISC_THE_WORLD);
                        entries.add(ModItems.MUSIC_DISC_MY_WAY);
                        entries.add(ModItems.MUSIC_DISC_SUNSHINE);
                        entries.add(ModItems.MUSIC_DISC_SEA);
                        entries.add(ModItems.MUSIC_DISC_ORDER);
                        entries.add(ModItems.MUSIC_DISC_ROSSO);
                        entries.add(ModItems.MUSIC_DISC_WILLI);
                        entries.add(ModItems.MUSIC_DISC_LIFE);
                        entries.add(ModItems.MUSIC_DISC_BUMMS);
                        entries.add(ModItems.MUSIC_DISC_JACKPOT);
                        entries.add(ModItems.MUSIC_DISC_RICK);
                    }).build());

    //register
    public static void registerModItemGroups() {
        Backrooms.LOGGER.info("Register ItemGroups for: " + Backrooms.MOD_ID);
    }
}
