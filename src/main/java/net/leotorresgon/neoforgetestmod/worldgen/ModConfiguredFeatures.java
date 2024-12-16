package net.leotorresgon.neoforgetestmod.worldgen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ZIRCON_ORE_KEY = registerKey("zircon_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SOULWOOD_KEY = registerKey("soulwood");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
//        RuleTest stoneReplaceabes = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest gravelReplaceabes = new BlockMatchTest(Blocks.GRAVEL);
        RuleTest sandReplaceabes = new BlockMatchTest(Blocks.SAND);

        List<OreConfiguration.TargetBlockState> overworldZirconOres = List.of(
//                OreConfiguration.target(stoneReplaceabes, ModBlocks.BLUE_ZIRCON_ORE.get().defaultBlockState()),
                OreConfiguration.target(gravelReplaceabes, ModBlocks.BLUE_ZIRCON_GRAVEL_ORE.get().defaultBlockState()),
                OreConfiguration.target(sandReplaceabes, ModBlocks.BLUE_ZIRCON_SAND_ORE.get().defaultBlockState()));

        register(context, ZIRCON_ORE_KEY, Feature.ORE, new OreConfiguration(overworldZirconOres, 1));

        register(context, SOULWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SOUL_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.SOUL_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),
                new TwoLayersFeatureSize(1, 0, 2)).dirt(BlockStateProvider.simple(Blocks.SOUL_SOIL)).forceDirt().build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
