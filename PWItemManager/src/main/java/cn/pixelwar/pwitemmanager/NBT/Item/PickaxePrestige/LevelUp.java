package cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige;

import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class LevelUp {


    public ItemStack getLeveledItem(ItemStack originItem, Engine engine) {
        NBTItem nbtItem = new NBTItem(originItem);
        int oldPrestige = nbtItem.getInteger("toolprestige");
        int oldMaxLevel = nbtItem.getInteger("toolmaxlevel");
        String name = nbtItem.getString("toolname");
        for (String key : nbtItem.getKeys()) {
            if (key.contains("ae_enchantment;")) {
                nbtItem.removeKey(key);
            }
        }
        nbtItem.setInteger("tooldig", 0);
        nbtItem.setInteger("toollevel", 0);
        nbtItem.setInteger("toolenchant", 0);
        nbtItem.setInteger("enchantAmount", 0);
        nbtItem.setInteger("toolfail", 0);
        nbtItem.setInteger("toolprestige", oldPrestige + 1);
        nbtItem.setInteger("toolmaxlevel", oldMaxLevel + 15);

        boolean isFree = false;
        if (nbtItem.hasKey("freeprestige")) {
            nbtItem.removeKey("freeprestige");
            isFree = true;
        }


        String engineLore = "";
        int energybooster = nbtItem.getInteger("energybooster");
        int maxgem = nbtItem.getInteger("maxgem");
        if (engine.equals(Engine.CUBE)) {
            double num = (double) NumberFormat.getRandomInt(4, 8) / 10;
            nbtItem.setDouble("engineCUBE", num);
            engineLore = ChatColorCast.format("&b❒ 魔方引擎: &7+" + num + "%几率获得银河魔方");
        }
        if (engine.equals(Engine.EFFICIENCY)) {
            int num = NumberFormat.getRandomInt(30, 50);
            nbtItem.setInteger("engineEFFICIENCY", num);
            engineLore = ChatColorCast.format("&b&l⸕ &b效率引擎: &7+" + num + "%挖掘速度");
        }
        if (engine.equals(Engine.ENERGY)) {
            int num = NumberFormat.getRandomInt(70, 150);
            nbtItem.setInteger("engineENERGY", num);
            engineLore = ChatColorCast.format("&b❁ 能量引擎: &7+" + num + "%能量收集速度");
            energybooster = energybooster + num;
            nbtItem.setInteger("energybooster", energybooster);
        }
        if (engine.equals(Engine.FUEL)) {
            int num = NumberFormat.getRandomInt(10, 30);
            nbtItem.setInteger("engineFUEL", num);
            engineLore = ChatColorCast.format("&b✧ 能源引擎: &7+" + num + "%几率发现熔炉燃料");
        }
        if (engine.equals(Engine.XP)) {
            int num = NumberFormat.getRandomInt(15, 25);
            nbtItem.setInteger("engineXP", num);
            engineLore = ChatColorCast.format("&b❈ 经验引擎: &7+" + num + "%经验");
        }
        if (engine.equals(Engine.GEM)) {
            int num = NumberFormat.getRandomInt(3, 6);
            nbtItem.setInteger("engineGEM", num);
            engineLore = ChatColorCast.format("&b✯ 宝石引擎: &7+" + num + "个宝石孔");
            maxgem = maxgem + num;
            nbtItem.setInteger("maxgem", maxgem);
        }
        if (engine.equals(Engine.SHARD)) {
            double num = (double) NumberFormat.getRandomInt(15, 20) / 10;
            nbtItem.setDouble("engineSHARD", num);
            engineLore = ChatColorCast.format("&b᠅ 碎片引擎: &7" + num + "倍碎片发现几率");
        }
        if (engine.equals(Engine.ORE)) {
            nbtItem.setInteger("engineORE", 2);
            engineLore = ChatColorCast.format("&b⸎ 钻头引擎: &72倍矿物数量");
        }
        if (engine.equals(Engine.OREGEN)) {
            double num = (double) NumberFormat.getRandomInt(1, 4) / 10;
            nbtItem.setDouble("engineOREGEN", num);
            engineLore = ChatColorCast.format("&b☬ 机械引擎: &7+" + num + "%几率获得对应等级的矿物生成机");
        }
        if (engine.equals(Engine.ENERGYLIMIT)) {
            nbtItem.setInteger("engineENERGYLIMIT", 1);
            engineLore = ChatColorCast.format("&b⚚ 扩展引擎: &7无视能量满后不能挖掘的限制");
        }
        ItemStack nbtDone = nbtItem.getItem();
        ItemMeta itemMeta = nbtDone.getItemMeta();
        itemMeta.setDisplayName(getName(name, oldPrestige + 1, 0));
        List<String> lore = itemMeta.getLore();
        int infoLine = 0;
        for (String str : lore) {
            if (str.contains("| 工具信息")) {
                break;
            }
            infoLine++;
        }
        lore.set(infoLine + 1, ChatColorCast.format("&b▪ &f已挖掘: &e&L0"));
        lore.set(infoLine + 2, ChatColorCast.format("&b▪ &f最高等级: &d&l" + (oldMaxLevel + 15)));
        String[] gemLore = lore.get(infoLine + 3).split(":");
        lore.set(infoLine + 3, ChatColorCast.format("&b▪ &f宝石&7(0/" + maxgem + "):" + gemLore[1]));

        int energyLine = 0;
        for (String str : lore) {
            if (str.contains("| 宇宙能量")) {
                break;
            }
            energyLine++;
        }
        if (energybooster > 0) {
            lore.set(energyLine, ChatColorCast.format("&6&l| 宇宙能量 &7(+" + energybooster + "%)"));
        }

        int engineLine = 0;
        for (String str : lore) {
            if (str.contains("| 引擎信息")) {
                break;
            }
            engineLine++;
        }
        if (isFree) {
            lore.set(engineLine, ChatColorCast.format("&6&l| 引擎信息"));
        }
        if (oldPrestige == 0) {
            lore.set(engineLine + 1, engineLore);
        } else {
            lore.add(engineLine + 1 + oldPrestige, engineLore);
        }

        int enchantLine = 0;
        for (String str : lore) {
            if (str.contains("| 附魔信息")) {
                break;
            }
            enchantLine++;
        }
        lore.set(enchantLine + 1, ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="));
        lore.set(enchantLine + 2, ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="));

        int times = lore.size() - (enchantLine + 3);

        for (int e = 0; e < times; e++) {
            lore.remove(enchantLine + 3);
        }

        lore.add(" ");
        lore.add(ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔"));
        itemMeta.setLore(lore);
        nbtDone.setItemMeta(itemMeta);
        return nbtDone;

    }

    public ItemStack getLeveledItemWithoutClear(ItemStack originItem, Engine engine) {
        NBTItem nbtItem = new NBTItem(originItem);
        int oldPrestige = nbtItem.getInteger("toolprestige");
        int oldMaxLevel = nbtItem.getInteger("toolmaxlevel");
        int oldLevel = nbtItem.getInteger("toollevel");
        String name = nbtItem.getString("toolname");
        nbtItem.setInteger("toolprestige", oldPrestige + 1);
        nbtItem.setInteger("toolmaxlevel", oldMaxLevel + 15);


        if (nbtItem.hasKey("nuclearfreeprestige")) {
            nbtItem.removeKey("nuclearfreeprestige");

        }

        String engineLore = "";
        int energybooster = nbtItem.getInteger("energybooster");
        int maxgem = nbtItem.getInteger("maxgem");
        if (engine.equals(Engine.CUBE)) {
            double num = (double) NumberFormat.getRandomInt(4, 8) / 10;
            nbtItem.setDouble("engineCUBE", num);
            engineLore = ChatColorCast.format("&b❒ 魔方引擎: &7+" + num + "%几率获得银河魔方");
        }
        if (engine.equals(Engine.EFFICIENCY)) {
            int num = NumberFormat.getRandomInt(30, 50);
            nbtItem.setInteger("engineEFFICIENCY", num);
            engineLore = ChatColorCast.format("&b&l⸕ &b效率引擎: &7+" + num + "%挖掘速度");
        }
        if (engine.equals(Engine.ENERGY)) {
            int num = NumberFormat.getRandomInt(70, 150);
            nbtItem.setInteger("engineENERGY", num);
            engineLore = ChatColorCast.format("&b❁ 能量引擎: &7+" + num + "%能量收集速度");
            energybooster = energybooster + num;
            nbtItem.setInteger("energybooster", energybooster);
        }
        if (engine.equals(Engine.FUEL)) {
            int num = NumberFormat.getRandomInt(10, 30);
            nbtItem.setInteger("engineFUEL", num);
            engineLore = ChatColorCast.format("&b✧ 能源引擎: &7+" + num + "%几率发现熔炉燃料");
        }
        if (engine.equals(Engine.XP)) {
            int num = NumberFormat.getRandomInt(15, 25);
            nbtItem.setInteger("engineXP", num);
            engineLore = ChatColorCast.format("&b❈ 经验引擎: &7+" + num + "%经验");
        }
        if (engine.equals(Engine.GEM)) {
            int num = NumberFormat.getRandomInt(3, 6);
            nbtItem.setInteger("engineGEM", num);
            engineLore = ChatColorCast.format("&b✯ 宝石引擎: &7+" + num + "个宝石孔");
            maxgem = maxgem + num;
            nbtItem.setInteger("maxgem", maxgem);
        }
        if (engine.equals(Engine.SHARD)) {
            double num = (double) NumberFormat.getRandomInt(15, 20) / 10;
            nbtItem.setDouble("engineSHARD", num);
            engineLore = ChatColorCast.format("&b᠅ 碎片引擎: &7" + num + "倍碎片发现几率");
        }
        if (engine.equals(Engine.ORE)) {
            nbtItem.setInteger("engineORE", 2);
            engineLore = ChatColorCast.format("&b⸎ 钻头引擎: &72倍矿物数量");
        }
        if (engine.equals(Engine.OREGEN)) {
            double num = (double) NumberFormat.getRandomInt(1, 4) / 10;
            nbtItem.setDouble("engineOREGEN", num);
            engineLore = ChatColorCast.format("&b☬ 机械引擎: &7+" + num + "%几率获得对应等级的矿物生成机");
        }
        if (engine.equals(Engine.ENERGYLIMIT)) {
            nbtItem.setInteger("engineENERGYLIMIT", 1);
            engineLore = ChatColorCast.format("&b⚚ 扩展引擎: &7无视能量满后不能挖掘的限制");
        }
        ItemStack nbtDone = nbtItem.getItem();
        ItemMeta itemMeta = nbtDone.getItemMeta();
        itemMeta.setDisplayName(getName(name, oldPrestige + 1, oldLevel));
        List<String> lore = itemMeta.getLore();
        int infoLine = 0;
        for (String str : lore) {
            if (str.contains("| 工具信息")) {
                break;
            }
            infoLine++;
        }
        lore.set(infoLine + 2, ChatColorCast.format("&b▪ &f最高等级: &d&l" + (oldMaxLevel + 15)));
        String[] gemLore = lore.get(infoLine + 3).split(":");
        lore.set(infoLine + 3, ChatColorCast.format("&b▪ &f宝石&7(0/" + maxgem + "):" + gemLore[1]));

        int energyLine = 0;
        for (String str : lore) {
            if (str.contains("| 宇宙能量")) {
                break;
            }
            energyLine++;
        }
        if (energybooster > 0) {
            lore.set(energyLine, ChatColorCast.format("&6&l| 宇宙能量 &7(+" + energybooster + "%)"));
        }

        int engineLine = 0;
        for (String str : lore) {
            if (str.contains("| 引擎信息")) {
                break;
            }
            engineLine++;
        }

        lore.set(engineLine, ChatColorCast.format("&6&l| 引擎信息"));


        if (oldPrestige == 0) {
            lore.set(engineLine + 1, engineLore);
        } else {
            lore.add(engineLine + 1 + oldPrestige, engineLore);
        }

        itemMeta.setLore(lore);
        nbtDone.setItemMeta(itemMeta);
        return nbtDone;

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


}
