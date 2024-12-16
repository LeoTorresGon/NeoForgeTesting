package net.leotorresgon.neoforgetestmod.datagen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, NeoForgeTestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BLUE_ZIRCON_ORE.get())
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.BLUE_ZIRCON_ORE.get())
                .add(ModBlocks.BLUE_ZIRCON_GRAVEL_ORE.get())
                .add(ModBlocks.BLUE_ZIRCON_SAND_ORE.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.BLUE_ZIRCON_GRAVEL_ORE.get())
                .add(ModBlocks.BLUE_ZIRCON_SAND_ORE.get());
        this.tag(BlockTags.LOGS)
                .add(ModBlocks.SOUL_LOG.get())
                .add(ModBlocks.STRIPPED_SOUL_LOG.get());
    }

}
