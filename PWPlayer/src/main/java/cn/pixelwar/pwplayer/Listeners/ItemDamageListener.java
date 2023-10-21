package cn.pixelwar.pwplayer.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import static cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.ArmorEnchant.doVictimArmorDurDamage;

public class ItemDamageListener implements Listener {


    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        //NPC的装备无限耐久
        if (((Entity) player).hasMetadata("NPC")) {
            event.setCancelled(true);
            return;
        }

        ItemStack item = event.getItem();
        if (
                item.getType().toString().contains("HELMET") ||
                        item.getType().toString().contains("CHESTPLATE") ||
                        item.getType().toString().contains("LEGGINGS") ||
                        item.getType().toString().contains("BOOTS")
        ) {
            int damage = doVictimArmorDurDamage(event.getPlayer(), item, event.getDamage());
            event.setDamage(damage);
        }

    }
}
