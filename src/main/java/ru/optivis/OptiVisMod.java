package ru.optivis;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.optivis.config.OptiVisConfig;
import ru.optivis.gui.OptiVisScreen;

public class OptiVisMod implements ClientModInitializer {

    public static final String MOD_ID = "optivis";
    public static KeyBinding openGuiKey;

    @Override
    public void onInitializeClient() {
        OptiVisConfig.load();

        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.optivis.open_gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.optivis"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGuiKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new OptiVisScreen());
                }
            }
        });
    }
}
