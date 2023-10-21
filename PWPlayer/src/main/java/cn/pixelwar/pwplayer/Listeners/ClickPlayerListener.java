package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwplayer.Listeners.ArmorEquipEvent.ArmorEquipEvent;
import cn.pixelwar.pwplayer.PlayerStatCheck.CheckStat;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ClickPlayerListener implements Listener {

    @EventHandler
    public void equipOn(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().hasMetadata("NPC")) {
            return;
        }
        if (e.getRightClicked().getType().equals(EntityType.PLAYER)) {
            if (e.getPlayer().isSneaking()) {
                CheckStat checkStat = new CheckStat();
                checkStat.checkPlayer(e.getPlayer(), (Player) e.getRightClicked());
            }
        }
    }

}
