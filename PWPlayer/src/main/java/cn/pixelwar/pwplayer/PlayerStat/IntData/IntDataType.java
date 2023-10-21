package cn.pixelwar.pwplayer.PlayerStat.IntData;

public enum IntDataType {
    CLEARINVONJOIN(0),
    ENCHANTSLOTS(3),
    ENCHANTREROLL(0),
    DIGTOTAL(0),
    DIGCOAL(0),
    DIGIRON(0),
    DIGLAPIS(0),
    DIGREDSTONE(0),
    DIGGOLD(0),
    DIGDIAMOND(0),
    DIGEMERALD(0),
    BREAKMETEOR(0),
    BREAKJINGHUA(0),
    PVPKILL(0),
    PVPDEATH(0),
    TPWAIT(10),
    TPCOOLDOWN(150),
    HOMEMAXHOME(1),

    BANKMONEY(0),
    BANKMAX(50000),
    BANKWITHDRAW(25),  //25%


    ;
    private int defaultNum;

    IntDataType(int defaultNum) {
        this.defaultNum = defaultNum;
    }

    public int getDefaultNum() {
        return defaultNum;
    }
}
