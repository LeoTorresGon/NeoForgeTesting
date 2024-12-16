package net.leotorresgon.neoforgetestmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class DashDaggerItem extends SwordItem {

    public DashDaggerItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.getItemInHand(usedHand).hurtAndBreak(0, player, EquipmentSlot.MAINHAND);
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
        }
        player.push(h *= n * 2 / m, y, l *= n * 2 / m);
        player.getCooldowns().addCooldown(this, 30);

//        player.push(h *= n / 1.2f / m, k *= n * 2 / m, l *= n / 1.2f / m);
//        float o = 1.1999999f;
//        player.move(MoverType.SELF, new Vec3(0.0, 3.5, 0.0));



        return super.use(level, player, usedHand);
    }
}
