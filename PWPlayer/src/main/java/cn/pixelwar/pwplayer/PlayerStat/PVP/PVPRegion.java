package cn.pixelwar.pwplayer.PlayerStat.PVP;

import ch.njol.skript.hooks.regions.WorldGuardHook;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.Utils.GetWGRegion;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

import static java.util.Arrays.asList;

public enum PVPRegion {

    GREEN(
            ChatColorCast.format("&a&l绝对安全区"),
            asList(
                    "银河守卫"
            ),
            asList(
                    "spawn",
                    "spawncoal",
                    "ironspawn",
                    "coalmine",
                    "lapisspawn",
                    "redstonespawn",
                    "goldspawn",
                    "diamondspawn",
                    "emeraldspawn"
            ),
            35
    ),
    BLUE(
            ChatColorCast.format("&b&l安全区"),
            asList(
                    "裁决者"
            ),
            asList(
                    "ironmine",
                    "lapismine"
            ),
            30
    ),
    GOLD(
            ChatColorCast.format("&6&l中等安全区"),
            asList(
                    "执法者"
            ),
            asList(
                    "redstonemine",
                    "goldmine"
            ),
            25
    ),
    YELLOW(
            ChatColorCast.format("&e&l低安全区"),
            asList(
                    "守卫"
            ),
            asList(
                    "diamondmine"
            ),
            20
    ),
    BLACK(
            ChatColorCast.format("&c&l非安全区"),
            asList(
            ),
            asList(
            ),
            15
    );

    private String name;
    //哪些guard包含在这个pvp区域里
    private List<String> guardName;
    private List<String> WGRegionName;
    private int pvpTagTime;

    PVPRegion(String name, List<String> guardName, List<String> WGRegionName, int pvpTagTime) {
        this.name = name;
        this.guardName = guardName;
        this.WGRegionName = WGRegionName;
        this.pvpTagTime = pvpTagTime;
    }

    public String getName() {
        return name;
    }

    public int getPvpTagTime() {
        return pvpTagTime;
    }

    public List<String> getGuardName() {
        return guardName;
    }

    public List<String> getWGRegionName() {
        return WGRegionName;
    }

    public static PVPRegion getPVPRegionWithGuardName(String name) {
        for (PVPRegion pvpRegion : PVPRegion.values()) {
            for (String n : pvpRegion.getGuardName()) {
                if (name.contains(n)) {
                    return pvpRegion;
                }
            }
        }
        return PVPRegion.BLACK;
    }

    public static PVPRegion getPVPRegionWithWGRegionName(String name) {
        for (PVPRegion pvpRegion : PVPRegion.values()) {
            for (String n : pvpRegion.getWGRegionName()) {
                if (name.equals(n)) {
                    return pvpRegion;
                }
            }
        }
        return PVPRegion.BLACK;
    }

    public static PVPRegion getPVPRegionWithWGRegionName(Player player) {
        ApplicableRegionSet regionSet = GetWGRegion.getWGRegion(player);
        for (ProtectedRegion region : regionSet) {
            String name = region.getId();
            for (PVPRegion pvpRegion : PVPRegion.values()) {
                for (String n : pvpRegion.getWGRegionName()) {
                    if (name.equals(n)) {
                        return pvpRegion;
                    }
                }
            }
        }
        return PVPRegion.BLACK;
    }

    public static PVPRegion checkPlayerPVPRegion(Player player) {
        for (Entity en : player.getNearbyEntities(20, 20, 20)) {
            if (en instanceof Player) {
                //如果站在旁边的是守卫
                if (GuardManager.checkIfEntityIsGuard(en)) {
                    String name = ((Player) en).getName();
                    return PVPRegion.getPVPRegionWithGuardName(name);
                }
            }
        }
        return PVPRegion.BLACK;
    }
}
