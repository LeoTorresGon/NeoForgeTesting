package net.leotorresgon.neoforgetestmod.item;

import net.leotorresgon.neoforgetestmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    public static final Tier BISMUTH = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BISMUTH_TOOL,
            32, 4f, 3f, 28, () -> Ingredient.of(ModItems.BISMUTH.get()));

}
