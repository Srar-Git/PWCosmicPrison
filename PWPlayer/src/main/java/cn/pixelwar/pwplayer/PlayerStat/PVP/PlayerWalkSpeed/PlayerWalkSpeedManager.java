package cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerWalkSpeed;

import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerWalkSpeedManager {

    public static ConcurrentHashMap<Player, List<PlayerWalkSpeedBuff>> WalkSpeedBuffMap = new ConcurrentHashMap<>();

    public double getMaxWalkSpeed(Player player) {
        double maxWalkSpeed = 0;
        double basic = DoubleDataManager.getDoubleData(player, DoubleDataType.BASICWALKSPEED);
        //基础移速
        maxWalkSpeed += basic;

        //buff移速
        if (WalkSpeedBuffMap.containsKey(player)) {
            List<PlayerWalkSpeedBuff> playerWalkSpeedBuffs = WalkSpeedBuffMap.get(player);
            for (PlayerWalkSpeedBuff playerWalkSpeedBuff : playerWalkSpeedBuffs) {
                double buffWalkSpeed = playerWalkSpeedBuff.getBuffWalkSpeed();
                maxWalkSpeed += buffWalkSpeed;
            }
        }

//        //装备移速
//        double equip = 0;
//        ItemStack b = player.getInventory().getBoots();
//        NBTItem nbtItem = new NBTItem(b);
//        if (nbtItem.hasKey("ae_enchantment;xuexi")){
//            int level = nbtItem.getInteger("ae_enchantment;xuexi");
//            equip = (double)level*0.2;
//        }
//
//
//        maxWalkSpeed+=equip;
        return maxWalkSpeed;
    }

    public void updateMaxWalkSpeed(Player player) {
        double maxWalkSpeed = getMaxWalkSpeed(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setWalkSpeed((float) maxWalkSpeed);
            }
        }.runTask(PWPlayer.getPlugin());

    }


    public void giveBuff(Player player, double WalkSpeed, int time) {
        PlayerWalkSpeedBuff playerWalkSpeedBuff = new PlayerWalkSpeedBuff((float) WalkSpeed, time);
        if (WalkSpeedBuffMap.containsKey(player)) {
            List<PlayerWalkSpeedBuff> playerWalkSpeedBuffs = WalkSpeedBuffMap.get(player);
            playerWalkSpeedBuffs.add(playerWalkSpeedBuff);
            WalkSpeedBuffMap.put(player, playerWalkSpeedBuffs);
        } else {
            List<PlayerWalkSpeedBuff> playerWalkSpeedBuffs = new ArrayList<>();
            playerWalkSpeedBuffs.add(playerWalkSpeedBuff);
            WalkSpeedBuffMap.put(player, playerWalkSpeedBuffs);
        }

        updateMaxWalkSpeed(player);
    }

    public void WalkSpeedBuffTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                WalkSpeedBuffMap.forEach((player, playerWalkSpeedBuffs) -> {
                    for (int i = 0; i < playerWalkSpeedBuffs.size(); i++) {
                        PlayerWalkSpeedBuff playerWalkSpeedBuff = playerWalkSpeedBuffs.get(i);
                        int time = playerWalkSpeedBuff.getBuffTime();
                        playerWalkSpeedBuffs.remove(playerWalkSpeedBuff);
                        if (time <= 0) {
                            updateMaxWalkSpeed(player);
                        } else {
                            playerWalkSpeedBuff.setBuffTime(time - 1);
                            playerWalkSpeedBuffs.add(playerWalkSpeedBuff);
                        }
                    }
                });

            }
        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 20l);

    }


}
