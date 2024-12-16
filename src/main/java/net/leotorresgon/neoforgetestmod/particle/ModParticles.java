package net.leotorresgon.neoforgetestmod.particle;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticles {
    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, NeoForgeTestMod.MOD_ID);

    public static final Supplier<SimpleParticleType> BLOOD_PARTICLES =
            PARTICLE_TYPES.register("blood_particles", () -> new SimpleParticleType(false));
    public static final Supplier<SimpleParticleType> BLOOD_PARTICLES_STATIC =
            PARTICLE_TYPES.register("blood_particles_static", () -> new SimpleParticleType(false));

    public static void register(IEventBus event){
        PARTICLE_TYPES.register(event);
    }
}
