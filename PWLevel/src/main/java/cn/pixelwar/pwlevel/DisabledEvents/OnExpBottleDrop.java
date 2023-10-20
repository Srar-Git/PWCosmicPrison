package cn.pixelwar.pwlevel.DisabledEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class OnExpBottleDrop implements Listener {


    @EventHandler
    public void onBreak(ExpBottleEvent event) {
        event.setExperience(0);
        event.setShowEffect(false);
    }
}
