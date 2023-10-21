package cn.pixelwar.pwlevel.PlayerData.PlayerBooster;

public class PlayerShardBoosterData {
    private int ShardBoosterTime;
    private double ShardMultiple;

    public int getShardBoosterTime() {
        return ShardBoosterTime;
    }


    public double getShardMultiple() {
        return ShardMultiple;
    }


    public void setShardBoosterTime(int shardBoosterTime) {
        ShardBoosterTime = shardBoosterTime;
    }


    public void setShardMultiple(double ShardMultiple) {
        ShardMultiple = ShardMultiple;
    }


    public PlayerShardBoosterData(int ShardBoosterTime, double ShardMultiple) {
        this.ShardBoosterTime = ShardBoosterTime;
        this.ShardMultiple = ShardMultiple;
    }
}
