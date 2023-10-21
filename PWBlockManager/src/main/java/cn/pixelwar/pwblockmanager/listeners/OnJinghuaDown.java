package cn.pixelwar.pwblockmanager.listeners;


import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.Jinghua;
import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.JinghuaDataManager;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager;
import cn.pixelwar.pwblockmanager.customevents.JinghuaDownEvent;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnJinghuaDown implements Listener {
    Jinghua jinghua = new Jinghua();

    @EventHandler
    public void OnJinghuaDown(JinghuaDownEvent event) {
        Location location = event.getLocation();
        String jinghuaType = event.getJinghua().toString();
        Player player = event.getPlayer();
        location.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.clone().add(0.5, 0.5, 0.5), 55, 0.3, 0.3, 0.3, 0);
        location.getWorld().playSound(location, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0F, 0.1F);

        jinghua.removeJinghua(location);

    }
}