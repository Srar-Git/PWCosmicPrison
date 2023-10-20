package cn.pixelwar.pwitemmanager.NBT.Item;

import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public enum PWItem {
    wooden_sword(
            Material.WOODEN_SWORD,
            Lists.newArrayList(
                    "weaponname-&f木剑"
            ),
            Lists.newArrayList(
                    "weaponevel-0",
                    "weaponmaxlevel-10",
                    "weaponkillplayer-0",
                    "weaponkillmob-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&f木剑 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 武器信息"),
                    ChatColorCast.format("&b▪ &f使用等级: &c&oLv.0"),
                    ChatColorCast.format("&b▪ &f击杀玩家: &e0"),
                    ChatColorCast.format("&b▪ &f击杀怪物: &a0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l10"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    " "
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    stone_sword(
            Material.STONE_SWORD,
            Lists.newArrayList(
                    "weaponname-&f石剑"
            ),
            Lists.newArrayList(
                    "weaponlevel-0",
                    "weaponmaxlevel-15",
                    "weaponkillplayer-0",
                    "weaponkillmob-0",
                    "enchantAmount-0",
                    "maxgem-2",
                    "gem-0"
            ),
            "&f石剑 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 武器信息"),
                    ChatColorCast.format("&b▪ &f使用等级: &c&oLv.10"),
                    ChatColorCast.format("&b▪ &f击杀玩家: &e0"),
                    ChatColorCast.format("&b▪ &f击杀怪物: &a0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/2): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    " "
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    golden_sword(
            Material.GOLDEN_SWORD,
            Lists.newArrayList(
                    "weaponname-&f金剑"
            ),
            Lists.newArrayList(
                    "weaponlevel-0",
                    "weaponmaxlevel-25",
                    "weaponkillplayer-0",
                    "weaponkillmob-0",
                    "enchantAmount-0",
                    "maxgem-3",
                    "gem-0"
            ),
            "&f金剑 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 武器信息"),
                    ChatColorCast.format("&b▪ &f使用等级: &c&oLv.30"),
                    ChatColorCast.format("&b▪ &f击杀玩家: &e0"),
                    ChatColorCast.format("&b▪ &f击杀怪物: &a0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l25"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/3): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    " "
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    iron_sword(
            Material.IRON_SWORD,
            Lists.newArrayList(
                    "weaponname-铁剑"
            ),
            Lists.newArrayList(
                    "weaponlevel-0",
                    "weaponmaxlevel-40",
                    "weaponkillplayer-0",
                    "weaponkillmob-0",
                    "enchantAmount-0",
                    "maxgem-4",
                    "gem-0"
            ),
            "&f铁剑 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 武器信息"),
                    ChatColorCast.format("&b▪ &f使用等级: &c&oLv.60"),
                    ChatColorCast.format("&b▪ &f击杀玩家: &e0"),
                    ChatColorCast.format("&b▪ &f击杀怪物: &a0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l40"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/4): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    " "
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    diamond_sword(
            Material.DIAMOND_SWORD,
            Lists.newArrayList(
                    "weaponname-钻石剑"
            ),
            Lists.newArrayList(
                    "weaponlevel-0",
                    "weaponmaxlevel-60",
                    "weaponkillplayer-0",
                    "weaponkillmob-0",
                    "enchantAmount-0",
                    "maxgem-5",
                    "gem-0"
            ),
            "&f钻石剑 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 武器信息"),
                    ChatColorCast.format("&b▪ &f使用等级: &c&oLv.100"),
                    ChatColorCast.format("&b▪ &f击杀玩家: &e0"),
                    ChatColorCast.format("&b▪ &f击杀怪物: &a0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l60"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/5): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    " "
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_coalore(
            Material.COAL_ORE,
            Lists.newArrayList(
                    "orebagname-&8&l煤矿存储箱",
                    "material-COAL_ORE",
                    "materialname-煤矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&8&l煤矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储煤矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_coal(
            Material.COAL,
            Lists.newArrayList(
                    "orebagname-&8&l煤存储箱",
                    "material-COAL",
                    "materialname-煤"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&8&l煤存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储煤: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_ironore(
            Material.IRON_ORE,
            Lists.newArrayList(
                    "orebagname-&7&l铁矿存储箱",
                    "material-IRON_ORE",
                    "materialname-铁矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&7&l铁矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储铁矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_iron(
            Material.IRON_INGOT,
            Lists.newArrayList(
                    "orebagname-&7&l铁锭存储箱",
                    "material-IRON_INGOT",
                    "materialname-铁锭"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&7&l铁锭矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储铁锭: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_lapisore(
            Material.LAPIS_ORE,
            Lists.newArrayList(
                    "orebagname-&9&l青金石矿存储箱",
                    "material-LAPIS_ORE",
                    "materialname-青金石矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&9&l青金石矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储青金石矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_lapis(
            Material.LAPIS_LAZULI,
            Lists.newArrayList(
                    "orebagname-&9&l青金石存储箱",
                    "material-LAPIS_LAZULI",
                    "materialname-青金石"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&9&l青金石存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储青金石: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_redstoneore(
            Material.REDSTONE_ORE,
            Lists.newArrayList(
                    "orebagname-&c&l红石矿存储箱",
                    "material-REDSTONE_ORE",
                    "materialname-红石矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&c&l红石矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储青红石矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_redstone(
            Material.REDSTONE,
            Lists.newArrayList(
                    "orebagname-&c&l红石存储箱",
                    "material-REDSTONE",
                    "materialname-红石"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&c&l红石存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储青红石: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_goldore(
            Material.GOLD_ORE,
            Lists.newArrayList(
                    "orebagname-&e&l金矿存储箱",
                    "material-GOLD_ORE",
                    "materialname-金矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&e&l金矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储金矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_gold(
            Material.GOLD_INGOT,
            Lists.newArrayList(
                    "orebagname-&e&l金锭存储箱",
                    "material-GOLD_INGOT",
                    "materialname-金锭"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&e&l金锭存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储金锭: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_diamondore(
            Material.DIAMOND_ORE,
            Lists.newArrayList(
                    "orebagname-&b&l钻石矿存储箱",
                    "material-DIAMOND_ORE",
                    "materialname-钻石矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&b&l钻石矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储钻石矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_diamond(
            Material.DIAMOND,
            Lists.newArrayList(
                    "orebagname-&b&l钻石存储箱",
                    "material-DIAMOND",
                    "materialname-钻石"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&b&l钻石存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储钻石: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_emeraldore(
            Material.EMERALD_ORE,
            Lists.newArrayList(
                    "orebagname-&a&l绿宝石矿存储箱",
                    "material-EMERALD_ORE",
                    "materialname-绿宝石矿"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&a&l绿宝石矿存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储绿宝石矿: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    bag_emerald(
            Material.EMERALD,
            Lists.newArrayList(
                    "orebagname-&a&l绿宝石存储箱",
                    "material-EMERALD",
                    "materialname-绿宝石"
            ),
            Lists.newArrayList(
                    "bagxp-0",
                    "bagneedxp-4800",
                    "baglevel-0",
                    "bagmaxlevel-15",
                    "size-0",
                    "maxsize-2304",
                    "bagfail-0",
                    "bagenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&a&l绿宝石存储箱 &7▸ &e&lLV.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&7你可以将此物品放在背包中存储矿物"),
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&6&l| 存储箱信息"),
                    ChatColorCast.format("&b▪ &f存储绿宝石: &b0/2,304"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l15"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    //默认镐
    wooden_pickaxe(
            Material.WOODEN_PICKAXE,
            Lists.newArrayList(
                    "toolname-&f木镐"
            ),
            Lists.newArrayList(
                    "toolxp-0",
                    "toolneedxp-4800",
                    "toollevel-0",
                    "toolmaxlevel-30",
                    "tooldig-0",
                    "toolprestige-0",
                    "toolfail-0",
                    "toolenchant-0",
                    "enchantAmount-0",
                    "maxgem-1",
                    "gem-0"
            ),
            "&f木镐 &7▸ &e&lLv.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 工具信息"),
                    ChatColorCast.format("&b▪ &f已挖掘: &e&L0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l30"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/1): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 4,800)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 引擎信息"),
                    ChatColorCast.format("&b▪ &f装配引擎: &c&l✖ &c无"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
                    )
    ),
    stone_pickaxe(
            Material.STONE_PICKAXE,
            Lists.newArrayList(
                    "toolname-&f石镐"
            ),
            Lists.newArrayList(
                    "toolxp-0",
                    "toolneedxp-6000",
                    "toollevel-0",
                    "toolmaxlevel-40",
                    "tooldig-0",
                    "toolprestige-0",
                    "toolfail-0",
                    "toolenchant-0",
                    "enchantAmount-0",
                    "maxgem-2",
                    "gem-0"
            ),
            "&f石镐 &7▸ &e&lLv.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 工具信息"),
                    ChatColorCast.format("&b▪ &f已挖掘: &e&L0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l40"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/2): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 6,000)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 引擎信息"),
                    ChatColorCast.format("&b▪ &f装配引擎: &c&l✖ &c无"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    golden_pickaxe(
            Material.GOLDEN_PICKAXE,
            Lists.newArrayList(
                    "toolname-&f金镐"
            ),
            Lists.newArrayList(
                    "toolxp-0",
                    "toolneedxp-7200",
                    "toollevel-0",
                    "toolmaxlevel-50",
                    "tooldig-0",
                    "toolprestige-0",
                    "toolfail-0",
                    "toolenchant-0",
                    "enchantAmount-0",
                    "maxgem-3",
                    "gem-0"
            ),
            "&f金镐 &7▸ &e&lLv.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 工具信息"),
                    ChatColorCast.format("&b▪ &f已挖掘: &e&L0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l50"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/3): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 7,200)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 引擎信息"),
                    ChatColorCast.format("&b▪ &f装配引擎: &c&l✖ &c无"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    iron_pickaxe(
            Material.IRON_PICKAXE,
            Lists.newArrayList(
                    "toolname-&f铁镐"
            ),
            Lists.newArrayList(
                    "toolxp-0",
                    "toolneedxp-8400",
                    "toollevel-0",
                    "toolmaxlevel-70",
                    "tooldig-0",
                    "toolprestige-0",
                    "toolfail-0",
                    "toolenchant-0",
                    "enchantAmount-0",
                    "maxgem-4",
                    "gem-0"

            ),
            "&f铁镐 &7▸ &e&lLv.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 工具信息"),
                    ChatColorCast.format("&b▪ &f已挖掘: &e&L0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l70"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/4): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 8,400)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 引擎信息"),
                    ChatColorCast.format("&b▪ &f装配引擎: &c&l✖ &c无"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    diamond_pickaxe(
            Material.DIAMOND_PICKAXE,
            Lists.newArrayList(
                    "toolname-&f钻石镐"
            ),
            Lists.newArrayList(
                    "toolxp-0",
                    "toolneedxp-9600",
                    "toollevel-0",
                    "toolmaxlevel-90",
                    "tooldig-0",
                    "toolprestige-0",
                    "toolfail-0",
                    "toolenchant-0",
                    "enchantAmount-0",
                    "maxgem-5",
                    "gem-0"
            ),
            "&f钻石镐 &7▸ &e&lLv.0",
            Lists.newArrayList(
                    " ",
                    ChatColorCast.format("&6&l| 工具信息"),
                    ChatColorCast.format("&b▪ &f已挖掘: &e&L0"),
                    ChatColorCast.format("&b▪ &f最高等级: &d&l90"),
                    ChatColorCast.format("&b▪ &f宝石&7(0/5): &c&l✖"),
                    " ",
                    ChatColorCast.format("&6&l| 宇宙能量"),
                    ChatColorCast.format("&c┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃┃ &f&l0%"),
                    ChatColorCast.format("&7(&f0&7 / 9,600)"),
                    ChatColorCast.format(""),
                    ChatColorCast.format("&6&l| 引擎信息"),
                    ChatColorCast.format("&b▪ &f装配引擎: &c&l✖ &c无"),
                    " ",
                    ChatColorCast.format("&6&l| 附魔信息"),
                    ChatColorCast.format("&a&l&m=&m &f&l 0 &a&l成功附魔 &a&l&m &m="),
                    ChatColorCast.format("&c&l&m=&m &f&l 0 &c&l失败附魔 &c&l&m &m="),
                    " ",
                    ChatColorCast.format("&c▸ &7能量满后&b&l/spawn&7前往虫洞进行附魔")
            ),
            Lists.newArrayList(),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    shard_common(
            Material.AMETHYST_SHARD,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "shardtier-1"
            ),
            "&f&l普通矿物碎片",
            Lists.newArrayList(
                    ChatColorCast.format("&7你可以通过挖矿等方式获得这个"),
                    ChatColorCast.format("&7充满宝藏的矿物碎片!"),
                    " ",
                    ChatColorCast.format("&7内部包含&f&n普通品质&7的物品..."),
                    ChatColorCast.format("&f▸ &7普通违禁品箱"),
                    ChatColorCast.format("&f▸ &7矿物生成机"),
                    ChatColorCast.format("&f▸ &7能量海绵"),
                    ChatColorCast.format("&f▸ &7充能宝石"),
                    ChatColorCast.format("&f▸ &7大量矿物"),
                    ChatColorCast.format("&f▸ &7木镐"),
                    ChatColorCast.format("&f▸ &7木剑"),
                    ChatColorCast.format("&f▸ &7普通附魔粉"),
                    ChatColorCast.format("&f▸ &7普通随机附魔卷轴"),
                    ChatColorCast.format("&f▸ &75,000 - 20,000宇宙能量"),
                    " ",
                    ChatColorCast.format("&f&l右键&7打开碎片")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    shard_uncommon(
            Material.AMETHYST_SHARD,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "shardtier-2"
            ),
            "&a&l优秀矿物碎片",
            Lists.newArrayList(
                    ChatColorCast.format("&7你可以通过挖矿等方式获得这个"),
                    ChatColorCast.format("&7充满宝藏的矿物碎片!"),
                    " ",
                    ChatColorCast.format("&7内部包含&a&n优秀品质&7的物品..."),
                    ChatColorCast.format("&a▸ &7优秀违禁品箱"),
                    ChatColorCast.format("&a▸ &7矿物生成机"),
                    ChatColorCast.format("&a▸ &7能量海绵"),
                    ChatColorCast.format("&a▸ &7充能宝石"),
                    ChatColorCast.format("&a▸ &7大量矿物"),
                    ChatColorCast.format("&a▸ &7石镐"),
                    ChatColorCast.format("&a▸ &7石剑"),
                    ChatColorCast.format("&a▸ &7优秀附魔粉"),
                    ChatColorCast.format("&a▸ &7优秀随机附魔卷轴"),
                    ChatColorCast.format("&a▸ &717,000 - 75,000宇宙能量"),
                    " ",
                    ChatColorCast.format("&a&l右键&7打开碎片")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    shard_rare(
            Material.AMETHYST_SHARD,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "shardtier-3"
            ),
            "&9&l稀有矿物碎片",
            Lists.newArrayList(
                    ChatColorCast.format("&7你可以通过挖矿等方式获得这个"),
                    ChatColorCast.format("&7充满宝藏的矿物碎片!"),
                    " ",
                    ChatColorCast.format("&7内部包含&9&n稀有品质&7的物品..."),
                    ChatColorCast.format("&9▸ &7稀有违禁品箱"),
                    ChatColorCast.format("&9▸ &7矿物生成机"),
                    ChatColorCast.format("&9▸ &7能量海绵"),
                    ChatColorCast.format("&9▸ &7充能宝石"),
                    ChatColorCast.format("&9▸ &7大量矿物"),
                    ChatColorCast.format("&9▸ &7金镐"),
                    ChatColorCast.format("&9▸ &7金剑"),
                    ChatColorCast.format("&9▸ &7稀有附魔粉"),
                    ChatColorCast.format("&9▸ &7稀有随机附魔卷轴"),
                    ChatColorCast.format("&9▸ &7附魔池重抽卷轴"),
                    ChatColorCast.format("&9▸ &735,000 - 125,000宇宙能量"),
                    " ",
                    ChatColorCast.format("&9&l右键&7打开碎片")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    shard_epic(
            Material.AMETHYST_SHARD,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "shardtier-4"
            ),
            "&e&l史诗矿物碎片",
            Lists.newArrayList(
                    ChatColorCast.format("&7你可以通过挖矿等方式获得这个"),
                    ChatColorCast.format("&7充满宝藏的矿物碎片!"),
                    " ",
                    ChatColorCast.format("&7内部包含&e&n史诗品质&7的物品..."),
                    ChatColorCast.format("&e▸ &7史诗违禁品箱"),
                    ChatColorCast.format("&e▸ &7矿物生成机"),
                    ChatColorCast.format("&e▸ &7物品命名牌"),
                    ChatColorCast.format("&e▸ &7能量海绵"),
                    ChatColorCast.format("&e▸ &7充能宝石"),
                    ChatColorCast.format("&e▸ &7大量矿物"),
                    ChatColorCast.format("&e▸ &7铁镐"),
                    ChatColorCast.format("&e▸ &7铁剑"),
                    ChatColorCast.format("&e▸ &7史诗附魔粉"),
                    ChatColorCast.format("&e▸ &7史诗随机附魔卷轴"),
                    ChatColorCast.format("&e▸ &7附魔池重抽卷轴"),
                    ChatColorCast.format("&e▸ &750,000 - 150,000宇宙能量"),
                    " ",
                    ChatColorCast.format("&e&l右键&7打开碎片")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    random_book_common(
            Material.BOOK,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "randombooktier-1"
            ),
            "&f&l普通随机附魔书",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7这本书中随机包含了一本品"),
                    ChatColorCast.format("&7质为&f&n普通&7的附魔书"),
                    " ",
                    ChatColorCast.format("&f&l右键&7使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    random_book_uncommon(
            Material.BOOK,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "randombooktier-2"
            ),
            "&a&l优秀随机附魔书",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7这本书中随机包含了一本品"),
                    ChatColorCast.format("&7质为&a&n优秀&7的附魔书"),
                    " ",
                    ChatColorCast.format("&a&l右键&7使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    random_book_rare(
            Material.BOOK,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "randombooktier-3"
            ),
            "&9&l稀有随机附魔书",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7这本书中随机包含了一本品"),
                    ChatColorCast.format("&7质为&9&n稀有&7的附魔书"),
                    " ",
                    ChatColorCast.format("&9&l右键&7使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    random_book_epic(
            Material.BOOK,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "randombooktier-4"
            ),
            "&e&l史诗随机附魔书",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7这本书中随机包含了一本品"),
                    ChatColorCast.format("&7质为&e&n史诗&7的附魔书"),
                    " ",
                    ChatColorCast.format("&e&l右键&7使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    random_book_legend(
            Material.BOOK,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "randombooktier-5"
            ),
            "&6&l传说随机附魔书",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7这本书中随机包含了一本品"),
                    ChatColorCast.format("&7质为&6&n传说&7的附魔书"),
                    " ",
                    ChatColorCast.format("&6&l右键&7使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    random_book_nuclear(
            Material.BOOK,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "randombooktier-6"
            ),
            "&d&l核能随机附魔书",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7这本书中随机包含了一本品"),
                    ChatColorCast.format("&7质为&d&n核能&7的附魔书"),
                    " ",
                    ChatColorCast.format("&d&l右键&7使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_UNBREAKABLE
            )
    ),
    gem_enchant(
            Material.RAW_IRON,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "gemenchant-1"
            ),
            "&a&l附魔保护石 &a❃",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7将次物品镶嵌到你的物品上后"),
                    ChatColorCast.format("&7物品附魔失败将不会消失"),
                    " ",
                    ChatColorCast.format("&a&l拖拽&7到物品上来使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
            )
    ),
    gem_death(
            Material.RAW_GOLD,
            Lists.newArrayList(
            ),
            Lists.newArrayList(
                    "gemdeath-1"
            ),
            "&b&l死亡保护石 &b☠",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7将次物品镶嵌到你的物品上后"),
                    ChatColorCast.format("&7玩家死亡后物品将不会消失,"),
                    ChatColorCast.format("&7但物品上所有&n宇宙能量&7会消失"),
                    " ",
                    ChatColorCast.format("&c&l注意!&7仅限镐、矿物存储箱!"),
                    " ",
                    ChatColorCast.format("&b&l拖拽&7到物品上来使用")
            ),
            Lists.newArrayList(
            ),
            Lists.newArrayList(
            )
    ),
    absorber(
            Material.SPONGE,
            Lists.newArrayList(
            ),
            Lists.newArrayList(

            ),
            "&e&l能量海绵",
            Lists.newArrayList(
                    ChatColorCast.format(" "),
                    ChatColorCast.format("&7使用此物品可以将使用对象"),
                    ChatColorCast.format("&7上的&n所有&7能量取出"),
                    " ",
                    ChatColorCast.format("&e&l拖拽&7到物品上来使用")
            ),
            Lists.newArrayList(
                    Enchantment.DURABILITY
            ),
            Lists.newArrayList(
                    ItemFlag.HIDE_ENCHANTS
            )
    );


    private final Material material;
    //路径-值
    private final List<String> stringNBT;
    private final List<String> intNBT;

    private final String name;
    private final List<String> lore;
    private final List<Enchantment> enchantments;
    private final List<ItemFlag> flags;

    public Material getMaterial() {
        return material;
    }

    public List<String> getStringNBT() {
        return stringNBT;
    }

    public List<String> getIntNBT() {
        return intNBT;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

    public List<ItemFlag> getFlags() {
        return flags;
    }

    PWItem(Material material, List<String> stringNBT, List<String> intNBT, String name, List<String> lore, List<Enchantment> enchantments, List<ItemFlag> flags) {
        this.material = material;
        this.stringNBT = stringNBT;
        this.intNBT = intNBT;
        this.name = name;
        this.lore = lore;
        this.enchantments = enchantments;
        this.flags = flags;
    }
}
