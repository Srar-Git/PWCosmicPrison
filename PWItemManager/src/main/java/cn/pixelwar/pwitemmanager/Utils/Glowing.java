package cn.pixelwar.pwitemmanager.Utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Glowing {

    public void makeHandGlow(Player player) {
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.OXYGEN, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        player.setItemInHand(item);
    }
}
