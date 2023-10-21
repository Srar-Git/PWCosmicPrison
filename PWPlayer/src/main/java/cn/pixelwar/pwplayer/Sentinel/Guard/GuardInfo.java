package cn.pixelwar.pwplayer.Sentinel.Guard;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.PrimitiveIterator;

public class GuardInfo {

    private Location spawnLocation;
    private double health;
    private float walkSpeed;
    private int attackRate;

    public GuardInfo(Location spawnLocation, double health, float walkSpeed, int attackRate) {
        this.spawnLocation = spawnLocation;
        this.health = health;
        this.walkSpeed = walkSpeed;
        this.attackRate = attackRate;
    }

    public GuardInfo(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public double getHealth() {
        return health;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public int getAttackRate() {
        return attackRate;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public void setAttackRate(int attackRate) {
        this.attackRate = attackRate;
    }
}
