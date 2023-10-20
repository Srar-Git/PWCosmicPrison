package cn.pixelwar.pwlevel.PlayerData.PlayerBooster;

import cn.pixelwar.pwlevel.CustomEvents.BoosterDoneEvent;
import cn.pixelwar.pwlevel.PWLevel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerBoosterDataManager {

    private static Map<String, PlayerExpBoosterData> playerExpBoosterDataMap = new ConcurrentHashMap<>();
    private static Map<String, PlayerEnergyBoosterData> playerEnergyBoosterDataMap = new ConcurrentHashMap<>();
    private static Map<String, PlayerShardBoosterData> playerShardBoosterDataMap = new ConcurrentHashMap<>();

    public static Map<String, PlayerExpBoosterData> getPlayerExpBoosterDataMap() {
        return playerExpBoosterDataMap;
    }
    public static Map<String, PlayerShardBoosterData> getPlayerShardBoosterDataMap() {
        return playerShardBoosterDataMap;
    }

    public static Map<String, PlayerEnergyBoosterData> getPlayerEnergyBoosterDataMap() {
        return playerEnergyBoosterDataMap;
    }

    public int getExpBoosterTime(Player player){
        if (playerExpBoosterDataMap.containsKey(player.getName())) {
            return playerExpBoosterDataMap.get(player.getName()).getExpBoosterTime();
        }
        return 0;
    }
    public int getEnergyBoosterTime(Player player){
        if (playerEnergyBoosterDataMap.containsKey(player.getName())) {
            return playerEnergyBoosterDataMap.get(player.getName()).getEnergyBoosterTime();
        }
        return 0;
    }
    public int getShardBoosterTime(Player player){
        if (playerShardBoosterDataMap.containsKey(player.getName())) {
            return playerShardBoosterDataMap.get(player.getName()).getShardBoosterTime();
        }
        return 0;
    }
    public double getExpMultiple(Player player){
        if (playerExpBoosterDataMap.containsKey(player.getName())) {
            return playerExpBoosterDataMap.get(player.getName()).getExpMultiple();
        }
        return 1.0;
    }
    public double getEnergyMultiple(Player player){
        if (playerEnergyBoosterDataMap.containsKey(player.getName())) {
            return playerEnergyBoosterDataMap.get(player.getName()).getEnergyMultiple();
        }
        return 1.0;
    }
    public double getShardMultiple(Player player){
        if (playerShardBoosterDataMap.containsKey(player.getName())) {
            return playerShardBoosterDataMap.get(player.getName()).getShardMultiple();
        }
        return 1.0;
    }

    public void createExpBoosterData(Player player, int ExpBoosterTime, double ExpMultiple){
        PlayerExpBoosterData playerExpBoosterData = new PlayerExpBoosterData(ExpBoosterTime, ExpMultiple);
        playerExpBoosterDataMap.put(player.getName(), playerExpBoosterData);
    }
    public void createEnergyBoosterData(Player player, int EnergyBoosterTime, double EnergyMultiple){
        PlayerEnergyBoosterData playerEnergyBoosterData = new PlayerEnergyBoosterData(EnergyBoosterTime, EnergyMultiple);
        playerEnergyBoosterDataMap.put(player.getName(), playerEnergyBoosterData);
    }
    public void createShardBoosterData(Player player, int ShardBoosterTime, double ShardMultiple){
        PlayerShardBoosterData playerShardBoosterData = new PlayerShardBoosterData(ShardBoosterTime, ShardMultiple);
        playerShardBoosterDataMap.put(player.getName(), playerShardBoosterData);
    }

    public void removePlayerFromMap(Player player){
        playerExpBoosterDataMap.remove(player.getName());
        playerEnergyBoosterDataMap.remove(player.getName());
        playerShardBoosterDataMap.remove(player.getName());
    }

    public void BoosterTimer(){
        new BukkitRunnable() {
            @Override
            public void run() {

                playerExpBoosterDataMap.forEach((key, value) -> {
                    int lastTimeExp = value.getExpBoosterTime();

                    if (lastTimeExp <= 0){
                        Bukkit.getServer().getPluginManager().callEvent(new BoosterDoneEvent(true, Bukkit.getPlayer(key), "exp", playerExpBoosterDataMap.get(key).getExpMultiple()));
                        playerExpBoosterDataMap.remove(key);
                    }
                    if (lastTimeExp > 0) {
                        playerExpBoosterDataMap.get(key).setExpBoosterTime(lastTimeExp-1);
                    }
                });

                playerEnergyBoosterDataMap.forEach((key, value) -> {
                    int lastTimeEnergy = value.getEnergyBoosterTime();

                    if (lastTimeEnergy <= 0){
                        Bukkit.getServer().getPluginManager().callEvent(new BoosterDoneEvent(true, Bukkit.getPlayer(key), "energy", playerEnergyBoosterDataMap.get(key).getEnergyMultiple()));
                        playerEnergyBoosterDataMap.remove(key);
                    }
                    if (lastTimeEnergy > 0) {
                        playerEnergyBoosterDataMap.get(key).setEnergyBoosterTime(lastTimeEnergy-1);
                    }
                });
                playerShardBoosterDataMap.forEach((key, value) -> {
                    int lastTimeShard = value.getShardBoosterTime();

                    if (lastTimeShard <= 0){
                        Bukkit.getServer().getPluginManager().callEvent(new BoosterDoneEvent(true, Bukkit.getPlayer(key), "shard", playerShardBoosterDataMap.get(key).getShardMultiple()));
                        playerShardBoosterDataMap.remove(key);
                    }
                    if (lastTimeShard > 0) {
                        playerShardBoosterDataMap.get(key).setShardBoosterTime(lastTimeShard-1);
                    }
                });

            }
        }.runTaskTimerAsynchronously(PWLevel.getPlugin(), 0L, 20L);
    }



}
