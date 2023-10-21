package cn.pixelwar.pwblockmanager.BlockStuff.enchants;

import de.tr7zw.nbtapi.NBTItem;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Lucky {

    public int getLuckyChance(Player player) {
        ItemStack tool = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(tool);
        if (nbtItem.hasKey("ae_enchantment;lucky")) {
            int enchantLevel = nbtItem.getInteger("ae_enchantment;lucky");
            int n5 = RandomUtils.nextInt(0, 100);
            if (n5 < enchantLevel) {
                return 2;
            }
            return 1;
        }
        return 1;
    }

}
