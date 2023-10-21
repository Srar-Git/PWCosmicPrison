package cn.pixelwar.pwplayer.PlayerStat.Home;

import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDown;
import org.bukkit.Location;

import java.util.concurrent.ConcurrentHashMap;

public class HomeData {
    //home name，loc
    private ConcurrentHashMap<String, Location> onePlayerHomeMap = new ConcurrentHashMap<>();


    public void addHome(String name, Location location) {
        this.onePlayerHomeMap.put(name, location);
    }

    public void delHome(String name) {
        this.onePlayerHomeMap.remove(name);
    }


    public ConcurrentHashMap<String, Location> getOnePlayerHomeMap() {
        return onePlayerHomeMap;
    }

}
