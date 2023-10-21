package cn.pixelwar.pwlevel.DisabledEvents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setExpToDrop(0);
    }

}
