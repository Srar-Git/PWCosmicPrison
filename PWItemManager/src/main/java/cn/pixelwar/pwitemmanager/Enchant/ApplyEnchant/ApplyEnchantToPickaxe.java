package cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.NBT.UpdateHandLore;
import cn.pixelwar.pwitemmanager.NBT.UpdateSlotLore;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketText;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class ApplyEnchantToPickaxe {

    NumberFormat numberFormat = new NumberFormat();
    Random random = new Random();
    UpdateHandLore updateHandLore = new UpdateHandLore();
    UpdateSlotLore updateSlotLore = new UpdateSlotLore();

    public void applyHandItemEnchantToPickaxe(Player player, String enchantEnglishName, int enchantLevel, int success, boolean clearXP) {

        ItemStack item = player.getItemInHand();
        if (!(item.getType().equals(Material.WOODEN_PICKAXE) ||
                item.getType().equals(Material.STONE_PICKAXE) ||
                item.getType().equals(Material.IRON_PICKAXE) ||
                item.getType().equals(Material.GOLDEN_PICKAXE) ||
                item.getType().equals(Material.DIAMOND_PICKAXE) ||
                item.getType().equals(Material.NETHERITE_PICKAXE))
        ) {
            return;
        }

        int randomNum = random.nextInt(100);
        if (randomNum <= success) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey("ae_enchantment;" + enchantEnglishName)) {
                updateEnchant(player, enchantEnglishName, enchantLevel, clearXP);
            } else {
                createEnchant(player, enchantEnglishName, enchantLevel, clearXP);
            }
            success(player, enchantEnglishName, enchantLevel);
        } else {
            updateFail(player, clearXP);
            fail(player);

        }


    }

    public void applySlotItemEnchantToPickaxe(Player player, int slot, String enchantEnglishName, int enchantLevel, int success, boolean clearXP) {

        ItemStack item = player.getInventory().getItem(slot);
        if (!(item.getType().equals(Material.WOODEN_PICKAXE) ||
                item.getType().equals(Material.STONE_PICKAXE) ||
                item.getType().equals(Material.IRON_PICKAXE) ||
                item.getType().equals(Material.GOLDEN_PICKAXE) ||
                item.getType().equals(Material.DIAMOND_PICKAXE) ||
                item.getType().equals(Material.NETHERITE_PICKAXE))
        ) {
            return;
        }

        int randomNum = random.nextInt(100);
        if (randomNum <= success) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey("ae_enchantment;" + enchantEnglishName)) {
                updateEnchant(player, slot, enchantEnglishName, enchantLevel, clearXP);
            } else {
                createEnchant(player, slot, enchantEnglishName, enchantLevel, clearXP);
            }
            success(player, slot, enchantEnglishName, enchantLevel);
        } else {
            fail(player, slot);
            updateFail(player, slot, clearXP);
            NBTItem nbtItem = new NBTItem(item);
            int protect = nbtItem.getInteger("gemenchant");
            if (protect == 1) {
                nbtItem.setInteger("gemenchant", 0);
                updateSlotLore.removeSlotPickaxeGem(player, "gemenchant", slot);
                player.sendMessage(ChatColorCast.format("&c▸ 附魔失败，但是&a&l附魔保护石&c保护了你的物品!"));
                return;
            } else {
                player.getInventory().setItem(slot, new ItemStack(Material.AIR));
                player.sendMessage(ChatColorCast.format("&c▸ 附魔失败，你的物品已消失!"));
            }
        }


    }

    public void updateEnchant(Player player, String enchantEnglishName, int enchantLevel, boolean clearXP) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        ItemStack item = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
        int oldLevel = nbtItem.getInteger("ae_enchantment;" + enchantEnglishName);
        int oldToolLevel = nbtItem.getInteger("toollevel");
        int oldSuccess = nbtItem.getInteger("toolenchant");

        //修改能量
        int oldToolXP = nbtItem.getInteger("toolxp");
        int oldToolNeedXP = nbtItem.getInteger("toolneedxp");
        if (clearXP) {
            oldToolXP = getClearedEnergy(player, oldToolXP, oldToolNeedXP, item);
        }
        int gap = 9600;
        if (item.getType().equals(Material.STONE_PICKAXE)) {
            gap = 10200;
        }
        if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            gap = 10800;
        }
        if (item.getType().equals(Material.IRON_PICKAXE)) {
            gap = 11400;
        }
        if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            gap = 12000;
        }
        int newToolNeedXP = oldToolNeedXP + (enchantLevel - oldLevel) * gap;
        updateHandLore.updateHandEnergy(player, oldToolXP, newToolNeedXP);


        int toolPrestige = nbtItem.getInteger("toolprestige");
        String name = nbtItem.getString("toolname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        if (enchantLevel <= oldLevel || enchantLevel > enchant.getMaxLevel()) {
            return;
        }


        String newName = getName(name, toolPrestige, oldToolLevel + enchantLevel - oldLevel);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("toollevel", oldToolLevel + enchantLevel - oldLevel);

        nbtItem.setInteger("toolenchant", oldSuccess + enchantLevel - oldLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);
        List<String> newLoreList = oldLoreList;

        int enchantLine = 0;
        for (String str : newLoreList) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        int i = 0;
        for (String element : oldLoreList) {
            if (i < enchantLine) {
                i++;
                continue;
            }
            if (element.contains(enchantChineseName)) {
                break;
            }
            i++;
        }
        newLoreList.set(i, enchant.getRankColor() + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);

