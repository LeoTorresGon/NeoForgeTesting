package net.leotorresgon.neoforgetestmod.datagen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, NeoForgeTestMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BISMUTH_BLOCK);
        blockWithItem(ModBlocks.BLUE_ZIRCON_ORE);
        blockWithItem(ModBlocks.BLUE_ZIRCON_GRAVEL_ORE);
        blockWithItem(ModBlocks.BLUE_ZIRCON_SAND_ORE);

        blockWithItem(ModBlocks.BISMUTH_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_BISMUTH_ORE);

        logBlock((RotatedPillarBlock) ModBlocks.SOUL_LOG.get());
        logBlock((RotatedPillarBlock) ModBlocks.STRIPPED_SOUL_LOG.get());
        blockItem(ModBlocks.SOUL_LOG);
        blockItem(ModBlocks.STRIPPED_SOUL_LOG);
        axisBlock(((RotatedPillarBlock) ModBlocks.SOUL_WOOD.get()), blockTexture(ModBlocks.SOUL_LOG.get()), blockTexture(ModBlocks.SOUL_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SOUL_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SOUL_LOG.get()), blockTexture(ModBlocks.STRIPPED_SOUL_LOG.get()));
        blockItem(ModBlocks.SOUL_WOOD);
        blockItem(ModBlocks.STRIPPED_SOUL_WOOD);

        simpleBlockWithItem(ModBlocks.SOUL_TUNER.get(), new ModelFile.UncheckedModelFile(modLoc("block/soul_tuner")));


        blockWithItem(ModBlocks.SOUL_PLANKS);



        leavesBlock(ModBlocks.SOUL_LEAVES);
        saplingBlock(ModBlocks.SOUL_FUNGUS);

    }


    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }


    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neoforgetestmod:block/" + deferredBlock.getId().getPath()));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("neoforgetestmod:block/" + deferredBlock.getId().getPath() + appendix));
    }

}
