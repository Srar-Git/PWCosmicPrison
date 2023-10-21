package cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerWalkSpeed;

public class PlayerWalkSpeedBuff {

    private float buffSpeed;
    private int buffTime;

    public PlayerWalkSpeedBuff(float buffSpeed, int buffTime) {
        this.buffSpeed = buffSpeed;
        this.buffTime = buffTime;
    }

    public double getBuffWalkSpeed() {
        return buffSpeed;
    }

    public int getBuffTime() {
        return buffTime;
    }

    public void setBuffSpeed(float buffSpeed) {
        this.buffSpeed = buffSpeed;
    }

    public void setBuffTime(int buffTime) {
        this.buffTime = buffTime;
    }
}

