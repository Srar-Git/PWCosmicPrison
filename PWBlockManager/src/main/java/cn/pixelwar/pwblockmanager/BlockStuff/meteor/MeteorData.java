package cn.pixelwar.pwblockmanager.BlockStuff.meteor;

public class MeteorData {

    private int amount;
    private int lastTime;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public int getAmount() {
        return amount;
    }

    public int getLastTime() {
        return lastTime;
    }

    public MeteorData(int amount, int lastTime) {
        this.amount = amount;
        this.lastTime = lastTime;
    }
}
