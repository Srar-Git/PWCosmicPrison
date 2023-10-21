package cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige;

import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static java.util.Arrays.asList;

public class Menu {

    public void openMainMenu(Player player) {
        Inventory gui = Bukkit.createInventory(player, 54, "镐荣誉/引擎装配");

        ItemStack white = getButton(
                Material.WHITE_STAINED_GLASS_PANE,
                ChatColorCast.format("&6&l请点击背包中的镐进行升级"),
                asList(
                ),
                true
        );
        ItemStack paper = getButton(
                Material.FLOWER_BANNER_PATTERN,
                ChatColorCast.format("&b&l镐荣誉/引擎装配"),
                asList(
                        ChatColorCast.format(" "),
                        ChatColorCast.format("&7为你的镐升级荣誉并装配引擎可以使其获得"),
                        ChatColorCast.format("&7永久保留的强大技能，每次升级荣誉后你可"),
                        ChatColorCast.format("&7以选择一个引擎进行装配，你的镐需要达到"),
                        ChatColorCast.format("&7要求才能升级荣誉!"),
                        ChatColorCast.format(""),
                        ChatColorCast.format("&6&l| 引擎列表"),
                        ChatColorCast.format("&b❁ 能量引擎: &7+70%~150%能量收集速度"),
                        ChatColorCast.format("&b❈ 经验引擎: &7+15%~25%经验"),
                        ChatColorCast.format("&b&l⸕ &b效率引擎: &7+30%~50%挖掘速度"),
                        ChatColorCast.format("&b᠅ 碎片引擎: &71.5倍~2倍碎片发现几率"),
                        ChatColorCast.format("&b⸎ 钻头引擎: &72倍矿物数量"),
                        ChatColorCast.format("&b✯ 宝石引擎: &7+3个~6个宝石孔"),
                        ChatColorCast.format("&b☬ 机械引擎: &7+0.1%~0.4%几率获得对应等级的矿物生成机"),
                        ChatColorCast.format("&b⚚ 扩展引擎: &7无视能量满后不能挖掘的限制"),
                        ChatColorCast.format("&b✧ 能源引擎: &7+10%~30%几率发现熔炉燃料(玩家荣誉等级达到II才会生效)"),
                        ChatColorCast.format("&b❒ 魔方引擎: &7+0.4%~0.8%几率获得银河魔方(需要镐荣誉等级IX(9级))"),
                        ChatColorCast.format(""),
                        ChatColorCast.format("&c&l注意!&7升级荣誉会导致你的镐失去所有附魔，等级"),
                        ChatColorCast.format("&7重置为0级，挖掘数量清空!但是最高等级+15级!")
                ),
                true
        );
        ItemStack back = getButton(
                Material.BLACK_STAINED_GLASS_PANE,
                ChatColorCast.format(" "),
                asList(
                ),
                false
        );
        for (int i = 0; i < 54; i++) {
            gui.setItem(i, back);
        }
        gui.setItem(4, paper);
        gui.setItem(22, white);


        player.openInventory(gui);
        player.playSound(player.getEyeLocation(), Sound.UI_BUTTON_CLICK, 1f, 2.0f);


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

    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow, String nbtPath, String nbt, String nbtPath2, int nbtint) {
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
        nbtItem.setString(nbtPath, nbt);
        nbtItem.setInteger(nbtPath2, nbtint);
        item = nbtItem.getItem();
        return item;
    }


}
