package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwplayer.PlayerStat.CoolDown.Warn;
import cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.ArmorEnchant;
import cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.SwordEnchant;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPTagManager;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.mcmonkey.sentinel.SentinelTrait;

public class DeathListener implements Listener {


    @EventHandler
    public void EntityDeathEvent(EntityDeathEvent e) {
        Entity victim = e.getEntity();

        //死者是玩家
        if (victim instanceof Player) {
            Player playerVictim = (Player) victim;
            //让玩家不再被追杀
            if (GuardManager.guardHuntingPlayerMap.containsKey(playerVictim.getName())) {
                NPC npc = GuardManager.guardHuntingPlayerMap.get(playerVictim.getName());
                GuardManager.resetGuard(npc, playerVictim.getName());
                GuardManager.guardHuntingPlayerMap.remove(playerVictim.getName());
            }


        }


    }


}
