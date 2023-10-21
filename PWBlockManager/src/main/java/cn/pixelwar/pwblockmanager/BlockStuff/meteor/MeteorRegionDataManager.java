package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MeteorRegionDataManager {

    private static Map<String, MeteorRegionData> meteorRegionDataMap = new ConcurrentHashMap<>();

    public static Map<String, MeteorRegionData> getMeteorRegionDataMap() {
        return meteorRegionDataMap;
    }

    public void createRegionData(String region, World world, int minx, int maxx, int minz, int maxz) {
        MeteorRegionData meteorRegionData = new MeteorRegionData(minx, maxx, minz, maxz, world);
        meteorRegionDataMap.put(region, meteorRegionData);
    }

    public String getRandomRegion(World world) {
        if (world.equals(Bukkit.getWorld("world"))) {
            Random random = new Random();
            int r = random.nextInt(6);
            switch (r) {
                case 1:
                    return "coal";
                case 2:
                    return "iron";
                case 3:
                    return "lapis";
                case 4:
                    return "redstone";
                case 5:
                    return "gold";
                case 6:
                    return "diamond";
                case 0:
                    return "emerald";
                default:
                    return "iron";
            }
        } else {
            return "newcoal";
        }


    }
}
