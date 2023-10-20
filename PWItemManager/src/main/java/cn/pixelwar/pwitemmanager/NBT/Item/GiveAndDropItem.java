package cn.pixelwar.pwitemmanager.NBT.Item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static cn.pixelwar.pwitemmanager.Listeners.DropListener.dropItem;

public class GiveAndDropItem {

    public static void giveItem(Player player, ItemStack item){
        if (player.getInventory().firstEmpty()==-1){
            for (ItemStack itemin : player.getInventory().getContents()){
                if (itemin==null){
                    continue;
                }
                if (itemin.getAmount()<=64-item.getAmount()){
                    ItemStack item2 = itemin.clone();
                    ItemStack item1 = item.clone();
                    item2.setAmount(1);
                    item1.setAmount(1);
                    if (item2.equals(item1)){
                        player.getInventory().addItem(item);
                        return;
                    }
                }
            }
            dropItem(player, item);
            return;
        }
        else {
            player.getInventory().addItem(item);
        }
    }

}
