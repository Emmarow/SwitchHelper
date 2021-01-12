package net.switchhelper.client.keybinding;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.switchhelper.util.Constants;
import org.lwjgl.input.Keyboard;

public class KeyBindingRegistry
{
    public static final KeyBinding
            SWITCH_ARMOUR = new KeyBinding("key.switcharmour", Keyboard.KEY_NUMPAD0, Constants.General.MOD_NAME),
            SWITCH_SENSITIVITY = new KeyBinding("key.switchsensitivity", Keyboard.KEY_NUMPAD1, Constants.General.MOD_NAME);

    public static void initialization()
    {
        ClientRegistry.registerKeyBinding(SWITCH_ARMOUR);
        ClientRegistry.registerKeyBinding(SWITCH_SENSITIVITY);
    }
}
