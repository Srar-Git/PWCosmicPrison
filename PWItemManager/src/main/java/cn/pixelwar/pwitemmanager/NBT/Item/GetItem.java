package cn.pixelwar.pwitemmanager.NBT.Item;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GetItem {

    NumberFormat numberFormat = new NumberFormat();
    Random random = new Random();
    public ItemStack getItem(String name){
        PWItem pwItem = PWItem.valueOf(name);
        ItemStack item = new ItemStack(pwItem.getMaterial());

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColorCast.format(pwItem.getName()));
        itemMeta.setLore(pwItem.getLore());
        for (Enchantment enchantment : pwItem.getEnchantments()){
            itemMeta.addEnchant(enchantment, 1, true);
        }
        for (ItemFlag itemFlag : pwItem.getFlags()){
            itemMeta.addItemFlags(itemFlag);
        }
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        for (String str : pwItem.getStringNBT()){
            String[] temp = str.split("-");
            nbtItem.setString(temp[0], temp[1]);
        }
        for (String str : pwItem.getIntNBT()){
            String[] temp = str.split("-");
            nbtItem.setInteger(temp[0], Integer.valueOf(temp[1]));
        }
        if(name.contains("bag") ||name.contains("pickaxe") ){
            UUID uuid = UUID.randomUUID();
            nbtItem.setString("uuid", uuid.toString());
        }
        ItemStack finalItem = nbtItem.getItem();
        return finalItem;
    }

    public ItemStack getEnchantBook(String enchant, int level, int enchantSuccess, int levelFail){
        PWEnchant pwEnchant =  PWEnchant.valueOf(enchant);
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        int energyNeed;
        switch (pwEnchant.getRank()){
            case 1:
                energyNeed =  getRandomInt(3000, 5000)*level;
                break;
            case 2:
                energyNeed =  getRandomInt(10000, 13000)*level;
                break;
            case 3:
                energyNeed =  getRandomInt(45000, 60000)*level;
                break;
            case 4:
                energyNeed =  getRandomInt(230000, 260000)*level;
                break;
            case 5:
                energyNeed =  getRandomInt(950000, 1200000)*level;
                break;
            case 6:
                energyNeed =  getRandomInt(4800000, 6500000)*level;
                break;
            default:
                energyNeed = 999999999;
        }
        itemMeta.setDisplayName(ChatColorCast.format(pwEnchant.getRankColor()+pwEnchant.getChineseName()+" &b&l"+getLevel(level)+" &7(&f"+enchantSuccess+"%&7)"));
        List<String> lore = new ArrayList<>();
        for (String str : pwEnchant.getDescription()) {lore.add(str);}
        lore.add(" ");
        lore.add(ChatColorCast.format("&6&l| 几率"));
        lore.add(ChatColorCast.format("&b▪ &f附魔成功率: &a&l"+enchantSuccess+"%"));
        lore.add(ChatColorCast.format("&b▪ &f升级失败率: &c&l"+levelFail+"%"));
        lore.add(" ");
        lore.add(ChatColorCast.format("&6&l| 宇宙能量"));
        lore.add(ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"));
        lore.add(ChatColorCast.format("&7(&f0&7 / "+ numberFormat.getIntFormat(energyNeed) +")"));
        lore.add(" ");
        lore.add(ChatColorCast.format("&6&l| 概况"));
        lore.add(ChatColorCast.format("&b▪ &f最高等级: &b&l"+getLevel(pwEnchant.getMaxLevel())));
        lore.add(ChatColorCast.format("&b▪ &f适用对象: &e&l"+pwEnchant.getType()));
        lore.add(" ");
        lore.add(ChatColorCast.format("&c&o注意!&7&o附魔失败附魔对象会消失!"));
        lore.add(ChatColorCast.format("&c&o注意!&7&o升级失败附魔书会消失,获得附魔券!"));
        lore.add(" ");
        lore.add(ChatColorCast.format("&7拖拽到你要使用的物品上来附魔"));
        itemMeta.setLore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("bookxp", 0);
        nbtItem.setInteger("bookneedxp",  energyNeed);
        nbtItem.setInteger("booklevel",  level);
        nbtItem.setInteger("bookmaxlevel", pwEnchant.getMaxLevel());
        nbtItem.setInteger("booktier", pwEnchant.getRank());
        nbtItem.setInteger("levelfail",  levelFail);
        nbtItem.setInteger("enchantsuccess",  enchantSuccess);
        nbtItem.setString("enchant", enchant);
        nbtItem.setString("enchantfor", pwEnchant.getType());
        item = nbtItem.getItem();
        return item;
    }

    public ItemStack getEnchantPage(int rank, int num){
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = item.getItemMeta();
        String name = "";
        switch (rank){
            case 1:
                name = ChatColorCast.format("&f&l普通附魔券 &7("+num+"%)");
                break;
            case 2:
                name = ChatColorCast.format("&a&l优秀附魔券 &7("+num+"%)");
                break;
            case 3:
                name = ChatColorCast.format("&9&l稀有附魔券 &7("+num+"%)");
                break;
            case 4:
                name = ChatColorCast.format("&e&l史诗附魔券 &7("+num+"%)");
                break;
            case 5:
                name = ChatColorCast.format("&6&l传说附魔券 &7("+num+"%)");
                break;
            case 6:
                name = ChatColorCast.format("&d&l核能附魔券 &7("+num+"%)");
                break;
            default:
                break;
        }
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColorCast.format("&7将此物品放到对应等级的附魔书"));
        lore.add(ChatColorCast.format("&7上后能够增加附魔成功率，同时"));
        lore.add(ChatColorCast.format("&7降低附魔书在附魔池升级失败率"));
        lore.add(ChatColorCast.format(""));
        lore.add(ChatColorCast.format("&c&l注意!&7核能附魔券可以用于所有"));
        lore.add(ChatColorCast.format("&7等级的附魔书"));
        lore.add(ChatColorCast.format(""));
        lore.add(ChatColorCast.format("&b▪ &f成功率: &a&l+"+num+"%"));
        lore.add(ChatColorCast.format("&b▪ &f失败率: &c&l-"+num+"%"));
        lore.add(ChatColorCast.format(""));
        lore.add(ChatColorCast.format("&b&l拖拽&7到物品上使用"));
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("num",num);
        nbtItem.setInteger("rank",rank);
        return nbtItem.getItem();
    }
    public ItemStack getChargeGem(int num){
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta itemMeta = item.getItemMeta();
        String name = ChatColorCast.format("&6&l充能宝石 &6☢ &7(&f"+num+"%&7)");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColorCast.format("&7将此物品附至镐上后会加速镐的"));
        lore.add(ChatColorCast.format("&7宇宙能量充能速度"));
        lore.add(ChatColorCast.format(" "));
        lore.add(ChatColorCast.format("&b&l拖拽&7到物品上来使用"));
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("num",num);
        nbtItem.setInteger("gemcharge",1);
        return nbtItem.getItem();
    }
    public ItemStack getPrestigeGem(int num, int type){
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta itemMeta = item.getItemMeta();
        String name = "";
        List<String> lore = new ArrayList<>();
        if (type==0) {
            name = ChatColorCast.format("&e&l荣誉宝石 &e✪ &d&l<&b&l"+getLevel(num)+"&d&l>");
            lore.add("");
            lore.add(ChatColorCast.format("&7将此物品附至镐上后你可以直接升级"));
            lore.add(ChatColorCast.format("&7镐的荣誉等级，无需达到要求!"));
            lore.add(ChatColorCast.format(" "));
            lore.add(ChatColorCast.format("&c&l注意!&7你只能使用这个宝石升级"));
            lore.add(ChatColorCast.format("&7至荣誉等级 &d&l<&b&l" + getLevel(num) + "&d&l> 或者更低!"));
            lore.add(ChatColorCast.format(" "));
            lore.add(ChatColorCast.format("&b&l拖拽&7到物品上来使用"));
        }if (type==1){
            name = ChatColorCast.format("&d&l核能&e&l荣誉宝石 &d✪ &d&l<&b&l"+getLevel(num)+"&d&l>");
            lore.add("");
            lore.add(ChatColorCast.format("&7将此物品附至镐上后你可以直接升级"));
            lore.add(ChatColorCast.format("&7镐的荣誉等级，无需达到要求!并且"));
            lore.add(ChatColorCast.format("&7升级后你的镐属性&n均不会消失!"));
            lore.add(ChatColorCast.format(" "));
            lore.add(ChatColorCast.format("&c&l注意!&7你只能使用这个宝石升级"));
            lore.add(ChatColorCast.format("&7至荣誉等级 &d&l<&b&l" + getLevel(num) + "&d&l> 或者更低!"));
            lore.add(ChatColorCast.format(" "));
            lore.add(ChatColorCast.format("&b&l拖拽&7到物品上来使用"));
        }
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("num",num);
        nbtItem.setString("level", getLevel(num));
        if (type==1) {
            nbtItem.setInteger("gemprestige1", 1);

        }
        if (type==0) {
            nbtItem.setInteger("gemprestige0", 1);
        }
        return nbtItem.getItem();
    }



    public ItemStack getEnchantDust(int rank, int num){
        ItemStack item = new ItemStack(Material.SUGAR);
        ItemMeta itemMeta = item.getItemMeta();
        String name = "";
        switch (rank){
            case 1:
                name = ChatColorCast.format("&f&l普通附魔粉 &7("+num+"%)");
                break;
            case 2:
                name = ChatColorCast.format("&a&l优秀附魔粉 &7("+num+"%)");
                break;
            case 3:
                name = ChatColorCast.format("&9&l稀有附魔粉 &7("+num+"%)");
                break;
            case 4:
                name = ChatColorCast.format("&e&l史诗附魔粉 &7("+num+"%)");
                break;
            case 5:
                name = ChatColorCast.format("&6&l传说附魔粉 &7("+num+"%)");
                break;
            case 6:
                name = ChatColorCast.format("&d&l核能附魔粉 &7("+num+"%)");
                break;
            default:
                break;
        }
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColorCast.format("&7在附魔池中进行附魔时将此物品放在背包内，打开"));
        lore.add(ChatColorCast.format("&7附魔页面点击此物品，会提升你的附魔成功率!"));
        lore.add(ChatColorCast.format(""));
        lore.add(ChatColorCast.format("&c&l注意!&7该物品使用后不能再从附魔池页面中拿出!"));
        lore.add(ChatColorCast.format("&c&l注意!&7核能附魔粉能够提升所有类别的附魔成功率!"));
        lore.add(ChatColorCast.format(""));
        lore.add(ChatColorCast.format("&b▪ &f成功率: &a&l+"+num+"%"));
        lore.add(ChatColorCast.format(""));
        lore.add(ChatColorCast.format("&b&l点击&7使用"));
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemMeta.addEnchant(Enchantment.DURABILITY,1,true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setInteger("num",num);
        nbtItem.setInteger("rank",rank);
        return nbtItem.getItem();
    }

    public String getLevel(int level){
        switch (level){
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

    public int getRandomInt(int min, int max){
        return random.nextInt(max-min+1)+min;
    }
}
