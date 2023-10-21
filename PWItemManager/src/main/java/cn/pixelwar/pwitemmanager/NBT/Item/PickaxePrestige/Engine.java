package cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige;

public enum Engine {
    ENERGY("&b❁ 能量引擎", "&7+70%~150%能量收集速度"),
    XP("&b❈ 经验引擎", "&7+15%~25%经验"),
    EFFICIENCY("&b&l⸕ &b效率引擎", "&7+30%~50%挖掘速度"),
    SHARD("&b᠅ 碎片引擎", "&71.5倍~2倍碎片发现几率"),
    ORE("&b⸎ 钻头引擎", "&72倍矿物数量"),
    GEM("&b✯ 宝石引擎", "&7+3个~6个宝石孔"),
    OREGEN("&b☬ 机械引擎", "&7+0.1%~0.4%几率获得对应等级的矿物生成机"),
    FUEL("&b✧ 能源引擎", "&7+10%~30%几率发现熔炉燃料(玩家荣誉等级达到II才会生效)"),
    ENERGYLIMIT("&b⚚ 扩展引擎", "&7无视能量满后不能挖掘的限制"),
    CUBE("&b❒ 魔方引擎", "&7+0.4%~0.8%几率获得银河魔方(需要镐荣誉等级IX(9级))");
    private String name;
    private String description;

    Engine(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
