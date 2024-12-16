package net.leotorresgon.neoforgetestmod.entity.client.custom;

import net.leotorresgon.neoforgetestmod.entity.custom.SlashProjectile;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SlashEntityRenderer extends EntityRenderer<SlashProjectile> {
    public SlashEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(SlashProjectile entity) {
        return null;
    }
}
