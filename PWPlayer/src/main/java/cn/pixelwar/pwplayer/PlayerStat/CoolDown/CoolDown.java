package cn.pixelwar.pwplayer.PlayerStat.CoolDown;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CoolDown {

    private ConcurrentHashMap<CoolDownType, Integer> oneCoolDownMap = new ConcurrentHashMap<>();

    public CoolDown() {
    }

    public void updateCoolDown(CoolDownType coolDownType, int time) {
        this.oneCoolDownMap.put(coolDownType, time);
    }

    public void removeCoolDown(CoolDownType coolDownType) {
        this.oneCoolDownMap.remove(coolDownType);
    }

    public int getCoolDown(CoolDownType coolDownType) {
        return this.oneCoolDownMap.get(coolDownType);
    }

    public ConcurrentHashMap<CoolDownType, Integer> getOneCoolDownMap() {
        return this.oneCoolDownMap;
    }

    public void removeOneSeconds() {
        this.oneCoolDownMap.forEach(
                (k, v) -> {
                    this.oneCoolDownMap.put(k, v - 1);
                    if (v <= 0) {
                        this.oneCoolDownMap.remove(k);
                    }
                }
        );
    }

}
