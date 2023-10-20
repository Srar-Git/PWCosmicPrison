package cn.pixelwar.pwlevel.Listeners;

import cn.pixelwar.pwlevel.Files.YamlStorage;
import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerOreExp.PlayerOreExpDataManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerJoin implements Listener {

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {
        PlayerOreExpDataManager playerOreExpDataManager = new PlayerOreExpDataManager();
        new BukkitRunnable() {
            @Override
            public void run() {
                YamlStorage yamlStorage = new YamlStorage();
                yamlStorage.CheckYamlFile(event.getPlayer());
                yamlStorage.CreatePlayerDataMap(event.getPlayer());
                playerOreExpDataManager.addOreExp(event.getPlayer(), 1, "emerald");
            }
        }.runTaskAsynchronously(PWLevel.getPlugin());

    }

}
