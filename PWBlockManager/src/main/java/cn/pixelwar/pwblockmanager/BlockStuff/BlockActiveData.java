package cn.pixelwar.pwblockmanager.BlockStuff;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BlockActiveData {
    private Player player;
    private boolean isActive;
    private Location activeLoc;


    public Player getPlayer() {
        return player;
    }

    public boolean isActive() {
        return isActive;
    }

    public Location getActiveLoc() {
        return activeLoc;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public BlockActiveData(Player player, boolean isActive, Location activeLoc) {
        this.player = player;
        this.isActive = isActive;
        this.activeLoc = activeLoc;
    }
}
