package cn.pixelwar.pwblockmanager.BlockStuff;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockPacketDataManager {

    private static Map<Location, BlockPackData> blockPacketData = new ConcurrentHashMap<>();
    private static Map<Location, BukkitTask> blockTaskData = new ConcurrentHashMap<>();

    public static Map<Location, BlockPackData> getBlockPacketData() {
        return blockPacketData;
    }


    public void createTask(Block block, String packetMaterial, int resetTime) {
        BukkitTask task = new BlockPacketTask(block, packetMaterial, resetTime).runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 1L);
        blockTaskData.put(block.getLocation(), task);
    }

    public void cancelTask(Block block) {
        BukkitTask task = blockTaskData.get(block.getLocation());
        if (task == null) {
            return;
        }
        task.cancel();
        blockTaskData.remove(block.getLocation());
    }


    public void updatePacketData(Block block, String packetMaterial, int resetTime) {

        BlockPackData blockPackData = new BlockPackData(block, Material.matchMaterial(packetMaterial), resetTime);
        blockPacketData.put(block.getLocation(), blockPackData);
        cancelTask(block);
        createTask(block, packetMaterial, resetTime);
//        BukkitTask task = new BlockPacketTask(block, packetMaterial, resetTime).runTaskTimerAsynchronously(PixelWarBlockManager.getPlugin(), 0L, 1L);


    }

    public void packetTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {

                blockPacketData.forEach((key, value) -> {
                    int nowTime = value.getResetTime() - 1;
                    blockPacketData.get(key).setResetTime(nowTime);
                    if (nowTime < 0) {

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendBlockChange(blockPacketData.get(key).getOriginBlock().getLocation(), blockPacketData.get(key).getOriginBlock().getType(), (byte) 0);
                        }
                        blockPacketData.remove(key);
                        try {
                            cancelTask(blockPacketData.get(key).getOriginBlock());
                        } catch (NullPointerException e) {
                        }
                    } else {
//                        for (Player p : Bukkit.getOnlinePlayers()) {
//                            p.sendBlockChange(blockPacketData.get(key).getOriginBlock().getLocation(), blockPacketData.get(key).getPacketMaterial() ,(byte) 0);
//                        }
                    }
                });
            }
        }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 20L);
    }

}
