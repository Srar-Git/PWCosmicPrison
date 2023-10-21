package cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Requirement {

    private ItemStack requireItem;
    private boolean reachRequirement;

    public Requirement() {

    }

    public void setRequireItem(ItemStack requireItem) {
        this.requireItem = requireItem;
    }

    public void setReachRequirement(boolean reachRequirement) {
        this.reachRequirement = reachRequirement;
    }

    public ItemStack getRequireItem() {
        return requireItem;
    }

    public boolean isReachRequirement() {
        return reachRequirement;
    }
}
