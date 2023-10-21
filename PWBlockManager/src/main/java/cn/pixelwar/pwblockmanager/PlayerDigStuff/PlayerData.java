package cn.pixelwar.pwblockmanager.PlayerDigStuff;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerData {

    private static Map<Player, PlayerDigData> playerDigDataMap = new ConcurrentHashMap<>();
    private static Map<Player, PlayerCritData> playerCritDataMap = new ConcurrentHashMap<>();

    public static Map<Player, PlayerDigData> getPlayerDigData() {
        return playerDigDataMap;
    }

    public static Map<Player, PlayerCritData> getPlayerCritData() {
        return playerCritDataMap;
    }

    public void updatePlayerData(float damageSpeed, Block block, Player player, String blockFace) {
        PlayerDigData playerDigData = new PlayerDigData(damageSpeed, block, blockFace);
        playerDigDataMap.put(player, playerDigData);
    }

    public Block getIsDigBlock(Player player) {
        return playerDigDataMap.get(player).getDigBlock();
    }

    public String getIsDigBlockFace(Player player) {
        return playerDigDataMap.get(player).getBlockFace();
    }

    public float getDigSpeed(Player player) {
        return playerDigDataMap.get(player).getBlockDamage();
    }

    public int getCrit(Player player) {
        return playerCritDataMap.get(player).getCritNum();
    }

    public float getSpeedMultiple(Player player) {
        return playerCritDataMap.get(player).getSpeedMultiple();
    }

    public void updateCritData(Player player, int addNum) {


        if (!(playerCritDataMap.containsKey(player))) {
            PlayerCritData playerCritData = new PlayerCritData(0, 1.25f);
            playerCritDataMap.put(player, playerCritData);
        } else {
            int critNow = addNum + playerCritDataMap.get(player).getCritNum();
            if (critNow < 0) critNow = 0;
            double speedMultipleNodecimal = Math.sqrt(critNow) / 10;
            BigDecimal speedMultipleBigDecimal = new BigDecimal(speedMultipleNodecimal);
            double speedMultipleRounded = speedMultipleBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double speedMultiple = (speedMultipleRounded + 25) / 100 + 1;

            PlayerCritData playerCritData = new PlayerCritData(critNow, (float) speedMultiple);
            playerCritDataMap.put(player, playerCritData);
            if (addNum >= 0) {
                sendActionbar(player, "连击: " + ChatColor.AQUA + playerCritDataMap.get(player).getCritNum() + ChatColor.GRAY + "(" + ChatColor.GREEN + "+" + addNum + ChatColor.GRAY + ")" + " □ " + ChatColor.WHITE + "挖掘加速: " + ChatColor.GOLD + (speedMultipleRounded + 25) + "%" + ChatColor.GRAY + " □ " + ChatColor.WHITE + "暴击概率: " + ChatColor.LIGHT_PURPLE + speedMultipleRounded + "%");
            } else {
                sendActionbar(player, "连击: " + ChatColor.AQUA + playerCritDataMap.get(player).getCritNum() + ChatColor.GRAY + "(" + ChatColor.RED + addNum + ChatColor.GRAY + ")" + " □ " + ChatColor.WHITE + "挖掘加速: " + ChatColor.GOLD + (speedMultipleRounded + 25) + "%" + ChatColor.GRAY + " □ " + ChatColor.WHITE + "暴击概率: " + ChatColor.LIGHT_PURPLE + speedMultipleRounded + "%");
            }


        }


    }


    public static void sendActionbar(Player player, String msg) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(msg));
    }


    public void clearPlayerDigData(Player player) {
        playerDigDataMap.remove(player);
    }


}
