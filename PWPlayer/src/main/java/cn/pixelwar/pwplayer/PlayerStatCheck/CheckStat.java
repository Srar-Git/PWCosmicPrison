package cn.pixelwar.pwplayer.PlayerStatCheck;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckStat {

    public void checkPlayer(Player viewer, Player checked) {
        Map<Integer, ItemStack> items = getPlayerStatItems(checked);
        if (items == null) {
            return;
        }
        Inventory gui = Bukkit.createInventory(viewer, 54, ChatColor.DARK_GRAY + "个人信息 " + ChatColor.UNDERLINE + checked.getDisplayName());

        ItemStack back = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);

        ItemMeta infoMeta = back.getItemMeta();
        infoMeta.setDisplayName(ChatColor.RED + " ");
        back.setItemMeta(infoMeta);

        viewer.playSound(viewer.getEyeLocation(), Sound.UI_BUTTON_CLICK, SoundCategory.BLOCKS, 1.0f, 2.0f);
//        PlayPacketSound(player, "ui.button.click", player.getEyeLocation().getX(), player.getEyeLocation().getY(), player.getEyeLocation().getZ(), 1.0f, 2.0f);
        for (int i = 0; i < 54; i++) {
            gui.setItem(i, back);
        }

        for (int slot : items.keySet()) {
            gui.setItem(slot, items.get(slot));
        }
        viewer.openInventory(gui);
    }

    public Map<Integer, ItemStack> getPlayerStatItems(Player player) {
        if (player == null) {
            return null;
        }
        if (!(player.isOnline())) {
            return null;
        }
        //<格子,物品>
        Map<Integer, ItemStack> items = new HashMap<>();
        //玩家信息
        ItemStack playerInfo = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) playerInfo.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        playerInfo.setItemMeta(meta);
        items.put(0, playerInfo);

        ItemStack red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta infoMeta2 = red.getItemMeta();
        infoMeta2.setDisplayName(ChatColor.RED + "没有物品");
        red.setItemMeta(infoMeta2);
        if (player.getInventory().getHelmet() == null) {
            items.put(2, red);
        } else {
            items.put(2, player.getInventory().getHelmet());
        }
        if (player.getInventory().getChestplate() == null) {
            items.put(3, red);
        } else {
            items.put(3, player.getInventory().getChestplate());
        }
        if (player.getInventory().getLeggings() == null) {
            items.put(4, red);
        } else {
            items.put(4, player.getInventory().getLeggings());
        }
        if (player.getInventory().getBoots() == null) {
            items.put(5, red);
        } else {
            items.put(5, player.getInventory().getBoots());
        }
        if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            items.put(7, red);
        } else {
            items.put(7, player.getInventory().getItemInMainHand());
        }
        if (player.getInventory().getItemInOffHand() == null || player.getInventory().getItemInOffHand().getType().equals(Material.AIR)) {
            items.put(8, red);
        } else {
            items.put(8, player.getInventory().getItemInOffHand());
        }
        return items;
    }


}
