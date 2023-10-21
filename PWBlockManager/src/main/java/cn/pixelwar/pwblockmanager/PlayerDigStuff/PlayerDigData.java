package cn.pixelwar.pwblockmanager.PlayerDigStuff;

import org.bukkit.block.Block;

import java.util.Date;

public class PlayerDigData {

    private Date lastDigTime;
    private float blockDamage;
    private Block digBlock;

    public String getBlockFace() {
        return blockFace;
    }

    private String blockFace;

    public Date getLastDigTime() {
        return lastDigTime;
    }

    public float getBlockDamage() {
        return blockDamage;
    }

    public Block getDigBlock() {
        return digBlock;
    }

    public void setLastDigTime() {
        this.lastDigTime = new Date();
    }


    public PlayerDigData(float blockDamage, Block digBlock, String blockFace) {
        this.lastDigTime = new Date();
        this.blockDamage = blockDamage;
        this.digBlock = digBlock;
        this.blockFace = blockFace;
    }


}
