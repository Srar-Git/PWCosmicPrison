package cn.pixelwar.pwlevel.Listeners;

import cn.pixelwar.pwlevel.Files.YamlStorage;
import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerQuit implements Listener {

    @EventHandler
    public void OnPlayerJoin(PlayerQuitEvent event) {
        PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
        new BukkitRunnable() {
            @Override
            public void run() {
                YamlStorage yamlStorage = new YamlStorage();
                yamlStorage.savePlayerData(event.getPlayer());
                playerBoosterDataManager.removePlayerFromMap(event.getPlayer());
            }
        }.runTaskAsynchronously(PWLevel.getPlugin());

    }

}
