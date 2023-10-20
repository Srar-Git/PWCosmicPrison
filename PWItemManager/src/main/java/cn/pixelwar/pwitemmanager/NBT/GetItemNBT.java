package cn.pixelwar.pwitemmanager.NBT;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetItemNBT {

    public int getItemIntNBT(ItemStack item, String path){
        NBTItem nbti = new NBTItem(item);
        if (!(nbti.hasKey(path))){return 0;}
        return nbti.getInteger(path);
    }
    public double getItemDoubleNBT(ItemStack item, String path){
        NBTItem nbti = new NBTItem(item);
        if (!(nbti.hasKey(path))){return 0;}
        return nbti.getDouble(path);
    }


    public String getItemStringNBT(ItemStack item, String path){
        NBTItem nbti = new NBTItem(item);
        if (!(nbti.hasKey(path))){return "null";}
//        if (nbti.getString(path) == null){
//            return "NONE";
//        }
        return nbti.getString(path);
    }


}
