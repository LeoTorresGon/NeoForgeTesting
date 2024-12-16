package net.leotorresgon.neoforgetestmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class WitheringEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<WitheringEnchantmentEffect> CODEC = MapCodec.unit(WitheringEnchantmentEffect::new);


    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof LivingEntity livingEntity){
            MobEffectInstance effectInstance = new MobEffectInstance(MobEffects.WITHER, 200, 0, true, true);
            livingEntity.addEffect(effectInstance);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
