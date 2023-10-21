package cn.pixelwar.pwlevel.PlayerData.PlayerOreExp;

import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerOreExpDataManager {
    PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
    private static Map<String, PlayerOreExp> playerOreExpDataMap = new ConcurrentHashMap<>();

    public static Map<String, PlayerOreExp> getPlayerOreExpDataMap() {
        return playerOreExpDataMap;
    }

    public float getOreExpPercent(Player player, String type) {
        long nextExp = PWLevel.config.getLong("levels.level" + (playerExpDataManager.getLevel(player) + 1));
        nextExp = nextExp * (1 + playerExpDataManager.getPrestige(player));
        long lastExp = PWLevel.config.getLong("levels.level" + playerExpDataManager.getLevel(player));
        long totalneed = nextExp - lastExp;
        float percent;
        switch (type) {
            case "iron":
                long ironExp = playerOreExpDataMap.get(player.getName()).getIronExp();
                percent = ((float) ironExp / totalneed);
                break;
            case "lapis":
                long lapisExp = playerOreExpDataMap.get(player.getName()).getLapisExp();
                percent = ((float) lapisExp / totalneed);
                break;
            case "redstone":
                long redstoneExp = playerOreExpDataMap.get(player.getName()).getRedstoneExp();
                percent = ((float) redstoneExp / totalneed);
                break;
            case "gold":
                long goldExp = playerOreExpDataMap.get(player.getName()).getGoldExp();
                percent = ((float) goldExp / totalneed);
                break;
            case "diamond":
                long diamondExp = playerOreExpDataMap.get(player.getName()).getDiamondExp();
                percent = ((float) diamondExp / totalneed);
                break;
            case "emerald":
                long emeraldExp = playerOreExpDataMap.get(player.getName()).getEmeraldExp();
                percent = ((float) emeraldExp / totalneed);
                break;
            default:
                percent = 0f;
        }
        if (percent >= 0.7) {
            percent = 0.7f;
        }
        return (float) (1 - percent);
    }

    public void addOreExp(Player player, long exp, String type) {
        if ((playerOreExpDataMap.containsKey(player.getName()))) {
            PlayerOreExp playerOreExp;
            switch (type) {
                case "iron":
                    playerOreExp = new PlayerOreExp(playerOreExpDataMap.get(player.getName()).getIronExp() + exp, 0, 0, 0, 0, 0);
                    playerOreExpDataMap.put(player.getName(), playerOreExp);
//                playerOreExpDataMap.get(player.getName()).setIronExp(newIronExp);
                    break;
                case "lapis":
                    playerOreExp = new PlayerOreExp(0, playerOreExpDataMap.get(player.getName()).getLapisExp() + exp, 0, 0, 0, 0);
                    playerOreExpDataMap.put(player.getName(), playerOreExp);
                    break;
                case "redstone":
                    playerOreExp = new PlayerOreExp(0, 0, playerOreExpDataMap.get(player.getName()).getRedstoneExp() + exp, 0, 0, 0);
                    playerOreExpDataMap.put(player.getName(), playerOreExp);
                    break;
                case "gold":
                    playerOreExp = new PlayerOreExp(0, 0, 0, playerOreExpDataMap.get(player.getName()).getGoldExp() + exp, 0, 0);
                    playerOreExpDataMap.put(player.getName(), playerOreExp);
                    break;
                case "diamond":
                    playerOreExp = new PlayerOreExp(0, 0, 0, 0, playerOreExpDataMap.get(player.getName()).getDiamondExp() + exp, 0);
                    playerOreExpDataMap.put(player.getName(), playerOreExp);
                    break;
                case "emerald":
                    playerOreExp = new PlayerOreExp(0, 0, 0, 0, 0, playerOreExpDataMap.get(player.getName()).getEmeraldExp() + exp);
                    playerOreExpDataMap.put(player.getName(), playerOreExp);
                    break;
            }
        } else {
            PlayerOreExp playerOreExp = new PlayerOreExp(0, 0, 0, 0, 0, 0);
            playerOreExpDataMap.put(player.getName(), playerOreExp);
        }


    }

    public void resetOreExp(Player player) {
        PlayerOreExp playerOreExp = new PlayerOreExp(0, 0, 0, 0, 0, 0);
        playerOreExpDataMap.put(player.getName(), playerOreExp);
    }
}
