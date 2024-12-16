package net.leotorresgon.neoforgetestmod.block;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.custom.ModFlammableRotatedPillarBlock;
import net.leotorresgon.neoforgetestmod.block.custom.NetherSaplingBlock;
import net.leotorresgon.neoforgetestmod.block.custom.SoulTuningStationBlock;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.leotorresgon.neoforgetestmod.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoForgeTestMod.MOD_ID);

     public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
             ()-> new Block(BlockBehaviour.Properties.of()
                     .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

     public static final DeferredBlock<Block> BLUE_ZIRCON_ORE = registerBlock("blue_zircon_ore",
             ()-> new Block(BlockBehaviour.Properties.of()
                     .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
     public static final DeferredBlock<Block> BLUE_ZIRCON_GRAVEL_ORE = registerBlock("blue_zircon_gravel_ore",
             ()-> new Block(BlockBehaviour.Properties.of()
                     .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
     public static final DeferredBlock<Block> BLUE_ZIRCON_SAND_ORE = registerBlock("blue_zircon_sand_ore",
             ()-> new Block(BlockBehaviour.Properties.of()
                     .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

     public static final DeferredBlock<Block> SOUL_LOG = registerBlock("soulwood_log",
             ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STEM)));
     public static final DeferredBlock<Block> STRIPPED_SOUL_LOG = registerBlock("stripped_soulwood_log",
             ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_STEM)));
     public static final DeferredBlock<Block> SOUL_WOOD = registerBlock("soulwood_wood",
             ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_STEM)));
     public static final DeferredBlock<Block> STRIPPED_SOUL_WOOD = registerBlock("stripped_soulwood_wood",
             ()-> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_WARPED_STEM)));

     public static final DeferredBlock<Block> SOUL_PLANKS = registerBlock("soulwood_planks",
             ()-> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS)){
                 @Override
                 public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                     return false;
                 }
             });

     public static final DeferredBlock<Block> SOUL_LEAVES = registerBlock("soul_leaves",
             ()-> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
                 @Override
                 public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                     return false;
                 }
             });

    public static final DeferredBlock<Block> SOUL_FUNGUS = registerBlock("soul_fungus",
            ()-> new NetherSaplingBlock(ModTreeGrowers.SOULWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM)));

    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            ()-> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_BISMUTH_ORE = registerBlock("deepslate_bismuth_ore",
            ()-> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> SOUL_TUNER = registerBlock("soul_tuner",
            ()-> new SoulTuningStationBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(10.0f, 100.0f)
                    .sound(SoundType.STONE).noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
