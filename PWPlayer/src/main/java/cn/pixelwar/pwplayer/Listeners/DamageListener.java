package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.ArmorEnchant.*;

public class DamageListener implements Listener {

    @EventHandler
    public void damage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            Player victim = (Player) entity;
            doVictimArmorEnchantDamage(victim, e.getDamage(), e.getCause());
        }
    }


}
