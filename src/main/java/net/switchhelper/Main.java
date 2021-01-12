package net.switchhelper;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.switchhelper.client.command.CommandSensitivity;
import net.switchhelper.client.keybinding.KeyBindingRegistry;
import net.switchhelper.client.listener.EventListener;
import net.switchhelper.util.Constants;

@Mod(modid = Constants.General.MOD_ID, name = Constants.General.MOD_NAME, version = Constants.General.MOD_VERSION)
@SideOnly(Side.CLIENT)
public class Main
{
    @Mod.EventHandler
    public void preInitialization(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new EventListener());

        KeyBindingRegistry.initialization();
    }

    @Mod.EventHandler
    public void onFMLServerStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSensitivity());
    }
}
