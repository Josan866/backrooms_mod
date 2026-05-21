package de.josan.backrooms.sound;

import de.josan.backrooms.Backrooms;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ONEDAY = registerSoundEvent("one_day_some_day");
    public static final SoundEvent JANE = registerSoundEvent("jane");
    public static final SoundEvent MEMORY = registerSoundEvent("memory");
    public static final SoundEvent JUDAS = registerSoundEvent("judas");
    public static final SoundEvent BPE = registerSoundEvent("bubble_pop_electric");
    public static final SoundEvent AIZO = registerSoundEvent("aizo");
    public static final SoundEvent SPEC = registerSoundEvent("specialz");
    public static final SoundEvent MEET_AGAIN = registerSoundEvent("meet_again");
    public static final SoundEvent RICK = registerSoundEvent("rick");

    public static final RegistryKey<JukeboxSong> ONEDAY_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("one_day_some_day"));
    public static final RegistryKey<JukeboxSong> JANE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("jane"));
    public static final RegistryKey<JukeboxSong> MEMORY_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("memory"));
    public static final RegistryKey<JukeboxSong> JUDAS_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("judas"));
    public static final RegistryKey<JukeboxSong> BPE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("bubble_pop_electric"));
    public static final RegistryKey<JukeboxSong> AIZO_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("aizo"));
    public static final RegistryKey<JukeboxSong> SPEC_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("specialz"));
    public static final RegistryKey<JukeboxSong> MEET_AGAIN_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("meet_again"));
    public static final RegistryKey<JukeboxSong> RICK_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("rick"));

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Backrooms.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Backrooms.LOGGER.info("Register Sounds for: " + Backrooms.MOD_ID);
    }
}
