package net.leotorresgon.neoforgetestmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.enchantment.custom.LightningStrikerEnchatmentEffect;
import net.leotorresgon.neoforgetestmod.enchantment.custom.PullStrikeEnchantmentEffect;
import net.leotorresgon.neoforgetestmod.enchantment.custom.WitheringEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHATMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, NeoForgeTestMod.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHATMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchatmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> PUSH_STRIKE =
            ENTITY_ENCHATMENT_EFFECTS.register("push_strike", () -> PullStrikeEnchantmentEffect.CODEC);
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> WITHERING =
            ENTITY_ENCHATMENT_EFFECTS.register("withering", () -> WitheringEnchantmentEffect.CODEC);

    public static void register(IEventBus event){
        ENTITY_ENCHATMENT_EFFECTS.register((event));
    }
}
