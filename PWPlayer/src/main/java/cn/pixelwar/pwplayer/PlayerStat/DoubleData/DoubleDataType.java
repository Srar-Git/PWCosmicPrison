package cn.pixelwar.pwplayer.PlayerStat.DoubleData;

public enum DoubleDataType {
    BANKLEVELUP(1.5), //1.5%
    BASICHEALTH(20.0),
    LASTONLINEHEALTH(20.0),//上一次退出时还剩的血量
    BASICWALKSPEED(0.2),
    LASTONLINEWALKSPEED(0.2),//上一次退出时还剩的血量
    BASICARMOR(0.0);
    private double defaultNum;

    DoubleDataType(double defaultNum) {
        this.defaultNum = defaultNum;
    }

    public double getDefaultNum() {
        return defaultNum;
    }
}
