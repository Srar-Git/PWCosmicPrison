package cn.pixelwar.pwitemmanager.OreBag;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwblockmanager.customevents.BlockBrokenEvent;
import cn.pixelwar.pwitemmanager.NBT.Item.SellItem.OrePrice;
import cn.pixelwar.pwitemmanager.NBT.UpdateSlotLore;
import cn.pixelwar.pwitemmanager.PWItemManager;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import de.tr7zw.nbtapi.NBTItem;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Orebag implements Listener {
    Random random = new Random();

    public void giveOre(Player player, String materialStr, int amount) {
        if (amount == 0) {
            return;
        }
        Inventory inventory = player.getInventory();
        ItemStack ore = new ItemStack(Material.matchMaterial(materialStr));
        ore.setAmount(amount);
        NBTItem nbtItem;
        for (int i = 0; i <= 36; i++) {
            if (i == 36) {
                inventory.addItem(ore);
                return;
            }
            if (player.getInventory().getItem(i) == null) {
                continue;
            }
            Material item = player.getInventory().getItem(i).getType();
            if (
                    item.equals(Material.COAL) || item.equals(Material.COAL_ORE) ||
                            item.equals(Material.IRON_INGOT) || item.equals(Material.IRON_ORE) ||
                            item.equals(Material.LAPIS_LAZULI) || item.equals(Material.LAPIS_ORE) ||
                            item.equals(Material.REDSTONE) || item.equals(Material.REDSTONE_ORE) ||
                            item.equals(Material.GOLD_INGOT) || item.equals(Material.GOLD_ORE) ||
                            item.equals(Material.DIAMOND) || item.equals(Material.DIAMOND_ORE) ||
                            item.equals(Material.EMERALD) || item.equals(Material.EMERALD_ORE)
            ) {
                nbtItem = new NBTItem(player.getInventory().getItem(i));
                String material = nbtItem.getString("material");
                if (material.equals(materialStr)) {
                    NumberFormat numberFormat = new NumberFormat();
                    int size = nbtItem.getInteger("size");
                    int maxSize = nbtItem.getInteger("maxsize");
                    if (size >= maxSize) {
                        continue;
                    }
                    int nowSize = size + amount;
                    if (nowSize >= maxSize) {
                        amount = maxSize - nowSize;
                        nowSize = maxSize;
                    }
                    nbtItem.setInteger("size", nowSize);
                    String bagType = nbtItem.getString("materialname");
                    ItemMeta itemMeta = nbtItem.getItem().getItemMeta();
                    List<String> lores = itemMeta.getLore();
                    int info = 0;
                    for (String s : lores) {
                        if (s.contains("| 存储箱信息")) {
                            break;
                        }
                        info++;
                    }
                    lores.set(info + 1, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "存储" + bagType + ": " + ChatColor.AQUA + numberFormat.getIntFormat(nowSize) + "/" + numberFormat.getIntFormat(maxSize));

                    ItemStack finalItem = nbtItem.getItem();
                    itemMeta.setLore(lores);
                    finalItem.setItemMeta(itemMeta);
                    player.getInventory().setItem(i, finalItem);
                    ItemStack oneOre = ore.clone();
                    oneOre.setAmount(1);
                    Bukkit.getServer().getPluginManager().callEvent(new OreBagAddEvent(player, oneOre.getType(), amount, i));

                    return;
                }

            }

        }
        ItemStack oreadd = new ItemStack(Material.matchMaterial(materialStr), amount);
        player.getInventory().addItem(oreadd);

    }


    public void doBagEnchant(Player player, int slot, Material ore, int amount) {
        ItemStack bag = player.getInventory().getItem(slot);

        autoSell(player, slot, bag);
        energyMirror(player, slot, bag);
        nenergyMirror(player, slot, bag);
        multiOre(player, slot, bag, amount);
    }

    public void autoSell(Player player, int slot, ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        if (!(nbtItem.hasKey("ae_enchantment;sell"))) {
            return;
        }
        int level = nbtItem.getInteger("ae_enchantment;sell");
        int random = NumberFormat.getRandomInt(0, 100);
        if (random > level) {
            return;
        }
        int size = nbtItem.getInteger("size");
        String m = nbtItem.getString("material");
        double money = (double) size * OrePrice.valueOf(m).getPrice();
        Economy economy = PWItemManager.getEconomy();
        economy.depositPlayer(player, money);
        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
        updateSlotLore.setBagSize(player, 0, slot);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_SHEEP_SHEAR, 1f, 0.1f);
        NumberFormat numberFormat = new NumberFormat();
        player.sendMessage(ChatColorCast.format("&d▸ &f触发存储箱附魔: &a&l自动出售 &7(你获得了&e&l" + numberFormat.getDoubleFormat(money) + "&2&l$&7)"));
    }

    public void energyMirror(Player player, int slot, ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        if (!(nbtItem.hasKey("ae_enchantment;energymirror"))) {
            return;
        }
        int level = nbtItem.getInteger("ae_enchantment;energymirror");
        int random = NumberFormat.getRandomInt(0, 100);
        if (random > level) {
            return;
        }
        int xp = nbtItem.getInteger("bagxp");
        int add = level * 10;
        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
        updateSlotLore.updateSlotEnergy(player, (xp + add), nbtItem.getInteger("bagneedxp"), slot);
        player.playSound(player.getEyeLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2.0f);
        player.sendMessage(ChatColorCast.format("&d▸ &f触发存储箱附魔: &e&l能量之镜 &7(你获得了&f&lx &b&l" + add + "&7宇宙能量)"));
    }

    public void nenergyMirror(Player player, int slot, ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        if (!(nbtItem.hasKey("ae_enchantment;energymirror"))) {
            return;
        }
        int level = nbtItem.getInteger("ae_enchantment;energymirror");
        int random = NumberFormat.getRandomInt(0, 100);
        if (random > level * 2) {
            return;
        }
        int xp = nbtItem.getInteger("bagxp");
        int add = level * 50;
        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
        updateSlotLore.updateSlotEnergy(player, (xp + add), nbtItem.getInteger("bagneedxp"), slot);
        player.playSound(player.getEyeLocation(), Sound.BLOCK_GLASS_BREAK, 1f, 2.0f);
        player.sendMessage(ChatColorCast.format("&d▸ &f触发存储箱附魔: &d&l能量之源 &7(你获得了&f&lx &b&l" + add + "&7宇宙能量)"));
    }

    public void multiOre(Player player, int slot, ItemStack item, int amount) {
        NBTItem nbtItem = new NBTItem(item);
        if (!(nbtItem.hasKey("ae_enchantment;multiore"))) {
            return;
        }
        int level = nbtItem.getInteger("ae_enchantment;multiore");
        int size = nbtItem.getInteger("size");
        int random = NumberFormat.getRandomInt(0, 100);
        if (random > level) {
            return;
        }
        int add = level * amount;
        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
        updateSlotLore.setBagSize(player, (add + size), slot);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_HORSE_ARMOR, 1f, 0.1f);
        player.sendMessage(ChatColorCast.format("&d▸ &f触发存储箱附魔: &6&l多倍矿物 &7(你获得了额外&f&lx &a&l" + add + "&7个矿物)"));
    }

    @EventHandler
    public void OnDig(BlockBrokenEvent event) {
        Material material = event.getBlock().getType();

        Player digger = event.getPlayer();

        if (material.equals(Material.COAL_BLOCK)) {
            material = Material.COAL;
        }
        if (material.equals(Material.IRON_BLOCK)) {
            material = Material.IRON_INGOT;
        }
        if (material.equals(Material.LAPIS_BLOCK)) {
            material = Material.LAPIS_LAZULI;
        }
        if (material.equals(Material.REDSTONE_BLOCK)) {
            material = Material.REDSTONE;
        }
        if (material.equals(Material.GOLD_BLOCK)) {
            material = Material.GOLD_INGOT;
        }
        if (material.equals(Material.DIAMOND_BLOCK)) {
            material = Material.DIAMOND;
        }
        if (material.equals(Material.EMERALD_BLOCK)) {
            material = Material.EMERALD;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (digger.equals(p)) {
                continue;
            }
            if (digger.getLocation().distance(p.getLocation()) > 15) {
                continue;
            }

            if (CoolDownManager.playerCoolDownMap.containsKey(p.getName())) {
                if (CoolDownManager.playerCoolDownMap.get(p.getName()).getOneCoolDownMap().containsKey(CoolDownType.BAGENCHANT)) {
                    continue;
                }
            }

            for (int i = 0; i <= 36; i++) {
                ItemStack item = p.getInventory().getItem(i);
                if (item == null || (!(item.getType().equals(material)))) {
                    continue;
                }

                if (item.hasItemMeta()) {

                    NBTItem nbtItem = new NBTItem(item);
                    if (nbtItem.hasKey("ae_enchantment;share")) {

                        int level = nbtItem.getInteger("ae_enchantment;share");
                        int size = nbtItem.getInteger("size");
                        int random = NumberFormat.getRandomInt(0, 100);
                        if (random > level) {
                            break;
                        }
                        //增加size内的矿物
                        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
                        int finalI = i;
                        updateSlotLore.setBagSize(p, size + level, finalI);
                        CoolDownManager.addPlayerCoolDown(p, CoolDownType.BAGENCHANT, 3);
                        p.playSound(p.getEyeLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
                        p.sendMessage(ChatColorCast.format("&d▸ &f触发存储箱附魔: &6&l矿物共享 &7(你获得了来自&b&l" + digger.getName() + "&7的&e&l" + level + "个&7矿物)"));
                        break;
                    }
                }


            }
        }
    }

}
