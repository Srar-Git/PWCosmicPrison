package cn.pixelwar.pwitemmanager.Shards.Events;

import cn.pixelwar.pwitemmanager.Shards.GUI.ShardMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ShardMenuEvents implements Listener {


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().getType().equals(Material.AMETHYST_SHARD)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                ShardMenu gui = new ShardMenu();
                gui.createGUI(event.getPlayer());
            }
        }


    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryView inventoryView = event.getView();
        String title = inventoryView.getTitle();
        Inventory viewInventory = event.getView().getTopInventory();
        if (event.getClickedInventory() == null) {
            return;
        }
        if (title.contains("矿物碎片")) {
            event.setCancelled(true);
        }
        if (title.contains("矿物碎片")) {
            if (event.getClickedInventory().equals(player.getInventory())) {
                event.setCancelled(true);
                if (event.getCurrentItem().getType().equals(Material.AMETHYST_SHARD)) {
                    viewInventory.setItem(0, new ItemStack(Material.AMETHYST_SHARD));
                    player.updateInventory();
                }
            }
        }


    }


}
