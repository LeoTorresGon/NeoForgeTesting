package net.leotorresgon.neoforgetestmod.entity.custom;

import net.leotorresgon.neoforgetestmod.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class LifeStealProjectileEntity extends Fireball {
    public LifeStealProjectileEntity(EntityType<? extends Fireball> entityType, Level level) {
        super(entityType, level);
        setInvisible(true);
    }
    public LifeStealProjectileEntity(Level level, LivingEntity owner, Vec3 movement) {
        super(ModEntities.LIFESTEAL_PROJECTILE.get(), owner, movement, level);
    }

    public LifeStealProjectileEntity(Level level, double x, double y, double z, Vec3 movement) {
        super(ModEntities.LIFESTEAL_PROJECTILE.get(), x, y, z, movement, level);
    }

    @Override
    public ItemStack getItem() {
        return Items.FIREWORK_ROCKET.getDefaultInstance();
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level() instanceof ServerLevel serverlevel) {
            Entity victim = result.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource = this.damageSources().fireball(this, owner);
            victim.hurt(damageSource, 5);
            if(owner != null){
                Player player = (Player) owner;
                player.heal(5);
                float health = Player.MAX_HEALTH - player.getHealth();
                if(health < 5){
                    player.heal(health);
                    player.getFoodData().eat((int) (5 - health), 1.0F);
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (!this.level().isClientSide) {
            this.discard();
        }
        super.onHitBlock(result);
    }
}
