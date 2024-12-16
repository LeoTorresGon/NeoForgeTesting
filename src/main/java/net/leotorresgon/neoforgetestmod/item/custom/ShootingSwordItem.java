package net.leotorresgon.neoforgetestmod.item.custom;

import net.leotorresgon.neoforgetestmod.entity.ModEntities;
import net.leotorresgon.neoforgetestmod.entity.custom.LifeStealProjectileEntity;
import net.leotorresgon.neoforgetestmod.entity.custom.SlashProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ShootingSwordItem extends SwordItem {
    public ShootingSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(level.isClientSide()){
            return InteractionResultHolder.success(player.getItemInHand(usedHand));
        }
        Vec3 lookDirection = player.getViewVector(1.0f).normalize();
        Vec3 spawnPosition = player.position().add(lookDirection.x * 1.5, lookDirection.y + 1.5, lookDirection.z * 1.5);
        LifeStealProjectileEntity lifeStealProjectileEntity = new LifeStealProjectileEntity(level, lookDirection.x, lookDirection.y, lookDirection.z, lookDirection);

        lifeStealProjectileEntity.setPos(spawnPosition.x, spawnPosition.y, spawnPosition.z);

        lifeStealProjectileEntity.setOwner(player);
        level.addFreshEntity(lifeStealProjectileEntity);

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), false);
    }

//    @Override
//    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
//        Vec3 lookDirection = entity.getViewVector(1.0f).normalize();
//        Vec3 spawnPosition = entity.position().add(lookDirection.x * 1.5, lookDirection.y + 1.5, lookDirection.z * 1.5);
//        shootParticles(entity.level(), spawnPosition, entity.getViewVector(1.0f), (Player) entity);
//        return super.onEntitySwing(stack, entity, hand);
//    }


    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity, InteractionHand hand) {
        SlashProjectile slashProjectile = new SlashProjectile(ModEntities.SLASH_PROJECTILE.get(), entity.getX(), entity.getY(), entity.getZ(),
                entity.getViewVector(1), entity.level());
        Vec3 lookDirection = entity.getViewVector(1.0f).normalize();
        Vec3 spawnPosition = entity.position().add(lookDirection.x * 1.5, lookDirection.y + 1.5, lookDirection.z * 1.5);
        slashProjectile.setPos(spawnPosition.x, spawnPosition.y, spawnPosition.z);
        slashProjectile.setOwner(entity);
        entity.level().addFreshEntity(slashProjectile);
        return super.onEntitySwing(stack, entity, hand);
    }



}
