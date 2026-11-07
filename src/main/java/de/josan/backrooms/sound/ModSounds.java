package de.josan.backrooms.sound;

import de.josan.backrooms.Backrooms;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
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
    public static final SoundEvent LOOSE_CANNON = registerSoundEvent("loose_cannon");
    public static final SoundEvent BERNADETTE = registerSoundEvent("bernadette");
    public static final SoundEvent PIWGO = registerSoundEvent("places_i_would_go");
    public static final SoundEvent THE_WORLD = registerSoundEvent("the_world");
    public static final SoundEvent MY_WAY = registerSoundEvent("my_way");
    public static final SoundEvent SUNSHINE = registerSoundEvent("sunshine");
    public static final SoundEvent SEA = registerSoundEvent("sea");
    public static final SoundEvent ORDER = registerSoundEvent("order");
    public static final SoundEvent ROSSO = registerSoundEvent("rosso");
    public static final SoundEvent WILLI = registerSoundEvent("willi");
    public static final SoundEvent LIFE = registerSoundEvent("life");
    public static final SoundEvent BUMMS = registerSoundEvent("bumms");
    public static final SoundEvent JACKPOT = registerSoundEvent("jackpot");
    public static final SoundEvent BLOODLOSS = registerSoundEvent("bloodloss");
    public static final SoundEvent SHADOW_STANCE = registerSoundEvent("shadow_stance");
    public static final SoundEvent FETTARME = registerSoundEvent("milkyway");
    public static final SoundEvent RICK = registerSoundEvent("rick");
    public static final SoundEvent RANSOM = registerSoundEvent("ransom");

    public static final RegistryKey<JukeboxSong> ONEDAY_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("one_day_some_day"));
    public static final RegistryKey<JukeboxSong> JANE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("jane"));
    public static final RegistryKey<JukeboxSong> MEMORY_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("memory"));
    public static final RegistryKey<JukeboxSong> JUDAS_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("judas"));
    public static final RegistryKey<JukeboxSong> BPE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("bubble_pop_electric"));
    public static final RegistryKey<JukeboxSong> AIZO_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("aizo"));
    public static final RegistryKey<JukeboxSong> SPEC_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("specialz"));
    public static final RegistryKey<JukeboxSong> MEET_AGAIN_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("meet_again"));
    public static final RegistryKey<JukeboxSong> LOOSE_CANNON_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("loose_cannon"));
    public static final RegistryKey<JukeboxSong> BERNADETTE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("bernadette"));
    public static final RegistryKey<JukeboxSong> PIWGO_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("places_i_would_go"));
    public static final RegistryKey<JukeboxSong> THE_WORLD_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("the_world"));
    public static final RegistryKey<JukeboxSong> MY_WAY_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("my_way"));
    public static final RegistryKey<JukeboxSong> SUNSHINE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("sunshine"));
    public static final RegistryKey<JukeboxSong> SEA_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("sea"));
    public static final RegistryKey<JukeboxSong> ORDER_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("order"));
    public static final RegistryKey<JukeboxSong> ROSSO_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("rosso"));
    public static final RegistryKey<JukeboxSong> WILLI_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("willi"));
    public static final RegistryKey<JukeboxSong> LIFE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("life"));
    public static final RegistryKey<JukeboxSong> BUMMS_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("bumms"));
    public static final RegistryKey<JukeboxSong> JACKPOT_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("jackpot"));
    public static final RegistryKey<JukeboxSong> BLOODLOSS_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("bloodloss"));
    public static final RegistryKey<JukeboxSong> SHADOW_STANCE_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("shadow_stance"));
    public static final RegistryKey<JukeboxSong> RICK_KEY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Backrooms.id("rick"));

    public static final RegistryEntry.Reference<SoundEvent> FETTARME_EV = registerReference("fettarme");

    private static RegistryEntry.Reference<SoundEvent> registerReference(String id) {
        return registerReference(Backrooms.id(id));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id) {
        return registerReference(id, id);
    }

    private static SoundEvent register(Identifier id, Identifier soundId) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(Identifier id, Identifier soundId) {
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(soundId));
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Backrooms.id(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Backrooms.LOGGER.info("Register Sounds for: " + Backrooms.MOD_ID);
    }
}
