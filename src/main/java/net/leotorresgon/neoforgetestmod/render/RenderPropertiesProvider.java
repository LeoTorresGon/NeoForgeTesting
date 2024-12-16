package net.leotorresgon.neoforgetestmod.render;

import net.leotorresgon.neoforgetestmod.render.armor.ICustomArmor;
import net.leotorresgon.neoforgetestmod.render.armor.ISpecialGear;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import org.jetbrains.annotations.NotNull;

public class RenderPropertiesProvider {

    private RenderPropertiesProvider() {

    }

    public record CustomArmorRenderProperties(BlockEntityWithoutLevelRenderer renderer, ICustomArmor gearModel) implements ISpecialGear {

        @NotNull
        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return renderer;
        }
    }
}
