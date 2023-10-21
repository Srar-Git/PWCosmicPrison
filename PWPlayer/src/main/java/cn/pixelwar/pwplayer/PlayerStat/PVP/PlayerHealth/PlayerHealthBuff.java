package cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth;

public class PlayerHealthBuff {

    private double buffHealth;
    private int buffTime;

    public PlayerHealthBuff(double buffHealth, int buffTime) {
        this.buffHealth = buffHealth;
        this.buffTime = buffTime;
    }

    public double getBuffHealth() {
        return buffHealth;
    }

    public int getBuffTime() {
        return buffTime;
    }

    public void setBuffHealth(double buffHealth) {
        this.buffHealth = buffHealth;
    }

    public void setBuffTime(int buffTime) {
        this.buffTime = buffTime;
    }
}
