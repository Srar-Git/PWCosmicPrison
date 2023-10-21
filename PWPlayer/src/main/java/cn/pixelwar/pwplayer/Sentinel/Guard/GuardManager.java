package cn.pixelwar.pwplayer.Sentinel.Guard;

import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleData;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPRegion;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Spawned;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuardManager {

    /*
     * 玩家被守卫追杀的列表，每个玩家只能被一个守卫追杀
     * 每一个守卫也只能追杀一个玩家
     *
     * 只有NPC在开始攻击人的时候才加上sentinel，但是在创建的时候就放入guardmap
     *
     * 移除玩家被追杀的事件:
     * 1. 玩家被杀死
     * 移除内容:
     * 1. npc的sentinel trait
     * 2. guardHuntingPlayerMap中的数据
     * 2. 将NPC重置并满血归位
     */
    public static HashMap<String, NPC> guardHuntingPlayerMap = new HashMap<>();
    public static HashMap<NPC, GuardInfo> guardMap = new HashMap<>();
    public static List<Location> guardLocations;

    public static void resetGuard(Entity guardEntity, String player) {
        NPC guard = CitizensAPI.getNPCRegistry().getNPC(guardEntity);
        Location fixedLoc = guard.getOrAddTrait(GuardTrait.class).getFixedLoc();
//        guard.getOrAddTrait(SentinelTrait.class).removeTarget("uuid:" + player.getUniqueId());
        guard.getNavigator().cancelNavigation();
        guard.removeTrait(SentinelTrait.class);
        if (guardHuntingPlayerMap.containsKey(player)) {
            guardHuntingPlayerMap.remove(player);
        }
        Bukkit.broadcastMessage("地址: " + fixedLoc.toString());
        guard.teleport(fixedLoc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        GuardType guardType = GuardType.getWardenWithName(guard.getName());
        int health = (int) guardType.getHealth();
        guard.getOrAddTrait(HologramTrait.class).setLine(0, ChatColorCast.format(health + "&c&l❤"));
    }

    public static void resetGuard(Entity guardEntity) {
        NPC guard = CitizensAPI.getNPCRegistry().getNPC(guardEntity);
        if (guard == null) {
            return;
        }
        Location fixedLoc = guard.getOrAddTrait(GuardTrait.class).getFixedLoc();
//        guard.getOrAddTrait(SentinelTrait.class).removeTarget("uuid:" + player.getUniqueId());
        guard.getNavigator().cancelNavigation();
        guard.removeTrait(SentinelTrait.class);
        for (String player : guardHuntingPlayerMap.keySet()) {
            if (guardHuntingPlayerMap.get(player).equals(guard)) {
                guardHuntingPlayerMap.remove(player);
            }
            Bukkit.getLogger().info(("[PWPlayer] 重置守卫 - 地址: " + fixedLoc.toString()));
            guard.teleport(fixedLoc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
        GuardType guardType = GuardType.getWardenWithName(guard.getName());
        int health = (int) guardType.getHealth();
        guard.getOrAddTrait(HologramTrait.class).setLine(0, ChatColorCast.format(health + "&c&l❤"));
    }

    public static void resetGuard(NPC guard, String player) {
        Location fixedLoc = guard.getOrAddTrait(GuardTrait.class).getFixedLoc();
//        guard.getOrAddTrait(SentinelTrait.class).removeTarget("uuid:" + player.getUniqueId());
        guard.getNavigator().cancelNavigation();
        guard.removeTrait(SentinelTrait.class);
        if (guardHuntingPlayerMap.containsKey(player)) {
            guardHuntingPlayerMap.remove(player);
        }
//        Bukkit.broadcastMessage("地址: "+fixedLoc.toString());
        guard.teleport(fixedLoc, PlayerTeleportEvent.TeleportCause.PLUGIN);
        GuardType guardType = GuardType.getWardenWithName(guard.getName());
        int health = (int) guardType.getHealth();
        guard.getOrAddTrait(HologramTrait.class).setLine(0, ChatColorCast.format(health + "&c&l❤"));
    }


    public static void makeGuardAttack(Player player, Entity NPCEntity) {
        NPC npc = CitizensAPI.getNPCRegistry().getNPC(NPCEntity);
        //如果玩家已经在被追杀了
        if (guardHuntingPlayerMap.containsKey(player.getName())) {

            if (npc.getId() != guardHuntingPlayerMap.get(player.getName()).getId()) {
                return;
            }
        }
        //如果守卫已经在追杀人了

        for (String p : guardHuntingPlayerMap.keySet()) {
            if (guardHuntingPlayerMap.get(p).equals(npc) && !(p.equals(player.getName()))) {
                return;
            }
        }

        GuardType guardType = GuardType.getWardenWithName(((Player) NPCEntity).getName());
        guardHuntingPlayerMap.put(player.getName(), npc);
        SentinelTrait sentinelTrait = npc.getOrAddTrait(SentinelTrait.class);
        sentinelTrait.addTarget("uuid:" + player.getUniqueId());

        sentinelTrait.attackRate = guardType.getAttackRate();
        sentinelTrait.health = guardType.getHealth();
        sentinelTrait.speed = guardType.getSpeed();
//        ((Player) NPCEntity).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 6,false,false) );


        npc.addTrait(sentinelTrait);

    }

    //    public static void makeGuardAttack(Entity playerNPCEntity, Entity NPCEntity){
//        //如果守卫已经在追杀人了
//        NPC npc = CitizensAPI.getNPCRegistry().getNPC(NPCEntity);
//        for (String p : guardHuntingPlayerMap.keySet()){
//            if (guardHuntingPlayerMap.get(p).equals(npc)){
//                return;
//            }
//        }
//        GuardType guardType = GuardType.getWardenWithName(((Player)NPCEntity).getName());
//        SentinelTrait sentinelTrait = npc.getOrAddTrait(SentinelTrait.class);
//        sentinelTrait.addTarget("uuid:"+playerNPCEntity.getUniqueId());
//        sentinelTrait.attackRate= guardType.getAttackRate();
//        sentinelTrait.health= guardType.getHealth();
//        sentinelTrait.speed= guardType.getSpeed();
//        npc.addTrait(sentinelTrait);
//
//    }
    public static void resetAllGuards() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (NPC npc : CitizensAPI.getNPCRegistry()) {
                    if (npc.getOrAddTrait(GuardTrait.class) != null) {
                        resetGuard(npc.getEntity());
                    }
                }
            }
        }.runTaskLater(PWPlayer.getPlugin(), 2l);
    }

    public static void getAllGuardLocation() {
        List<Location> locations = new ArrayList<>();
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            if (npc.getOrAddTrait(GuardTrait.class) != null) {
                locations.add(npc.getOrAddTrait(GuardTrait.class).getFixedLoc());
            }
        }
        guardLocations = locations;
    }


    public static boolean checkIfEntityIsGuard(Entity entity) {
        if (
                entity.hasMetadata("NPC") &&
                        (((Player) entity).getName().contains("守卫") ||
                                ((Player) entity).getName().contains("银河守卫") ||
                                ((Player) entity).getName().contains("裁决者") ||
                                ((Player) entity).getName().contains("执法者"))
        ) {
            return true;
        }
        return false;
    }

    public void checkGuardPlayerDistanceTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (String playerName : guardHuntingPlayerMap.keySet()) {
                    Player player = Bukkit.getPlayer(playerName);
                    if (player == null) {
                        guardHuntingPlayerMap.remove(playerName);
                        continue;
                    }
                    Entity npcEn = guardHuntingPlayerMap.get(playerName).getEntity();
                    double distance = npcEn.getLocation().distance(player.getLocation());
                    if (distance >= 80) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                resetGuard(guardHuntingPlayerMap.get(playerName), playerName);
                                player.sendMessage(ChatColorCast.format("&a▸ 你逃脱了&c守卫&a的追杀!"));
                            }
                        }.runTask(PWPlayer.getPlugin());
                    }

                }
            }
        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 100l);
    }

    public static String getNearNPCName(Player player, int distance) {
        List<Entity> entityList = player.getNearbyEntities(distance, distance, distance);
        int guard1 = 0;
        int guard2 = 0;
        int guard3 = 0;
        int guard4 = 0;
        for (Entity entity : entityList) {
            if (!(entity.hasMetadata("NPC"))) {
                continue;
            }
            if (!(entity.getType() == EntityType.PLAYER)) {
                continue;
            }
            if (((Player) entity).getName().contains("银河守卫")) {
                guard1++;
            }
            if (((Player) entity).getName().contains("裁决者")) {
                guard2++;
            }
            if (((Player) entity).getName().contains("执法者")) {
                guard3++;
            }
            if (
                    ((Player) entity).getName().contains("守卫") &&
                            (!((Player) entity).getName().contains("银河守卫"))
            ) {
                guard4++;
            }


        }
        if (guard1 > 0) {
            return "银河守卫";
        }
        if (guard2 > 0) {
            return "裁决者";
        }
        if (guard3 > 0) {
            return "执法者";
        }
        if (guard4 > 0) {
            return "守卫";
        }
        return "无";
    }


}
