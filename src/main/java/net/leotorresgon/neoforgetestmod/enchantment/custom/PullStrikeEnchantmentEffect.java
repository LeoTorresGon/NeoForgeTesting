package net.leotorresgon.neoforgetestmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record PullStrikeEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<PullStrikeEnchantmentEffect> CODEC = MapCodec.unit(PullStrikeEnchantmentEffect::new);


    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        entity.move(MoverType.PLAYER,
                item.owner().position().subtract(entity.position()).subtract(item.owner().position().normalize().multiply(1, 0, 1))
        );
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
