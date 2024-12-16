package net.leotorresgon.neoforgetestmod.datagen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, NeoForgeTestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.SOUL_PLANKS.asItem());
        this.tag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItems.WARP.get());
    }
}
