package cn.pixelwar.pwlevel.PlayerData.PlayerBooster;

public class PlayerExpBoosterData {
    private int ExpBoosterTime;
    private double ExpMultiple;

    public int getExpBoosterTime() {
        return ExpBoosterTime;
    }


    public double getExpMultiple() {
        return ExpMultiple;
    }


    public void setExpBoosterTime(int expBoosterTime) {
        ExpBoosterTime = expBoosterTime;
    }


    public void setExpMultiple(double expMultiple) {
        ExpMultiple = expMultiple;
    }


    public PlayerExpBoosterData(int expBoosterTime, double expMultiple) {
        this.ExpBoosterTime = expBoosterTime;
        this.ExpMultiple = expMultiple;
    }
}