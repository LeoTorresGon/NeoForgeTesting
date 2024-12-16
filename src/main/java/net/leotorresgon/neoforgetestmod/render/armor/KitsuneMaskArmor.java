package net.leotorresgon.neoforgetestmod.render.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import net.leotorresgon.neoforgetestmod.model.KitsuneMaskModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class KitsuneMaskArmor implements ICustomArmor, ResourceManagerReloadListener {

    public static final KitsuneMaskArmor KITSUNE_MASK = new KitsuneMaskArmor();

    private KitsuneMaskModel model;

    private KitsuneMaskArmor(){
    }


    @Override
    public void render(HumanoidModel<? extends LivingEntity> baseModel, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight, float partialTicks, boolean hasEffect, LivingEntity entity, ItemStack stack) {
        if (!baseModel.head.visible){
            return;
        }
        renderMask(baseModel, matrix, renderer, light, overlayLight, hasEffect);

    }

    private void renderMask(HumanoidModel<? extends LivingEntity> baseModel, @NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight, boolean hasEffect) {
        matrix.pushPose();
        baseModel.head.translateAndRotate(matrix);
        matrix.translate(0, 0, 0.01);
        model.render(matrix, renderer, light, overlayLight, hasEffect);
        matrix.popPose();
    }

    @Override
    public void onResourceManagerReload(ResourceManager resourceManager) {
        model = new KitsuneMaskModel(Minecraft.getInstance().getEntityModels());
    }
}
