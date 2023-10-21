package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorFile;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import cn.pixelwar.pwpacketstuff.customevents.MeteorLandEvent;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;

public class OnMeteorLand implements Listener {

    MeteorDataManager manager = new MeteorDataManager();
    MeteorFile meteorFile = new MeteorFile();

    @EventHandler
    public void OnMeteorLand(MeteorLandEvent event) {
        Location location = event.getLocation();
        Random random = new Random();
        int amount = random.nextInt(71) + 50;
        int time = 36000;
        if (location.getWorld().toString().contains("new")) {
            time = 7200;
        }
        manager.createMeteorData(location, amount, time);
        meteorFile.saveMeteorData(location);
        if (location.getWorld().equals(Bukkit.getWorld("world"))) {
            MeteorDataManager.setTotalOfWorld(MeteorDataManager.getTotalOfWorld() + 1);
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.YELLOW + "" + ChatColor.BOLD + "流星矿石" + ChatColor.WHITE + "降落在了星球表面 " + ChatColor.GRAY + "x:" + ChatColor.WHITE + location.getBlockX() + ChatColor.GRAY + " y:" + ChatColor.WHITE + location.getBlockY() + ChatColor.GRAY + " z:" + ChatColor.WHITE + location.getBlockZ());
        } else {
            MeteorDataManager.setTotalOfNew(MeteorDataManager.getTotalOfNew() + 1);
        }
    }
}
