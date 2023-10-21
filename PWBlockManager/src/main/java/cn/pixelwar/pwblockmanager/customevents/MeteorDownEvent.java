package cn.pixelwar.pwblockmanager.customevents;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MeteorDownEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Location location;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Location getLocation() {
        return location;
    }

    public MeteorDownEvent(Location location, Player player) {
        this.location = location;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
