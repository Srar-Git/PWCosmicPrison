package cn.pixelwar.pwplayer.PlayerStat.PVP;

import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth.PlayerHealthManager;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.ConcurrentHashMap;

public class PVPTagManager {

    public static ConcurrentHashMap<String, Integer> PVPTagMap = new ConcurrentHashMap<>();

    public void sendActionbar(Player player, String msg) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
    }

    public void addTag(Player player, int time) {
        if (!(PVPTagManager.PVPTagMap.containsKey(player.getName()))) {
            player.sendMessage(ChatColorCast.format("&c▸ &f你已进入战斗状态，持续时间&d&l" + time + "秒"));
            player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 2f);
        }
        PVPTagManager.PVPTagMap.put(player.getName(), time);
    }

    public void addTag(String playerName, int time) {
        PVPTagManager.PVPTagMap.put(playerName, time);
    }

    public void PVPTagTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                PVPTagMap.forEach((playerName, time) -> {
                            if (time <= 0) {
                                PVPTagMap.remove(playerName);
                                Player player = Bukkit.getPlayer(playerName);
                                //如果玩家在线
                                if (null != player) {
                                    player.sendMessage(ChatColorCast.format("&c▸ &f你已退出战斗状态!"));
                                    player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 2f);
                                }
                                //如果玩家不在线
                                else {
                                    if (PlayerNPCManager.playerNPCMap.containsKey(playerName)) {
                                        new BukkitRunnable() {
                                            @Override
                                            public void run() {
                                                NPC npc = CitizensAPI.getNPCRegistry().getById(PlayerNPCManager.playerNPCMap.get(playerName).getNPCID());
                                                //把追杀该NPC的守卫重置
                                                PlayerNPCManager.removePlayerNPC(npc);
                                                PlayerNPCManager.playerNPCMap.remove(playerName);
                                            }
                                        }.runTask(PWPlayer.getPlugin());
                                    }
                                }
                            } else {
                                PVPTagMap.put(playerName, time - 1);
                                Player player = Bukkit.getPlayer(playerName);
                                //如果玩家在线
                                if (null != player) {
//                                    String blocks = "";
//                                    for (int i = 1; i<=15;i++){
//                                        if (i<=(15-PVPTagMap.get(playerName))) {
//                                            blocks = blocks.concat("&b▍");
//                                        }else{blocks = blocks.concat("&f▍");}
//                                    }
//                                    sendActionbar(player, ChatColorCast.format("&f战斗状态时间 &d▸ "+blocks+" &7(&e"+PVPTagMap.get(playerName)+"s&7)"));
                                    sendActionbar(player, ChatColorCast.format("&f战斗状态时间 &d▸ &e&l" + PVPTagMap.get(playerName) + "s"));
                                }
                            }
                        }
                );
            }
        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 20l);
    }
}
