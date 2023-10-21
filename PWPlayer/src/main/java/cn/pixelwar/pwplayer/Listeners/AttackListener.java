package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.Warn;
import cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.ArmorEnchant;
import cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.SwordEnchant;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPRegion;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPTagManager;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class AttackListener implements Listener {


    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        Entity victim = e.getEntity();
        Entity attacker = e.getDamager();
        PVPTagManager pvpTagManager = new PVPTagManager();


        //判定攻击者手中的武器
        if (attacker instanceof Player) {
            Player playerAttacker = (Player) attacker;
            Material material = playerAttacker.getItemInHand().getType();
            if (!(
                    material.toString().contains("SWORD") ||
                            material.equals(Material.WOODEN_AXE) ||
                            material.equals(Material.STONE_AXE) ||
                            material.equals(Material.GOLDEN_AXE) ||
                            material.equals(Material.IRON_AXE) ||
                            material.equals(Material.DIAMOND_AXE) ||
                            material.equals(Material.NETHERITE_AXE) ||
                            material.equals(Material.BOW)
            )) {
                e.setCancelled(true);
                Warn warn = new Warn();
                warn.plingWithMessage(playerAttacker, ChatColorCast.format("&c▸ 你需要使用弓、剑、斧子才能进行攻击!"), 3);
                return;
            }
        }

        //射箭
        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            if (arrow.getShooter() instanceof Player) {
                PVPRegion pvpRegion = PVPRegion.checkPlayerPVPRegion((Player) arrow.getShooter());
                pvpTagManager.addTag((Player) arrow.getShooter(), pvpRegion.getPvpTagTime());
            }
            if (victim instanceof Player) {
                PVPRegion pvpRegion = PVPRegion.checkPlayerPVPRegion((Player) victim);
                pvpTagManager.addTag((Player) victim, pvpRegion.getPvpTagTime());
            }
            return;
        }

        //双方都是玩家
        if (victim instanceof Player && attacker instanceof Player) {
            Player playerVictim = (Player) victim;
            Player playerAttacker = (Player) attacker;
            PVPRegion pvpRegion = PVPRegion.checkPlayerPVPRegion(playerVictim);
            //如果攻击者是npc
            if ((attacker.hasMetadata("NPC"))) {
                //如果受害者也是npc,并且是守卫
                if (GuardManager.checkIfEntityIsGuard(victim)) {
                    GuardManager.resetGuard(attacker);
                    e.setCancelled(true);
                    return;
                }
                //如果受害者是玩家
                else {
                    if (!(GuardManager.guardHuntingPlayerMap.containsKey(playerVictim.getName()))) {
                        GuardManager.resetGuard(attacker);
                        e.setCancelled(true);
                        return;
                    }
                }


            }

            //如果攻击者是玩家
            //如果被攻击的是NPC
            if ((victim.hasMetadata("NPC"))) {
                //如果被攻击的是玩家NPC
                if (PlayerNPCManager.playerNPCMap.containsKey(playerVictim.getName())) {
                    pvpTagManager.addTag(playerVictim.getName(), pvpRegion.getPvpTagTime());
                    NPC npc = CitizensAPI.getNPCRegistry().getNPC(victim);
                    int id = npc.getId();
                    int health = (int) (playerVictim.getHealth());
                    Bukkit.broadcastMessage("health: " + health);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            npc.getOrAddTrait(HologramTrait.class).setLine(0, ChatColorCast.format(health + "&c&l❤"));
                            //如果npc已经不存在了，已经死了
                            try {
                                NPC n = CitizensAPI.getNPCRegistry().getById(id);
                            } catch (IllegalArgumentException e) {
                                GuardManager.resetGuard(attacker);
                            }
                        }
                    }.runTaskLater(PWPlayer.getPlugin(), 1l);


                    return;
                }


                //如果被攻击的是守卫
                if (GuardManager.checkIfEntityIsGuard(victim)) {
                    NPC npc = CitizensAPI.getNPCRegistry().getNPC(victim);
                    if (
                            (!(GuardManager.guardHuntingPlayerMap.values().contains(npc))) ||
                                    (!(GuardManager.guardHuntingPlayerMap.containsKey(playerAttacker.getName())))
                    ) {
                        e.setCancelled(true);
                        Warn warn = new Warn();
                        warn.plingWithMessage(playerAttacker, ChatColorCast.format("&c▸ 守卫不屑与弱者切磋!"), 3);
                        return;
                    }

                    int health = (int) (playerVictim.getHealth());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            npc.getOrAddTrait(HologramTrait.class).setLine(0, ChatColorCast.format(health + "&c&l❤"));
                        }
                    }.runTaskLater(PWPlayer.getPlugin(), 1l);
                }

            }
            //攻击的是玩家
            //check周围的entity
            for (Entity en : playerVictim.getNearbyEntities(20, 20, 20)) {
                if (en instanceof Player) {
                    //如果站在旁边的是银河守卫
                    if (GuardManager.checkIfEntityIsGuard(en)) {
                        //如果打人的不是守卫
                        if (!(GuardManager.checkIfEntityIsGuard(attacker))) {
                            GuardManager.makeGuardAttack(playerAttacker, en);
                        }

                    }
                }
            }


            pvpTagManager.addTag(playerAttacker, pvpRegion.getPvpTagTime());
            pvpTagManager.addTag(playerVictim, pvpRegion.getPvpTagTime());
