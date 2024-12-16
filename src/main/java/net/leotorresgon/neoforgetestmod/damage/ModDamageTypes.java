package net.leotorresgon.neoforgetestmod.damage;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.attachment.ModAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

public class ModDamageTypes {
    public static final ResourceKey<DamageType> NUMBNESS_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, "numbess"));

    RegistryAccess registryAccess = Minecraft.getInstance().level.registryAccess();

    DamageSource damageSource = new DamageSource(
            registryAccess.lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(NUMBNESS_DAMAGE)
    );

    public static DamageSource numbnessDamage(Entity causer) {
        return new DamageSource(
                causer.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(NUMBNESS_DAMAGE),
                causer);
    }


}
