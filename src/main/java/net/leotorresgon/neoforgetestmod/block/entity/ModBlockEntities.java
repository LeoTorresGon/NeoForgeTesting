package net.leotorresgon.neoforgetestmod.block.entity;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NeoForgeTestMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SoulTunerBlockEntity>> SOUL_TUNER_BE = BLOCK_ENTITIES.register("soul_tuner_be", () ->
            BlockEntityType.Builder.of(SoulTunerBlockEntity::new,
                    ModBlocks.SOUL_TUNER.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
