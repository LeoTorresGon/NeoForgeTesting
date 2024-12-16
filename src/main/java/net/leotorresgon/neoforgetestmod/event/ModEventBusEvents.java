package net.leotorresgon.neoforgetestmod.event;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.leotorresgon.neoforgetestmod.model.DataBasedModelLoader;
import net.leotorresgon.neoforgetestmod.model.KitsuneMaskModel;
import net.leotorresgon.neoforgetestmod.particle.ModParticles;
import net.leotorresgon.neoforgetestmod.particle.custom.BloodParticles;
import net.leotorresgon.neoforgetestmod.particle.custom.BloodParticlesStatic;
import net.leotorresgon.neoforgetestmod.render.RenderPropertiesProvider;
import net.leotorresgon.neoforgetestmod.render.RenderPropertiesProvider.CustomArmorRenderProperties;
import net.leotorresgon.neoforgetestmod.render.armor.KitsuneMaskArmor;
import net.leotorresgon.neoforgetestmod.render.item.KitsuneMaskRender;
import net.leotorresgon.neoforgetestmod.render.layer.MekanismArmorLayer;
import net.leotorresgon.neoforgetestmod.screen.ModMenuTypes;
import net.leotorresgon.neoforgetestmod.screen.SoulTunerScreen;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.util.TriState;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = NeoForgeTestMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {



    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ModParticles.BLOOD_PARTICLES.get(), BloodParticles.Provider::new);
        event.registerSpriteSet(ModParticles.BLOOD_PARTICLES_STATIC.get(), BloodParticlesStatic.Provider::new);
    }

    @SubscribeEvent
    private static void registerScreens(RegisterMenuScreensEvent event){
        event.register(ModMenuTypes.SOUL_TUNER_MENU.get(), SoulTunerScreen::new);
    }

    @SubscribeEvent
    private static void registerBindings(RegisterKeyMappingsEvent event){
        event.register(ModKeyMappings.MAPPING);
    }

    @SubscribeEvent
    private static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(KitsuneMaskModel.MASK_LAYER, KitsuneMaskModel::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(KitsuneMaskRender.RENDERER);
        event.registerReloadListener(KitsuneMaskArmor.KITSUNE_MASK);
    }

    @SubscribeEvent
    public static void registerClientExtension(RegisterClientExtensionsEvent event){
        event.registerItem(new CustomArmorRenderProperties(KitsuneMaskRender.RENDERER, KitsuneMaskArmor.KITSUNE_MASK), ModItems.KITSUNE_MASK);
//        event.registerItem(new RenderPropertiesProvider.MekRenderProperties(KitsuneMaskRender.RENDERER), ModItems.KITSUNE_MASK);
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        //Add our own custom armor and elytra layer to the various player renderers
        for (PlayerSkin.Model skin : event.getSkins()) {
            //Note: We expect this to always be an instanceof PlayerRenderer, but we just bother checking if it is a LivingEntityRenderer
            if (event.getSkin(skin) instanceof LivingEntityRenderer<?, ?> renderer) {
                addCustomLayers(EntityType.PLAYER, renderer, event.getContext());
            }
        }
        //Add our own custom armor and elytra layer to everything that has an armor layer
        //Note: This includes any modded mobs that have vanilla's HumanoidArmorLayer or ElytraLayer added to them
        for (EntityType<?> entityType : event.getEntityTypes()) {
            if (event.getRenderer(entityType) instanceof LivingEntityRenderer<?, ?> renderer) {
                addCustomLayers(entityType, renderer, event.getContext());
            }
        }
    }

    private static <LIVING extends LivingEntity, MODEL extends EntityModel<LIVING>> void addCustomLayers(@NotNull EntityType<?> type,
                                                                                                         @NotNull LivingEntityRenderer<LIVING, MODEL> renderer, @NotNull EntityRendererProvider.Context context) {
        int layerTypes = 2;
        Map<String, RenderLayer<LIVING, MODEL>> layersToAdd = new HashMap<>(layerTypes);
        for (RenderLayer<LIVING, MODEL> layerRenderer : renderer.layers) {
            //Validate against the layer render being null, as it seems like some mods do stupid things and add in null layers
            if (layerRenderer != null) {
                //Only allow an exact class match, so we don't add to modded entities that only have a modded extended armor or elytra layer
                Class<?> layerClass = layerRenderer.getClass();
                if (layerClass == HumanoidArmorLayer.class) {
                    //Note: We know that the MODEL is actually an instance of HumanoidModel, or there wouldn't be a
                    //noinspection unchecked,rawtypes
                    layersToAdd.put("Armor", new MekanismArmorLayer(renderer, (HumanoidArmorLayer<LIVING, ?, ?>) layerRenderer, context.getModelManager()));
                    if (layersToAdd.size() == layerTypes) {
                        break;
                    }
                }
            }
        }
        if (!layersToAdd.isEmpty()) {
            ResourceLocation entityName = BuiltInRegistries.ENTITY_TYPE.getKey(type);
            for (Map.Entry<String, RenderLayer<LIVING, MODEL>> entry : layersToAdd.entrySet()) {
                renderer.addLayer(entry.getValue());
            }
        }
    }

    @SubscribeEvent
    public static void registerModelLoaders(ModelEvent.RegisterGeometryLoaders event) {
        event.register(NeoForgeTestMod.rl("data_based"), DataBasedModelLoader.INSTANCE);
    }


}
