package cn.pixelwar.pwblockmanager.BlockStuff.Jinghua;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketJinghua;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class JinghuaFile {

    private final FileConfiguration config = new YamlConfiguration();

    PacketJinghua packetJinghua = new PacketJinghua();

    public void CheckJinghuaFile() {
        File dataFolder = new File("plugins/PWBlockManager/Jinghua");
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File meteorDataFile = new File("plugins/PWBlockManager/Jinghua/jinghuas.yml");
        if (!meteorDataFile.exists()) {
            try {
                meteorDataFile.createNewFile();
                config.save(meteorDataFile);
            } catch (IOException ex) {
            }
        }
    }

    public void CreateAllDataMap() {
        JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
        File dataFile = new File("plugins/PWBlockManager/Jinghua/jinghuas.yml");

        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }

        int coalAmount = 0, ironAmount = 0, lapisAmount = 0, redstoneAmount = 0, goldAmount = 0, diamondAmount = 0, emeraldAmount = 0;
        String type = "";
        for (String locationStr : config.getKeys(false)) {
            String[] split = locationStr.split(",");
            Location loc = new Location(Bukkit.getWorld(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]), Double.parseDouble(split[3]));
            type = config.getString(locationStr + ".jinghuaType");
            if (type.contains("COAL")) {
                coalAmount++;
            }
            if (type.contains("IRON")) {
                ironAmount++;
            }
            if (type.contains("LAPIS")) {
                lapisAmount++;
            }
            if (type.contains("REDSTONE")) {
                redstoneAmount++;
            }
            if (type.contains("GOLD")) {
                goldAmount++;
            }
            if (type.contains("DIAMOND")) {
                diamondAmount++;
            }
            if (type.contains("EMERALD")) {
                emeraldAmount++;
            }


            int amount = config.getInt(locationStr + ".amount");
            int totalAmount = config.getInt(locationStr + ".totalAmount");
            int armorStandID = config.getInt(locationStr + ".armorStandID");
            Material originalType = Material.matchMaterial(config.getString(locationStr + ".originalType"));
            Material JinghuaType = Material.matchMaterial(config.getString(locationStr + ".jinghuaType"));
            Material item = Material.matchMaterial(config.getString(locationStr + ".item"));

            //创建datamap
            jinghuaDataManager.createJinghuaData(loc, amount, totalAmount, armorStandID, originalType, JinghuaType, item);

            if (!(Bukkit.getWorld(split[0]).getBlockAt(loc).getType().equals(JinghuaType))) {
                Bukkit.getWorld(split[0]).getBlockAt(loc).setType(JinghuaType);
            }

            //创建hologram
            packetJinghua.createText(armorStandID, loc.clone().add(0.5, 1.7, 0.5), new ItemStack(item));
            new BukkitRunnable() {
                int i = 1;

                @Override
                public void run() {
                    if (!(JinghuaDataManager.getJinghuaDataMap().containsKey(loc))) {
                        packetJinghua.delete(armorStandID);
                        cancel();
                    }
                    packetJinghua.update(loc.clone().add(0.5, 1.7, 0.5), armorStandID, JinghuaDataManager.getJinghuaDataMap().get(loc).getAmount(), i * 10, i * 10);
                    i++;

                }
            }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 2l);
        }
        jinghuaDataManager.getJinghuaAmountDataMap().put("coal", coalAmount);
        jinghuaDataManager.getJinghuaAmountDataMap().put("iron", ironAmount);
        jinghuaDataManager.getJinghuaAmountDataMap().put("lapis", lapisAmount);
        jinghuaDataManager.getJinghuaAmountDataMap().put("redstone", redstoneAmount);
        jinghuaDataManager.getJinghuaAmountDataMap().put("gold", goldAmount);
        jinghuaDataManager.getJinghuaAmountDataMap().put("diamond", diamondAmount);
        jinghuaDataManager.getJinghuaAmountDataMap().put("emerald", emeraldAmount);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载煤矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("coal"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载铁矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("iron"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载青金石矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("lapis"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载红石矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("redstone"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载金矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("gold"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载钻石矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("diamond"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "加载绿宝石矿个数：" + jinghuaDataManager.getJinghuaAmountDataMap().get("emerald"));

    }

    public void saveJinghuaData(Location location) {
        JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
        new BukkitRunnable() {
            @Override
            public void run() {
                String locStr = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
                File dataFile = new File("plugins/PWBlockManager/Jinghua/jinghuas.yml");
                try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
                    config.load(Config);
                } catch (IOException | InvalidConfigurationException ex) {
                }
                config.set(locStr + ".amount", jinghuaDataManager.getAmount(location));
                config.set(locStr + ".totalAmount", jinghuaDataManager.getTotalAmount(location));
                config.set(locStr + ".armorStandID", jinghuaDataManager.getID(location));
                config.set(locStr + ".originalType", jinghuaDataManager.getOriginalType(location).toString());
                config.set(locStr + ".jinghuaType", jinghuaDataManager.getJinghuaType(location).toString());
                config.set(locStr + ".item", jinghuaDataManager.getItem(location).toString());
                try {
                    config.save(dataFile);
                } catch (IOException e) {
                }
            }
        }.runTaskAsynchronously(PWBlockManager.getPlugin());
    }

    public void saveAllJinghua() {
        JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
        File dataFile = new File("plugins/PWBlockManager/Jinghua/jinghuas.yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(dataFile), "UTF-8")) {
            config.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }
        JinghuaDataManager.getJinghuaDataMap().forEach((key, value) -> {
            String locStr = key.getWorld().getName() + "," + key.getBlockX() + "," + key.getBlockY() + "," + key.getBlockZ();
            config.set(locStr + ".amount", jinghuaDataManager.getAmount(key));
            config.set(locStr + ".totalAmount", jinghuaDataManager.getTotalAmount(key));
            config.set(locStr + ".armorStandID", jinghuaDataManager.getID(key));
            config.set(locStr + ".originalType", jinghuaDataManager.getOriginalType(key).toString());
            config.set(locStr + ".jinghuaType", jinghuaDataManager.getJinghuaType(key).toString());
            config.set(locStr + ".item", jinghuaDataManager.getItem(key).toString());
        });
        try {
            config.save(dataFile);
        } catch (IOException e) {
        }

    }

    public void removeJinghuaData(Location location) {

        new BukkitRunnable() {
            @Override
            public void run() {
                String locStr = location.getWorld().getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ();
                File dataFile = new File("plugins/PWBlockManager/Jinghua/jinghuas.yml");
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
