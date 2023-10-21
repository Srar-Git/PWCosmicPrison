package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import cn.pixelwar.pwblockmanager.BlockStuff.enchants.Baoji;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import cn.pixelwar.pwblockmanager.customevents.BlockBrokenEvent;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnBlockBroken implements Listener {
    Baoji baoji = new Baoji();
    PlayerData playerData = new PlayerData();
    BlockAnimationData blockAnimationData = new BlockAnimationData();

    @EventHandler
    public void onBlockBroken(BlockBrokenEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        String blockFace = event.getBlockFace();
        String locString = event.getPlayer() + " " + block.getLocation();
        int crit = playerData.getCrit(event.getPlayer());
        try {
            if (blockAnimationData.ifBlockActive(locString)) {
                playerData.updateCritData(event.getPlayer(), 1 + baoji.getBaoji(player));
            } else {
                int removecrit = (int) Math.floor(crit * 0.2);
                playerData.updateCritData(event.getPlayer(), 0 - removecrit);
            }
        } catch (NullPointerException e) {
        }

        if (!(block.getType().equals(Material.NETHER_QUARTZ_ORE))) {
            playerData.clearPlayerDigData(event.getPlayer());
        }

        blockAnimationData.resetBlock(locString, event.getPlayer());

    }


}
