package cn.pixelwar.pwlevel.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LevelUpEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private int level;

    public Player getPlayer() {
        return player;
    }

    public int getlevel() {
        return level;
    }


    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public LevelUpEvent(Player player, int level) {
        this.player = player;
        this.level = level;
    }

}
