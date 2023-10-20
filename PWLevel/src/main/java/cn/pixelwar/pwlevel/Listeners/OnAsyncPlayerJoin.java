package cn.pixelwar.pwlevel.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class OnAsyncPlayerJoin implements Listener {


    @EventHandler
    public void OnPlayerJoin(AsyncPlayerPreLoginEvent event) {
        String playerName = event.getName();


    }
}
