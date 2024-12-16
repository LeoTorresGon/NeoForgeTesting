package net.leotorresgon.neoforgetestmod.datagen;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH,
                ModBlocks.BISMUTH_ORE, ModBlocks.DEEPSLATE_BISMUTH_ORE);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BISMUTH.get())
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.SOUL_PLANKS.get(), 4)
                .requires(ModBlocks.SOUL_LOG)
                .unlockedBy("has_soul_log", has(ModBlocks.SOUL_LOG)).save(recipeOutput, "neoforgetestmod:soul_planks");

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CRYSTAL_DAGGER.get())
                    .pattern("   ")
                    .pattern(" B ")
                    .pattern("A  ")
                    .define('A', Items.STICK)
                    .define('B', ModItems.CRYSTAL_BLADE)
                    .unlockedBy("has_zircon", has(ModItems.BLUE_ZIRCON)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.WINDSPIRE.get())
                        .pattern(" AA")
                        .pattern(" CA")
                        .pattern("B  ")
                        .define('A', ModItems.BLUE_ZIRCON)
                        .define('B', Items.BREEZE_ROD)
                        .define('C', Items.TRIDENT)
                        .unlockedBy("has_zircon", has(ModItems.BLUE_ZIRCON)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.WITHERTHORN.get())
                        .pattern("ABC")
                        .pattern("ADB")
                        .pattern("DC ")
                        .define('A', Items.NETHERITE_INGOT)
                        .define('B', Items.NETHERITE_SCRAP)
                        .define('C', Items.WITHER_ROSE)
                        .define('D', Items.STICK)
                        .unlockedBy("has_wither_rose", has(Items.WITHER_ROSE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CRYSTAL_BLADE.get())
                        .pattern(" A ")
                        .pattern("ABA")
                        .pattern(" A ")
                        .define('A', ModItems.BLUE_ZIRCON.get())
                        .define('B', Items.IRON_INGOT)
                        .unlockedBy("has_zircon", has(ModItems.BLUE_ZIRCON)).save(recipeOutput);

        oreSmelting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 200, "bismuth");
        oreBlasting(recipeOutput, BISMUTH_SMELTABLES, RecipeCategory.MISC, ModItems.BISMUTH.get(), 0.25f, 100, "bismuth");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, NeoForgeTestMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
