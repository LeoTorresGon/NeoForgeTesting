package net.leotorresgon.neoforgetestmod.datagen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NeoForgeTestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());
        basicItem(ModItems.BLUE_ZIRCON.get());
        basicItem(ModItems.CRYSTAL_BLADE.get());

        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.TOT_MUSICA_MUSIC_DISC.get());
        basicItem(ModItems.DICE.get());

        handheldItem(ModItems.SHOOT_SWORD);
        handheldItem(ModItems.CRYSTAL_DAGGER);
//        handheldItem(ModItems.RIPTIDETRIDENT);
        handheldItem(ModItems.WITHERTHORN);
        handheldItem(ModItems.WARP);

        saplingItem(ModBlocks.SOUL_FUNGUS);
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
