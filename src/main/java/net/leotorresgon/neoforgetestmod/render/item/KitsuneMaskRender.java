package net.leotorresgon.neoforgetestmod.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.leotorresgon.neoforgetestmod.model.KitsuneMaskModel;
import net.leotorresgon.neoforgetestmod.render.ISTER;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class KitsuneMaskRender extends ISTER {

    public static KitsuneMaskRender RENDERER = new KitsuneMaskRender();
    private KitsuneMaskModel kitsuneMasks;

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager resourceManager) {
        kitsuneMasks = new KitsuneMaskModel(getEntityModels());
    }

    @Override
    public void renderByItem(@NotNull ItemStack stack, @NotNull ItemDisplayContext displayContext, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer,
                             int light, int overlayLight) {
        matrix.pushPose();
        matrix.translate(0.5, 0.5, 0.5);
        matrix.mulPose(Axis.ZP.rotationDegrees(180));
        kitsuneMasks.render(matrix, renderer, light, overlayLight, stack.hasFoil());
        matrix.popPose();
    }
}
