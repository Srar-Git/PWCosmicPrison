package cn.pixelwar.pwblockmanager.BlockStuff;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockPacketTask extends BukkitRunnable {

    private Block block;
    private String packetMaterial;
    private int resetTime;

    public BlockPacketTask(Block block, String packetMaterial, int resetTime) {
        this.block = block;
        this.packetMaterial = packetMaterial;
        this.resetTime = resetTime;
    }

    int i = 0;

    @Override

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendBlockChange(block.getLocation(), Material.matchMaterial(packetMaterial), (byte) 0);
        }
        i++;
        if (i >= resetTime * 20) cancel();
    }

}
