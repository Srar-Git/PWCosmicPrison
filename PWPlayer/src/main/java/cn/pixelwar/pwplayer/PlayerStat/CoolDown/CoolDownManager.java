package cn.pixelwar.pwplayer.PlayerStat.CoolDown;

import cn.pixelwar.pwplayer.PWPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CoolDownManager {

    public static ConcurrentHashMap<String, CoolDown> playerCoolDownMap = new ConcurrentHashMap<>();

    public static void addPlayerCoolDown(Player player, CoolDownType coolDownType, int time) {
        if (!(playerCoolDownMap.containsKey(player.getName()))) {
            CoolDown coolDown = new CoolDown();
            coolDown.getOneCoolDownMap().put(coolDownType, time);
            playerCoolDownMap.put(player.getName(), coolDown);
        } else {
            CoolDown coolDown = playerCoolDownMap.get(player.getName());
            coolDown.getOneCoolDownMap().put(coolDownType, time);
            playerCoolDownMap.put(player.getName(), coolDown);
        }
//        playerCoolDownMap.get(player.getName()).updateCoolDown(coolDownType, time);
    }

    public void CoolDowntimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                playerCoolDownMap.forEach((playerName, oneCoolDownMap) -> {
                            oneCoolDownMap.removeOneSeconds();
                        }
                );
            }
        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 20l);
    }


}
