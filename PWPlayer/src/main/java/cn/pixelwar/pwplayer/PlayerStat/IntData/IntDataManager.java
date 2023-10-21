package cn.pixelwar.pwplayer.PlayerStat.IntData;

import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class IntDataManager {

    public static ConcurrentHashMap<String, IntData> IntDataMap = new ConcurrentHashMap<>();

    public static int getIntData(Player player, IntDataType intDataType) {
        return IntDataMap.get(player.getName()).singleMap.get(intDataType);
    }

}
