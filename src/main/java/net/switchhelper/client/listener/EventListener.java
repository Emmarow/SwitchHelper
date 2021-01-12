package net.switchhelper.client.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.switchhelper.client.keybinding.KeyBindingRegistry;

public class EventListener
{
    public static float sensitivity = 0.0F;
    public static float originalSensitivity = 100.0F;
    public static boolean sensitivitySwitched = false;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        if (KeyBindingRegistry.SWITCH_ARMOUR.isPressed())
        {
            if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemElytra)
            {
                int bestArmourSlot = getBestArmourSlot();

                if (bestArmourSlot != -1)
                    swapItems(bestArmourSlot, 6);
            } else if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemArmor)
            {
                int bestElytraSlot = getBestElytraSlot();

                if (bestElytraSlot != -1)
                    swapItems(bestElytraSlot, 6);
            } else if (Minecraft.getMinecraft().player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.AIR)
            {
                int bestElytraSlot = getBestElytraSlot();
                int bestArmourSlot = getBestArmourSlot();

                if (bestElytraSlot != -1)
                    Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, bestElytraSlot, 0, ClickType.QUICK_MOVE, Minecraft.getMinecraft().player);
                else if (bestArmourSlot != -1)
                    Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, bestArmourSlot, 0, ClickType.QUICK_MOVE, Minecraft.getMinecraft().player);
            }
        }

        if (KeyBindingRegistry.SWITCH_SENSITIVITY.isPressed())
        {
            if (!sensitivitySwitched)
            {
                originalSensitivity = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
                System.out.println(Minecraft.getMinecraft().gameSettings.mouseSensitivity);
                Minecraft.getMinecraft().gameSettings.mouseSensitivity = sensitivity;
            }

            if (sensitivitySwitched)
                Minecraft.getMinecraft().gameSettings.mouseSensitivity = originalSensitivity;

            sensitivitySwitched = !sensitivitySwitched;
        }
    }

    private void swapItems(int startSlot, int endSlot)
    {
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, startSlot, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, endSlot, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().player.inventoryContainer.windowId, startSlot, 0, ClickType.PICKUP, Minecraft.getMinecraft().player);
    }

    private int getBestElytraSlot()
    {
        int bestSlot = -1;

        for (int i = 0; i < Minecraft.getMinecraft().player.inventoryContainer.getInventory().size(); i++)
        {
            if (Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack().getItem() instanceof ItemElytra)
            {
                if (bestSlot == -1)
                    bestSlot = i;
                else
                {
                    if (Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack().getItemDamage() < Minecraft.getMinecraft().player.inventoryContainer.getSlot(bestSlot).getStack().getItemDamage())
                        bestSlot = i;
                }
            }
        }

        return bestSlot;
    }

    private int getBestArmourSlot()
    {
        int bestSlot = -1;

        for (int i = 0; i < Minecraft.getMinecraft().player.inventoryContainer.getInventory().size(); i++)
        {
            if (Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack().getItem() instanceof ItemArmor)
            {
                if (((ItemArmor) Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack().getItem()).armorType.equals(EntityEquipmentSlot.CHEST))
                {
                    if (bestSlot == -1)
                        bestSlot = i;
                    else
                    {
                        if (((ItemArmor) Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack().getItem()).getArmorMaterial().getDamageReductionAmount(EntityEquipmentSlot.CHEST) > ((ItemArmor) Minecraft.getMinecraft().player.inventoryContainer.getSlot(bestSlot).getStack().getItem()).getArmorMaterial().getDamageReductionAmount(EntityEquipmentSlot.CHEST))
                            bestSlot = i;
                    }
                }
            }
        }

        return bestSlot;
    }
}
