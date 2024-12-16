package net.leotorresgon.neoforgetestmod.model;
// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KitsuneMaskModel extends JavaModel {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation MASK_LAYER = new ModelLayerLocation(NeoForgeTestMod.rl("kitsune_mask_purple"), "main");
	public static final ResourceLocation MASK_TEXTURE = ResourceLocation.fromNamespaceAndPath(NeoForgeTestMod.MOD_ID,"render/kitsune_mask_purple_texture.png");
	private final RenderType RENDER_TYPE = renderType(MASK_TEXTURE);

	private static final ModelPartData BASE = new ModelPartData("base", CubeListBuilder.create()
			.texOffs(7, 7)
			.addBox(-4.0F, -8.0F, -5.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0f)));
	private static final ModelPartData LEFT_EAR = new ModelPartData("left_ear", CubeListBuilder.create()
			.texOffs(0, 4).mirror()
			.addBox(2.0F, -11.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(1, 1)
			.addBox(-4.0F, -10.0F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)));

	private static final ModelPartData RIGHT_EAR = new ModelPartData("right_ear", CubeListBuilder.create()
			.texOffs(1, 1).mirror()
			.addBox(1.0F, -10.0F, -5.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(6, 4)
			.addBox(-4.0F, -11.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)));

	private static final ModelPartData NOSE = new ModelPartData("nose", CubeListBuilder.create()
			.texOffs(0, 6)
			.addBox(-3.0F, -3.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(9, 1)
			.addBox(-2.0F, -4.0F, -6.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, 1.0F, 0.0F));

	private final List<ModelPart> parts;



	public KitsuneMaskModel(EntityModelSet entityModelSet){
		super(RenderType::entitySolid);
		ModelPart root = entityModelSet.bakeLayer(MASK_LAYER);
		parts = getRenderableParts(root, BASE, LEFT_EAR, RIGHT_EAR, NOSE);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();


//		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public static LayerDefinition createLayerDefinition() {
		return createLayerDefinition(64, 64, BASE, LEFT_EAR, RIGHT_EAR, NOSE);
	}

	protected static VertexConsumer getVertexConsumer(@NotNull MultiBufferSource renderer, @NotNull RenderType renderType, boolean hasEffect) {
		return ItemRenderer.getFoilBufferDirect(renderer, renderType, false, hasEffect);
	}

	public void render(@NotNull PoseStack matrix, @NotNull MultiBufferSource renderer, int light, int overlayLight, boolean hasEffect){
		renderToBuffer(matrix, getVertexConsumer(renderer, RENDER_TYPE, hasEffect), light, overlayLight, 0xFFFFFFFF);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		renderPartsToBuffer(parts, poseStack, buffer, packedLight, packedOverlay, color);
//		Head.render(poseStack, buffer, packedLight, packedOverlay, color);
	}
}