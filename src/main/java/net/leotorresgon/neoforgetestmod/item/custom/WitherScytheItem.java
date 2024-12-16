package net.leotorresgon.neoforgetestmod.item.custom;

import net.leotorresgon.neoforgetestmod.effect.ModEffects;
import net.leotorresgon.neoforgetestmod.particle.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

import java.util.Collection;

public class WitherScytheItem extends SwordItem {
    private boolean effectActive;

    public WitherScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
        effectActive = false;
    }


    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }

    public boolean isEffectActive(){
        return effectActive;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (effectActive){
            addWitherScytheEffect(target, 100);
            effectActive = false;
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    public void addWitherScytheEffect(LivingEntity target, int duration) {
        target.addEffect(new MobEffectInstance(ModEffects.NUMBNESS, duration, 0));
        this.effectActive = false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        effectActive = true;
        player.startUsingItem(usedHand);
        return super.use(level, player, usedHand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().getBlockState(context.getClickedPos()).getBlock() == Blocks.WITHER_ROSE){

        }
        return super.useOn(context);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        if (livingEntity instanceof Player player){
            if (this.getUseDuration(stack, player) - timeLeft < 10){
                spawnEffectParticles(player);
                player.getCooldowns().addCooldown(this, 200);
            } else {
                livingEntity.hurt(level.damageSources().generic(), 0);
                spawnEffectParticles(player);
                addWitherScytheEffect(player, 300);
                effectActive = false;
                player.getCooldowns().addCooldown(this, 340);
            }
        }
        super.releaseUsing(stack, level, livingEntity, timeLeft);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 200;
    }

    private void spawnEffectParticles(Player player) {
        ParticleOptions particleOptions = ModParticles.BLOOD_PARTICLES.get();
        for (int i = 0; i < 25; i++) {
            player.level().addParticle(particleOptions, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        }
    }
}
