package cn.pixelwar.pwblockmanager.BlockStuff;

import org.bukkit.block.Block;

import java.util.Date;

public class BlockData {

    private Date lastDamageTime;
    private float blockDamage;
    private Block block;


    public float getBlockDamage() {
        return blockDamage;
    }

    public Date getLastDamageTime() {
        return lastDamageTime;
    }

    public Block getBlock() {
        return block;
    }

    public BlockData(Date lastDamageTime, float blockDamage, Block block) {
        this.lastDamageTime = lastDamageTime;
        this.blockDamage = blockDamage;
        this.block = block;
    }
}
