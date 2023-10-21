package cn.pixelwar.pwplayer.Sentinel.Guard;

public enum GuardType {

    WARDEN("银河守卫", 1000, 15, 3),
    WARDEN2("裁决者", 500, 20, 2.2),
    WARDEN3("执法者", 200, 25, 1.6),
    WARDEN4("守卫", 100, 30, 1),
    ;
    private final String name;
    private final double health;
    private final int attackRate;
    private final double speed;

    GuardType(String name, double health, int attackRate, double speed) {
        this.name = name;
        this.health = health;
        this.attackRate = attackRate;
        this.speed = speed;
    }

    public static GuardType getWardenWithName(String name) {
        for (GuardType guardType : GuardType.values()) {
            if (name.contains(guardType.getName())) {
                return guardType;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public int getAttackRate() {
        return attackRate;
    }

    public double getSpeed() {
        return speed;
    }
}
