package net.leotorresgon.neoforgetestmod.entity.custom;

import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.leotorresgon.neoforgetestmod.entity.ModEntities;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class DiceProjectileEntity extends ThrowableItemProjectile {
    public DiceProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }
    public DiceProjectileEntity(Level level) {
        super(ModEntities.DICE_PROJECTILE.get(), level);
    }
    public DiceProjectileEntity(Level level, LivingEntity livingEntity) {
        super(ModEntities.DICE_PROJECTILE.get(), livingEntity, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DICE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(), ModBlocks.BISMUTH_BLOCK.get().defaultBlockState(), 3);
        }
        super.onHitBlock(result);
    }
}
