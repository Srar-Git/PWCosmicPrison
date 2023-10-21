package cn.pixelwar.pwlevel.Files;

import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager.*;

public class YamlStorage {

    private final FileConfiguration config = new YamlConfiguration();
    PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
    PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();

    //    PlayerOreExpDataManager playerOreExpDataManager = new PlayerOreExpDataManager();
    public void CheckYamlFile(Player player) {

        String playerName = player.getName();
        boolean isExist = true;

        File dataFolder = new File("plugins/PWLevel/Players");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File("plugins/PWLevel/Players/" + playerName + ".yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                isExist = false;
            } catch (IOException ex) {
            }
        }
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {

        }
        config.set("Name", player.getName());
        if (!(isExist)) {
            config.set("totalExp", 0);
            config.set("level", 0);
            config.set("prestige", 0);
            config.set("ExpBoosterTime", 0);
            config.set("ExpMultiple", 1.0);
            config.set("EnergyBoosterTime", 0);
            config.set("EnergyMultiple", 1.0);
            config.set("ShardBoosterTime", 0);
            config.set("ShardMultiple", 1.0);

//            config.set("IronExp", 0);
//            config.set("LapisExp", 0);
//            config.set("RedstoneExp", 0);
//            config.set("GoldExp", 0);
//            config.set("DiamondExp", 0);
//            config.set("EmeraldExp", 0);
        }
        try {
            config.save(dataFile);
        } catch (IOException ex) {
        }
    }

    public void CreatePlayerDataMap(Player player) {


        File dataFolder = new File("plugins/PWLevel/Players");
        File dataFile = new File("plugins/PWLevel/Players/" + player.getName() + ".yml");

        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }

        long totalExp = config.getLong("totalExp");
        int level = config.getInt("level");
        int prestige = config.getInt("prestige");
        int ExpBoosterTime = config.getInt("ExpBoosterTime");
        int EnergyBoosterTime = config.getInt("EnergyBoosterTime");
        int ShardBoosterTime = config.getInt("ShardBoosterTime");
        double ExpMultiple = config.getDouble("ExpMultiple");
        double EnergyMultiple = config.getDouble("EnergyMultiple");
        double ShardMultiple = config.getDouble("ShardMultiple");

        playerExpDataManager.createPlayerExpData(player, totalExp, prestige, level);


        if (ExpBoosterTime > 0) {
            playerBoosterDataManager.createExpBoosterData(player, ExpBoosterTime, ExpMultiple);
        }
        if (EnergyBoosterTime > 0) {
            playerBoosterDataManager.createEnergyBoosterData(player, EnergyBoosterTime, EnergyMultiple);
        }
        if (ShardBoosterTime > 0) {
            playerBoosterDataManager.createShardBoosterData(player, ShardBoosterTime, ShardMultiple);
        }
    }

    public void savePlayerData(Player player) {
        File dataFolder = new File("plugins/PWLevel/Players");
        File dataFile = new File("plugins/PWLevel/Players/" + player.getName() + ".yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }

        config.set("totalExp", playerExpDataManager.getTotalExp(player));
        config.set("level", playerExpDataManager.getLevel(player));
        config.set("prestige", playerExpDataManager.getPrestige(player));

        if (getPlayerExpBoosterDataMap().containsKey(player.getName())) {
            config.set("ExpBoosterTime", playerBoosterDataManager.getExpBoosterTime(player));
            config.set("ExpMultiple", playerBoosterDataManager.getExpMultiple(player));
        } else {
            config.set("ExpBoosterTime", 0);
            config.set("ExpMultiple", 1.0);
        }
        if (getPlayerEnergyBoosterDataMap().containsKey(player.getName())) {
            config.set("EnergyBoosterTime", playerBoosterDataManager.getEnergyBoosterTime(player));
            config.set("EnergyMultiple", playerBoosterDataManager.getEnergyMultiple(player));
        } else {
            config.set("EnergyBoosterTime", 0);
            config.set("EnergyMultiple", 1.0);
        }
        if (getPlayerShardBoosterDataMap().containsKey(player.getName())) {
            config.set("ShardBoosterTime", playerBoosterDataManager.getShardBoosterTime(player));
            config.set("ShardMultiple", playerBoosterDataManager.getShardMultiple(player));
        } else {
            config.set("ShardBoosterTime", 0);
            config.set("ShardMultiple", 1.0);
        }
        try {
            config.save(dataFile);
        } catch (IOException ex) {
            System.out.println("玩家" + player.getName() + "的等级信息保存出错");
        }
    }
}
