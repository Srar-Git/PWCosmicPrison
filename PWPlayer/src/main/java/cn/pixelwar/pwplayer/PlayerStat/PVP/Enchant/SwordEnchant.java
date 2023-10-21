package cn.pixelwar.pwplayer.PlayerStat.PVP.Enchant;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketText;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.Shatter;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SwordEnchant {
    Random random = new Random();

    public static void doSwordEnchantPVP(Player attacker, Player victim, ItemStack weapon) {

        NBTItem nbtItem = new NBTItem(weapon);
        //触发的附魔


        List<String> weaponEnchants = new ArrayList<>();
        for (String s : nbtItem.getKeys()) {
            if (s.contains("ae_enchantment;")) {
                PWEnchant enchant = PWEnchant.valueOf(s.split(";")[1]);
                PWEnchant fired = fireEnchant(enchant, attacker, victim, weapon);
                if (fired != null) {
                    weaponEnchants.add(fired.getRankColor() + fired.getChineseName());
                }
            }
        }


    }

    public static PWEnchant fireEnchant(PWEnchant enchant, Player attacker, Player victim, ItemStack weapon) {
        NBTItem nbtItem = new NBTItem(weapon);
        PacketText packetText = new PacketText();

        if (enchant.equals(PWEnchant.daze)) {
            if (!(nbtItem.hasKey("ae_enchantment;daze"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;daze");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, level * 3 * 20, level, false, true));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.daze.getRankColor() + PWEnchant.daze.getChineseName() + " &b&l" + getLevel(level)));
            victim.sendMessage(ChatColorCast.format("&c▸ &f你中了&c&l" + attacker.getName() + "&f的附魔: " + PWEnchant.daze.getRankColor() + PWEnchant.daze.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.daze;

        }
        if (enchant.equals(PWEnchant.shirenzu)) {
            if (!(nbtItem.hasKey("ae_enchantment;shirenzu"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;shirenzu");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            double nowHealth = attacker.getHealth() + (double) level;
            if (nowHealth > attacker.getMaxHealth()) {
                nowHealth = attacker.getMaxHealth();
            }
            attacker.setHealth(nowHealth);
            attacker.setSaturation(attacker.getSaturation() + level);
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.shirenzu.getRankColor() + PWEnchant.shirenzu.getChineseName() + " &b&l" + getLevel(level)));
            //victim.sendMessage(ChatColorCast.format("&c▸ &f你中了&c&l"+attacker.getName()+"&f的附魔: "+PWEnchant.daze.getRankColor()+PWEnchant.daze.getChineseName()+" &b&l"+getLevel(level)));
            return PWEnchant.shirenzu;
        }
        if (enchant.equals(PWEnchant.poison)) {
            if (!(nbtItem.hasKey("ae_enchantment;poison"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;poison");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 3 * 20, level, false, true));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.poison.getRankColor() + PWEnchant.poison.getChineseName() + " &b&l" + getLevel(level)));
            victim.sendMessage(ChatColorCast.format("&c▸ &f你中了&c&l" + attacker.getName() + "&f的附魔: " + PWEnchant.poison.getRankColor() + PWEnchant.poison.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.poison;
        }
        if (enchant.equals(PWEnchant.dianxing)) {
            if (!(nbtItem.hasKey("ae_enchantment;dianxing"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;dianxing");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, level * 3 * 20, level, false, true));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.dianxing.getRankColor() + PWEnchant.dianxing.getChineseName() + " &b&l" + getLevel(level)));
            victim.sendMessage(ChatColorCast.format("&c▸ &f你中了&c&l" + attacker.getName() + "&f的附魔: " + PWEnchant.dianxing.getRankColor() + PWEnchant.dianxing.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.dianxing;
        }
        if (enchant.equals(PWEnchant.scorch)) {
            if (!(nbtItem.hasKey("ae_enchantment;scorch"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;scorch");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            victim.setFireTicks(40 * level);
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.scorch.getRankColor() + PWEnchant.scorch.getChineseName() + " &b&l" + getLevel(level)));
            victim.sendMessage(ChatColorCast.format("&c▸ &f你中了&c&l" + attacker.getName() + "&f的附魔: " + PWEnchant.scorch.getRankColor() + PWEnchant.scorch.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.scorch;
        }
        if (enchant.equals(PWEnchant.molecular)) {
            if (!(nbtItem.hasKey("ae_enchantment;molecular"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;molecular");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            ItemStack armor = attacker.getEquipment().getChestplate();
            if (armor == null) {
                return null;
            }
            attacker.sendMessage(ChatColorCast.format("&b▸ &f盔甲耐久度&d&l+" + level));
            armor.setDurability((short) (armor.getDurability() - level));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.molecular.getRankColor() + PWEnchant.molecular.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.molecular;
        }
        if (enchant.equals(PWEnchant.fling)) {
            if (!(nbtItem.hasKey("ae_enchantment;fling"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;fling");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    victim.setVelocity(new Vector(0, level * 0.8, 0));
                }
            }.runTaskLater(PWPlayer.getPlugin(), 1l);

            victim.sendMessage(ChatColorCast.format("&c▸ &f你中了&c&l" + attacker.getName() + "&f的附魔: " + PWEnchant.fling.getRankColor() + PWEnchant.fling.getChineseName() + " &b&l" + getLevel(level)));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.fling.getRankColor() + PWEnchant.fling.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.fling;
        }
        if (enchant.equals(PWEnchant.blaze)) {
            if (!(nbtItem.hasKey("ae_enchantment;blaze"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;blaze");
            int random = NumberFormat.getRandomInt(0, 100);
            if (random > level * 2) {
                return null;
            }
            List<Block> blocks = getNearbyBlocks(attacker.getLocation(), level);
            List<Block> fires = new ArrayList<>();
            for (Block block : blocks) {
                Location up = block.getLocation().add(0, 1, 0);
                Block fire = up.getBlock();
                if (fire.getType().equals(Material.AIR) &&
                        !(fire.getLocation().subtract(0, 1, 0).getBlock().getType().equals(Material.AIR))
                ) {
                    fire.setType(Material.FIRE);
                    fires.add(fire);
                }
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Block block : fires) {
                        block.setType(Material.AIR);
                    }
                }
            }.runTaskLater(PWPlayer.getPlugin(), (level * 100));
            attacker.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, level * 100, level, false, true));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.blaze.getRankColor() + PWEnchant.blaze.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.blaze;
        }
        if (enchant.equals(PWEnchant.hook)) {
            if (!(nbtItem.hasKey("ae_enchantment;hook"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;hook");
            int random = NumberFormat.getRandomInt(0, 100);
//            if (random > level*2) {return null;}
            Vector direction = victim.getLocation().toVector().subtract(attacker.getLocation().toVector()).normalize();
            victim.setVelocity(direction);
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.hook.getRankColor() + PWEnchant.hook.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.hook;
        }
        if (enchant.equals(PWEnchant.fix)) {
            if (!(nbtItem.hasKey("ae_enchantment;fix"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;fix");
            int random = NumberFormat.getRandomInt(0, 100);
//            if (random > level*2) {return null;}

            if (weapon == null) {
                return null;
            }
            attacker.sendMessage(ChatColorCast.format("&b▸ &f武器耐久度&d&l+" + level));
            weapon.setDurability((short) (weapon.getDurability() - level));
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.fix.getRankColor() + PWEnchant.fix.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.fix;
        }
        if (enchant.equals(PWEnchant.melt)) {
            if (!(nbtItem.hasKey("ae_enchantment;melt"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;melt");
            int random = NumberFormat.getRandomInt(0, 100);
//            if (random > level*2) {return null;}
            ItemStack helmet = attacker.getEquipment().getHelmet();
            ItemStack chestplate = attacker.getEquipment().getChestplate();
            ItemStack leggings = attacker.getEquipment().getLeggings();
            ItemStack boots = attacker.getEquipment().getBoots();
            if (helmet == null && chestplate == null && leggings == null && boots == null) {
                return null;
            }
            if (helmet != null) {
                helmet.setDurability((short) (helmet.getDurability() + level));
            }
            if (chestplate != null) {
                chestplate.setDurability((short) (chestplate.getDurability() + level));
            }
            if (leggings != null) {
                leggings.setDurability((short) (leggings.getDurability() + level));
            }
            if (boots != null) {
                boots.setDurability((short) (boots.getDurability() + level));
            }
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.melt.getRankColor() + PWEnchant.melt.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.melt;
        }
        if (enchant.equals(PWEnchant.execute)) {
            if (!(nbtItem.hasKey("ae_enchantment;execute"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;execute");
            int random = NumberFormat.getRandomInt(0, 100);
//            if (random > level*2) {return null;}

            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.execute.getRankColor() + PWEnchant.execute.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.execute;
        }
        if (enchant.equals(PWEnchant.shatter)) {
            if (!(nbtItem.hasKey("ae_enchantment;shatter"))) {
                return null;
            }
            int level = nbtItem.getInteger("ae_enchantment;shatter");
            int random = NumberFormat.getRandomInt(0, 100);
//            if (random > level*2) {return null;}
            Shatter shatter = new Shatter();
            ItemMeta itemMeta = weapon.getItemMeta();
            double damage = (victim.getHealth() / 4) / ((victim.getAttribute(Attribute.GENERIC_ARMOR).getValue() + 10) / 10);
            int times = shatter.doShatter(victim, level, damage);
            if (times == 0) {
                return null;
            }
            packetText.createAttackText(victim, ChatColorCast.format(PWEnchant.shatter.getRankColor() + PWEnchant.shatter.getChineseName() + " &b&l" + getLevel(level)));
            return PWEnchant.shatter;
        }


        return null;
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
