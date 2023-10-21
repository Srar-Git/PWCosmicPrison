package cn.pixelwar.pwlevel.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BoosterDoneEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private String type;
    private double multi;

    public Player getPlayer() {
        return player;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public BoosterDoneEvent(boolean isAsync, Player player, String type, double multi) {
        super(isAsync);
        this.player = player;
        this.type = type;
        this.multi = multi;
    }

    public String getType() {
        return type;
    }

    public double getMulti() {
        return multi;
    }
}
