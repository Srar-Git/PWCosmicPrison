package cn.pixelwar.pwblockmanager.BlockStuff;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockPackData {

    private Block originBlock;
    private Material packetMaterial;
    private int resetTime;

    public BlockPackData(Block originBlock, Material packetMaterial, int resetTime) {
        this.originBlock = originBlock;
        this.packetMaterial = packetMaterial;
        this.resetTime = resetTime;
    }

    public Block getOriginBlock() {
        return originBlock;
    }

    public Material getPacketMaterial() {
        return packetMaterial;
    }

    public int getResetTime() {
        return resetTime;
    }

    public void setPacketMaterial(Material packetMaterial) {
        this.packetMaterial = packetMaterial;
    }

    public void setResetTime(int resetTime) {
        this.resetTime = resetTime;
    }
}
