package cn.pixelwar.pwitemmanager.NBT;

import cn.pixelwar.pwitemmanager.PWItemManager;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SetItemNBT {

    public void updateHandNBT(Player player, String path, String nbtString) {
        new BukkitRunnable() {
            @Override
            public void run() {
                NBTItem nbti = new NBTItem(player.getItemInHand());
                nbti.setString(path, nbtString);
                ItemStack finalItem = nbti.getItem();
                player.getInventory().setItemInMainHand(finalItem);
            }
        }.runTask(PWItemManager.getPlugin());
    }

    public void updateHandNBT(Player player, String path, int nbtInt) {
        new BukkitRunnable() {
            @Override
            public void run() {
                NBTItem nbti = new NBTItem(player.getItemInHand());
                nbti.setInteger(path, nbtInt);
                ItemStack finalItem = nbti.getItem();
                player.getInventory().setItemInMainHand(finalItem);
            }
        }.runTask(PWItemManager.getPlugin());
    }
//    public void updateHandNBT(Player player, String path, double nbtDouble){
//        NBTItem nbti = new NBTItem(player.getItemInHand());
//        nbti.setDouble(path, nbtDouble);
//        ItemStack finalItem = nbti.getItem();
//        player.getInventory().setItemInMainHand(finalItem);
//    }
//    public void updateHandNBT(Player player, ItemStack item, String path, boolean nbtBoolean){
//        NBTItem nbti = new NBTItem(item);
//        nbti.setBoolean(path, nbtBoolean);
//        ItemStack finalItem = nbti.getItem();
//        player.getInventory().setItemInMainHand(finalItem);
//    }


}
