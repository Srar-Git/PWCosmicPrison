package cn.pixelwar.pwblockmanager.customevents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BlockBrokenEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private Player player;
    private Block block;
    private String blockFace;

    public Player getPlayer() {
        return player;
    }

    public String getBlockFace() {
        return blockFace;
    }

    public Block getBlock() {
        return block;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public BlockBrokenEvent(Player player, Block block, String blockFace) {
        this.player = player;
        this.block = block;
        this.blockFace = blockFace;
    }

}