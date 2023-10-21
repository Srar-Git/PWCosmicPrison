package cn.pixelwar.pwitemmanager.Enchant.EnchantEum;

import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

import static java.util.Arrays.asList;

public enum PWEnchant {
    //剑、斧子附魔
    daze("剑/斧子", "发呆", ChatColor.WHITE, 1, 5, Material.GHAST_TEAR,
            asList(
                    ChatColorCast.format("&7有几率使你的敌人陷入迷惑")
            )
    ),
    shirenzu("剑/斧子", "食人族", ChatColor.GREEN, 2, 5, Material.BEEF,
            asList(
                    ChatColorCast.format("&7有几率增加你的饱食度和体力值")
            )
    ),
    poison("剑", "毒药", ChatColor.GREEN, 2, 5, Material.SWEET_BERRIES,
            asList(
                    ChatColorCast.format("&7有几率使你的敌人中毒")
            )
    ),
    scorch("剑", "灼烧", ChatColor.GREEN, 2, 5, Material.FIRE_CHARGE,
            asList(
                    ChatColorCast.format("&7有几率使你的敌人灼烧")
            )
    ),
    molecular("剑", "分子构建", ChatColor.BLUE, 3, 5, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率使你的盔甲恢复一定耐久")
            )
    ),
    knockback("剑", "击退", ChatColor.BLUE, 3, 4, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7击中敌人使其被击退")
            )
    ),
    fling("剑", "旋风", ChatColor.BLUE, 3, 3, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7击中敌人有几率使其被抬起")
            )
    ),
    blaze("剑/斧子", "烈焰", ChatColor.BLUE, 3, 3, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率点燃你周围的区域来保护你"),
                    ChatColorCast.format("&7同时你获得抗火效果")
            )
    ),
    dianxing("剑/斧子", "触电", ChatColor.BLUE, 3, 3, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率电击你的敌人，使其缓慢")
            )
    ),
    hook("剑", "链钩", ChatColor.YELLOW, 4, 3, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率将你的敌人拉向你")
            )
    ),
    shatter("剑/斧子", "裂地猛击", ChatColor.YELLOW, 4, 3, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率将地上的方块掀起砸向敌人"),
                    ChatColorCast.format("&7并造成与剩余生命值成正比的伤害"),
                    ChatColorCast.format("&7该伤害还会被护甲值影响")
            )
    ),
    fix("剑/斧子", "修复", ChatColor.YELLOW, 4, 3, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率增加你的武器耐久")
            )
    ),
    melt("剑", "熔化", ChatColor.YELLOW, 4, 4, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7对敌人的护甲造成更大的耐久伤害")
            )
    ),
    execute("剑/斧子", "行刑", ChatColor.YELLOW, 4, 5, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7你的敌人越接近死亡受到的伤害越高")
            )
    ),
    yuxue("剑/斧子", "浴血", ChatColor.YELLOW, 4, 4, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7你越接近死亡造成的伤害越高")
            )
    ),
    hunzhan("剑/斧子", "混战", ChatColor.YELLOW, 4, 5, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7你的敌人越接近你受到的伤害越高")
            )
    ),
    weaken("剑/斧子", "削弱", ChatColor.YELLOW, 4, 5, Material.IRON_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7有几率使你的敌人虚弱")
            )
    ),


    //存储箱附魔
    sell("矿物存储箱", "自动出售", ChatColor.GREEN, 2, 10, Material.PAPER,
            asList(
                    ChatColorCast.format("&7存储增加时有几率自动出售矿物")
            )
    ),
    energymirror("矿物存储箱", "能量之镜", ChatColor.YELLOW, 4, 10, Material.GLASS,
            asList(
                    ChatColorCast.format("&7存储增加时有几率获得宇宙能量")
            )
    ),
    multiore("矿物存储箱", "多倍矿物", ChatColor.GOLD, 5, 10, Material.DEEPSLATE_GOLD_ORE,
            asList(
                    ChatColorCast.format("&7存储增加时有几率获得多倍矿物")
            )
    ),
    share("矿物存储箱", "矿物共享", ChatColor.GOLD, 5, 10, Material.CHEST,
            asList(
                    ChatColorCast.format("&7附近15格以内玩家挖掘矿物时你也可能获得矿物")
            )
    ),
    nenergymirror("矿物存储箱", "能量之源", ChatColor.LIGHT_PURPLE, 6, 10, Material.PACKED_ICE,
            asList(
                    ChatColorCast.format("&7相比'能量之镜'几率更大,能量更多")
            ),
            PWEnchant.energymirror
    ),

    //镐附魔
    //普通镐附魔
    efficiency("镐", "效率", ChatColor.WHITE, 1, 6, Material.IRON_PICKAXE,
            asList(
                    ChatColorCast.format("&7增加镐挖掘矿物的速度")
            )
    ),
    magnet("镐", "电磁铁", ChatColor.WHITE, 1, 10, Material.FLINT_AND_STEEL,
            asList(
                    ChatColorCast.format("&7挖掘时可能会吸取更多矿物")
            )
    ),
    water("镐", "水下速掘", ChatColor.WHITE, 1, 3, Material.WATER_BUCKET,
            asList(
                    ChatColorCast.format("&7在水下挖掘更加迅速")
            )
    ),
    feed("镐", "饱食", ChatColor.WHITE, 1, 10, Material.BREAD,
            asList(
                    ChatColorCast.format("&7挖掘时可能会恢复饥饿度")
            )
    ),
    //优秀附魔
    lianjin("镐", "炼金", ChatColor.GREEN, 2, 10, Material.GOLD_NUGGET,
            asList(
                    ChatColorCast.format("&7挖掘时可能会直接出售获得的矿物,"),
                    ChatColorCast.format("&7并将其变为支票直接放入你的背包")
            )
    ),
    shard("镐", "碎片勘探", ChatColor.GREEN, 2, 10, Material.AMETHYST_SHARD,
            asList(
                    ChatColorCast.format("&7挖掘时可能会发现矿物碎片,"),
                    ChatColorCast.format("&7碎片等级取决于挖掘的矿物")
            )
    ),
    zuantou("镐", "能量钻头", ChatColor.GREEN, 2, 10, Material.BLUE_DYE,
            asList(
                    ChatColorCast.format("&7挖掘时可能会获得更多宇宙能量")
            )
    ),
    dianshi("镐", "点石成金", ChatColor.GREEN, 2, 10, Material.GOLD_BLOCK,
            asList(
                    ChatColorCast.format("&7挖掘时可能会获得更高级的矿物")
            )
    ),

    //稀有附魔
    tiqu("镐", "源矿提取", ChatColor.BLUE, 3, 10, Material.CAMPFIRE,
            asList(
                    ChatColorCast.format("&7挖掘时可能将周围的矿石变为"),
                    ChatColorCast.format("&7对应的矿物方块")
            )
    ),
    powerball("镐", "矿弹", ChatColor.BLUE, 3, 10, Material.FIRE_CHARGE,
            asList(
                    ChatColorCast.format("&7使用SHIFT+右键发射出一定"),
                    ChatColorCast.format("&7数量的矿弹,冷却时间50秒,"),
                    ChatColorCast.format("&7矿弹击中的矿物会爆炸")
            )
    ),
    pohuai("镐", "破坏", ChatColor.BLUE, 3, 10, Material.TRIDENT,
            asList(
                    ChatColorCast.format("&7挖掘过程中可能瞬间破坏矿物")
            )
    ),
    baoji("镐", "暴击", ChatColor.BLUE, 3, 10, Material.TIPPED_ARROW,
            asList(
                    ChatColorCast.format("&7你的连击数量随着暴击等级增加")
            )
    ),
    huifu("镐", "恢复", ChatColor.BLUE, 3, 10, Material.DIAMOND_ORE,
            asList(
                    ChatColorCast.format("&7挖掘时有较高几率瞬间恢复矿物")
            )
    ),

    //史诗附魔
    suilie("镐", "碎裂", ChatColor.YELLOW, 4, 10, Material.CRACKED_STONE_BRICKS,
            asList(
                    ChatColorCast.format("&7挖掘过程中使周围矿物破裂")
            )
    ),
    worm("镐", "虫洞", ChatColor.YELLOW, 4, 10, Material.ENDER_EYE,
            asList(
                    ChatColorCast.format("&7使用右键打开工具附魔菜单"),
                    ChatColorCast.format("&7可以瞬间传送至与你等级对"),
                    ChatColorCast.format("&7应的矿场,受PVP时间影响")
            )
    ),
    guruojintang("镐", "固若金汤", ChatColor.YELLOW, 4, 10, Material.NETHERITE_CHESTPLATE,
            asList(
                    ChatColorCast.format("&7挖掘时可能会获得更多生命值"),
                    ChatColorCast.format("&7持续30秒")
            )
    ),

    //传说附魔
    explode("镐", "爆破", ChatColor.GOLD, 5, 10, Material.TNT,
            asList(
                    ChatColorCast.format("&7挖掘时可能会使周围矿物爆炸")
            )
    ),
    niuquxianshi("镐", "扭曲现实", ChatColor.GOLD, 5, 10, Material.POTION,
            asList(
                    ChatColorCast.format("&7挖掘时可能会获得经验/能量加成")
            )
    ),
    escape("镐", "逃生舱", ChatColor.GOLD, 5, 5, Material.TOTEM_OF_UNDYING,
            asList(
                    ChatColorCast.format("&7使用右键打开工具附魔菜单"),
                    ChatColorCast.format("&7可以瞬间传送至空间站,并且"),
                    ChatColorCast.format("&7不受PVP时间影响")
            )
    ),
    lucky("镐", "幸运", ChatColor.GOLD, 5, 10, Material.SPYGLASS,
            asList(
                    ChatColorCast.format("&7有几率以更高几率触发所有其他附魔")
            )
    ),
    battery("镐", "能量电池", ChatColor.GOLD, 5, 5, Material.LIGHT_BLUE_CANDLE,
            asList(
                    ChatColorCast.format("&7减少附魔所需的宇宙能量")
            )
    ),
    //核能附魔
    nexplode("镐", "核能爆破", ChatColor.LIGHT_PURPLE, 6, 10, Material.TNT_MINECART,
            asList(
                    ChatColorCast.format("&7挖掘时可能会使周围矿物大范围爆炸")
            ),
            PWEnchant.explode
    ),
    nefficiency("镐", "核能效率", ChatColor.LIGHT_PURPLE, 6, 6, Material.DIAMOND_PICKAXE,
            asList(
                    ChatColorCast.format("&7效果为普通效率3倍")
            ),
            PWEnchant.efficiency
    ),
    nlucky("镐", "幸运星", ChatColor.LIGHT_PURPLE, 6, 5, Material.NETHER_STAR,
            asList(
                    ChatColorCast.format("&7100%使得其他所有附魔触发几率提高")
            ),
            PWEnchant.lucky
    ),
    nniuquxianshi("镐", "扭曲虚空", ChatColor.LIGHT_PURPLE, 6, 10, Material.END_CRYSTAL,
            asList(
                    ChatColorCast.format("&7获得能量/经验加成的时间和几率更大")
            ),
            PWEnchant.niuquxianshi
    ),
    ntiqu("镐", "核能提取", ChatColor.LIGHT_PURPLE, 6, 10, Material.SOUL_CAMPFIRE,
            asList(
                    ChatColorCast.format("&7挖掘时可能将周围更大范围"),
                    ChatColorCast.format("&7的矿石变为对应的矿物方块")
            ),
            PWEnchant.tiqu
    );
    private final String chineseName;
    private final String type;
    private final List<String> description;
    private final ChatColor rankColor;
    private final int rank;
    private final int maxLevel;
    private final Material item;
    private PWEnchant depend;

    private PWEnchant(String type, String chineseName, ChatColor rankColor, int rank, int maxLevel, Material item, List<String> description) {
        this.chineseName = chineseName;
        this.description = description;
        this.type = type;
        this.rankColor = rankColor;
        this.rank = rank;
        this.maxLevel = maxLevel;
        this.item = item;
    }

    private PWEnchant(String type, String chineseName, ChatColor rankColor, int rank, int maxLevel, Material item, List<String> description, PWEnchant depend) {
        this.chineseName = chineseName;
        this.description = description;
        this.type = type;
        this.rankColor = rankColor;
        this.rank = rank;
        this.maxLevel = maxLevel;
        this.item = item;
        this.depend = depend;
    }

    public String getChineseName() {
        return chineseName;
    }

    public PWEnchant getDepend() {
        return depend;
    }

    public String getType() {
        return type;
    }

    public List<String> getDescription() {
        return description;
    }

    public ChatColor getRankColor() {
        return rankColor;
    }

    public int getRank() {
        return rank;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Material getItem() {
        return item;
    }
}
