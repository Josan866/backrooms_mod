package de.josan.backrooms.datagen;

import de.josan.backrooms.block.ModBlocks;
import de.josan.backrooms.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelGen extends FabricModelProvider {
    public ModModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLACK_ABYSS);
        //blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WHITE_ABYSS);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.WALL);

        blockStateModelGenerator.registerDoor(ModBlocks.BACKROOMS_DOOR_LOCKED);
        blockStateModelGenerator.registerDoor(ModBlocks.BACKROOMS_DOOR);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BACKROOMS_ICON, Models.GENERATED);
        itemModelGenerator.register(ModItems.ALMOND_WATER, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOTH_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHIMMER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SHADOW_STANCE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEER_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPAWNRELOCATE, Models.GENERATED);

        itemModelGenerator.register(ModItems.HYPERION, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CONJURING, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ASTRAEA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.VORPAL_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.MUSIC_DISC_ONE_DAY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_JANE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_MEMORY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_JUDAS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_BPE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_AIZO, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_SPEC, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_MEET_AGAIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_LOOSE_CANNON, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_BERNADETTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_PIWGO, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_THE_WORLD, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_MY_WAY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_SUNSHINE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_SEA, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_ORDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_ROSSO, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_WILLI, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_LIFE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_BUMMS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_JACKPOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_RICK, Models.GENERATED);

        itemModelGenerator.register(ModItems.ADVANCEMENT_01, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_02, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_03, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_04, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_05, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_06, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_07, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_08, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADVANCEMENT_09, Models.GENERATED);
    }
}
