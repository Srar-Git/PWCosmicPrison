package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

import org.bukkit.World;

public class MeteorRegionData {

    private int minx;
    private int maxx;
    private int minz;
    private int maxz;
    private World world;

    public MeteorRegionData(int minx, int maxx, int minz, int maxz, World world) {
        this.minx = minx;
        this.maxx = maxx;
        this.minz = minz;
        this.maxz = maxz;
        this.world = world;
    }

    public void setMinx(int minx) {
        this.minx = minx;
    }

    public void setMaxx(int maxx) {
        this.maxx = maxx;
    }

    public void setMinz(int minz) {
        this.minz = minz;
    }

    public void setMaxz(int maxz) {
        this.maxz = maxz;
    }

    public int getMinx() {
        return minx;
    }

    public int getMaxx() {
        return maxx;
    }

    public int getMinz() {
        return minz;
    }

    public int getMaxz() {
        return maxz;
    }

    public World getWorld() {
        return world;
    }
}