//            Bukkit.broadcastMessage("移速: "+playerAttacker.getWalkSpeed());
//            Bukkit.broadcastMessage("e.getDamage(): "+e.getDamage());
//            Bukkit.broadcastMessage("e.getDamage(ABSORPTION): "+e.getDamage(EntityDamageEvent.DamageModifier.ABSORPTION));
//            Bukkit.broadcastMessage("e.getDamage(ARMOR): "+e.getDamage(EntityDamageEvent.DamageModifier.ARMOR));
//            Bukkit.broadcastMessage("e.getDamage(BASE): "+e.getDamage(EntityDamageEvent.DamageModifier.BASE));
//            Bukkit.broadcastMessage("e.getDamage(BLOCKING): "+e.getDamage(EntityDamageEvent.DamageModifier.BLOCKING));
//            Bukkit.broadcastMessage("e.getDamage(HARD_HAT): "+e.getDamage(EntityDamageEvent.DamageModifier.HARD_HAT));
//            Bukkit.broadcastMessage("e.getDamage(MAGIC): "+e.getDamage(EntityDamageEvent.DamageModifier.MAGIC));
//            Bukkit.broadcastMessage("e.getDamage(RESISTANCE): "+e.getDamage(EntityDamageEvent.DamageModifier.RESISTANCE));
//
//            Bukkit.broadcastMessage("e.getFinalDamage(): "+e.getFinalDamage());
//            e.setDamage(5.0);
//            Bukkit.broadcastMessage("e.getDamage(): "+e.getDamage());
//            Bukkit.broadcastMessage("e.getFinalDamage(): "+e.getFinalDamage());
//            Bukkit.broadcastMessage("Attribute.GENERIC_ARMOR: "+playerVictim.getAttribute(Attribute.GENERIC_ARMOR).getValue());
//            Bukkit.broadcastMessage("Attribute.GENERIC_ARMOR_TOUGHNESS: "+playerVictim.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue());
//            Bukkit.broadcastMessage("Attribute.GENERIC_ATTACK_DAMAGE: "+playerAttacker.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());

            ItemStack weapon = playerAttacker.getItemInHand();
            if (weapon.getType().toString().contains("SWORD")) {
                SwordEnchant.doSwordEnchantPVP(playerAttacker, playerVictim, weapon);
            }
            double damage = e.getDamage();
            damage = ArmorEnchant.doVictimArmorEnchantPVP(playerAttacker, playerVictim, damage, weapon);
            e.setDamage(damage);


        }


    }


}
