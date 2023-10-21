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

public class ApplyEnchantToSwordAndAxe {

    NumberFormat numberFormat = new NumberFormat();
    Random random = new Random();
    UpdateHandLore updateHandLore = new UpdateHandLore();
    UpdateSlotLore updateSlotLore = new UpdateSlotLore();

    public void applyHandItemEnchantToSwordAndAxe(Player player, String enchantEnglishName, int enchantLevel, int success) {

        ItemStack item = player.getItemInHand();
        if (!(item.getType().equals(Material.WOODEN_SWORD) || item.getType().equals(Material.WOODEN_AXE) ||
                item.getType().equals(Material.STONE_SWORD) || item.getType().equals(Material.STONE_AXE) ||
                item.getType().equals(Material.IRON_SWORD) || item.getType().equals(Material.IRON_AXE) ||
                item.getType().equals(Material.GOLDEN_SWORD) || item.getType().equals(Material.GOLDEN_AXE) ||
                item.getType().equals(Material.DIAMOND_SWORD) || item.getType().equals(Material.DIAMOND_AXE) ||
                item.getType().equals(Material.NETHERITE_SWORD) || item.getType().equals(Material.NETHERITE_AXE))
        ) {
            return;
        }

        int randomNum = random.nextInt(100);
        if (randomNum <= success) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey("ae_enchantment;" + enchantEnglishName)) {
                updateEnchant(player, enchantEnglishName, enchantLevel);
            } else {
                createEnchant(player, enchantEnglishName, enchantLevel);
            }
            success(player, enchantEnglishName, enchantLevel);
        } else {
            fail(player);
            updateFail(player);
        }


    }

    public void applySlotItemEnchantToSwordAndAxe(Player player, int slot, String enchantEnglishName, int enchantLevel, int success) {

        ItemStack item = player.getInventory().getItem(slot);
        if (!(item.getType().equals(Material.WOODEN_SWORD) || item.getType().equals(Material.WOODEN_AXE) ||
                item.getType().equals(Material.STONE_SWORD) || item.getType().equals(Material.STONE_AXE) ||
                item.getType().equals(Material.IRON_SWORD) || item.getType().equals(Material.IRON_AXE) ||
                item.getType().equals(Material.GOLDEN_SWORD) || item.getType().equals(Material.GOLDEN_AXE) ||
                item.getType().equals(Material.DIAMOND_SWORD) || item.getType().equals(Material.DIAMOND_AXE) ||
                item.getType().equals(Material.NETHERITE_SWORD) || item.getType().equals(Material.NETHERITE_AXE))
        ) {
            return;
        }

        int randomNum = random.nextInt(100);
        if (randomNum <= success) {
            NBTItem nbtItem = new NBTItem(item);
            if (nbtItem.hasKey("ae_enchantment;" + enchantEnglishName)) {
                updateEnchant(player, slot, enchantEnglishName, enchantLevel);
            } else {
                createEnchant(player, slot, enchantEnglishName, enchantLevel);
            }
            success(player, slot, enchantEnglishName, enchantLevel);
        } else {
            fail(player, slot);
            updateFail(player, slot);
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

    public void updateEnchant(Player player, String enchantEnglishName, int enchantLevel) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        ItemStack item = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
        int oldLevel = nbtItem.getInteger("ae_enchantment;" + enchantEnglishName);
        int oldWeaponLevel = nbtItem.getInteger("weaponlevel");

        String name = nbtItem.getString("weaponname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        if (enchantLevel <= oldLevel || enchantLevel > enchant.getMaxLevel()) {
            return;
        }


        String newName = getName(name, oldWeaponLevel + enchantLevel - oldLevel);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("weaponlevel", oldWeaponLevel + enchantLevel - oldLevel);


        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);
        List<String> newLoreList = oldLoreList;


        if (enchantType == 1) {//普通附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.WHITE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 2) {// 优秀附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.GREEN + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 3) {// 稀有附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.BLUE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 4) {// 史诗附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.YELLOW + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 5) {// 传说附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.GOLD + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 6) {// 核能附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.LIGHT_PURPLE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        if (enchant.equals(PWEnchant.knockback)) {
            itemMeta.addEnchant(Enchantment.KNOCKBACK, enchantLevel, true);
        }
        nbtDoneItem.setItemMeta(itemMeta);
        player.setItemInHand(nbtDoneItem);

    }

    public void updateEnchant(Player player, int slot, String enchantEnglishName, int enchantLevel) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
        int oldLevel = nbtItem.getInteger("ae_enchantment;" + enchantEnglishName);
        int oldWeaponLevel = nbtItem.getInteger("weaponlevel");
        int oldSuccess = nbtItem.getInteger("weaponenchant");


        String name = nbtItem.getString("weaponname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        if (enchantLevel <= oldLevel || enchantLevel > enchant.getMaxLevel()) {
            return;
        }


        String newName = getName(name, oldWeaponLevel + enchantLevel - oldLevel);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("weaponlevel", oldWeaponLevel + enchantLevel - oldLevel);

        nbtItem.setInteger("weaponenchant", oldSuccess + enchantLevel - oldLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);
        List<String> newLoreList = oldLoreList;


        if (enchantType == 1) {//普通附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.WHITE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 2) {// 优秀附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.GREEN + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 3) {// 稀有附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.BLUE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 4) {// 史诗附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.YELLOW + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 5) {// 传说附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.GOLD + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 6) {// 核能附魔
            int i = 0;
            for (String element : oldLoreList) {
                if (element.contains(enchantChineseName)) {
                    break;
                }
                i++;
            }
            newLoreList.set(i, ChatColor.LIGHT_PURPLE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        if (enchant.equals(PWEnchant.knockback)) {
            itemMeta.addEnchant(Enchantment.KNOCKBACK, enchantLevel, true);
        }
        nbtDoneItem.setItemMeta(itemMeta);
        player.getInventory().setItem(slot, nbtDoneItem);

    }

    public void createEnchant(Player player, String enchantEnglishName, int enchantLevel) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        if (enchantLevel > enchant.getMaxLevel()) {
            return;
        }
        ItemStack item = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldWeaponLevel = nbtItem.getInteger("weaponlevel");


        String name = nbtItem.getString("weaponname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("enchantAmount", enchantAmount + 1);
        nbtItem.setInteger("weaponlevel", oldWeaponLevel + enchantLevel);


        String newName = getName(name, oldWeaponLevel + enchantLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);

        List<String> newLoreList = oldLoreList;
        int enchantBeginLine = 0;
        for (String element : oldLoreList) {
            if (element.contains("| 附魔信息")) {
                enchantBeginLine = enchantBeginLine + 2;
                break;
            }
            enchantBeginLine++;
        }
        if (enchantType == 1) {//普通附魔
            newLoreList.add(enchantBeginLine + enchantAmount, ChatColor.WHITE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 2) {//优秀附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        if (enchant.equals(PWEnchant.knockback)) {
            itemMeta.addEnchant(Enchantment.KNOCKBACK, enchantLevel, true);
        }
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        nbtDoneItem.setItemMeta(itemMeta);
        player.setItemInHand(nbtDoneItem);
    }

    public void createEnchant(Player player, int slot, String enchantEnglishName, int enchantLevel) {
        PWEnchant enchant = PWEnchant.valueOf(enchantEnglishName);
        String enchantChineseName = enchant.getChineseName();
        int enchantType = enchant.getRank();
        if (enchantLevel > enchant.getMaxLevel()) {
            return;
        }
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldWeaponLevel = nbtItem.getInteger("weaponlevel");

        String name = nbtItem.getString("weaponname");
        int enchantAmount = nbtItem.getInteger("enchantAmount");

        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("enchantAmount", enchantAmount + 1);
        nbtItem.setInteger("ae_enchantment;" + enchantEnglishName, enchantLevel);
        nbtItem.setInteger("weaponlevel", oldWeaponLevel + enchantLevel);


        String newName = getName(name, oldWeaponLevel + enchantLevel);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        List<String> oldLoreList = itemMeta.getLore();
        String enchantLevelBig = getLevel(enchantLevel);

        List<String> newLoreList = oldLoreList;
        int enchantBeginLine = 0;
        for (String element : oldLoreList) {
            if (element.contains("| 附魔信息")) {
                enchantBeginLine = enchantBeginLine + 2;
                break;
            }
            enchantBeginLine++;
        }

        if (enchantType == 1) {//普通附魔
            newLoreList.add(enchantBeginLine + enchantAmount, ChatColor.WHITE + enchantChineseName + " " + ChatColor.AQUA + enchantLevelBig);
        }
        if (enchantType == 2) {//优秀附魔
            int i = 0;
            for (String element : oldLoreList) {
                i++;
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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
                if (i < enchantBeginLine) {
                    continue;
                }
                if (i > enchantBeginLine + enchantAmount) {
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

        itemMeta.setLore(newLoreList);
        itemMeta.setDisplayName(newName);
        if (enchant.equals(PWEnchant.knockback)) {
            itemMeta.addEnchant(Enchantment.KNOCKBACK, enchantLevel, true);
        }
        itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        nbtDoneItem.setItemMeta(itemMeta);
        player.getInventory().setItem(slot, nbtDoneItem);
    }

    public void updateFail(Player player) {
        ItemStack item = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldWeaponLevel = nbtItem.getInteger("weaponlevel");

        String name = nbtItem.getString("weaponname");

        nbtItem.setInteger("weaponlevel", oldWeaponLevel + 1);

        String newName = getName(name, oldWeaponLevel + 1);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        itemMeta.setDisplayName(newName);
        nbtDoneItem.setItemMeta(itemMeta);
        player.setItemInHand(nbtDoneItem);
    }

    public void updateFail(Player player, int slot) {
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
//        int oldLevel = nbtItem.getInteger("ae_enchantment;"+enchantEnglishName);
        int oldWeaponLevel = nbtItem.getInteger("weaponlevel");

        String name = nbtItem.getString("weaponname");

        nbtItem.setInteger("weaponlevel", oldWeaponLevel + 1);

        String newName = getName(name, oldWeaponLevel + 1);

        ItemStack nbtDoneItem = nbtItem.getItem();
        ItemMeta itemMeta = nbtDoneItem.getItemMeta();
        itemMeta.setDisplayName(newName);
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

    public String getName(String name, int weaponLevel) {
        String n = name + " &7▸ &e&lLv." + weaponLevel;
        return ChatColorCast.format(n);
    }
}
