package cn.pixelwar.pwpacketstuff.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PowerballHitBlockEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Player shooter;
    private ItemStack item;
    private float x;
    private float y;
    private float z;
    private int coaloreCount;
    private int ironoreCount;
    private int lapisoreCount;
    private int redstoneoreCount;
    private int goldoreCount;
    private int diamondoreCount;
    private int emeraldoreCount;

    public int getCoaloreCount() {
        return coaloreCount;
    }

    public int getIronoreCount() {
        return ironoreCount;
    }

    public int getLapisoreCount() {
        return lapisoreCount;
    }

    public int getRedstoneoreCount() {
        return redstoneoreCount;
    }

    public int getGoldoreCount() {
        return goldoreCount;
    }

    public int getDiamondoreCount() {
        return diamondoreCount;
    }

    public int getEmeraldoreCount() {
        return emeraldoreCount;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public PowerballHitBlockEvent(Player shooter, ItemStack item, float x, float y, float z, int coaloreCount, int ironoreCount, int lapisoreCount, int redstoneoreCount, int goldoreCount, int diamondoreCount, int emeraldoreCount) {
        this.shooter = shooter;
        this.item = item;
        this.x = x;
        this.y = y;
        this.z = z;
        this.coaloreCount = coaloreCount;
        this.ironoreCount = ironoreCount;
        this.lapisoreCount = lapisoreCount;
        this.goldoreCount = goldoreCount;
        this.diamondoreCount = diamondoreCount;
        this.redstoneoreCount = redstoneoreCount;
        this.emeraldoreCount = emeraldoreCount;
    }

    public Player getShooter() {
        return shooter;
    }

    public ItemStack getItem() {
        return item;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
