package cn.pixelwar.pwlevel.PlayerData.PlayerExp;

import cn.pixelwar.pwlevel.CustomEvents.LevelUpEvent;
import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerOreExp.PlayerOreExpDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerExpDataManager {

    private static Map<String, PlayerExpData> playerExpDataMap = new ConcurrentHashMap<>();

    public static Map<String, PlayerExpData> getPlayerExpDataMap() {
        return playerExpDataMap;
    }

    public int getLevel(Player player) {

        return playerExpDataMap.get(player.getName()).getLevel();
    }


    public long getTotalExp(Player player) {
        return playerExpDataMap.get(player.getName()).getTotalExp();
    }

    public int getPrestige(Player player) {
        return playerExpDataMap.get(player.getName()).getPrestige();
    }

    public void createPlayerExpData(Player player, long totalExp, int prestige, int level) {
        PlayerExpData playerExpData = new PlayerExpData(totalExp, level, prestige);
        playerExpDataMap.put(player.getName(), playerExpData);
    }

    public void checkPlayerLevel(Player player) {
        PlayerOreExpDataManager playerOreExpDataManager = new PlayerOreExpDataManager();
        int prestige = playerExpDataMap.get(player.getName()).getPrestige();
        int nowLevel = playerExpDataMap.get(player.getName()).getLevel();
        long totalExp = playerExpDataMap.get(player.getName()).getTotalExp();
        long nxetExp = PWLevel.config.getLong("levels.level" + (nowLevel + 1));
        nxetExp = nxetExp * (1 + prestige);
        if (totalExp >= nxetExp) {
            nowLevel++;
            Bukkit.getServer().getPluginManager().callEvent(new LevelUpEvent(player, nowLevel));
            playerOreExpDataManager.resetOreExp(player);
        }
        createPlayerExpData(player, totalExp, prestige, nowLevel);
    }

    public void setPlayerLevelStuff(Player player) {
        int prestige = playerExpDataMap.get(player.getName()).getPrestige();
        int level = playerExpDataMap.get(player.getName()).getLevel();
        long nxetExp = PWLevel.config.getLong("levels.level" + (getLevel(player) + 1));
        nxetExp = nxetExp * (1 + prestige);
        long lastExp = PWLevel.config.getLong("levels.level" + getLevel(player));
        long totalneed = nxetExp - lastExp;
        long thislevelexp = getTotalExp(player) - lastExp;
        float percent = ((float) thislevelexp / totalneed);
        player.setLevel(level);
        if (percent > 1.0) {
            percent = 1.0f;
        }
        if (percent < 0) {
            percent = 0f;
        }
        player.setExp(percent);
    }


}
