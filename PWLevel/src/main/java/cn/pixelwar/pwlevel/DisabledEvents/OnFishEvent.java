package cn.pixelwar.pwlevel.DisabledEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class OnFishEvent implements Listener {


    @EventHandler
    public void onBreak(PlayerFishEvent event) {
        event.setExpToDrop(0);
    }
}