//        if (enchantType==1){//普通附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if (i<enchantLine){
//                    continue;
//                }
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//
//            newLoreList.set(i, ChatColor.WHITE+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==2){// 优秀附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if (i<enchantLine){
//                    continue;
//                }
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.GREEN+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==3){// 稀有附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if (i<enchantLine){
//                    continue;
//                }
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.BLUE+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==4){// 史诗附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if (i<enchantLine){
//                    continue;
//                }
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.YELLOW+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==5){// 传说附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.GOLD+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==6){// 核能附魔
//
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.LIGHT_PURPLE+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }

        //&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m=
        newLoreList.set(enchantLine + 1, ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "=" + ChatColor.STRIKETHROUGH + " " + ChatColor.WHITE + "" + ChatColor.BOLD + " " + (oldSuccess + enchantLevel - oldLevel) + " " + ChatColor.GREEN + "" + ChatColor.BOLD + "成功附魔 " + ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " " + ChatColor.STRIKETHROUGH + "=");

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        if (enchant.equals(PWEnchant.water)) {
            itemMeta.addEnchant(Enchantment.WATER_WORKER, enchantLevel, true);
        }
        nbtDoneItem.setItemMeta(itemMeta);
        player.setItemInHand(nbtDoneItem);

    }

    public void updateEnchant(Player player, int slot, String enchantEnglishName, int enchantLevel, boolean clearXP) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
        int oldLevel = nbtItem.getInteger("ae_enchantment;" + enchantEnglishName);
        int oldToolLevel = nbtItem.getInteger("toollevel");
        int oldSuccess = nbtItem.getInteger("toolenchant");

        //修改能量
        int oldToolXP = nbtItem.getInteger("toolxp");
        int oldToolNeedXP = nbtItem.getInteger("toolneedxp");
        if (clearXP) {
            oldToolXP = getClearedEnergy(player, oldToolXP, oldToolNeedXP, item);
        }
        int gap = 9600;
        if (item.getType().equals(Material.STONE_PICKAXE)) {
            gap = 10200;
        }
        if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            gap = 10800;
        }
        if (item.getType().equals(Material.IRON_PICKAXE)) {
            gap = 11400;
        }
        if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            gap = 12000;
        }
        int newToolNeedXP = oldToolNeedXP + (enchantLevel - oldLevel) * gap;
        updateSlotLore.updateSlotEnergy(player, oldToolXP, newToolNeedXP, slot);


        int toolPrestige = nbtItem.getInteger("toolprestige");
        String name = nbtItem.getString("toolname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        if (enchantLevel <= oldLevel || enchantLevel > enchant.getMaxLevel()) {
            return;
        }


        String newName = getName(name, toolPrestige, oldToolLevel + enchantLevel - oldLevel);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("toollevel", oldToolLevel + enchantLevel - oldLevel);

        nbtItem.setInteger("toolenchant", oldSuccess + enchantLevel - oldLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);
        List<String> newLoreList = oldLoreList;

        int enchantLine = 0;
        for (String str : newLoreList) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        int i = 0;
        for (String element : oldLoreList) {
            if (i < enchantLine) {
                i++;
                continue;
            }
            if (element.contains(enchantChineseName)) {
                break;
            }
            i++;
        }
        newLoreList.set(i, enchant.getRankColor() + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
//        if (enchantType==1){//普通附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.WHITE+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==2){// 优秀附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.GREEN+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==3){// 稀有附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.BLUE+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==4){// 史诗附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.YELLOW+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==5){// 传说附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.GOLD+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }
//        if (enchantType==6){// 核能附魔
//            int i = 0;
//            for (String element: oldLoreList) {
//                if(element.contains(enchantChineseName)){
//                    break;
//                }
//                i++;
//            }
//            newLoreList.set(i, ChatColor.LIGHT_PURPLE+enchantChineseName + " "+ ChatColor.AQUA + enchantLevelBig);
//        }

        //&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m=
        newLoreList.set(enchantLine + 1, ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "=" + ChatColor.STRIKETHROUGH + " " + ChatColor.WHITE + "" + ChatColor.BOLD + " " + (oldSuccess + enchantLevel - oldLevel) + " " + ChatColor.GREEN + "" + ChatColor.BOLD + "成功附魔 " + ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " " + ChatColor.STRIKETHROUGH + "=");

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        if (enchant.equals(PWEnchant.water)) {
            itemMeta.addEnchant(Enchantment.WATER_WORKER, enchantLevel, true);
        }
        nbtDoneItem.setItemMeta(itemMeta);
        player.getInventory().setItem(slot, nbtDoneItem);

    }

    public void createEnchant(Player player, String enchantEnglishName, int enchantLevel, boolean clearXP) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        if (enchantLevel > enchant.getMaxLevel()) {
            return;
        }
        ItemStack item = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldToolLevel = nbtItem.getInteger("toollevel");
        int oldSuccess = nbtItem.getInteger("toolenchant");
        //修改能量
        int oldToolXP = nbtItem.getInteger("toolxp");
        int oldToolNeedXP = nbtItem.getInteger("toolneedxp");
        if (clearXP) {
            oldToolXP = getClearedEnergy(player, oldToolXP, oldToolNeedXP, item);
        }
        int gap = 9600;
        if (item.getType().equals(Material.STONE_PICKAXE)) {
            gap = 10200;
        }
        if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            gap = 10800;
        }
        if (item.getType().equals(Material.IRON_PICKAXE)) {
            gap = 11400;
        }
        if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            gap = 12000;
        }
        int newToolNeedXP = oldToolNeedXP + enchantLevel * gap;
        updateHandLore.updateHandEnergy(player, oldToolXP, newToolNeedXP);
        int toolPrestige = nbtItem.getInteger("toolprestige");
        String name = nbtItem.getString("toolname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("enchantAmount", enchantAmount + 1);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        if (enchant.getRank() == 6) {
            nbtItem.removeKey("ae_enchantment;" + enchant.getDepend().toString());
            nbtItem.setInteger("enchantAmount", enchantAmount);
        }

        nbtItem.setInteger("toollevel", oldToolLevel + enchantLevel);
        nbtItem.setInteger("toolenchant", oldSuccess + enchantLevel);

        String newName = getName(name, toolPrestige, oldToolLevel + enchantLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);

        List<String> newLoreList = oldLoreList;
        if (newLoreList.get(newLoreList.size() - 1).contains("/spawn")) {
            newLoreList.set(newLoreList.size() - 1, " ");
        }
        int enchantLine = 0;
        for (String str : newLoreList) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        if (enchantType == 1) {//普通附魔
            newLoreList.add(enchantLine + 4 + enchantAmount, ChatColor.WHITE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 2) {//优秀附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.GREEN + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 3) {//稀有附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§9")) {
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.BLUE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 4) {//史诗附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§e")) {
                    break;
                }
                if (element.contains("§9")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.YELLOW + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 5) {//传说附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§6")) {
                    break;
                }
                if (element.contains("§e")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§9")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.GOLD + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 6) {//核能附魔
            PWEnchant depend = enchant.getDepend();

            int j = 0;
            for (String element : oldLoreList) {
                if (element.contains(depend.getChineseName())) {
                    break;
                }
                j++;
            }
            newLoreList.remove(j);
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§d")) {
                    break;
                }
                if (element.contains("§6")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§e")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§9")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.LIGHT_PURPLE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);


        }
        //&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m=
        newLoreList.set(enchantLine + 1, ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "=" + ChatColor.STRIKETHROUGH + " " + ChatColor.WHITE + "" + ChatColor.BOLD + " " + (oldSuccess + enchantLevel) + " " + ChatColor.GREEN + "" + ChatColor.BOLD + "成功附魔 " + ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " " + ChatColor.STRIKETHROUGH + "=");

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        if (enchant.equals(PWEnchant.water)) {
            itemMeta.addEnchant(Enchantment.WATER_WORKER, enchantLevel, true);
        }
        nbtDoneItem.setItemMeta(itemMeta);
        player.setItemInHand(nbtDoneItem);
    }

    public void createEnchant(Player player, int slot, String enchantEnglishName, int enchantLevel, boolean clearXP) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        if (enchantLevel > enchant.getMaxLevel()) {
            return;
        }
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldToolLevel = nbtItem.getInteger("toollevel");
        int oldSuccess = nbtItem.getInteger("toolenchant");
        //修改能量
        int oldToolXP = nbtItem.getInteger("toolxp");
        int oldToolNeedXP = nbtItem.getInteger("toolneedxp");
        if (clearXP) {
            oldToolXP = getClearedEnergy(player, oldToolXP, oldToolNeedXP, item);
        }
        int gap = 9600;
        if (item.getType().equals(Material.STONE_PICKAXE)) {
            gap = 10200;
        }
        if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            gap = 10800;
        }
        if (item.getType().equals(Material.IRON_PICKAXE)) {
            gap = 11400;
        }
        if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            gap = 12000;
        }
        int newToolNeedXP = oldToolNeedXP + enchantLevel * gap;
        updateSlotLore.updateSlotEnergy(player, oldToolXP, newToolNeedXP, slot);
        int toolPrestige = nbtItem.getInteger("toolprestige");
        String name = nbtItem.getString("toolname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("enchantAmount", enchantAmount + 1);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        if (enchant.getRank() == 6) {
            nbtItem.removeKey("ae_enchantment;" + enchant.getDepend().toString());
            nbtItem.setInteger("enchantAmount", enchantAmount);
        }
        nbtItem.setInteger("toollevel", oldToolLevel + enchantLevel);
        nbtItem.setInteger("toolenchant", oldSuccess + enchantLevel);

        String newName = getName(name, toolPrestige, oldToolLevel + enchantLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);

        List<String> newLoreList = oldLoreList;
        if (newLoreList.get(newLoreList.size() - 1).contains("/spawn")) {
            newLoreList.set(newLoreList.size() - 1, " ");
        }
        int enchantLine = 0;
        for (String str : newLoreList) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        if (enchantType == 1) {//普通附魔
            newLoreList.add(enchantLine + 4 + enchantAmount, ChatColor.WHITE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 2) {//优秀附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.GREEN + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 3) {//稀有附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§9")) {
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.BLUE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 4) {//史诗附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§e")) {
                    break;
                }
                if (element.contains("§9")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.YELLOW + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 5) {//传说附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§6")) {
                    break;
                }
                if (element.contains("§e")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§9")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.GOLD + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 6) {//核能附魔

            PWEnchant depend = enchant.getDepend();

            int j = 0;
            for (String element : oldLoreList) {
                if (element.contains(depend.getChineseName())) {
                    break;
                }
                j++;
            }
            newLoreList.remove(j);

            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantLine + 4) {
                    continue;
                }
                if (i > enchantLine + 4 + enchantAmount) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§d")) {
                    break;
                }
                if (element.contains("§6")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§e")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§9")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§a")) {
                    i = i - 1;
                    break;
                }
                if (element.contains("§f")) {
                    i = i - 1;
                    break;
                }
            }
            newLoreList.add(i, ChatColor.LIGHT_PURPLE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        //&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m=
        newLoreList.set(enchantLine + 1, ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "=" + ChatColor.STRIKETHROUGH + " " + ChatColor.WHITE + "" + ChatColor.BOLD + " " + (oldSuccess + enchantLevel) + " " + ChatColor.GREEN + "" + ChatColor.BOLD + "成功附魔 " + ChatColor.GREEN + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + " " + ChatColor.STRIKETHROUGH + "=");

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        if (enchant.equals(PWEnchant.water)) {
            itemMeta.addEnchant(Enchantment.WATER_WORKER, enchantLevel, true);
        }
        nbtDoneItem.setItemMeta(itemMeta);
        player.getInventory().setItem(slot, nbtDoneItem);
    }

    public void updateFail(Player player, boolean clearXP) {
        ItemStack item = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldToolLevel = nbtItem.getInteger("toollevel");
        int oldFail = nbtItem.getInteger("toolfail");
        //修改能量
        int oldToolXP = nbtItem.getInteger("toolxp");
        int oldToolNeedXP = nbtItem.getInteger("toolneedxp");
        if (clearXP) {
            oldToolXP = getClearedEnergy(player, oldToolXP, oldToolNeedXP, item);
        }
        int gap = 9600;
        if (item.getType().equals(Material.STONE_PICKAXE)) {
            gap = 10200;
        }
        if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            gap = 10800;
        }
        if (item.getType().equals(Material.IRON_PICKAXE)) {
            gap = 11400;
        }
        if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            gap = 12000;
        }
        int newToolNeedXP = oldToolNeedXP + gap;
        updateHandLore.updateHandEnergy(player, oldToolXP, newToolNeedXP);
        int toolPrestige = nbtItem.getInteger("toolprestige");
        String name = nbtItem.getString("toolname");

        nbtItem.setInteger("toollevel", oldToolLevel + 1);
        nbtItem.setInteger("toolfail", oldFail + 1);

        String newName = getName(name, toolPrestige, oldToolLevel + 1);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        List<String> newLoreList = oldLoreList;
        int enchantLine = 0;
        for (String str : newLoreList) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        //&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m=
        newLoreList.set(enchantLine + 2, ChatColorCast.format("&c&l&m=&m &f&l " + (oldFail + 1) + " &c&l失败附魔 &c&l&m &m="));

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        nbtDoneItem.setItemMeta(itemMeta);
        player.setItemInHand(nbtDoneItem);
    }

    public void updateFail(Player player, int slot, boolean clearXP) {
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldToolLevel = nbtItem.getInteger("toollevel");
        int oldFail = nbtItem.getInteger("toolfail");
        //修改能量
        int oldToolXP = nbtItem.getInteger("toolxp");
        int oldToolNeedXP = nbtItem.getInteger("toolneedxp");
        if (clearXP) {
            oldToolXP = getClearedEnergy(player, oldToolXP, oldToolNeedXP, item);
        }
        int gap = 9600;
        if (item.getType().equals(Material.STONE_PICKAXE)) {
            gap = 10200;
        }
        if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
            gap = 10800;
        }
        if (item.getType().equals(Material.IRON_PICKAXE)) {
            gap = 11400;
        }
        if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
            gap = 12000;
        }
        int newToolNeedXP = oldToolNeedXP + gap;
        updateSlotLore.updateSlotEnergy(player, oldToolXP, newToolNeedXP, slot);
        int toolPrestige = nbtItem.getInteger("toolprestige");
        String name = nbtItem.getString("toolname");

        nbtItem.setInteger("toollevel", oldToolLevel + 1);
        nbtItem.setInteger("toolfail", oldFail + 1);

        String newName = getName(name, toolPrestige, oldToolLevel + 1);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        List<String> newLoreList = oldLoreList;
        int enchantLine = 0;
        for (String str : newLoreList) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        //&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m=
        newLoreList.set(enchantLine + 2, ChatColorCast.format("&c&l&m=&m &f&l " + (oldFail + 1) + " &c&l失败附魔 &c&l&m &m="));

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        nbtDoneItem.setItemMeta(itemMeta);
        player.getInventory().setItem(slot, nbtDoneItem);
    }


    public void fail(Player player) {
        player.playSound(player.getEyeLocation(), Sound.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_WITHER_SPAWN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        PacketText packetText = new PacketText();
        packetText.createEnchantText(player, player.getItemInHand(), ChatColorCast.format("&c&l附魔失败!"));
    }

    public void fail(Player player, int slot) {
        player.playSound(player.getEyeLocation(), Sound.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_WITHER_SPAWN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        PacketText packetText = new PacketText();
        packetText.createEnchantText(player, player.getInventory().getItem(slot), ChatColorCast.format("&c&l附魔失败!"));
    }

    public void success(Player player, String enchantEnglishName, int enchantLevel) {
        player.playSound(player.getEyeLocation(), Sound.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.playSound(player.getEyeLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        PacketText packetText = new PacketText();
        packetText.createEnchantText(player, player.getItemInHand(), ChatColorCast.format("&d&l附魔成功 &b▸ ") + PWEnchant.valueOf(enchantEnglishName).getRankColor() + PWEnchant.valueOf(enchantEnglishName).getChineseName() + ChatColorCast.format(" &b&l") + getLevel(enchantLevel));
    }

    public void success(Player player, int slot, String enchantEnglishName, int enchantLevel) {
        player.playSound(player.getEyeLocation(), Sound.ITEM_TRIDENT_RETURN, SoundCategory.BLOCKS, 1.0f, 1.0f);
        player.playSound(player.getEyeLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        PacketText packetText = new PacketText();
        packetText.createEnchantText(player, player.getInventory().getItem(slot), ChatColorCast.format("&d&l附魔成功 &b▸ ") + PWEnchant.valueOf(enchantEnglishName).getRankColor() + PWEnchant.valueOf(enchantEnglishName).getChineseName() + ChatColorCast.format(" &b&l") + getLevel(enchantLevel));
    }

    public String getLevel(int level) {
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

    public String getName(String name, int prestige, int toolLevel) {
        String n;
        if (prestige == 0) {
            n = name + " &7▸ &e&lLv." + toolLevel;
        } else {
            String prestigeSTR = getLevel(prestige);
            n = name + " &7▸ &e&lLv." + toolLevel + " &d&l<&b&l" + prestigeSTR + "&d&l>";
        }
        return ChatColorCast.format(n);
    }

    public int getClearedEnergy(Player player, int oldXP, int oldNeedXP, ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        double remove = 0;
        if (nbtItem.hasKey("ae_enchantment;battery")) {
            remove = (double) nbtItem.getInteger("ae_enchantment;battery") / 10;
        }
        int newXP = (int) Math.round(oldXP - oldNeedXP * (1 - remove));
        if (oldXP < 0) {
            newXP = 0;
        }


        return newXP;
    }

    public boolean couldApplyNuclearSlot(Player player, int slot, String enchantEnglishName, int enchantLevel) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        PWEnchant depend = enchant.getDepend();
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey("ae_enchantment;" + enchantEnglishName)) {
            int oldLevel = nbtItem.getInteger("ae_enchantment;" + enchantEnglishName);
            if (oldLevel != enchantLevel - 1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (nbtItem.getInteger("ae_enchantment;" + depend.toString()) == depend.getMaxLevel()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
