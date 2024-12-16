package net.leotorresgon.neoforgetestmod.entity;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.entity.custom.DiceProjectileEntity;
import net.leotorresgon.neoforgetestmod.entity.custom.LifeStealProjectileEntity;
import net.leotorresgon.neoforgetestmod.entity.custom.SlashProjectile;
import net.leotorresgon.neoforgetestmod.entity.custom.WarpTrident;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, NeoForgeTestMod.MOD_ID);

    public static final DeferredHolder<EntityType<?>,EntityType<DiceProjectileEntity>> DICE_PROJECTILE =
            register("dice_projectile", EntityType.Builder.<DiceProjectileEntity>of(DiceProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f));

    public static final DeferredHolder<EntityType<?>,EntityType<LifeStealProjectileEntity>> LIFESTEAL_PROJECTILE =
            register("lifesteal_projectile", EntityType.Builder.<LifeStealProjectileEntity>of(LifeStealProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f));

    public static final DeferredHolder<EntityType<?>, EntityType<SlashProjectile>> SLASH_PROJECTILE =
            register("slash_projectile", EntityType.Builder.<SlashProjectile>of(SlashProjectile::new, MobCategory.MISC).sized(1, 0.25f));

    public static final DeferredHolder<EntityType<?>, EntityType<WarpTrident>> WARP_TRIDENT =
            register("warp_trident", EntityType.Builder.<WarpTrident>of(WarpTrident::new, MobCategory.MISC).sized(1, 0.25f));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITY_TYPES.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }


    public static void register(IEventBus event){
        ENTITY_TYPES.register(event);
    }

}
