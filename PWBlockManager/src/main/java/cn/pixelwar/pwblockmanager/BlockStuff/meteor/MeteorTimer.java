package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

import static cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager.meteorDataMap;

public class MeteorTimer extends Meteor {

    public void meteorTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {

                meteorDataMap.forEach((key, value) -> {
                    int nowTime = value.getLastTime() - 1;
                    meteorDataMap.get(key).setLastTime(nowTime);

                    if (nowTime <= 0) {
                        removeMeteor(key);
                    }
                });
            }
        }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 20L);
    }

}
