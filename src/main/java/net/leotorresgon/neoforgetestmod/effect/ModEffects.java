package net.leotorresgon.neoforgetestmod.effect;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, NeoForgeTestMod.MOD_ID);

    public static final Holder<MobEffect> NUMBNESS = MOB_EFFECTS.register("numbness",
            () -> new NumbnessEffect(MobEffectCategory.NEUTRAL, 0x36ebab));
    public static final Holder<MobEffect> SOUL_TEAR = MOB_EFFECTS.register("soul_tear",
            ()-> new SoulTearEffect((MobEffectCategory.HARMFUL), 0x4272db));

    public static void register(IEventBus bus) {
        MOB_EFFECTS.register(bus);
    }
}
