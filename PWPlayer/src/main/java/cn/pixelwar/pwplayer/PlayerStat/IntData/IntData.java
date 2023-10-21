package cn.pixelwar.pwplayer.PlayerStat.IntData;

import java.util.concurrent.ConcurrentHashMap;

public class IntData {
    public ConcurrentHashMap<IntDataType, Integer> singleMap = new ConcurrentHashMap<>();

    public IntData() {

    }

    public void addNum(IntDataType intDataType, int num) {
        singleMap.put(intDataType, singleMap.get(intDataType) + num);
    }

    public void setNum(IntDataType intDataType, int num) {
        singleMap.put(intDataType, num);
    }

    public void removeNum(IntDataType intDataType, int num) {
        if ((singleMap.get(intDataType) - num) < 0) {
            singleMap.put(intDataType, 0);
            return;
        }
        singleMap.put(intDataType, singleMap.get(intDataType) - num);
    }

    public void clearNum(IntDataType intDataType) {
        singleMap.put(intDataType, 0);
    }


}
