package cn.pixelwar.pwlevel.Utils;

import cn.pixelwar.pwlevel.Files.YamlStorage;
import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveData {

    public void saveData(Player player){
        PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
                YamlStorage yamlStorage = new YamlStorage();
                yamlStorage.savePlayerData(player);
                playerBoosterDataManager.removePlayerFromMap(player);
    }

    public void savingTimer(){
        PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                    saveData(player);
                }
//                System.out.println("[PWLevel] 成功保存玩家数据");
                PWLevel.getPlugin().getLogger().info("成功保存玩家经验数据......");
            }
        }.runTaskTimerAsynchronously(PWLevel.getPlugin(), 0l, 12000l);
    }

}
