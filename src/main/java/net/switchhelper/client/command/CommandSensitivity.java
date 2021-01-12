package net.switchhelper.client.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.switchhelper.client.listener.EventListener;

public class CommandSensitivity extends CommandBase
{
    @Override
    public String getName()
    {
        return "sensitivity";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1 ||
            args.length > 1)
        {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Wrong usage!"));

            return;
        }

        if (Float.parseFloat(args[0]) < 0.0F ||
            Float.parseFloat(args[0]) > 10.0F)
        {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Sensitivity range: 0.0 - 10.0."));

            return;
        }

        EventListener.sensitivity = Float.parseFloat(args[0]);
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Sensitivity: " + args[0]));
    }
}
