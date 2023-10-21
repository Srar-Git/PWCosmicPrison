package cn.pixelwar.pwblockmanager.BlockStuff;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ResetingBlockData {

    private Block block;
    private Material originMaterial;
    private Material resettingMaterial;
    private double leftTimeTobeReset;

    public ResetingBlockData(Block block, Material originMaterial, Material resettingMaterial, double leftTimeTobeReset) {
        this.block = block;
        this.originMaterial = originMaterial;
        this.resettingMaterial = resettingMaterial;
        this.leftTimeTobeReset = leftTimeTobeReset;
    }

    public Block getBlock() {
        return block;
    }

    public Material getOriginMaterial() {
        return originMaterial;
    }

    public Material getResettingMaterial() {
        return resettingMaterial;
    }

    public double getLeftTimeTobeReset() {
        return leftTimeTobeReset;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setOriginMaterial(Material originMaterial) {
        this.originMaterial = originMaterial;
    }

    public void setResettingMaterial(Material resettingMaterial) {
        this.resettingMaterial = resettingMaterial;
    }

    public void setLeftTimeTobeReset(double leftTimeTobeReset) {
        this.leftTimeTobeReset = leftTimeTobeReset;
    }
}
