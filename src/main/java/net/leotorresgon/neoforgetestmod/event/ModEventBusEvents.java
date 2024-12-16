package net.leotorresgon.neoforgetestmod.event;

import net.leotorresgon.neoforgetestmod.NeoForgeTestMod;
import net.leotorresgon.neoforgetestmod.item.ModItems;
import net.leotorresgon.neoforgetestmod.model.KitsuneMaskModel;
import net.leotorresgon.neoforgetestmod.particle.ModParticles;
import net.leotorresgon.neoforgetestmod.particle.custom.BloodParticles;
import net.leotorresgon.neoforgetestmod.particle.custom.BloodParticlesStatic;
import net.leotorresgon.neoforgetestmod.render.RenderPropertiesProvider.CustomArmorRenderProperties;
import net.leotorresgon.neoforgetestmod.render.armor.KitsuneMaskArmor;
import net.leotorresgon.neoforgetestmod.render.item.KitsuneMaskRender;
import net.leotorresgon.neoforgetestmod.screen.ModMenuTypes;
import net.leotorresgon.neoforgetestmod.screen.SoulTunerScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

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
        event.registerLayerDefinition(KitsuneMaskModel.MASK_LOCATION, KitsuneMaskModel::createLayerDefinition);
    }

    @SubscribeEvent
    public static void registerClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(KitsuneMaskRender.RENDERER);
        event.registerReloadListener(KitsuneMaskArmor.KITSUNE_MASK);
    }

    @SubscribeEvent
    public static void registerClientExtension(RegisterClientExtensionsEvent event){
        event.registerItem(new CustomArmorRenderProperties(KitsuneMaskRender.RENDERER, KitsuneMaskArmor.KITSUNE_MASK), ModItems.KITSUNE_MASK);
    }
}
