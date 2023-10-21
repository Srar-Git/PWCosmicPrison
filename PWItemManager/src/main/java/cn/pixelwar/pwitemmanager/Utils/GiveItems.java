package cn.pixelwar.pwitemmanager.Utils;

import cn.pixelwar.pwitemmanager.Files.CustomItems;
import cn.pixelwar.pwitemmanager.NBT.Item.GiveAndDropItem;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static cn.pixelwar.pwitemmanager.Listeners.DropListener.dropItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static cn.pixelwar.pwitemmanager.Listeners.DropListener.dropItem;

public class GiveItems {
    NumberFormat numberFormat = new NumberFormat();

    public void giveExpBottle(Player player, long exp, String tier, int amount, int level) {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE, amount);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setLong("exp", exp);
        nbtItem.setInteger("level", level);
        nbtItem.setString("player", player.getName());
        nbtItem.setString("tier", tier);
        ItemStack nbtDoneItem = nbtItem.getItem();
        List<String> LoreList = new ArrayList<String>();
        ItemMeta itemStackMeta = nbtDoneItem.getItemMeta();


        if (tier.equalsIgnoreCase("common")) {
            itemStackMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "经验瓶" + ChatColor.GRAY + " ▸ " + ChatColor.LIGHT_PURPLE + numberFormat.getLongFormat(exp) + "XP");
            LoreList.add(0, "");
            LoreList.add(1, ChatColor.GRAY + "使用此经验瓶会给你" + ChatColor.GREEN + "" + ChatColor.BOLD + "+" + numberFormat.getLongFormat(exp) + ChatColor.GRAY + "经验");
            LoreList.add(2, "");
            LoreList.add(3, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "使用者: " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName());
            LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "需要等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + level);
            LoreList.add(5, "");
            LoreList.add(6, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "喝下经验瓶");
            itemStackMeta.setLore(LoreList);
            nbtDoneItem.setItemMeta(itemStackMeta);
            GiveAndDropItem.giveItem(player, nbtDoneItem);
            return;
        }
        if (tier.equalsIgnoreCase("uncommon")) {
            itemStackMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "经验瓶" + ChatColor.GRAY + " ▸ " + ChatColor.LIGHT_PURPLE + numberFormat.getLongFormat(exp) + "XP");
            LoreList.add(0, "");
            LoreList.add(1, ChatColor.GRAY + "使用此经验瓶会给你" + ChatColor.GREEN + "" + ChatColor.BOLD + "+" + numberFormat.getLongFormat(exp) + ChatColor.GRAY + "经验");
            LoreList.add(2, "");
            LoreList.add(3, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "使用者: " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName());
            LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "需要等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + level);
            LoreList.add(5, "");
            LoreList.add(6, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "喝下经验瓶");
            itemStackMeta.setLore(LoreList);
            nbtDoneItem.setItemMeta(itemStackMeta);

            GiveAndDropItem.giveItem(player, nbtDoneItem);
            return;
        }
        if (tier.equalsIgnoreCase("rare")) {
            itemStackMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "经验瓶" + ChatColor.GRAY + " ▸ " + ChatColor.LIGHT_PURPLE + numberFormat.getLongFormat(exp) + "XP");
            LoreList.add(0, "");
            LoreList.add(1, ChatColor.GRAY + "使用此经验瓶会给你" + ChatColor.GREEN + "" + ChatColor.BOLD + "+" + numberFormat.getLongFormat(exp) + ChatColor.GRAY + "经验");
            LoreList.add(2, "");
            LoreList.add(3, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "使用者: " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName());
            LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "需要等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + level);
            LoreList.add(5, "");
            LoreList.add(6, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "喝下经验瓶");
            itemStackMeta.setLore(LoreList);
            nbtDoneItem.setItemMeta(itemStackMeta);

            GiveAndDropItem.giveItem(player, nbtDoneItem);
            return;
        }
        if (tier.equalsIgnoreCase("epic")) {
            itemStackMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "经验瓶" + ChatColor.GRAY + " ▸ " + ChatColor.LIGHT_PURPLE + numberFormat.getLongFormat(exp) + "XP");
            LoreList.add(0, "");
            LoreList.add(1, ChatColor.GRAY + "使用此经验瓶会给你" + ChatColor.GREEN + "" + ChatColor.BOLD + "+" + numberFormat.getLongFormat(exp) + ChatColor.GRAY + "经验");
            LoreList.add(2, "");
            LoreList.add(3, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "使用者: " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName());
            LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "需要等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + level);
            LoreList.add(5, "");
            LoreList.add(6, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "喝下经验瓶");
            itemStackMeta.setLore(LoreList);
            nbtDoneItem.setItemMeta(itemStackMeta);

            GiveAndDropItem.giveItem(player, nbtDoneItem);
            return;
        }
        if (tier.equalsIgnoreCase("legend")) {
            itemStackMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "经验瓶" + ChatColor.GRAY + " ▸ " + ChatColor.LIGHT_PURPLE + numberFormat.getLongFormat(exp) + "XP");
            LoreList.add(0, "");
            LoreList.add(1, ChatColor.GRAY + "使用此经验瓶会给你" + ChatColor.GREEN + "" + ChatColor.BOLD + "+" + numberFormat.getLongFormat(exp) + ChatColor.GRAY + "经验");
            LoreList.add(2, "");
            LoreList.add(3, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "使用者: " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName());
            LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "需要等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + level);
            LoreList.add(5, "");
            LoreList.add(6, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "喝下经验瓶");
            itemStackMeta.setLore(LoreList);
            nbtDoneItem.setItemMeta(itemStackMeta);

            GiveAndDropItem.giveItem(player, nbtDoneItem);
            return;
        }
        if (tier.equalsIgnoreCase("god")) {
            itemStackMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "经验瓶" + ChatColor.GRAY + " ▸ " + ChatColor.LIGHT_PURPLE + numberFormat.getLongFormat(exp) + "XP");
            LoreList.add(0, "");
            LoreList.add(1, ChatColor.GRAY + "使用此经验瓶会给你" + ChatColor.GREEN + "" + ChatColor.BOLD + "+" + numberFormat.getLongFormat(exp) + ChatColor.GRAY + "经验");
            LoreList.add(2, "");
            LoreList.add(3, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "使用者: " + ChatColor.GOLD + "" + ChatColor.BOLD + player.getName());
            LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "需要等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + level);
            LoreList.add(5, "");
            LoreList.add(6, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "喝下经验瓶");
            itemStackMeta.setLore(LoreList);
            nbtDoneItem.setItemMeta(itemStackMeta);

            GiveAndDropItem.giveItem(player, nbtDoneItem);
            return;
        }


    }

    public void giveEnergy(Player player, long energy, int amount) {
        ItemStack item = new ItemStack(Material.BLUE_DYE);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setLong("energy", energy);
        ItemStack nbtDoneItem = nbtItem.getItem();
        List<String> LoreList = new ArrayList<String>();
        ItemMeta itemStackMeta = nbtDoneItem.getItemMeta();
        itemStackMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "宇宙能量" + ChatColor.GRAY + " ▸ " + ChatColor.WHITE + "" + ChatColor.BOLD + numberFormat.getLongFormat(energy));
        itemStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStackMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        LoreList.add(0, "");
        LoreList.add(1, ChatColor.GRAY + "这个物品包含了宇宙能量,你可以");
        LoreList.add(2, ChatColor.GRAY + "将其用于你的任何可附魔物品.");
        LoreList.add(3, "");
        LoreList.add(4, ChatColor.GOLD + "▪ " + ChatColor.WHITE + "能量: " + ChatColor.AQUA + "" + ChatColor.BOLD + "" + numberFormat.getLongFormat(energy));
        LoreList.add(5, "");
        LoreList.add(6, ChatColor.RED + "▸ 能量之间可互相堆叠合成." + ChatColor.GRAY + "(拖拽)");
        LoreList.add(7, ChatColor.RED + "▸ 使用指令" + ChatColor.GOLD + "/extra 数量" + ChatColor.RED + "可以取");
        LoreList.add(8, ChatColor.RED + "出相应数量的宇宙能量");
        LoreList.add(9, ChatColor.RED + "");
        LoreList.add(10, ChatColor.GREEN + "" + ChatColor.BOLD + "拖拽" + ChatColor.GRAY + "到物品上来使用");
        itemStackMeta.setLore(LoreList);
        nbtDoneItem.setItemMeta(itemStackMeta);
        GiveAndDropItem.giveItem(player, nbtDoneItem);
        return;

    }

    public void giveBanknote(Player player, double money) {
        NBTItem slotNbtItem;
        for (int i = 0; i <= 35; i++) {
            if (player.getInventory().getItem(i) == null) {
                continue;
            }
            slotNbtItem = new NBTItem(player.getInventory().getItem(i));
            if (slotNbtItem.getDouble("money") != null &&
                    slotNbtItem.getDouble("money") != 0d &&
                    slotNbtItem.getItem().getAmount() == 1 &&
                    slotNbtItem.getItem().getType().equals(Material.PAPER)
            ) {
                Double newMoney = slotNbtItem.getDouble("money") + money;
                slotNbtItem.setDouble("money", newMoney);
                ItemStack nbtDoneItem1 = slotNbtItem.getItem();
                List<String> LoreList1 = new ArrayList<String>();
                ItemMeta itemStackMeta1 = nbtDoneItem1.getItemMeta();
                itemStackMeta1.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "银行支票" + ChatColor.GRAY + " ▸ " + ChatColor.YELLOW + "" + ChatColor.BOLD + numberFormat.getDoubleFormat(newMoney) + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "$");
                LoreList1.add(0, "");
                LoreList1.add(1, ChatColor.GRAY + "这个物品包含了金钱,你可以从");
                LoreList1.add(2, ChatColor.GRAY + "中获得大量的现金.");
                LoreList1.add(3, "");
                LoreList1.add(4, ChatColor.GOLD + "▪ " + ChatColor.WHITE + "金钱: " + ChatColor.YELLOW + "" + ChatColor.BOLD + numberFormat.getDoubleFormat(newMoney) + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "$");
                LoreList1.add(5, "");
                LoreList1.add(6, ChatColor.RED + "▸ 支票之间可互相堆叠合成." + ChatColor.GRAY + "(拖拽)");
                LoreList1.add(7, ChatColor.RED + "");
                LoreList1.add(8, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "来使用");
                itemStackMeta1.setLore(LoreList1);
                nbtDoneItem1.setItemMeta(itemStackMeta1);


                player.getInventory().setItem(i, nbtDoneItem1);
                return;
            }

        }

        ItemStack item = new ItemStack(Material.PAPER);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setDouble("money", money);
        ItemStack nbtDoneItem = nbtItem.getItem();
        List<String> LoreList = new ArrayList<String>();
        ItemMeta itemStackMeta = nbtDoneItem.getItemMeta();
        itemStackMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "银行支票" + ChatColor.GRAY + " ▸ " + ChatColor.YELLOW + "" + ChatColor.BOLD + numberFormat.getDoubleFormat(money) + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + " $");
        LoreList.add(0, "");
        LoreList.add(1, ChatColor.GRAY + "这个物品包含了金钱,你可以从");
        LoreList.add(2, ChatColor.GRAY + "中获得大量的现金.");
        LoreList.add(3, "");
        LoreList.add(4, ChatColor.GOLD + "▪ " + ChatColor.WHITE + "金钱: " + ChatColor.YELLOW + "" + ChatColor.BOLD + numberFormat.getDoubleFormat(money) + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + " $");
        LoreList.add(5, "");
        LoreList.add(6, ChatColor.RED + "▸ 支票之间可互相堆叠合成." + ChatColor.GRAY + "(拖拽)");
        LoreList.add(7, ChatColor.RED + "");
        LoreList.add(8, ChatColor.GREEN + "" + ChatColor.BOLD + "右键" + ChatColor.GRAY + "来使用");
        itemStackMeta.setLore(LoreList);
        nbtDoneItem.setItemMeta(itemStackMeta);
        GiveAndDropItem.giveItem(player, nbtDoneItem);
    }

    public void giveSkin(Player player, String skin, String equip) {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("skin", skin);
        nbtItem.setString("equip", equip);
        ItemStack nbtDoneItem = nbtItem.getItem();
        List<String> LoreList = new ArrayList<String>();
        ItemMeta itemStackMeta = nbtDoneItem.getItemMeta();
        itemStackMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "物品皮肤" + ChatColor.GRAY + " ▸ " + ChatColor.GOLD + "" + ChatColor.BOLD + skin);
        LoreList.add(0, "");
        LoreList.add(1, ChatColor.GRAY + "这是一个皮肤物品,你可以在");
        LoreList.add(2, ChatColor.LIGHT_PURPLE + "" + ChatColor.UNDERLINE + equip + ChatColor.GRAY + "上使用这个皮肤.");
        LoreList.add(3, "");
        LoreList.add(4, ChatColor.GOLD + "▪ " + ChatColor.WHITE + "皮肤: " + ChatColor.YELLOW + "" + ChatColor.BOLD + skin);
        LoreList.add(5, ChatColor.GOLD + "▪ " + ChatColor.WHITE + "可用于: " + ChatColor.BLUE + "" + ChatColor.BOLD + equip);
        LoreList.add(6, "");
        LoreList.add(7, ChatColor.RED + "▸ 皮肤使用后不可取下.");
        LoreList.add(8, ChatColor.RED + "▸ 对已存在皮肤的物品使用会");
        LoreList.add(9, ChatColor.RED + "  覆盖之前的皮肤.");
        LoreList.add(10, "");
        LoreList.add(11, ChatColor.GREEN + "" + ChatColor.BOLD + "拖拽" + ChatColor.GRAY + "到物品上来使用");
        itemStackMeta.setLore(LoreList);
        nbtDoneItem.setItemMeta(itemStackMeta);
        GiveAndDropItem.giveItem(player, nbtDoneItem);
    }

    public void giveOreBag(Player player, String type) {
        Random r = new Random();
        int id = r.nextInt(99999999);
        ItemStack OreBag = null;
        String material = null;
        String name = null;
        switch (type) {
            case "煤矿":
                OreBag = new ItemStack(Material.COAL_ORE);
                material = "COAL_ORE";
                name = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "煤":
                OreBag = new ItemStack(Material.COAL);
                material = "COAL";
                name = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "铁矿":
                OreBag = new ItemStack(Material.IRON_ORE);
                material = "IRON_ORE";
                name = ChatColor.WHITE + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "铁锭":
                OreBag = new ItemStack(Material.IRON_INGOT);
                material = "IRON_INGOT";
                name = ChatColor.WHITE + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "青金石矿":
                OreBag = new ItemStack(Material.LAPIS_ORE);
                material = "LAPIS_ORE";
                name = ChatColor.BLUE + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "青金石":
                OreBag = new ItemStack(Material.LAPIS_LAZULI);
                material = "LAPIS_LAZULI";
                name = ChatColor.BLUE + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "红石矿":
                OreBag = new ItemStack(Material.REDSTONE_ORE);
                material = "REDSTONE_ORE";
                name = ChatColor.RED + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "红石":
                OreBag = new ItemStack(Material.REDSTONE);
                material = "REDSTONE";
                name = ChatColor.RED + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "金矿":
                OreBag = new ItemStack(Material.GOLD_ORE);
                material = "GOLD_ORE";
                name = ChatColor.YELLOW + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "金锭":
                OreBag = new ItemStack(Material.GOLD_INGOT);
                material = "GOLD_INGOT";
                name = ChatColor.YELLOW + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "钻石矿":
                OreBag = new ItemStack(Material.DIAMOND_ORE);
                material = "DIAMOND_ORE";
                name = ChatColor.AQUA + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "钻石":
                OreBag = new ItemStack(Material.DIAMOND);
                material = "DIAMOND";
                name = ChatColor.AQUA + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "绿宝石矿":
                OreBag = new ItemStack(Material.EMERALD_ORE);
                material = "EMERALD_ORE";
                name = ChatColor.GREEN + "" + ChatColor.BOLD + type + "存储箱";
                break;
            case "绿宝石":
                OreBag = new ItemStack(Material.EMERALD);
                material = "EMERALD";
                name = ChatColor.GREEN + "" + ChatColor.BOLD + type + "存储箱";
                break;
            default:
                break;
        }
        NBTItem nbtItem = new NBTItem(OreBag);
        nbtItem.setString("material", material);
        nbtItem.setString("name", name);
        nbtItem.setString("bagtype", type);
        nbtItem.setInteger("maxsize", 2304);
        nbtItem.setInteger("size", 0);
        nbtItem.setInteger("bagneedxp", 4800);
        nbtItem.setInteger("bagxp", 0);
        nbtItem.setInteger("level", 0);
        nbtItem.setInteger("maxlevel", 15);
        nbtItem.setInteger("enchant", 0);
        nbtItem.setInteger("fail", 0);
        nbtItem.setInteger("id", id);
        ItemStack nbtDoneItem = nbtItem.getItem();
        List<String> LoreList = new ArrayList<String>();
        ItemMeta itemStackMeta = nbtDoneItem.getItemMeta();
        itemStackMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        itemStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStackMeta.setDisplayName(name + ChatColor.GRAY + " ▸ " + ChatColor.YELLOW + "" + ChatColor.BOLD + "LV.0");
        LoreList.add(0, "");
        LoreList.add(1, ChatColor.GRAY + "你可以将此物品放在背包中存储矿物");
        LoreList.add(2, "");
        LoreList.add(3, ChatColor.GOLD + "" + ChatColor.BOLD + "| 存储箱信息");
        LoreList.add(4, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "等级: " + ChatColor.AQUA + "" + ChatColor.BOLD + "0");
        LoreList.add(5, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "存储" + type + ": " + ChatColor.GOLD + "" + ChatColor.BOLD + "0/2,304");
        LoreList.add(6, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "最高等级: " + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "15");
        LoreList.add(7, ChatColor.AQUA + "▪ " + ChatColor.WHITE + "附魔保护: " + ChatColor.RED + "" + ChatColor.BOLD + "✖");
        LoreList.add(8, "");
        LoreList.add(9, ChatColor.GOLD + "" + ChatColor.BOLD + "| 宇宙能量");
        LoreList.add(10, ChatColor.RED + "" + ChatColor.BOLD + "┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ " + ChatColor.WHITE + "" + ChatColor.BOLD + "0%");
        LoreList.add(11, ChatColor.GRAY + "(" + ChatColor.WHITE + "0 " + ChatColor.GRAY + "/ 4,800)");
        LoreList.add(12, "");
        LoreList.add(13, ChatColor.GOLD + "" + ChatColor.BOLD + "| 附魔信息");
        LoreList.add(14, ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "=" + ChatColor.STRIKETHROUGH + " " + ChatColor.WHITE + "" + ChatColor.BOLD + " " + 0 + " " + ChatColor.GREEN + "" + ChatColor.BOLD + "成功附魔 " + ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " " + ChatColor.STRIKETHROUGH + "=");
        LoreList.add(15, ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "=" + ChatColor.STRIKETHROUGH + " " + ChatColor.WHITE + "" + ChatColor.BOLD + " " + 0 + " " + ChatColor.RED + "" + ChatColor.BOLD + "失败附魔 " + ChatColor.RED + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " " + ChatColor.STRIKETHROUGH + "=");
        itemStackMeta.setLore(LoreList);
        nbtDoneItem.setItemMeta(itemStackMeta);
        GiveAndDropItem.giveItem(player, nbtDoneItem);
    }

    public void giveCustomItem(Player player, String item, int amount) {
        ItemStack customItem = (ItemStack) CustomItems.getItemFile().get(item);
        customItem.setAmount(amount);
        GiveAndDropItem.giveItem(player, customItem);
    }

}
