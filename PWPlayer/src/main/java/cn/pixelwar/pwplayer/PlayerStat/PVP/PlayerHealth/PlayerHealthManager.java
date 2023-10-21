package cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleData;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant.ArmorEnchant.getArmorsNBTItem;

public class PlayerHealthManager {

    //    public static HashMap<Player, Double> basicHealthMap = new HashMap<>();
    public static ConcurrentHashMap<Player, List<PlayerHealthBuff>> healthBuffMap = new ConcurrentHashMap<>();

    public double getMaxHealth(Player player) {
        double maxHealth = 0;
        double basic = DoubleDataManager.getDoubleData(player, DoubleDataType.BASICHEALTH);
        //基础生命值
        maxHealth += basic;

        //buff生命值
        if (healthBuffMap.containsKey(player)) {
            List<PlayerHealthBuff> playerHealthBuffs = healthBuffMap.get(player);
            for (PlayerHealthBuff playerHealthBuff : playerHealthBuffs) {
                double buffHealth = playerHealthBuff.getBuffHealth();
                maxHealth += buffHealth;
            }
        }

        //装备生命值
        double equip = 0;
        List<NBTItem> victimArmorsNBTItem = getArmorsNBTItem(player);
        //过载
        Map<PWEnchant, Integer> armorEnchants = new HashMap<>();
        for (NBTItem nbtItem : victimArmorsNBTItem) {
            if (nbtItem.hasKey("ae_enchantment;guozai")) {
                equip += nbtItem.getInteger("ae_enchantment;guozai") * 2;
                continue;
            }
            if (nbtItem.hasKey("ae_enchantment;nguozai")) {
                equip += nbtItem.getInteger("ae_enchantment;nguozai") * 4;
                continue;
            }
        }


        maxHealth += equip;
        return maxHealth;
    }

    public void updateMaxHealth(Player player) {
        double maxHealth = getMaxHealth(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setMaxHealth(maxHealth);
            }
        }.runTask(PWPlayer.getPlugin());

    }


    public void giveBuff(Player player, double health, int time) {
        PlayerHealthBuff playerHealthBuff = new PlayerHealthBuff(health, time);
        if (healthBuffMap.containsKey(player)) {
            List<PlayerHealthBuff> playerHealthBuffs = healthBuffMap.get(player);
            playerHealthBuffs.add(playerHealthBuff);
            healthBuffMap.put(player, playerHealthBuffs);
        } else {
            List<PlayerHealthBuff> playerHealthBuffs = new ArrayList<>();
            playerHealthBuffs.add(playerHealthBuff);
            healthBuffMap.put(player, playerHealthBuffs);
        }

        updateMaxHealth(player);
    }

    public void healthBuffTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {

                healthBuffMap.forEach((player, playerHealthBuffs) -> {
//                    List<PlayerHealthBuff> newBuffs
//                    Bukkit.broadcastMessage(player.getName()+" "+playerHealthBuffs)
                    for (int i = 0; i < playerHealthBuffs.size(); i++) {
                        PlayerHealthBuff playerHealthBuff = playerHealthBuffs.get(i);
                        int time = playerHealthBuff.getBuffTime();
                        playerHealthBuffs.remove(playerHealthBuff);
                        if (time <= 0) {
                            updateMaxHealth(player);
                        } else {
                            playerHealthBuff.setBuffTime(time - 1);
                            playerHealthBuffs.add(playerHealthBuff);
                        }
                    }
//                    for (PlayerHealthBuff playerHealthBuff : playerHealthBuffs){
//
//
//                    }
                });

            }
        }.runTaskTimerAsynchronously(PWPlayer.getPlugin(), 0l, 20l);

    }


}
