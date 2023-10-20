package cn.pixelwar.pwitemmanager.NBT;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetSlotNBT {

    public void setSlotItemNBT(Player player,int slot, String path, String nbtString){
        NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
        nbti.setString(path, nbtString);
        ItemStack finalItem = nbti.getItem();
        player.getInventory().setItem(slot, finalItem);
    }
    public void setSlotItemNBT(Player player,int slot, String path, double nbtDouble){
        NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
        nbti.setDouble(path, nbtDouble);
        ItemStack finalItem = nbti.getItem();
        player.getInventory().setItem(slot, finalItem);
    }


     public void setSlotItemNBT(Player player,int slot, String path, int nbtInt){
        NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
        nbti.setInteger(path, nbtInt);
        ItemStack finalItem = nbti.getItem();
        player.getInventory().setItem(slot, finalItem);
    }

}
