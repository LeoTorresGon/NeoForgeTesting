package net.leotorresgon.neoforgetestmod.item;

import net.minecraft.world.item.ArmorItem;

public class PurpleKitsuneMaskItem extends SpecialArmorItem {
    protected PurpleKitsuneMaskItem(Properties properties) {
        super(ModArmorMaterials.KITSUNE_MASKS, ArmorItem.Type.HELMET, properties.setNoRepair().stacksTo(1).durability(1000));
    }


}
