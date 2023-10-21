package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwplayer.File.YamlStorage;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleData;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPTagManager;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCManager;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCInfo;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;

public class OnQuit implements Listener {

    @EventHandler
    public void quit(PlayerQuitEvent e) {
        YamlStorage yamlStorage = new YamlStorage();
        Player p = e.getPlayer();

        //战斗模式退出的后果
        if (PVPTagManager.PVPTagMap.containsKey(p.getName())) {
            PlayerNPCInfo playerNPCInfo = new PlayerNPCInfo();
            playerNPCInfo.setPlayerNPCInfo(p);
            PlayerNPCManager playerNPCManager = new PlayerNPCManager();
            NPC quiterNPC = playerNPCManager.createPlayerNPC(playerNPCInfo);
            //如果是被守卫追杀
            if (GuardManager.guardHuntingPlayerMap.containsKey(p.getName())) {
                NPC guard = GuardManager.guardHuntingPlayerMap.get(p.getName());
                guard.removeTrait(SentinelTrait.class);
                GuardManager guardManager = new GuardManager();
                guardManager.makeGuardAttack((Player) quiterNPC.getEntity(), guard.getEntity());
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                //记录玩家血量
                DoubleData doubleData = DoubleDataManager.DoubleDataMap.get(p.getName());
                doubleData.setNum(DoubleDataType.LASTONLINEHEALTH, p.getHealth());
                DoubleDataManager.DoubleDataMap.put(p.getName(), doubleData);


                yamlStorage.savePlayerData(p);
                CoolDownManager.playerCoolDownMap.remove(p.getName());
                HomeManager.playerHomeMap.remove(p.getName());
                IntDataManager.IntDataMap.remove(p.getName());
                DoubleDataManager.DoubleDataMap.remove(p.getName());
            }
        }.runTaskAsynchronously(PWPlayer.getPlugin());
    }

}
