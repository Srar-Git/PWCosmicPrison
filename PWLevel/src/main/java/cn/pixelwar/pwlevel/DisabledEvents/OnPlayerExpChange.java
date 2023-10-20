package cn.pixelwar.pwlevel.DisabledEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class OnPlayerExpChange implements Listener {

    @EventHandler
    public void onBreak(PlayerExpChangeEvent event) {
        event.setAmount(0);
    }

}

