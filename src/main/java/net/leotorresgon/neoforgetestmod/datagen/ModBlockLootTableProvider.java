package net.leotorresgon.neoforgetestmod.datagen;

import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;


import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        dropSelf(ModBlocks.SOUL_LOG.get());
        dropSelf(ModBlocks.SOUL_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_SOUL_LOG.get());
        dropSelf(ModBlocks.STRIPPED_SOUL_WOOD.get());
        dropSelf(ModBlocks.SOUL_FUNGUS.get());
        dropSelf(ModBlocks.SOUL_PLANKS.get());
        dropSelf(ModBlocks.SOUL_TUNER.get());

        this.add(ModBlocks.SOUL_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.SOUL_FUNGUS.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        add(ModBlocks.BISMUTH_ORE.get(),
                block -> createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get()));

        add(ModBlocks.BLUE_ZIRCON_ORE.get(),
                block -> createOreDrop(ModBlocks.BLUE_ZIRCON_ORE.get(), ModItems.BLUE_ZIRCON.get()));

        add(ModBlocks.BLUE_ZIRCON_GRAVEL_ORE.get(),
                block -> createOreDrop(ModBlocks.BLUE_ZIRCON_GRAVEL_ORE.get(), ModItems.BLUE_ZIRCON.get()));

        add(ModBlocks.BLUE_ZIRCON_SAND_ORE.get(),
                block -> createOreDrop(ModBlocks.BLUE_ZIRCON_SAND_ORE.get(), ModItems.BLUE_ZIRCON.get()));

        add(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get(), 2, 5));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops){
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
