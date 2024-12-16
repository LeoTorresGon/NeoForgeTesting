package net.leotorresgon.neoforgetestmod.attachment;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.*;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, NeoForgeTestMod.MOD_ID);

    public static final Supplier<AttachmentType<DamageAccumulatorAttachment>> DAMAGE_ACCUMULATOR = ATTACHMENT_TYPES.register(
            "damage_accumulator", ()-> AttachmentType.serializable(DamageAccumulatorAttachment::new).build()
    );
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<DashAttachment>> DASH = ATTACHMENT_TYPES.register(
            "dash", ()-> AttachmentType.serializable(DashAttachment::new).build()
    );

    public void register(IEventBus eventBus){
        ATTACHMENT_TYPES.register(eventBus);
    }

    // Serialization via INBTSerializable
    private static final Supplier<AttachmentType<ItemStackHandler>> HANDLER = ATTACHMENT_TYPES.register(
            "handler", () -> AttachmentType.serializable(() -> new ItemStackHandler(1)).build()
    );

}
