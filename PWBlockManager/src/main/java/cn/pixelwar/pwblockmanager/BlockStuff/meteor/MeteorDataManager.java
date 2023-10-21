package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwblockmanager.customevents.JinghuaDownEvent;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MeteorDataManager {
    static Map<Location, MeteorData> meteorDataMap = new ConcurrentHashMap<>();
    private static int totalOfWorld;
    private static int totalOfNew;

    public static int getTotalOfWorld() {
        return totalOfWorld;
    }

    public static void setTotalOfWorld(int totalOfWorld) {
        MeteorDataManager.totalOfWorld = totalOfWorld;
    }

    public static void setTotalOfNew(int totalOfNew) {
        MeteorDataManager.totalOfNew = totalOfNew;
    }

    public static int getTotalOfNew() {
        return totalOfNew;
    }

    public static Map<Location, MeteorData> getMeteorDataMap() {
        return meteorDataMap;
    }

    public void createMeteorData(Location location, int amount, int time) {
        MeteorData meteorData = new MeteorData(amount, time);
        meteorDataMap.put(location, meteorData);
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().setType(location, Material.NETHER_QUARTZ_ORE);
            }
        }.runTask(PWBlockManager.getPlugin());
    }

    public void removeMeteorData(Location location) {
        if (meteorDataMap.containsKey(location)) {
            meteorDataMap.remove(location);

//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    Bukkit.getServer().getPluginManager().callEvent(new MeteorDownEvent(location));
//
//                }
//            }.runTask(PWBlockManager.getPlugin());

        } else {
            return;
        }
    }

    public void subtractAmount(Location location, int amount, Player player) {
        if (meteorDataMap.containsKey(location)) {
            amount = meteorDataMap.get(location).getAmount() - amount;
            if (amount <= 0) {
                Bukkit.getServer().getPluginManager().callEvent(new MeteorDownEvent(location, player));
                return;
            }
            createMeteorData(location, amount, meteorDataMap.get(location).getLastTime());
        }

    }

    public void setAmount(Location location, int amount) {
        if (meteorDataMap.containsKey(location)) {
            meteorDataMap.get(location).setAmount(amount);
        } else {
            return;
        }
    }

    public int getAmount(Location location) {
        if (meteorDataMap.containsKey(location)) {
            return meteorDataMap.get(location).getAmount();
        } else {
            return 0;
        }
    }

    public int getLastTime(Location location) {
        if (meteorDataMap.containsKey(location)) {
            return meteorDataMap.get(location).getLastTime();
        } else {
            return 0;
        }
    }


}
