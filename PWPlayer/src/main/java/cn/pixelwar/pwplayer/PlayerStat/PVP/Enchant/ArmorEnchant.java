package cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketText;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.Shatter;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerWalkSpeed.PlayerWalkSpeedManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class ArmorEnchant {
    Random random = new Random();

    public static List<NBTItem> getArmorsNBTItem(Player player) {
        List<NBTItem> armors = new ArrayList<>();
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();
        if (helmet != null) armors.add(new NBTItem(helmet));
        if (chestplate != null) armors.add(new NBTItem(chestplate));
        if (leggings != null) armors.add(new NBTItem(leggings));
        if (boots != null) armors.add(new NBTItem(boots));
        return armors;
    }

    //受到打架的伤害
    public static double doVictimArmorEnchantPVP(Player attacker, Player victim, double damage, ItemStack weapon) {

        List<NBTItem> victimArmorsNBTItem = getArmorsNBTItem(victim);


        //触发的附魔
        Map<PWEnchant, Integer> armorEnchants = new HashMap<>();
        for (NBTItem nbtItem : victimArmorsNBTItem) {
            for (String s : nbtItem.getKeys()) {
                if (s.contains("ae_enchantment;")) {
                    PWEnchant enchant = PWEnchant.valueOf(s.split(";")[1]);
                    int level = nbtItem.getInteger(s);
                    if (armorEnchants.containsKey(enchant)) {
                        armorEnchants.put(enchant, armorEnchants.get(enchant) + level);
                    } else {
                        armorEnchants.put(enchant, level);
                    }
                }
            }
        }
//        for (PWEnchant pwEnchant: armorEnchants.keySet()){
//            Bukkit.broadcastMessage("附魔: "+pwEnchant.toString()+" 等级: "+armorEnchants.get(pwEnchant));
//        }
        damage = fireVictimArmorEnchantPVP(armorEnchants, attacker, victim, damage, weapon);
        return damage;
    }

    //受到其他伤害
    public static double doVictimArmorEnchantDamage(Player victim, double damage, EntityDamageEvent.DamageCause cause) {

        List<NBTItem> victimArmorsNBTItem = getArmorsNBTItem(victim);


        //触发的附魔


        Map<PWEnchant, Integer> armorEnchants = new HashMap<>();
        for (NBTItem nbtItem : victimArmorsNBTItem) {
            for (String s : nbtItem.getKeys()) {
                if (s.contains("ae_enchantment;")) {
                    PWEnchant enchant = PWEnchant.valueOf(s.split(";")[1]);
                    int level = nbtItem.getInteger(s);
                    if (armorEnchants.containsKey(enchant)) {
                        armorEnchants.put(enchant, armorEnchants.get(enchant) + level);
                    } else {
                        armorEnchants.put(enchant, level);
                    }
                }
            }
        }
        damage = fireVictimArmorEnchantDamage(armorEnchants, victim, damage, cause);
        return damage;
    }

    //耐久相关附魔
    public static int doVictimArmorDurDamage(Player victim, ItemStack item, int itemDamage) {

        List<NBTItem> victimArmorsNBTItem = getArmorsNBTItem(victim);
        NBTItem nbtItem = new NBTItem(item);

        //触发的附魔
        Map<PWEnchant, Integer> armorEnchants = new HashMap<>();
        for (String s : nbtItem.getKeys()) {
            if (s.contains("ae_enchantment;")) {
                PWEnchant enchant = PWEnchant.valueOf(s.split(";")[1]);
                int level = nbtItem.getInteger(s);
                if (armorEnchants.containsKey(enchant)) {
                    armorEnchants.put(enchant, armorEnchants.get(enchant) + level);
                } else {
                    armorEnchants.put(enchant, level);
                }
            }
        }
        itemDamage = fireVictimArmorEnchantDur(armorEnchants, victim, itemDamage);
        return itemDamage;
    }

    public static double fireVictimArmorEnchantPVP(Map<PWEnchant, Integer> armorEnchants, Player attacker, Player victim, double damage, ItemStack weapon) {
        //濒死反抗
        if (armorEnchants.containsKey(PWEnchant.binsifankang)) {
            if (victim.getHealth() - damage <= 0) {
                int random = NumberFormat.getRandomInt(0, 100);
                if (random <= armorEnchants.get(PWEnchant.binsifankang)) {
                    damage = 0;
                    victim.setHealth(4.0);
                    victim.playSound(victim.getEyeLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
                    victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&6濒死反抗&7) &f你被救活了!"));
                    return damage;
                }
            }
        }
        //格挡一次攻击
        if (armorEnchants.containsKey(PWEnchant.yanggong)) {
            int random = NumberFormat.getRandomInt(0, 100);
            if (random <= armorEnchants.get(PWEnchant.yanggong)) {
                damage = 0;
                victim.playSound(victim.getEyeLocation(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1f, 1f);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&f佯攻&7) &f你成功&a闪避&f了一次攻击"));
                return damage;
            }

        }
        //圣光
        if (armorEnchants.containsKey(PWEnchant.shengguang)) {
            int random = NumberFormat.getRandomInt(0, 100);
            if (random <= armorEnchants.get(PWEnchant.shengguang)) {
                damage = 0;
                double health = victim.getHealth() + damage;
                if (health > victim.getMaxHealth()) {
                    health = victim.getMaxHealth();
                }
                victim.setHealth(health);
                victim.playSound(victim.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 0.1f);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&6圣光&7) &f你被圣光照耀"));
                return damage;
            }

        }
        //shift抵挡部分伤害
        if (armorEnchants.containsKey(PWEnchant.fangyu)) {
            if (victim.isSneaking()) {
                int level = armorEnchants.get(PWEnchant.fangyu);
                double percent = (double) (100 - 5 * level) / 100;
                double damage2 = (double) damage * percent;
                victim.playSound(victim.getEyeLocation(), Sound.BLOCK_BARREL_CLOSE, 1f, 1f);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&a防御&7) &f你成功&a抵挡&f了&c&l" + (damage - damage2) + "❤&f伤害"));
                return damage2;
            }

        }
        //点燃攻击者
        if (armorEnchants.containsKey(PWEnchant.huojia)) {
            int random = NumberFormat.getRandomInt(0, 100);
            int level = armorEnchants.get(PWEnchant.huojia);
            if (random <= level) {
                attacker.setFireTicks(100);
                victim.playSound(victim.getEyeLocation(), Sound.ENTITY_GHAST_SHOOT, 1f, 1f);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&a火甲&7) &f你点燃了&c&l" + attacker.getName()));
                attacker.sendMessage(ChatColorCast.format("&c▸ &f你被&c&l" + victim.getName() + "&f点燃了"));
            }
        }
        //震荡波
        if (armorEnchants.containsKey(PWEnchant.zhendangbo)) {
            int random = NumberFormat.getRandomInt(0, 100);
            int level = armorEnchants.get(PWEnchant.zhendangbo);
            if (random <= level) {
                Vector attackerVector = attacker.getLocation().toVector();
                Vector victimVector = victim.getLocation().toVector();

                Vector vector = attackerVector.subtract(victimVector);
                vector.normalize();
                vector.multiply(2);

                attacker.setVelocity(vector);
                victim.playSound(victim.getEyeLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 1f, 1f);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&9震荡波&7) &f你震退了&c&l" + attacker.getName()));
            }
        }
        //酸血
        if (armorEnchants.containsKey(PWEnchant.suanxue)) {
            if (victim.getHealth() <= (victim.getMaxHealth() / 2)) {
                int random = NumberFormat.getRandomInt(0, 100);
                int level = armorEnchants.get(PWEnchant.suanxue);
                if (random <= level) {
                    attacker.addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 5 * 20, level - 1, false, true));
                    victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&9酸血&7) &f你的敌人中毒了"));
                }
            }
        }
        //斗志
        if (armorEnchants.containsKey(PWEnchant.douzhi)) {
            if (victim.getHealth() <= (victim.getMaxHealth() / 6) + damage) {
                int random = NumberFormat.getRandomInt(0, 100);
                if (random <= 50) {
                    int level = armorEnchants.get(PWEnchant.douzhi);
                    victim.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10 * 20, level - 1, false, true));
                    victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&e斗志&7) &f你获得了&b&l抗性提升效果"));
                }
            }
        }
        //巫术
        if (armorEnchants.containsKey(PWEnchant.wushu)) {
            int random = NumberFormat.getRandomInt(0, 100);
            int level = armorEnchants.get(PWEnchant.wushu);
            if (random <= level) {
                attacker.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 6 * 20, level - 1, false, true));
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&6巫术&7) &c&l" + attacker.getName() + "&f中了你的&2&l巫术"));
                attacker.sendMessage(ChatColorCast.format("&d▸ &f对手触发附魔&7(&6巫术&7) &c&l" + victim.getName() + "&f使你中了&2&l巫术"));
            }
        }
        //铠甲
        if (weapon.getType().toString().contains("SWORD")) {
            if (armorEnchants.containsKey(PWEnchant.kaijia)) {
                int level = armorEnchants.get(PWEnchant.kaijia);
                damage = damage * (double) (1.00 - level * 0.05);
            }
        }
        //装甲
        if (weapon.getType().toString().contains("AXE")) {
            if (armorEnchants.containsKey(PWEnchant.zhuangjia)) {
                int level = armorEnchants.get(PWEnchant.zhuangjia);
                damage = damage * (double) (1.00 - level * 0.05);
            }
        }


        //盾 倒数第四
        if (armorEnchants.containsKey(PWEnchant.dun)) {
            int level = armorEnchants.get(PWEnchant.dun);
            int random = NumberFormat.getRandomInt(0, 100);
            if (random <= level) {
                damage = damage / 2;
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&e盾&7) &f你受到的伤害被抵挡了一般"));
            }
        }
        //斗气 倒数第三
        if (armorEnchants.containsKey(PWEnchant.douqi)) {
            if (victim.getHealth() <= (victim.getMaxHealth() / 8) + damage) {
                int random = NumberFormat.getRandomInt(0, 100);
                if (random <= 50) {
                    int level = armorEnchants.get(PWEnchant.douqi);
                    victim.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5 * 20, level - 1, false, true));
                    victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&e斗气&7) &f你获得了&a&l生命恢复效果"));
                }
            }
        }
        //血息 倒数第二个
        if (armorEnchants.containsKey(PWEnchant.xuexi)) {

//            Bukkit.broadcastMessage("------------------"+victim.getHealth()+"/"+ (victim.getMaxHealth()/10));
            if (victim.getHealth() <= (victim.getMaxHealth() / 10) + damage) {
                int level = armorEnchants.get(PWEnchant.xuexi);
                PlayerWalkSpeedManager playerWalkSpeedManager = new PlayerWalkSpeedManager();
                float speed = victim.getWalkSpeed();
                playerWalkSpeedManager.giveBuff(victim, 0.5, level * 5);
                playerWalkSpeedManager.updateMaxWalkSpeed(victim);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&9血息&7) &f你的移速提升了&c&l" + 2.5 + "倍&7 (持续&a&l" + (level * 5) + "S&7)"));
            }
        }
        //仙人刺 这个附魔放最后
        if (armorEnchants.containsKey(PWEnchant.xianrenci)) {
            int random = NumberFormat.getRandomInt(0, 100);
            int level = armorEnchants.get(PWEnchant.xianrenci);
            if (random <= level * 3) {
                attacker.damage(damage);
                victim.playSound(victim.getEyeLocation(), Sound.ITEM_TRIDENT_HIT, 1f, 1f);
                attacker.playSound(attacker.getEyeLocation(), Sound.ITEM_TRIDENT_HIT, 1f, 1f);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&9仙人刺&7) &f你将伤害反弹给了&c&l" + attacker.getName()));
                attacker.sendMessage(ChatColorCast.format("&d▸ &c&l" + victim.getName() + "&f将伤害反弹给了你"));
            }
        }
        return damage;
    }

    public static double fireVictimArmorEnchantDamage(Map<PWEnchant, Integer> armorEnchants, Player victim, double damage, EntityDamageEvent.DamageCause cause) {
        //被火烧
        if (cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK) || cause.equals(EntityDamageEvent.DamageCause.FIRE)) {
            //灭火头盔
            if (armorEnchants.containsKey(PWEnchant.miehuotoukui)) {
                int random = NumberFormat.getRandomInt(0, 100);
                if (random <= armorEnchants.get(PWEnchant.yanggong)) {
                    victim.setFireTicks(0);
                    victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&9灭火头盔&7) &f你身上的火焰已被熄灭"));
                }
            }
            //魔法免疫
            if (armorEnchants.containsKey(PWEnchant.mofamianyi)) {
                int random = NumberFormat.getRandomInt(0, 100);
                int level = armorEnchants.get(PWEnchant.mofamianyi);
                if (random <= level) {
                    damage = damage * (double) (level * 0.1);
                }
            }
        }
        if (cause.equals(EntityDamageEvent.DamageCause.POISON) || cause.equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
            //魔法免疫
            if (armorEnchants.containsKey(PWEnchant.mofamianyi)) {
                int random = NumberFormat.getRandomInt(0, 100);
                int level = armorEnchants.get(PWEnchant.mofamianyi);
                if (random <= level) {
                    damage = damage * (double) (level * 0.1);
                }
            }
        }


        //泰坦之血，任何伤害
        if (armorEnchants.containsKey(PWEnchant.taitanzhixue)) {
            int level = armorEnchants.get(PWEnchant.taitanzhixue);
            damage = damage * (double) (1.00 - level * 0.02);
        }


        return damage;
    }

    public static int fireVictimArmorEnchantDur(Map<PWEnchant, Integer> armorEnchants, Player victim, int itemDamage) {
        //血魔法
        if (armorEnchants.containsKey(PWEnchant.xuemofa)) {
            int random = NumberFormat.getRandomInt(0, 100);
            int level = armorEnchants.get(PWEnchant.xuemofa);
            if (random <= level) {
                itemDamage = itemDamage - level;
                if (itemDamage < 0) {
                    itemDamage = 0;
                }
                double health = victim.getHealth() + level;
                if (health >= victim.getMaxHealth()) {
                    health = victim.getMaxHealth();
                }
                victim.setHealth(health);
                victim.sendMessage(ChatColorCast.format("&d▸ &f触发附魔&7(&e血魔法&7) &f你此次受到的攻击使你回复&c&l" + level + "❤&f和抵消&e&l" + level + "点&f耐久减少"));
            }
        }
        if (armorEnchants.containsKey(PWEnchant.qishijiazhou)) {
            int random = NumberFormat.getRandomInt(0, 100);
            int level = armorEnchants.get(PWEnchant.qishijiazhou);
            if (random <= level) {
                itemDamage = (int) itemDamage * (1 / (4 - level));
            }
        }

        return itemDamage;
    }


    public static String getLevel(int level) {
        switch (level) {
            case 0:
                return "";
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            default:
                return null;
        }
    }

    public static List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }
}
