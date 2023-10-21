package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwplayer.Listeners.ArmorEquipEvent.ArmorEquipEvent;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth.PlayerHealthBuff;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth.PlayerHealthManager;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class EquipListener implements Listener {

    @EventHandler
    public void equipOn(ArmorEquipEvent e) {

        Player p = e.getPlayer();
        ItemStack newItem = e.getNewArmorPiece();
        ItemStack oldItem = e.getOldArmorPiece();
        PlayerHealthManager playerHealthManager = new PlayerHealthManager();
        //是脱下
        if (newItem == null) {
            //脱下头盔
            if (oldItem.getType().toString().contains("_HELMET")) {
                unequipHelmetCheck(p);
            }
            //脱下靴子
            if (oldItem.getType().toString().contains("_BOOTS")) {
                unequipBootsCheck(p);
            }
        } else if (newItem.getType().equals(Material.AIR)) {
            //脱下头盔
            if (oldItem.getType().toString().contains("_HELMET")) {
                unequipHelmetCheck(p);
            }
            //脱下靴子
            if (oldItem.getType().toString().contains("_BOOTS")) {
                unequipBootsCheck(p);
            }
        }

        //穿上
        if (oldItem == null) {

            //穿上头盔
            if (newItem.getType().toString().contains("_HELMET")) {
                equipHelmetCheck(p, newItem);
            }
            //穿上靴子
            if (newItem.getType().toString().contains("_BOOTS")) {
                equipBootsCheck(p, newItem);
            }
        } else if (oldItem.getType().equals(Material.AIR)) {

            //穿上头盔
            if (newItem.getType().toString().contains("_HELMET")) {
                equipHelmetCheck(p, newItem);
            }
            //穿上靴子
            if (newItem.getType().toString().contains("_BOOTS")) {
                equipBootsCheck(p, newItem);
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                playerHealthManager.updateMaxHealth(p);
            }
        }.runTaskLater(PWPlayer.getPlugin(), 1l);


    }

    public void equipHelmetCheck(Player player, ItemStack helmet) {

        NBTItem nbtItem = new NBTItem(helmet);
        //包含的附魔
        List<PWEnchant> helmetEnchants = new ArrayList<>();
        for (String s : nbtItem.getKeys()) {
            if (s.contains("ae_enchantment;")) {
                PWEnchant enchant = PWEnchant.valueOf(s.split(";")[1]);
                helmetEnchants.add(enchant);
            }
        }
        //增加药水效果
        //夜视
        if (helmetEnchants.contains(PWEnchant.nightvision)) {
            player.addPotionEffect(new PotionEffect(
                    PotionEffectType.NIGHT_VISION,
                    999999,
                    0,
                    false,
                    false,
                    true
            ));
        }
    }

    public void unequipHelmetCheck(Player player) {
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    public void equipBootsCheck(Player player, ItemStack boots) {

        NBTItem nbtItem = new NBTItem(boots);
        //包含的附魔
        List<PWEnchant> bootsEnchants = new ArrayList<>();
        for (String s : nbtItem.getKeys()) {
            if (s.contains("ae_enchantment;")) {
                PWEnchant enchant = PWEnchant.valueOf(s.split(";")[1]);
                bootsEnchants.add(enchant);
            }
        }
        //增加药水效果
        //跳跃提升
        if (bootsEnchants.contains(PWEnchant.jump)) {
            player.addPotionEffect(new PotionEffect(
                    PotionEffectType.JUMP,
                    999999,
                    nbtItem.getInteger("ae_enchantment;jump") - 1,
                    false,
                    false,
                    true
            ));
        }

    }

    public void unequipBootsCheck(Player player) {
        player.removePotionEffect(PotionEffectType.JUMP);
    }


}
