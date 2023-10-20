package cn.pixelwar.pwitemmanager.NBT;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.NBT.Item.SellItem.OrePrice;
import cn.pixelwar.pwitemmanager.PWItemManager;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class UpdateSlotLore {

    NumberFormat numberFormat = new NumberFormat();
    public void updateSlotEnergy(Player player,int xp, int needXp , int slot){
        new BukkitRunnable() {
            @Override
            public void run() {
                Material material = player.getInventory().getItem(slot).getType();
                if (material.toString().contains("PICKAXE")) {
                    NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                    nbti.setInteger("toolxp", xp);
                    nbti.setInteger("toolneedxp", needXp);
                    ItemStack finalItem = nbti.getItem();
                    int percent = (int) Math.round((double) xp / needXp * 100);
                    int blocks = percent / 2;
                    String block = "";

                    for (int i = 0; i < blocks; i++) {
                        block = block.concat("&b┃");
                        if (i >= 49) {
                            break;
                        }
                    }
                    for (int i = 0; i < 50 - blocks; i++) {
                        block = block.concat("&c┃");
                    }
                    ItemMeta itemMeta = finalItem.getItemMeta();
                    List<String> lore = itemMeta.getLore();
                    int energyLine = 0;
                    for (String str : lore){
                        if (str.contains("| 宇宙能量")){
                            break;
                        }energyLine++;
                    }
                    lore.set(energyLine+1, ChatColorCast.format(block + " &f&l" + percent + "%"));
                    lore.set(energyLine+2, ChatColorCast.format("&7(&f" + numberFormat.getIntFormat(xp) + " &7/ " + numberFormat.getIntFormat(needXp) + ")"));
                    itemMeta.setLore(lore);
                    finalItem.setItemMeta(itemMeta);
                    player.getInventory().setItem(slot, finalItem);
                }
                if (material.equals(Material.ENCHANTED_BOOK)){
                    NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                    PWEnchant enchant = PWEnchant.valueOf(nbti.getString("enchant"));
                    nbti.setInteger("bookxp", xp);
                    nbti.setInteger("bookneedxp", needXp);
                    ItemStack finalItem = nbti.getItem();
                    int percent = (int) Math.round((double) xp / needXp * 100);
                    int blocks = percent / 2;
                    String block = "";

                    for (int i = 0; i < blocks; i++) {
                        block = block.concat("&b┃");
                        if (i >= 49) {
                            break;
                        }
                    }
                    for (int i = 0; i < 50 - blocks; i++) {
                        block = block.concat("&c┃");
                    }
                    ItemMeta itemMeta = finalItem.getItemMeta();
                    List<String> lore = itemMeta.getLore();
                    int energyLine = 0;
                    for (String str : lore){
                        if (str.contains("| 宇宙能量")){
                            break;
                        }energyLine++;
                    }
                    lore.set(energyLine+1, ChatColorCast.format(block + " &f&l" + percent + "%"));
                    lore.set(energyLine+2, ChatColorCast.format("&7(&f" + numberFormat.getIntFormat(xp) + " &7/ " + numberFormat.getIntFormat(needXp) + ")"));
                    itemMeta.setLore(lore);
                    finalItem.setItemMeta(itemMeta);
                    player.getInventory().setItem(slot, finalItem);

                }
                if (
                        material.equals(Material.COAL) || material.equals(Material.COAL_ORE) ||
                                material.equals(Material.IRON_INGOT) || material.equals(Material.IRON_ORE) ||
                                material.equals(Material.LAPIS_LAZULI) || material.equals(Material.LAPIS_ORE) ||
                                material.equals(Material.REDSTONE) || material.equals(Material.REDSTONE_ORE) ||
                                material.equals(Material.GOLD_INGOT) || material.equals(Material.GOLD_ORE) ||
                                material.equals(Material.DIAMOND) || material.equals(Material.DIAMOND_ORE) ||
                                material.equals(Material.EMERALD) || material.equals(Material.EMERALD_ORE)
                ) {
                    NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                    nbti.setInteger("bagxp", xp);
                    nbti.setInteger("bagneedxp", needXp);
                    ItemStack finalItem = nbti.getItem();
                    int percent = (int) Math.round((double) xp / needXp * 100);
                    int blocks = percent / 2;
                    String block = "";

                    for (int i = 0; i < blocks; i++) {
                        block = block.concat("&b┃");
                        if (i >= 49) {
                            break;
                        }
                    }
                    for (int i = 0; i < 50 - blocks; i++) {
                        block = block.concat("&c┃");
                    }
                    ItemMeta itemMeta = finalItem.getItemMeta();
                    List<String> lore = itemMeta.getLore();
                    int energyLine = 0;
                    for (String str : lore){
                        if (str.contains("| 宇宙能量")){
                            break;
                        }energyLine++;
                    }
                    lore.set(energyLine+1, ChatColorCast.format(block + " &f&l" + percent + "%"));
                    lore.set(energyLine+2, ChatColorCast.format("&7(&f" + numberFormat.getIntFormat(xp) + " &7/ " + numberFormat.getIntFormat(needXp) + ")"));
                    itemMeta.setLore(lore);
                    finalItem.setItemMeta(itemMeta);
                    player.getInventory().setItem(slot, finalItem);
                }
            }
        }.runTask(PWItemManager.getPlugin());
    }

    public void addItemEnergy(Player player,int add, ItemStack itemStack) {
        NBTItem nbti = new NBTItem(itemStack);
        String uuid = nbti.getString("uuid");
        for (int i = 0; i <= 36; i++) {
            if (player.getInventory().getItem(i)==null){
                continue;
            }
            ItemStack checkItem = player.getInventory().getItem(i);
            if (checkItem.getType().equals(itemStack.getType())) {
                NBTItem nbt = new NBTItem(checkItem);
                String checkedUUID = nbt.getString("uuid");
                if (checkedUUID.equals(uuid)) {
                    if (checkItem.getType().toString().contains("PICKAXE")) {
                        int xp = nbt.getInteger("toolxp");
                        int needxp = nbt.getInteger("toolneedxp");
                        updateSlotEnergy(player, xp + add, needxp, i);
                    }
                    return;
                }
            }
        }
    }
    public void addPickaxeDig(Player player,int add, ItemStack itemStack) {
        NBTItem nbti = new NBTItem(itemStack);
        String uuid = nbti.getString("uuid");
        for (int i = 0; i <= 36; i++) {
            if (player.getInventory().getItem(i)==null){
                continue;
            }
            ItemStack checkItem = player.getInventory().getItem(i);
            if (checkItem.getType().equals(itemStack.getType())) {
                NBTItem nbt = new NBTItem(checkItem);
                String checkedUUID = nbt.getString("uuid");
                if (checkedUUID.equals(uuid)) {
                        int dig = nbt.getInteger("tooldig");
                        nbt.setInteger("tooldig", dig+add);
                        ItemStack nbtDone = nbt.getItem();
                        ItemMeta itemMeta = nbtDone.getItemMeta();
                        List<String> lore = itemMeta.getLore();
                        int digLine = 0;
                        for (String str : lore){
                            if (str.contains("已挖掘:")){
                                break;
                            }digLine++;
                        }
                        lore.set(digLine, ChatColorCast.format("&b▪ &f已挖掘: &e&L"+numberFormat.getIntFormat(dig+add)));
                        itemMeta.setLore(lore);
                        nbtDone.setItemMeta(itemMeta);
                        player.getInventory().setItem(i, nbtDone);
                    return;
                }
            }
        }
    }

    public void updateBookNum(Player player, int num, int slot){
        NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
        PWEnchant enchant = PWEnchant.valueOf(nbti.getString("enchant"));
        int s = nbti.getInteger("enchantsuccess")+num;
        int f = nbti.getInteger("levelfail")-num;
        if (s>100){s=100;}
        if (s<0){s=0;}
        nbti.setInteger("enchantsuccess", s);
        nbti.setInteger("levelfail", f);
        ItemStack finalItem = nbti.getItem();
        ItemMeta itemMeta = finalItem.getItemMeta();
        List<String> lore = itemMeta.getLore();
        int infoLine = 0;
        for (String str : lore){
            if (str.contains("| 几率")){
                break;
            }infoLine++;
        }
        lore.set(infoLine+1, ChatColorCast.format("&b▪ &f附魔成功率: &a&l"+s+"%"));
        lore.set(infoLine+2, ChatColorCast.format("&b▪ &f升级失败率: &c&l"+f+ "%"));
        itemMeta.setLore(lore);
        finalItem.setItemMeta(itemMeta);
        player.getInventory().setItem(slot, finalItem);
    }
    public void addSlotPickaxeGem(Player player, String gem, int slot){
        new BukkitRunnable() {
            @Override
            public void run() {
                NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                String gemStr = "";
                int gems = 0;
                if (gem.contains("gemenchant")){
                    nbti.setInteger("gemenchant",1);
                }
                if (gem.contains("gemprestige0")){
                    nbti.setInteger("gemprestige0",1);
                }
                if (gem.contains("gemprestige1")){
                    nbti.setInteger("gemprestige1",1);
                }
                if (gem.contains("gemdeath")){
                    nbti.setInteger("gemdeath",1);
                }
                if (gem.contains("gemcharge")){
                    nbti.setInteger("gemcharge", nbti.getInteger("gemcharge")+1);
                }
                if (nbti.getInteger("gemenchant")==1){
                    gemStr = gemStr.concat(" &a❃");
                    gems++;
                }
                if (nbti.getInteger("gemcharge")>=1){
                    for (int i=1;i<=nbti.getInteger("gemcharge");i++){
                        gemStr = gemStr.concat(" &6☢");
                        gems++;
                    }
                }
                if (nbti.getInteger("gemdeath")==1){
                    gemStr = gemStr.concat(" &b☠");
                    gems++;
                }
                if (nbti.getInteger("gemprestige1")==1){
                    gemStr = gemStr.concat(" &d✪");
                    gems++;
                }
                if (nbti.getInteger("gemprestige0")==1){
                    gemStr = gemStr.concat(" &e✪");
                    gems++;
                }
                int maxgem = nbti.getInteger("maxgem");
                nbti.setInteger("gem", gems);
                ItemStack finalItem = nbti.getItem();
                ItemMeta itemMeta = finalItem.getItemMeta();
                List<String> lore = itemMeta.getLore();
                int infoLine = 0;
                for (String str : lore){
                    if (str.contains("| 工具信息")){
                        break;
                    }infoLine++;
                }
                lore.set(infoLine+3, ChatColorCast.format("&b▪ &f宝石&7("+gems+"/"+maxgem+"):"+gemStr));
                itemMeta.setLore(lore);
                finalItem.setItemMeta(itemMeta);
                player.getInventory().setItem(slot, finalItem);
            }
        }.runTask(PWItemManager.getPlugin());
    }
    public void removeSlotPickaxeGem(Player player, String gem, int slot){
        new BukkitRunnable() {
            @Override
            public void run() {
                NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                String gemStr = "";
                int gems = 0;
                if (gem.contains("gemenchant")){
                    nbti.removeKey("gemenchant");
                }
                if (gem.contains("gemdeath")){
                    nbti.removeKey("gemdeath");
                }
                if (gem.contains("gemprestige0")){
                    nbti.removeKey("gemprestige0");
                }
                if (gem.contains("gemprestige1")){
                    nbti.removeKey("gemprestige1");
                }
                if (nbti.getInteger("gemenchant")==1){
                    gemStr = gemStr.concat(" &a❃");
                    gems++;
                }
                if (nbti.getInteger("gemcharge")>=1){
                    for (int i=1;i<=nbti.getInteger("gemcharge");i++){
                        gemStr = gemStr.concat(" &6☢");
                        gems++;
                    }
                }
                if (nbti.getInteger("gemdeath")==1){
                    gemStr = gemStr.concat(" &b☠");
                    gems++;
                }
                if (nbti.getInteger("gemprestige1")==1){
                    gemStr = gemStr.concat(" &d✪");
                    gems++;
                }
                if (nbti.getInteger("gemprestige0")==1){
                    gemStr = gemStr.concat(" &e✪");
                    gems++;
                }
                if (gemStr.equals("")){
                    gemStr = " &c&l✖";
                }
                int maxgem = nbti.getInteger("maxgem");
                nbti.setInteger("gem", gems);
                ItemStack finalItem = nbti.getItem();
                ItemMeta itemMeta = finalItem.getItemMeta();
                List<String> lore = itemMeta.getLore();
                int infoLine = 0;
                for (String str : lore){
                    if (str.contains("| 工具信息")){
                        break;
                    }infoLine++;
                }
                lore.set(infoLine+3, ChatColorCast.format("&b▪ &f宝石&7("+gems+"/"+maxgem+"):"+gemStr));
                itemMeta.setLore(lore);
                finalItem.setItemMeta(itemMeta);
                player.getInventory().setItem(slot, finalItem);
            }
        }.runTask(PWItemManager.getPlugin());
    }
    public void addSlotBagGem(Player player, String gem, int slot){
        new BukkitRunnable() {
            @Override
            public void run() {
                NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                String gemStr = "";
                int gems = 0;
                if (gem.contains("gemenchant")){
                    nbti.setInteger("gemenchant",1);
                }
                if (gem.contains("gemdeath")){
                    nbti.setInteger("gemdeath",1);
                }
                if (nbti.getInteger("gemenchant")==1){
                    gemStr = gemStr.concat(" &a❃");
                    gems++;
                }
                if (nbti.getInteger("gemdeath")==1){
                    gemStr = gemStr.concat(" &b☠");
                    gems++;
                }
                int maxgem = nbti.getInteger("maxgem");
                nbti.setInteger("gem", gems);
                ItemStack finalItem = nbti.getItem();
                ItemMeta itemMeta = finalItem.getItemMeta();
                List<String> lore = itemMeta.getLore();
                lore.set(6, ChatColorCast.format("&b▪ &f宝石&7("+gems+"/"+maxgem+"):"+gemStr));
                itemMeta.setLore(lore);
                finalItem.setItemMeta(itemMeta);
                player.getInventory().setItem(slot, finalItem);
            }
        }.runTask(PWItemManager.getPlugin());
    }
    public void removeSlotBagGem(Player player, String gem, int slot){
        new BukkitRunnable() {
            @Override
            public void run() {
                NBTItem nbti = new NBTItem(player.getInventory().getItem(slot));
                String gemStr = "";
                int gems = 0;
                if (gem.contains("gemenchant")){
                    nbti.setInteger("gemenchant",0);
                }
                if (gem.contains("gemdeath")){
                    nbti.setInteger("gemdeath",0);
                }
                if (nbti.getInteger("gemenchant")==1){
                    gemStr = gemStr.concat(" &a❃");
                    gems++;
                }
                if (nbti.getInteger("gemdeath")==1){
                    gemStr = gemStr.concat(" &b☠");
                    gems++;
                }
                if (gemStr.equals("")){
                    gemStr = " &c&l✖";
                }
                int maxgem = nbti.getInteger("maxgem");
                nbti.setInteger("gem", gems);
                ItemStack finalItem = nbti.getItem();
                ItemMeta itemMeta = finalItem.getItemMeta();
                List<String> lore = itemMeta.getLore();
                lore.set(6, ChatColorCast.format("&b▪ &f宝石&7("+gems+"/"+maxgem+"):"+gemStr));
                itemMeta.setLore(lore);
                finalItem.setItemMeta(itemMeta);
                player.getInventory().setItem(slot, finalItem);
            }
        }.runTask(PWItemManager.getPlugin());
    }

    public void setBagSize(Player player,int size, int slot) {
        ItemStack item = player.getInventory().getItem(slot);
        NBTItem nbtItem = new NBTItem(item);
        if (nbtItem.hasKey("size")) {
            //取出size内的矿物
            String bagType = nbtItem.getString("materialname");
            int maxSize = nbtItem.getInteger("maxsize");
            if (size>maxSize){
                size = maxSize;
            }
            nbtItem.setInteger("size", size);
            item = nbtItem.getItem();
            ItemMeta itemMeta = item.getItemMeta();
            List<String> lore = itemMeta.getLore();
            int info = 0;
            for (String s : lore) {
                if (s.contains("| 存储箱信息")) {
                    break;
                }
                info++;
            }
            lore.set(info + 1, ChatColorCast.format("&b▪ &f存储" + bagType + ": &b"+  numberFormat.getIntFormat(size)  +"/"+numberFormat.getIntFormat(maxSize)));
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            player.getInventory().setItem(slot,item);
        }
    }

}




