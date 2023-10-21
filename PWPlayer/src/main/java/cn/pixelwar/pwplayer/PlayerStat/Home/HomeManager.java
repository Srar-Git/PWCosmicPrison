package cn.pixelwar.pwplayer.PlayerStat.Home;

import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDown;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class HomeManager {

    public static ConcurrentHashMap<String, HomeData> playerHomeMap = new ConcurrentHashMap<>();

    public void addHome(Player player, String homeName, Location location) {

        if (!(playerHomeMap.containsKey(player.getName()))) {
            HomeData homeData = new HomeData();
            homeData.addHome(homeName, location);
            playerHomeMap.put(player.getName(), homeData);
            player.sendMessage(ChatColorCast.format("&d▸ &f你已经成功设置家: &d&l" + homeName));
        } else {
            HomeData homeData = playerHomeMap.get(player.getName());
            if (homeData.getOnePlayerHomeMap().size() >= IntDataManager.getIntData(player, IntDataType.HOMEMAXHOME)) {
                player.sendMessage(ChatColorCast.format("&c▸ &f你的家的数量已经达到上限&d&l" + IntDataManager.getIntData(player, IntDataType.HOMEMAXHOME) + "个&7(你可以通过&n商店、宝箱&7等方式解锁更高上限)"));
                return;
            }
            homeData.addHome(homeName, location);
            playerHomeMap.put(player.getName(), homeData);
            player.sendMessage(ChatColorCast.format("&d▸ &f你已经成功设置家: &d&l" + homeName));
        }
    }

    public void delHome(Player player, String homeName) {
        if (playerHomeMap.containsKey(player.getName())) {
            if (playerHomeMap.get(player.getName()).getOnePlayerHomeMap().containsKey(homeName)) {
                playerHomeMap.get(player.getName()).delHome(homeName);
                player.sendMessage(ChatColorCast.format("&d▸ &f你已经成功删除家: &c&l" + homeName));
                return;
            }
            player.sendMessage(ChatColorCast.format("&c▸ &f你没有该名字的家: &c&l" + homeName + " &f请使用&b&l/home list&f查看你的家"));
        }
    }

    public Location getHomeLoc(Player player, String name) {
        if (playerHomeMap.containsKey(player.getName())) {
            if (playerHomeMap.get(player.getName()).getOnePlayerHomeMap().containsKey(name)) {
                return playerHomeMap.get(player.getName()).getOnePlayerHomeMap().get(name);
            }
        }
        return null;
    }

}
