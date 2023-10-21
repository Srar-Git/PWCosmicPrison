package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import cn.pixelwar.pwlevel.PWLevel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager.getPlayerExpBoosterDataMap;

public class MeteorFile {

    private final FileConfiguration config = new YamlConfiguration();
    MeteorDataManager meteorDataManager = new MeteorDataManager();
    MeteorRegionDataManager meteorRegionDataManager = new MeteorRegionDataManager();

    public void CreateRegions() {
        ArrayList<World> worlds = new ArrayList<World>();
        ArrayList<String> regions = new ArrayList<String>();

        ConfigurationSection sectionWorlds = PWBlockManager.config.getConfigurationSection("worlds");
        for (String worldString : sectionWorlds.getKeys(false)) {

            World world = Bukkit.getWorld(worldString);
            worlds.add(world);
            ConfigurationSection sectionRegions = PWBlockManager.config.getConfigurationSection("worlds." + worldString);
            for (String region : sectionRegions.getKeys(false)) {
                regions.add(region);
                int minx = PWBlockManager.config.getInt("worlds." + worldString + "." + region + ".minx");
                int maxx = PWBlockManager.config.getInt("worlds." + worldString + "." + region + ".maxx");
                int minz = PWBlockManager.config.getInt("worlds." + worldString + "." + region + ".minz");
                int maxz = PWBlockManager.config.getInt("worlds." + worldString + "." + region + ".maxz");
                MeteorRegionData meteorRegionData = new MeteorRegionData(minx, maxx, minz, maxz, world);
                MeteorRegionDataManager.getMeteorRegionDataMap().put(region.toString(), meteorRegionData);
            }
        }
    }

    public void CheckMeteorFile() {
        File dataFolder = new File("plugins/PWBlockManager/Meteor");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File meteorDataFile = new File("plugins/PWBlockManager/Meteor/meteors.yml");
        if (!meteorDataFile.exists()) {
            try {
                meteorDataFile.createNewFile();
                config.save(meteorDataFile);
            } catch (IOException ex) {
            }
        }


    }

    public void CreateAllDataMap() {
        File dataFile = new File("plugins/PWBlockManager/Meteor/meteors.yml");

        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }
        int totalOfNew = 0;
        int totalOfWorld = 0;
        for (String locationStr : config.getKeys(false)) {
            String[] split = locationStr.split(",");
//            System.out.println(split[0]+" "+split[1]);
            if (split.length != 4) {
                continue;
            }
            Location loc = new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
            if (locationStr.contains("world")) {
                totalOfWorld++;
            }
            if (locationStr.contains("new")) {
                totalOfNew++;
            }
            int amount = config.getInt(locationStr + ".amount");
            int lastTime = config.getInt(locationStr + ".lastTime");
            meteorDataManager.setTotalOfNew(totalOfNew);
            meteorDataManager.setTotalOfWorld(totalOfWorld);
            meteorDataManager.createMeteorData(loc, amount, lastTime);
            if (!(Bukkit.getWorld(split[0]).getBlockAt(loc).getType().equals(Material.NETHER_QUARTZ_ORE))) {
                Bukkit.getWorld(split[0]).getBlockAt(loc).setType(Material.NETHER_QUARTZ_ORE);
            }
        }


    }

    public void saveMeteorData(Location location) {

        new BukkitRunnable() {
            @Override
            public void run() {
                String locStr = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
                File dataFile = new File("plugins/PWBlockManager/Meteor/meteors.yml");
                try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
                    config.load(Config);
                } catch (IOException | InvalidConfigurationException ex) {
                }
                config.set(locStr + ".amount", meteorDataManager.getAmount(location));
                config.set(locStr + ".lastTime", meteorDataManager.getLastTime(location));
                try {
                    config.save(dataFile);
                } catch (IOException e) {
                }
            }
        }.runTaskAsynchronously(PWBlockManager.getPlugin());
    }

    public void saveAllMeteor() {
        File dataFile = new File("plugins/PWBlockManager/Meteor/meteors.yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }
        MeteorDataManager.getMeteorDataMap().forEach((key, value) -> {
            String locStr = key.getWorld().getName() + "," + key.getBlockX() + "," + key.getBlockY() + "," + key.getBlockZ();
            config.set(locStr + ".amount", meteorDataManager.getAmount(key));
            config.set(locStr + ".lastTime", meteorDataManager.getLastTime(key));
        });
        try {
            config.save(dataFile);
        } catch (IOException e) {
        }
    }

    public void removeMeteorData(Location location) {

        new BukkitRunnable() {
            @Override
            public void run() {
                String locStr = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
                File dataFile = new File("plugins/PWBlockManager/Meteor/meteors.yml");
                try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
                    config.load(Config);
                } catch (IOException | InvalidConfigurationException ex) {
                }
                config.set(locStr, null);
                try {
                    config.save(dataFile);
                } catch (IOException e) {
                }
            }
        }.runTaskAsynchronously(PWBlockManager.getPlugin());


    }


}
