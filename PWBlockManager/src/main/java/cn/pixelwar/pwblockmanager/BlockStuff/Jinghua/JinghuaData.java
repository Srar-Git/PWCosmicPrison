package cn.pixelwar.pwblockmanager.BlockStuff.Jinghua;

import org.bukkit.Material;

public class JinghuaData {

    private int amount;
    private int totalAmount;
    private int armorStandID;
    private Material originalType;
    private Material JinghuaType;
    private Material item;

    public Material getItem() {
        return item;
    }

    public void setItem(Material item) {
        this.item = item;
    }

    public JinghuaData(int amount, int totalAmount, int armorStandID, Material originalType, Material jinghuaType, Material item) {
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.armorStandID = armorStandID;
        this.originalType = originalType;
        JinghuaType = jinghuaType;
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getArmorStandID() {
        return armorStandID;
    }

    public Material getOriginalType() {
        return originalType;
    }

    public Material getJinghuaType() {
        return JinghuaType;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setArmorStandID(int armorStandID) {
        this.armorStandID = armorStandID;
    }

    public void setOriginalType(Material originalType) {
        this.originalType = originalType;
    }

    public void setJinghuaType(Material jinghuaType) {
        JinghuaType = jinghuaType;
    }
}
