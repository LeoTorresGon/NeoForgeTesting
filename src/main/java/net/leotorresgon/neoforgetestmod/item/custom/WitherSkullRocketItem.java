package net.leotorresgon.neoforgetestmod.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class WitherSkullRocketItem implements ProjectileItem {
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction, UUID uuid) {
        Vec3 vec3 = new Vec3(0, 0, 0);
        return new WitherSkull(level, level.getPlayerByUUID(uuid), vec3);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        return null;
    }
}
