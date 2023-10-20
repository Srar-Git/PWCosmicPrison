package cn.pixelwar.pwitemmanager.Listeners;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class EatListener implements Listener {



    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item.getType().equals(Material.COOKIE)){
            player.setFoodLevel(20);
            player.setSaturation(1.65f);
            return;
        }
        if (item.getType().equals(Material.APPLE)){
            player.setFoodLevel(20);
            player.setSaturation(3.33f);
            return;
        }
        if (item.getType().equals(Material.BREAD)){
            player.setFoodLevel(20);
            player.setSaturation(8.5f);
            return;
        }
        if (item.getType().equals(Material.PUMPKIN_PIE)){
            player.setFoodLevel(20);
            player.setSaturation(6.4f);
            return;
        }
        if (item.getType().equals(Material.COOKED_CHICKEN)){
            player.setFoodLevel(20);
            player.setSaturation(11.3f);
            return;
        }
        if (item.getType().equals(Material.COOKED_BEEF)){
            player.setFoodLevel(20);
            player.setSaturation(15f);
            return;
        }
        if (item.getType().equals(Material.GOLDEN_CARROT)){
            player.setFoodLevel(20);
            player.setSaturation(20f);
            return;
        }

    }
}
