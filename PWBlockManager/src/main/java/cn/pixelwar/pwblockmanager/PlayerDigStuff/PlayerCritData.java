package cn.pixelwar.pwblockmanager.PlayerDigStuff;


public class PlayerCritData {

    private int critNum;
    private float speedMultiple;

    public int getCritNum() {
        return critNum;
    }

    public float getSpeedMultiple() {
        return speedMultiple;
    }

    public PlayerCritData(int critNum, float speedMultiple) {
        this.critNum = critNum;
        this.speedMultiple = speedMultiple;
    }
}
