package cn.pixelwar.pwitemmanager.Listeners;

import cn.pixelwar.pwitemmanager.PWItemManager;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DropListener implements Listener {

    private PWItemManager plugin = (PWItemManager) JavaPlugin.getPlugin(PWItemManager.class);
    private static Map<Item, Map.Entry<Player, Integer>> playerDrops = new HashMap<>();

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Item dropItem = event.getItemDrop();
        event.getItemDrop().setPickupDelay(this.plugin.getPlayerDropDelay());
        if (!(dropItem.getItemStack().hasItemMeta())) {
            new BukkitRunnable() {
                @Override
                public void run() {

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            dropItem.remove();
                        }
                    }.runTask(PWItemManager.getPlugin());

                }
            }.runTaskLaterAsynchronously(PWItemManager.getPlugin(), 100L);
//            try {
//                Field itemField = dropItem.getClass().getDeclaredField("item");
//                Field ageField;
//                Object entityItem;
//
//                itemField.setAccessible(true);
//                entityItem = itemField.get(dropItem);
//
//                ageField = entityItem.getClass().getDeclaredField("age");
//                ageField.setAccessible(true);
//                ageField.set(entityItem, 3500);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
        }

        if (this.plugin.getStealDelay() > 0) {

            this.playerDrops.put(event.getItemDrop(), new AbstractMap.SimpleEntry<>(event.getPlayer(), Integer.valueOf(this.plugin.getStealDelay())));
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Item item = event.getItem();
        if (this.playerDrops.containsKey(item)) {

            Player ply = (Player) ((Map.Entry) this.playerDrops.get(item)).getKey();
            UUID plyuuid = ply.getUniqueId();
            int delay = ((Integer) ((Map.Entry) this.playerDrops.get(item)).getValue()).intValue();

            if (!event.getPlayer().getUniqueId().equals(plyuuid) && item.getTicksLived() < delay) {

                event.setCancelled(true);

            } else {

                this.playerDrops.remove(item);
            }
        }
    }

    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        Item item = event.getEntity();
        if (this.playerDrops.containsKey(item)) {

            this.playerDrops.remove(item);
        }
    }

    public static void dropItem(Player owner, ItemStack item) {
        Item dropItem = owner.getWorld().dropItemNaturally(owner.getLocation(), item);
        dropItem.setItemStack(item);

        dropItem.setPickupDelay(PWItemManager.getPlayerDropDelay());

        if (!(dropItem.getItemStack().hasItemMeta())) {

            try {
                Field itemField = dropItem.getClass().getDeclaredField("item");
                Field ageField;
                Object entityItem;

                itemField.setAccessible(true);
                entityItem = itemField.get(dropItem);

                ageField = entityItem.getClass().getDeclaredField("age");
                ageField.setAccessible(true);
                ageField.set(entityItem, 3500);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (PWItemManager.getStealDelay() > 0) {
            playerDrops.put(dropItem, new AbstractMap.SimpleEntry<>(owner, Integer.valueOf(PWItemManager.getStealDelay())));
        }


    }

}
