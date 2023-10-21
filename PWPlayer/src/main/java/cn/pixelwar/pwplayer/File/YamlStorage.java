package cn.pixelwar.pwplayer.File;


import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerAction.Teleport.Teleport;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleData;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntData;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeData;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class YamlStorage {

    private final FileConfiguration config = new YamlConfiguration();

    public boolean CheckYamlFile(Player player) {

        String playerName = player.getName();
        boolean firstJoin = false;
        boolean isExist = true;

        File dataFolder = new File("plugins/PWPlayer/Players");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File dataFile = new File("plugins/PWPlayer/Players/" + playerName + ".yml");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                isExist = false;
                firstJoin = true;
            } catch (IOException ex) {
            }
        }
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {

        }
        config.set("Name", player.getName());
        try {
            config.save(dataFile);
        } catch (IOException ex) {
        }
        return firstJoin;
    }

    public void CreatePlayerDataMap(Player player) {
        File dataFile = new File("plugins/PWPlayer/Players/" + player.getName() + ".yml");

        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }

        //加载cooldown
        if (config.contains("coolDown")) {
            ConfigurationSection cooldowns = config.getConfigurationSection("coolDown");
            for (String type : cooldowns.getKeys(false)) {
                CoolDownType coolDownType = CoolDownType.valueOf(type);
                CoolDownManager.addPlayerCoolDown(player, coolDownType, config.getInt("coolDown." + type));
            }
        }

        //加载intdata
        IntDataType[] intDataTypes = IntDataType.values();
        IntData digData = new IntData();
        for (IntDataType intDataType : intDataTypes) {
            if (config.contains("intData." + intDataType.toString())) {
                int num = config.getInt("intData." + intDataType.toString());
                digData.singleMap.put(intDataType, num);
            } else {
                digData.singleMap.put(intDataType, intDataType.getDefaultNum());
            }
        }
        IntDataManager.IntDataMap.put(player.getName(), digData);

        //加载doubledata
        DoubleDataType[] doubleDataTypes = DoubleDataType.values();
        DoubleData doubleData = new DoubleData();
        for (DoubleDataType doubleDataType : doubleDataTypes) {
            if (config.contains("doubleData." + doubleDataType.toString())) {
                double num = config.getDouble("doubleData." + doubleDataType.toString());
                doubleData.singleMap.put(doubleDataType, num);
//                Bukkit.broadcastMessage("存在且加载"+doubleDataType.toString()+": "+num);
            } else {
                doubleData.singleMap.put(doubleDataType, doubleDataType.getDefaultNum());
//                Bukkit.broadcastMessage("不存在且加载"+doubleDataType.toString()+": "+doubleDataType.getDefaultNum());
            }
        }
        DoubleDataManager.DoubleDataMap.put(player.getName(), doubleData);


        //加载home
        if (config.contains("homes")) {
            ConfigurationSection homes = config.getConfigurationSection("homes");
            HomeData homeData = new HomeData();

            for (String homeName : homes.getKeys(false)) {
                String[] locStr = config.getString("homes." + homeName).split(";");
                Location loc = new Location(
                        Bukkit.getWorld(locStr[0]),
                        Double.valueOf(locStr[1]),
                        Double.valueOf(locStr[2]),
                        Double.valueOf(locStr[3]),
                        Float.valueOf(locStr[4]),
                        Float.valueOf(locStr[5])
                );
                homeData.addHome(homeName, loc);
            }
            HomeManager.playerHomeMap.put(player.getName(), homeData);
        } else {
            HomeData homeData = new HomeData();
            HomeManager.playerHomeMap.put(player.getName(), homeData);
        }

    }

    public void savePlayerData(Player player) {
        File dataFolder = new File("plugins/PWPlayer/Players");
        File dataFile = new File("plugins/PWPlayer/Players/" + player.getName() + ".yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }

        //保存cooldown
        config.set("coolDown", null);
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().forEach(
                    (type, time) -> {
                        config.set("coolDown." + type.toString(), time);
                    }
            );
        }
        //保存intdata
        Bukkit.broadcastMessage("doublenum: " + IntDataManager.IntDataMap.get(player.getName()).singleMap.size());
        IntDataManager.IntDataMap.get(player.getName()).singleMap.forEach(
                (key, value) -> {
                    config.set("intData." + key.toString(), value);
                }
        );
        //保存doubledata
        Bukkit.broadcastMessage("doublenum: " + DoubleDataManager.DoubleDataMap.get(player.getName()).singleMap.size());
        DoubleDataManager.DoubleDataMap.get(player.getName()).singleMap.forEach(
                (key, value) -> {
                    Bukkit.broadcastMessage("key: " + key.toString() + " value: " + value);
                    config.set("doubleData." + key.toString(), value);
                }
        );


        //保存home
        config.set("homes", null);
        HomeManager.playerHomeMap.get(player.getName()).getOnePlayerHomeMap().forEach(
                (name, loc) -> {
                    String locStr = loc.getWorld().getName() + ";" +
                            loc.getX() + ";" +
                            loc.getY() + ";" +
                            loc.getZ() + ";" +
                            loc.getYaw() + ";" +
                            loc.getPitch();
                    config.set("homes." + name, locStr);
                });


        try {
            config.save(dataFile);
        } catch (IOException ex) {
            System.out.println("玩家" + player.getName() + "的等级信息保存出错");
        }
    }

    public void saveOffLinePlayerData(String playerName, IntDataType intDataType, int value) {
        File dataFolder = new File("plugins/PWPlayer/Players");
        File dataFile = new File("plugins/PWPlayer/Players/" + playerName + ".yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }
        config.set("intData." + intDataType.toString(), value);
        try {
            config.save(dataFile);
        } catch (IOException ex) {
            System.out.println("玩家" + playerName + "的intData信息保存出错");
        }
    }

    public void saveOffLinePlayerData(String playerName, DoubleDataType doubleDataType, double value) {
        File dataFolder = new File("plugins/PWPlayer/Players");
        File dataFile = new File("plugins/PWPlayer/Players/" + playerName + ".yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }
        config.set("doubleData." + doubleDataType.toString(), value);
        try {
            config.save(dataFile);
        } catch (IOException ex) {
            System.out.println("玩家" + playerName + "的doubleData信息保存出错");
        }
    }


    public void loadWarps() {
        ConfigurationSection warps = PWPlayer.getPlugin().getConfig().getConfigurationSection("warps");
        for (String warp : warps.getKeys(false)) {
            String[] locStr = PWPlayer.getPlugin().getConfig().getString("warps." + warp).split(";");
            Location loc = new Location(
                    Bukkit.getWorld(locStr[0]),
                    Double.valueOf(locStr[1]),
                    Double.valueOf(locStr[2]),
                    Double.valueOf(locStr[3]),
                    Float.valueOf(locStr[4]),
                    Float.valueOf(locStr[5])
            );
            Teleport.warpsMap.put(warp, loc);
        }
    }
}
