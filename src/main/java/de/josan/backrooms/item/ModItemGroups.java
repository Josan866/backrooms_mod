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

                        entries.add(ModItems.ALMOND_WATER);
                        entries.add(ModItems.DEER_SPAWN_EGG);

                        entries.add(ModItems.SPAWNRELOCATE);

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
                        entries.add(ModItems.MUSIC_DISC_RICK);
                    }).build());

    //register
    public static void registerModItemGroups() {
        Backrooms.LOGGER.info("Register ItemGroups for: " + Backrooms.MOD_ID);
    }
}
