package net.leotorresgon.neoforgetestmod.sound;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, NeoForgeTestMod.MOD_ID);

    public static final Supplier<SoundEvent> TOT_MUSICA = registerSoundEvent("tot_musica");
    public static final ResourceKey<JukeboxSong> TOT_MUSICA_KEY = createSong("tot_musica");

    public static ResourceKey<JukeboxSong> createSong(String name){
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name){
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus event){
        SOUND_EVENTS.register(event);
    }
}
