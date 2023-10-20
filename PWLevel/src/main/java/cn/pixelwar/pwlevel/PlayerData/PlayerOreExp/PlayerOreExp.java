package cn.pixelwar.pwlevel.PlayerData.PlayerOreExp;

public class PlayerOreExp {

    private long ironExp;
    private long lapisExp;
    private long redstoneExp;
    private long goldExp;
    private long diamondExp;
    private long emeraldExp;

    public long getIronExp() {
        return ironExp;
    }

    public long getLapisExp() {
        return lapisExp;
    }

    public long getRedstoneExp() {
        return redstoneExp;
    }

    public long getGoldExp() {
        return goldExp;
    }

    public long getDiamondExp() {
        return diamondExp;
    }

    public long getEmeraldExp() {
        return emeraldExp;
    }

    public void setIronExp(long ironExp) {
        this.ironExp = ironExp;
    }

    public void setLapisExp(long lapisExp) {
        this.lapisExp = lapisExp;
    }

    public void setRedstoneExp(long redstoneExp) {
        this.redstoneExp = redstoneExp;
    }

    public void setGoldExp(long goldExp) {
        this.goldExp = goldExp;
    }

    public void setDiamondExp(long diamondExp) {
        this.diamondExp = diamondExp;
    }

    public void setEmeraldExp(long emeraldExp) {
        this.emeraldExp = emeraldExp;
    }

    public PlayerOreExp(long ironExp, long lapisExp, long redstoneExp, long goldExp, long diamondExp, long emeraldExp) {
        this.ironExp = ironExp;
        this.lapisExp = lapisExp;
        this.redstoneExp = redstoneExp;
        this.goldExp = goldExp;
        this.diamondExp = diamondExp;
        this.emeraldExp = emeraldExp;
    }
}
