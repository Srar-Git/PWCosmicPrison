package cn.pixelwar.pwlevel.PlayerData.PlayerExp;

public class PlayerExpData {

    private long totalExp;
    private int level;
    private int prestige;

    public long getTotalExp() {
        return totalExp;
    }

    public int getLevel() {
        return level;
    }

    public int getPrestige() {
        return prestige;
    }

    public void setTotalExp(long totalExp) {
        this.totalExp = totalExp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPrestige(int prestige) {
        this.prestige = prestige;
    }

    public PlayerExpData(long totalExp, int level, int prestige) {
        this.totalExp = totalExp;
        this.level = level;
        this.prestige = prestige;
    }
}
