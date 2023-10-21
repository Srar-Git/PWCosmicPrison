package cn.pixelwar.pwitemmanager.Enchant.menus;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class EnchantMenu {

    public void createGUI(Player player, ItemStack item) {
        int enchantSlot = IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTSLOTS);
        int reroll = IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL);
        if (player.hasPermission("enchantslot.1")) {
        }


        ItemMeta itemMeta = item.getItemMeta();
        String name = itemMeta.getDisplayName();
        List<String> lore = itemMeta.getLore();
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "附魔池 " + name);

        ItemStack back = getButton(
                Material.BLACK_STAINED_GLASS_PANE,
                " ",
                asList(),
                false
        );
        ItemStack red = getButton(
                Material.RED_STAINED_GLASS_PANE,
                ChatColorCast.format("&c✖ &f未解锁的附魔格"),
                asList(
                        " ",
                        ChatColorCast.format("&7请升级你的&6&l荣誉等级&7,升级&a&lVIP等级"),
                        ChatColorCast.format("&7或者通过&d&l特殊物品&7来增加附魔格数量")
                ),
                false
        );
        ItemStack green = getButton(
                Material.LIME_STAINED_GLASS_PANE,
                ChatColorCast.format("&a✔ &f已解锁的附魔格"),
                asList(" ",
                        ChatColorCast.format("&7点击下方的&6&l按钮&7来抽取多个随机"),
                        ChatColorCast.format("&7附魔，然后你可以选择其中&n一个&7!")
                ),
                false
        );


        ItemStack roll_book = getButton(
                Material.FIREWORK_ROCKET,
                ChatColorCast.format("&6&l升级附魔书"),
                asList(
                        " ",
                        ChatColorCast.format("&7升级附魔书会消耗附魔书上的所有"),
                        ChatColorCast.format("&7宇宙能量，升级成功后你会获得一"),
                        ChatColorCast.format("&7本&b更高等级&7的附魔书，失败会导致"),
                        ChatColorCast.format("&7附魔书&c消失&7，同时你会获得&d附魔券"),
                        ChatColorCast.format(" "),
                        ChatColorCast.format("&a&l点击&7升级附魔书")
                ),
                true
        );
        ItemStack dust = getButton(
                Material.SUGAR,
                ChatColorCast.format("&6&l附魔之尘几率加成"),
                asList(
                        " ",
                        ChatColorCast.format("&7你可以点击你背包中的附魔之尘"),
                        ChatColorCast.format("&7增加相应等级附魔的成功几率."),
                        ChatColorCast.format(" "),
                        ChatColorCast.format("&b▪ &f普通: &b&l+0%"),
                        ChatColorCast.format("&b▪ &a优秀: &b&l+0%"),
                        ChatColorCast.format("&b▪ &9稀有: &b&l+0%"),
                        ChatColorCast.format("&b▪ &e史诗: &b&l+0%"),
                        ChatColorCast.format("&b▪ &6传说: &b&l+0%")
                ),
                true
        );
        NBTItem nbtDust = new NBTItem(dust);
        nbtDust.setInteger("rank1", 0);
        nbtDust.setInteger("rank2", 0);
        nbtDust.setInteger("rank3", 0);
        nbtDust.setInteger("rank4", 0);
        nbtDust.setInteger("rank5", 0);
        dust = nbtDust.getItem();
        player.playSound(player.getEyeLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        int[] slots = {3, 4, 5, 11, 15, 19, 25, 29, 33, 39, 41, 40};
        List<Integer> redSlots = new ArrayList<>();
        List<Integer> greenSlots = new ArrayList<>();
        for (int e : slots) {
            redSlots.add(e);
        }
        for (int i = 0; i < enchantSlot; i++) {
            greenSlots.add(slots[i]);
        }
        for (int i = 0; i < 54; i++) {
            gui.setItem(i, back);
        }

        gui.setItem(22, item);
        if (item.getType().equals(Material.ENCHANTED_BOOK)) {
            gui.setItem(49, roll_book);
        }
        if (item.getType().toString().contains("PICKAXE")) {
            ItemStack roll_enchant = getButton(
                    Material.TURTLE_EGG,
                    ChatColorCast.format("&6&l开始抽取附魔"),
                    asList(
                            " ",
                            ChatColorCast.format("&7抽取附魔后你的所有已解锁附魔格会变为"),
                            ChatColorCast.format("&7相应附魔，你可以选取其中&b&l1个&7进行附魔"),
                            ChatColorCast.format("&7抽取完后若没有满意的附魔，你可以重新抽取"),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&b▪ &f剩余重抽次数: &d&l" + reroll),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&6&l点击&7开始抽取附魔")
                    ),
                    true
            );
            gui.setItem(49, roll_enchant);
            gui.setItem(8, dust);
            for (int i = 0; i < 54; i++) {
                if (redSlots.contains(i))
                    gui.setItem(i, red);
                if (greenSlots.contains(i))
                    gui.setItem(i, green);
            }
        }
        if (
                item.getType().equals(Material.COAL) || item.getType().equals(Material.COAL_ORE) ||
                        item.getType().equals(Material.IRON_INGOT) || item.getType().equals(Material.IRON_ORE) ||
                        item.getType().equals(Material.LAPIS_LAZULI) || item.getType().equals(Material.LAPIS_ORE) ||
                        item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.REDSTONE_ORE) ||
                        item.getType().equals(Material.GOLD_INGOT) || item.getType().equals(Material.GOLD_ORE) ||
                        item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.DIAMOND_ORE) ||
                        item.getType().equals(Material.EMERALD) || item.getType().equals(Material.EMERALD_ORE)
        ) {
            ItemStack roll_enchant = getButton(
                    Material.TURTLE_EGG,
                    ChatColorCast.format("&6&l开始抽取附魔"),
                    asList(
                            " ",
                            ChatColorCast.format("&7抽取附魔后你的所有已解锁附魔格会变为"),
                            ChatColorCast.format("&7相应附魔，你可以选取其中&b&l1个&7进行附魔"),
                            ChatColorCast.format("&7抽取完后若没有满意的附魔，你可以重新抽取"),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&b▪ &f剩余重抽次数: &d&l" + reroll),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&c&l注意!&7选择附魔后将不能选择升级!"),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&6&l点击&7开始抽取附魔")
                    ),
                    true
            );
            gui.setItem(48, roll_enchant);
            gui.setItem(8, dust);
            for (int i = 0; i < 54; i++) {
                if (redSlots.contains(i))
                    gui.setItem(i, red);
                if (greenSlots.contains(i))
                    gui.setItem(i, green);
            }

            ItemStack upbag = getButton(
                    Material.FIREWORK_ROCKET,
                    ChatColorCast.format("&6&l升级矿物存储箱"),
                    asList(
                            " ",
                            ChatColorCast.format("&7你可以消耗宇宙能量来升级你的矿物"),
                            ChatColorCast.format("&7存储箱，升级后容量可增加&d2,304"),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&b▪ &f成功率: &a&l100%"),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&c&l注意!&7选择升级后将不能选择附魔!"),
                            ChatColorCast.format(" "),
                            ChatColorCast.format("&6&l点击&7升级存储箱")
                    ),
                    true
            );
            gui.setItem(50, upbag);


        }


        player.openInventory(gui);
    }

    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        if (glow) {
            itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow, PWEnchant enchant, int level, int success) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        if (glow) {
            itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("enchant", enchant.toString());
        nbtItem.setInteger("level", level);
        nbtItem.setInteger("success", success);
        item = nbtItem.getItem();
        return item;
    }

}
