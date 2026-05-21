package de.josan.backrooms.block;

import de.josan.backrooms.Backrooms;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class ModBlocks {
    //Blocks
    public static final Block BLACK_ABYSS = registerBlock("abyss_black", new Block(AbstractBlock.Settings.create().strength(1000f)
            .requiresTool()
            .pistonBehavior(PistonBehavior.BLOCK)
            .dropsNothing()
            .noBlockBreakParticles()
            .mapColor(MapColor.BLACK)));

    public static final Block WHITE_ABYSS = registerBlock("abyss_white", new Block(AbstractBlock.Settings.create()
            .strength(1000f)
            .requiresTool()
            .pistonBehavior(PistonBehavior.BLOCK)
            .dropsNothing()
            .luminance(state -> 15)
            .emissiveLighting(Blocks::always)
            .nonOpaque()
            .noBlockBreakParticles()
            .mapColor(DyeColor.WHITE)));

    public static final Block WALL = registerBlock("wall", new Block(AbstractBlock.Settings.create()
            .strength(2f)
            .requiresTool()));

    public static final Block BACKROOMS_DOOR_LOCKED = registerBlock("backrooms_door_locked",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(3f).requiresTool().nonOpaque()));
    public static final Block BACKROOMS_DOOR = registerBlock("backrooms_door",
            new DoorBlock(BlockSetType.STONE, AbstractBlock.Settings.create().strength(3f).requiresTool().nonOpaque()));

    //Register Block
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Backrooms.MOD_ID, name), block);
    }
    //Register ItemBlock
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Backrooms.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    //Register Blocks
    public static void registerModBlocks() {
        Backrooms.LOGGER.info("Register Blocks For: " + Backrooms.MOD_ID);
    }
}
