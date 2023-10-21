package cn.pixelwar.pwitemmanager.OreBag;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OreBagAddEvent extends Event {
    private Player player;
    private Material ore;
    private int amount;
    private int slot;

    private static final HandlerList HANDLERS = new HandlerList();

    public Player getPlayer() {
        return player;
    }

    public Material getOre() {
        return ore;
    }

    public int getAmount() {
        return amount;
    }

    public int getSlot() {
        return slot;
    }

    public OreBagAddEvent(Player player, Material ore, int amount, int slot) {
        this.player = player;
        this.ore = ore;
        this.amount = amount;
        this.slot = slot;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }


}
