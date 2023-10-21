package cn.pixelwar.pwblockmanager.BlockStuff.enchants;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Baoji {

    public int getBaoji(Player player) {
        ItemStack tool = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(tool);
        if (nbtItem.hasKey("ae_enchantment;baoji")) {
            int enchantLevel = nbtItem.getInteger("ae_enchantment;baoji");

            return enchantLevel;
        } else {
            return 0;
        }
    }


}
