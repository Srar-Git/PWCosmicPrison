package cn.pixelwar.pwitemmanager.NBT.Item.SellItem;

public enum OrePrice {
    COAL_ORE(0.06),
    COAL(0.32),
    IRON_ORE(0.18),
    IRON_INGOT(0.92),
    LAPIS_ORE(0.45),
    LAPIS_LAZULI(2.35),
    REDSTONE_ORE(1.35),
    REDSTONE(7.14),
    GOLD_ORE(4.15),
    GOLD_INGOT(21.97),
    DIAMOND_ORE(7.32),
    DIAMOND(38.75),
    EMERALD_ORE(25.3),
    EMERALD(134.5);

    private double price;

    public double getPrice() {
        return price;
    }

    OrePrice(double price) {
        this.price = price;
    }
}
