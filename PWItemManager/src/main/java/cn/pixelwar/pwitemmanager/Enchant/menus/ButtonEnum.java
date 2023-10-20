package cn.pixelwar.pwitemmanager.Enchant.menus;

import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public enum ButtonEnum {

    UNLOCKED_SLOT(Material.LIME_STAINED_GLASS_PANE,
            ChatColorCast.format("&a✔ &f已解锁的附魔格"),
            asList(" ",
                    ChatColorCast.format("&7点击下方的&6&l按钮&7来抽取多个"),
                    ChatColorCast.format("&7随机附魔，然后你可以选择其中&n一个&r!")
            ),
            false
            ),
    BACKGROUND(Material.BLACK_STAINED_GLASS_PANE,
            " ",
            asList(),
            false
            ),
    LOCKED_SLOT(Material.RED_STAINED_GLASS_PANE,
            ChatColorCast.format("&c✖ &f未解锁的附魔格"),
            asList(
                    " ",
                    ChatColorCast.format("&7请升级你的&6&l荣誉等级&7,升级&a&lVIP等级"),
                    ChatColorCast.format("&7或者通过&d&l特殊物品&7来增加附魔格数量")
            ),
            false
    ),
    ROLL(Material.TURTLE_EGG,
            ChatColorCast.format("&a&l开始抽取附魔"),
            asList(
                    " ",
                    ChatColorCast.format("&7抽取附魔后你的所有已解锁附魔格会变为"),
                    ChatColorCast.format("&7相应附魔，你可以选取其中&d一个&7进行附魔"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&a&l点击&7开始抽取附魔")
            ),
            true
    ),









    ;

    private final ItemStack item;

    public ItemStack getItem() {
        return item;
    }

    ButtonEnum(Material material, String name, List<String> lore, boolean glow) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        if (glow) {
            itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        this.item = item;
    }
}
