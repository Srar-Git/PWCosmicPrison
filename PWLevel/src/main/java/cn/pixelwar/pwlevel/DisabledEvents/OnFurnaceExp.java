package cn.pixelwar.pwlevel.DisabledEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceExtractEvent;

public class OnFurnaceExp implements Listener {


    @EventHandler
    public void onBreak(FurnaceExtractEvent event) {
        event.setExpToDrop(0);
    }
}
