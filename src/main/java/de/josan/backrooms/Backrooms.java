package de.josan.backrooms;

import de.josan.backrooms.command.BackroomsCommand;
import de.josan.backrooms.command.SpeedCommand;
import de.josan.backrooms.enchantment.ModEnchantmentEffects;
import de.josan.backrooms.sound.ModSounds;
import de.josan.backrooms.block.ModBlocks;
import de.josan.backrooms.effect.ModEffects;
import de.josan.backrooms.entity.ModEntities;
import de.josan.backrooms.entity.custom.DeerEntity;
import de.josan.backrooms.item.ModItemGroups;
import de.josan.backrooms.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Backrooms implements ModInitializer {
	public static final String MOD_ID = "backrooms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    //private static void registerCommands() {
    //    CommandRegistrationCallback.EVENT.register(BackroomsCommand::register);
    //}

    public static Identifier id (String name) {
        return Identifier.of(MOD_ID, name);
    }

    public static boolean AccessAllowed(PlayerEntity player) {
        if (player == null || !player.getClass().equals(ServerPlayerEntity.class)) return false;

        if (isEarlyAccess(player) || FabricLoader.getInstance().isDevelopmentEnvironment()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isEarlyAccess(PlayerEntity player) {
        return false;
    }

    public static int speed = 0;

	@Override
	public void onInitialize() {

        //Items
        ModItems.registerModItems();

        //ItemGroups
        ModItemGroups.registerModItemGroups();

        //Blocks
        ModBlocks.registerModBlocks();

        //Entities
        ModEntities.registerModEntities();
        FabricDefaultAttributeRegistry.register(ModEntities.DEER, DeerEntity.createAttributes());

        //Effects
        ModEffects.registerEffects();

        //Sounds
        ModSounds.registerSounds();

        //EnchantmentEffects
        ModEnchantmentEffects.registerModEnchantmentEffects();

        //Commands
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            BackroomsCommand.register(dispatcher);
            SpeedCommand.register(dispatcher);
        }));
	}
}