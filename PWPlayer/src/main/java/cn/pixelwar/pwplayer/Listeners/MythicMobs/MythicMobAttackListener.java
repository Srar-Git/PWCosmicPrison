package cn.pixelwar.pwplayer.Listeners.MythicMobs;

import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPRegion;
import cn.pixelwar.pwplayer.Utils.GetWGRegion;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicDamageEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class MythicMobAttackListener implements Listener {


    @EventHandler
    public void MythicMobAttackEvent(EntityDamageByEntityEvent e) {
        Entity attacker = e.getDamager();
        Entity victim = e.getEntity();
        ActiveMob mythicMob = MythicBukkit.inst().getMobManager().getActiveMob(attacker.getUniqueId()).orElse(null);
        if (mythicMob == null) {
//            Bukkit.broadcastMessage("getName()"+mythicMob.getName());
//            Bukkit.broadcastMessage("getDisplayName()"+mythicMob.getDisplayName());
//            Bukkit.broadcastMessage("getDisplayName()"+ GetWGRegion.getWGRegion(attacker).toString());
//            Bukkit.broadcastMessage("getDamage(): " + e.getDamage());
            return;
        }
        if (mythicMob.getName().contains("强盗")) {
            ApplicableRegionSet regionSet = GetWGRegion.getWGRegion(victim);
            if (
                    GetWGRegion.checkIfInRegion(regionSet, "spawn") ||
                            GetWGRegion.checkIfInRegion(regionSet, "coalmine")
            ) {
                Bukkit.getServer().getEntity(mythicMob.getEntity().getUniqueId()).remove();
            }
        }
    }


}
