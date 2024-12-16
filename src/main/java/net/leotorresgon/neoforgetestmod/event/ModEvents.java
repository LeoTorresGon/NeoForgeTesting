package net.leotorresgon.neoforgetestmod.event;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.attachment.DamageAccumulatorAttachment;
import net.leotorresgon.neoforgetestmod.attachment.DashAttachment;
import net.leotorresgon.neoforgetestmod.attachment.ModAttachments;
import net.leotorresgon.neoforgetestmod.damage.ModDamageTypes;
import net.leotorresgon.neoforgetestmod.effect.ModEffects;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.leotorresgon.neoforgetestmod.item.custom.WitherScytheItem;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderNameTagEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.SweepAttackEvent;

import java.util.Objects;

@EventBusSubscriber(modid = NeoForgeTestMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingIncomingDamage(LivingIncomingDamageEvent event){
        ItemStack weapon = event.getSource().getWeaponItem();
        if (weapon != null){
            if (weapon.getItem() instanceof WitherScytheItem witherScytheItem){
                if (witherScytheItem.isEffectActive()){
                    witherScytheItem.addWitherScytheEffect(event.getEntity(), 100);
                }
            }
        }

        if (event.getEntity().hasEffect(ModEffects.NUMBNESS.getDelegate())){
            if(event.getSource().equals(event.getEntity().level().damageSources().generic())){
                return;
            }
            event.setAmount(0);
            DamageAccumulatorAttachment damageAccumulatorAttachment = event.getEntity().getData(ModAttachments.DAMAGE_ACCUMULATOR);
            damageAccumulatorAttachment.addDamage(event.getOriginalAmount());
            event.getEntity().setData(ModAttachments.DAMAGE_ACCUMULATOR, damageAccumulatorAttachment);
        }
        if(event.getSource().getWeaponItem() != null){
            if (event.getSource().getWeaponItem().is(ModItems.SOULBOUND_CROSSBOW)) {
                LivingEntity livingEntity = event.getEntity();
                livingEntity.addEffect(new MobEffectInstance(ModEffects.SOUL_TEAR, 100, 0));
            }
        }
    }

    @SubscribeEvent
    public static void onEffectExpire(MobEffectEvent.Expired event){
        if(Objects.equals(event.getEffectInstance().getEffect(), ModEffects.NUMBNESS)){
            DamageAccumulatorAttachment damageAccumulatorAttachment = event.getEntity().getData(ModAttachments.DAMAGE_ACCUMULATOR);
            float accumulatedDamage = damageAccumulatorAttachment.getAccumulatedDamage();
            if (accumulatedDamage > 0){
                event.getEntity().hurt(event.getEntity().level().damageSources().generic(), accumulatedDamage);
                damageAccumulatorAttachment.resetDamage();
                event.getEntity().setData(ModAttachments.DAMAGE_ACCUMULATOR, damageAccumulatorAttachment);
            }
        } else if(Objects.equals(event.getEffectInstance().getEffect(), ModEffects.SOUL_TEAR)){
            event.getEntity().hurt(event.getEntity().level().damageSources().cramming(), 6);
        }
    }

    @SubscribeEvent
    public static void onEffectRemove(MobEffectEvent.Remove event){
        if(Objects.equals(event.getEffectInstance().getEffect(), ModEffects.NUMBNESS)){
            DamageAccumulatorAttachment damageAccumulatorAttachment = event.getEntity().getData(ModAttachments.DAMAGE_ACCUMULATOR);
            float accumulatedDamage = damageAccumulatorAttachment.getAccumulatedDamage();
            if (accumulatedDamage > 0){
                event.getEntity().hurt(event.getEntity().level().damageSources().generic(), accumulatedDamage);
                damageAccumulatorAttachment.resetDamage();
                event.getEntity().setData(ModAttachments.DAMAGE_ACCUMULATOR, damageAccumulatorAttachment);
            }
        }
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event){
        while(ModKeyMappings.MAPPING.consumeClick()){
            Player player = Minecraft.getInstance().player;
            DashAttachment dashAttachment = player.getData(ModAttachments.DASH);
            if (dashAttachment.getDash() < dashAttachment.getMaxDashs()) {
                float f = player.getYRot();
                float g = player.getXRot();
                float h = -Mth.sin(f*((float)Mth.PI / 180)) * Mth.cos(g*((float)Mth.PI / 180));
                float k = -Mth.sin(g * (float) (Math.PI / 180.0));
                float l = Mth.cos(f * (float) (Math.PI / 180.0)) * Mth.cos(g * (float) (Math.PI / 180.0));
                float m = Mth.sqrt(h * h + k * k + l * l);
                float n = 3.0f * ((1.0f + (float)0) / 4.0f);
                float y = 1.1999999f / 3;
                if(player.onGround()){
                    y *= 3/2f;
                } else {
                    Vec3 deltaMovement = player.getDeltaMovement();
                    player.setDeltaMovement(deltaMovement.multiply(1, 0, 1));
                }
                player.push(h *= n * 2 / m, y, l *= n * 2 / m);
                dashAttachment.addDash();
                player.setData(ModAttachments.DASH, dashAttachment);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingFall(LivingFallEvent event){
        if (event.getEntity() instanceof Player player){
            DashAttachment dashAttachment = player.getData(ModAttachments.DASH);
            dashAttachment.resetDashs();
            player.setData(ModAttachments.DASH, dashAttachment);
        }
    }

    @SubscribeEvent
    public static void onSweepAttackEvent(SweepAttackEvent event){
        if (event.getEntity().getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.WITHERTHORN.get())){
            event.setSweeping(true);
        }
    }

    @SubscribeEvent
    public static void renderNameTag(RenderNameTagEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof Player player){
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.KITSUNE_MASK)){
                event.setContent(player.getItemBySlot(EquipmentSlot.HEAD).getDisplayName());
            }
        }
    }

}
