package net.leotorresgon.neoforgetestmod.entity.custom;

import net.leotorresgon.neoforgetestmod.particle.ModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SlashProjectile extends Projectile {
    public double accelerationPower = 0.1;
    int ticks = 0;
    Vec3 origVec3 = new Vec3(0, 0, 0);

    public SlashProjectile(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }
    public SlashProjectile(EntityType<? extends Projectile> entityType, double x, double y, double z, Vec3 movement, Level level){
        super(entityType, level);
        this.moveTo(x, y, z, this.getYRot(), this.getXRot());
        this.reapplyPosition();
        this.assignDirectionalMovement(movement, this.accelerationPower);
        origVec3 = movement;
    }

    private void assignDirectionalMovement(Vec3 movement, double accelerationPower) {
        this.setDeltaMovement(movement.normalize().scale(accelerationPower));
        this.hasImpulse = true;
    }

    @Override
    public void tick() {
        Entity entity = this.getOwner();
        if (this.level().isClientSide || (entity == null || !entity.isRemoved()) && this.level().hasChunkAt(this.blockPosition())) {
            super.tick();

            HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity, this.getClipType());
            if (hitresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) {
                this.hitTargetOrDeflectSelf(hitresult);
            }

            this.checkInsideBlocks();
            Vec3 vec3 = this.getDeltaMovement();
            double d0 = this.getX() + vec3.x;
            double d1 = this.getY() + vec3.y;
            double d2 = this.getZ() + vec3.z;
            ProjectileUtil.rotateTowardsMovement(this, 0.2F);
            if (this.isInWater()) {
                for (int i = 0; i < 4; i++) {
                    this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
                }
            }
            ParticleOptions particleoptions = this.getTrailParticle();
            if (particleoptions != null) {
                if (ticks > 3){
                    createParticles(particleoptions, entity, vec3);
                }
            }
            if (ticks > 200){
                this.remove(RemovalReason.DISCARDED);
            }
            this.setDeltaMovement(vec3.add(vec3.normalize().scale(this.accelerationPower)));
            this.setPos(d0, d1, d2);
            ticks++;
        } else {
            this.discard();
        }
    }

    private void createParticles(ParticleOptions particleoptions, Entity entity, Vec3 vec3) {
        for(double i = -1; i < 1; i+=0.05){
            this.level().addParticle(particleoptions, this.getX() - vec3.x * 0.25 - vec3.z * i, this.getY() + (vec3.y * i), this.getZ() - vec3.z * 0.25 + vec3.x * i,
                    0.1, 0.5, 0.1);
        }
    }

    protected ClipContext.Block getClipType() {
        return ClipContext.Block.COLLIDER;
    }

    protected ParticleOptions getTrailParticle() {
        return ModParticles.BLOOD_PARTICLES.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        result.getEntity().hurt(damageSources().playerAttack((Player) this.getOwner()), 15);
        super.onHitEntity(result);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.discard();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
}
