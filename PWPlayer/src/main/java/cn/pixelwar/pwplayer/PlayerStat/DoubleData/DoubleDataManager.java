package cn.pixelwar.pwplayer.PlayerStat.DoubleData;

import cn.pixelwar.pwplayer.PlayerStat.IntData.IntData;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class DoubleDataManager {

    public static ConcurrentHashMap<String, DoubleData> DoubleDataMap = new ConcurrentHashMap<>();

    public static double getDoubleData(Player player, DoubleDataType doubleDataType) {
        return DoubleDataMap.get(player.getName()).singleMap.get(doubleDataType);
    }

}
