package cn.pixelwar.pwblockmanager.customevents;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class JinghuaDownEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Location location;
    private Player player;
    private Material Jinghua;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Material getJinghua() {
        return Jinghua;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Location getLocation() {
        return location;
    }

    public JinghuaDownEvent(Location location, Material jinghua, Player player) {
        this.location = location;
        this.Jinghua = jinghua;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
