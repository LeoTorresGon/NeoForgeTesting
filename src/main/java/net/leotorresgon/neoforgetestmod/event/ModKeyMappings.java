package net.leotorresgon.neoforgetestmod.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {
    public static final KeyMapping MAPPING = new KeyMapping(
            "key.neoforgetestmod.dash", // Will be localized using this translation key
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_APOSTROPHE, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    );

}
