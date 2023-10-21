package cn.pixelwar.pwpacketstuff.customevents;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MeteorLandEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Location location;

    public Location getLocation() {
        return location;
    }

    public MeteorLandEvent(Location location) {
        this.location = location;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }


}
