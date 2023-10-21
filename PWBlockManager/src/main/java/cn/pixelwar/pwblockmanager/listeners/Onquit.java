package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Onquit implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {
        PlayerData.getPlayerCritData().remove(event.getPlayer());
    }


}
