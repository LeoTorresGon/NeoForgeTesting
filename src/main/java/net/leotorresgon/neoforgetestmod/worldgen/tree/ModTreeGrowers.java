package net.leotorresgon.neoforgetestmod.worldgen.tree;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower SOULWOOD = new TreeGrower(NeoForgeTestMod.MOD_ID + ":soulwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SOULWOOD_KEY), Optional.empty());

}
