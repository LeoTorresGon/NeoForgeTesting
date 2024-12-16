package net.leotorresgon.neoforgetestmod.entity.client;

import net.leotorresgon.neoforgetestmod.entity.ModEntities;
import net.leotorresgon.neoforgetestmod.entity.client.custom.SlashEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class ModEntityRenderers {
    public static void setup(final FMLClientSetupEvent event){
        event.enqueueWork(()-> {
            EntityRenderers.register(ModEntities.SLASH_PROJECTILE.get(), SlashEntityRenderer::new);
        });
    }
}
