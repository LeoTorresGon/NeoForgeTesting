package net.leotorresgon.neoforgetestmod.item;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NeoForgeTestMod.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            ()-> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.neoforgetestmod.bismuth_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                        output.accept(ModItems.BLUE_ZIRCON);
                        output.accept(ModItems.CRYSTAL_BLADE);

                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.WINDSPIRE);
                        output.accept(ModItems.TOT_MUSICA_MUSIC_DISC);
                        output.accept(ModItems.DICE);
                        output.accept(ModItems.WITHERTHORN);
                        output.accept(ModItems.CRYSTAL_DAGGER);
                        output.accept(ModItems.SOULBOUND_CROSSBOW);
                        output.accept(ModItems.WARP);
                        output.accept(ModItems.KITSUNE_MASK);
                    }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            ()-> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.neoforgetestmod.bismuth_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.DEEPSLATE_BISMUTH_ORE);
                        output.accept(ModBlocks.BLUE_ZIRCON_ORE);
                        output.accept(ModBlocks.BLUE_ZIRCON_GRAVEL_ORE);
                        output.accept(ModBlocks.BLUE_ZIRCON_SAND_ORE);
                        output.accept(ModBlocks.SOUL_LOG);
                        output.accept(ModBlocks.STRIPPED_SOUL_LOG);
                        output.accept(ModBlocks.SOUL_WOOD);
                        output.accept(ModBlocks.STRIPPED_SOUL_WOOD);
                        output.accept(ModBlocks.SOUL_LEAVES);
                        output.accept(ModBlocks.SOUL_PLANKS);
                        output.accept(ModBlocks.SOUL_FUNGUS);
                        output.accept(ModBlocks.SOUL_TUNER);
                    }).build());



    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
