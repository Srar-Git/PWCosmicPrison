package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PowerballHitBlockListener implements Listener {

    @EventHandler
    public void onPowerballHitBlockEvent(PowerballHitBlockEvent event) {
//        Bukkit.getServer().broadcastMessage("shooter: " + event.getShooter() + " Coordinate: " + event.getX() + " " + event.getY() + " " + event.getZ());
//        Bukkit.getServer().broadcastMessage("煤矿: " + event.getCoaloreCount());
//        Bukkit.getServer().broadcastMessage("铁矿: " + event.getIronoreCount());
//        Bukkit.getServer().broadcastMessage("青金石矿: " + event.getLapisoreCount());
//        Bukkit.getServer().broadcastMessage("红石矿: " + event.getRedstoneoreCount());
//        Bukkit.getServer().broadcastMessage("金矿: " + event.getGoldoreCount());
//        Bukkit.getServer().broadcastMessage("钻石矿: " + event.getDiamondoreCount());
//        Bukkit.getServer().broadcastMessage("绿宝石矿: " + event.getEmeraldoreCount());
        //OreRegenerate(1, (byte)0, 16, (byte)0, 200, Math.round(event.getX()), Math.round(event.getY()), Math.round(event.getZ()), ((CraftWorld) event.getShooter().getWorld()).getHandle());
    }

}
