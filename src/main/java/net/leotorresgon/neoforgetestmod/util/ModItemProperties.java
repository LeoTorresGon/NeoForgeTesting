package net.leotorresgon.neoforgetestmod.util;

import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.leotorresgon.neoforgetestmod.item.custom.SoulwoodCrossbowItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

public class ModItemProperties {
    public static void addCustomItemProperties(){
        makeCustomCrossbow(ModItems.SOULBOUND_CROSSBOW.get());
    }

    private static void makeCustomCrossbow(Item item){
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pull"),
                (stack, level, livingEntity, p_351685_) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    } else {
                        return SoulwoodCrossbowItem.isCharged(stack)
                                ? 0.0F
                                : (float)(stack.getUseDuration(livingEntity) - livingEntity.getUseItemRemainingTicks())
                                / (float)SoulwoodCrossbowItem.getChargeDuration(stack, livingEntity);
                    }
                }
        );
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("pulling"),
                (stack, level, livingEntity, i) -> livingEntity != null
                        && livingEntity.isUsingItem()
                        && livingEntity.getUseItem() == stack
                        && !SoulwoodCrossbowItem.isCharged(stack)
                        ? 1.0F
                        : 0.0F
        );
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("charged"),
                (p_275891_, p_275892_, p_275893_, p_275894_) -> SoulwoodCrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F
        );
        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("firework"), (p_329796_, p_329797_, p_329798_, p_329799_) -> {
            ChargedProjectiles chargedprojectiles = p_329796_.get(DataComponents.CHARGED_PROJECTILES);
            return chargedprojectiles != null && chargedprojectiles.contains(Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
