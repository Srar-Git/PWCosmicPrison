package cn.pixelwar.pwlevel.DisabledEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnEntityDeath implements Listener {


    @EventHandler
    public void onBreak(EntityDeathEvent event) {
        event.setDroppedExp(0);
    }
}
