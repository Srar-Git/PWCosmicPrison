package cn.pixelwar.pwblockmanager.BlockStuff;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResttingBlockDataManager {

    private static Map<Location, ResetingBlockData> resettingBlockData = new ConcurrentHashMap<>();

    public static Map<Location, ResetingBlockData> getResettingBlockData() {
        return resettingBlockData;
    }

    public void updateResetting(Block block, String originMaterial, String resettingMaterial, double resetTime) {
        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(Material.matchMaterial(resettingMaterial));
            }
        }.runTask(PWBlockManager.getPlugin());

        ResetingBlockData resetingBlockData = new ResetingBlockData(block, Material.matchMaterial(originMaterial), Material.matchMaterial(resettingMaterial), resetTime);
        resettingBlockData.put(block.getLocation(), resetingBlockData);
    }

    public void clearResettings() {
        resettingBlockData.forEach((key, value) -> {
            resettingBlockData.get(key).getBlock().setType(resettingBlockData.get(key).getOriginMaterial());
            resettingBlockData.remove(key);
        });
    }

    public void blockResetTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {

                resettingBlockData.forEach((key, value) -> {
                    double nowTime = value.getLeftTimeTobeReset() - 1;
                    resettingBlockData.get(key).setLeftTimeTobeReset(nowTime);
                    if (nowTime <= 0) {
                        //主线程修改方块
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                resettingBlockData.get(key).getBlock().setType(resettingBlockData.get(key).getOriginMaterial());
                                resettingBlockData.remove(key);
                            }
                        }.runTask(PWBlockManager.getPlugin());


                    }


                });
            }
        }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 20L);
    }


}
