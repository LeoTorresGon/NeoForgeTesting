package net.leotorresgon.neoforgetestmod.worldgen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ZIRCON_ORE_PLACED_KEY = registerKey("zircon_ore_placed");
    public static final ResourceKey<PlacedFeature> ZIRCON_GRAVEL_ORE_PLACED_KEY = registerKey("zircon_gravel_ore_placed");
    public static final ResourceKey<PlacedFeature> ZIRCON_SAND_ORE_PLACED_KEY = registerKey("zircon_sand_ore_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ZIRCON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZIRCON_ORE_KEY),
                ModOrePlacement.commomOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(328))));
//        ModOrePlacement.rareOrePlacement(15, HeightRangePlacement.uniform(VerticalAnchor.absolute(30), VerticalAnchor.absolute(328))));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
