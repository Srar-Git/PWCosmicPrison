package cn.pixelwar.pwitemmanager.Shards.GUI;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShardMenu {

    public void createGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY+"矿物碎片 "+ChatColor.UNDERLINE+player.getDisplayName());

        ItemStack back = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta infoMeta = back.getItemMeta();
        infoMeta.setDisplayName(ChatColor.RED + " ");
        back.setItemMeta(infoMeta);
        player.playSound(player.getEyeLocation(), Sound.UI_BUTTON_CLICK, SoundCategory.BLOCKS, 1.0f, 2.0f);
//        PlayPacketSound(player, "ui.button.click", player.getEyeLocation().getX(), player.getEyeLocation().getY(), player.getEyeLocation().getZ(), 1.0f, 2.0f);
        ItemStack[] allBackGround = {};
        for (int i = 0; i < 54; i++){
            gui.setItem(i, back);
        }
        ItemStack withdraw = new ItemStack(Material.CHEST);
        ItemMeta withdrawMeta = withdraw.getItemMeta();
        withdrawMeta.setDisplayName(ChatColor.RED+ ""+ ChatColor.BOLD + "取出所有矿物碎片");
        withdraw.setItemMeta(withdrawMeta);

        List<String> LoreList = new ArrayList<String>();
        LoreList.add(0, "");
        LoreList.add(1, ChatColor.GRAY + "(点击取出)");
        ItemMeta itemMeta = withdraw.getItemMeta();
        itemMeta.setLore(LoreList);
        withdraw.setItemMeta(itemMeta);
        gui.setItem(45, withdraw);
        player.openInventory(gui);
    }


}
