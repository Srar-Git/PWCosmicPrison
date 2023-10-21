package cn.pixelwar.pwplayer.Sentinel.NPC;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerNPCInfo {

    private int NPCID;


    private String name;
    private UUID uuid;
    private double health;
    private double maxHealth;
    private ItemStack[] equipments;
    private ItemStack handItem;
    private ItemStack offHandItem;
    private Inventory inventory;
    private Location location;

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getNPCID() {
        return NPCID;
    }

    public void setNPCID(int NPCID) {
        this.NPCID = NPCID;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public ItemStack[] getEquipments() {
        return equipments;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Location getLocation() {
        return location;
    }

    public ItemStack getHandItem() {
        return handItem;
    }

    public ItemStack getOffHandItem() {
        return offHandItem;
    }

    public PlayerNPCInfo() {
    }

    ;

    public void setPlayerNPCInfo(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.health = player.getHealth();
        this.maxHealth = player.getMaxHealth();
        ItemStack[] equipments;
        this.equipments = player.getInventory().getArmorContents();
        this.handItem = player.getItemInHand();
        this.inventory = player.getInventory();
        this.location = player.getLocation();
    }

}
