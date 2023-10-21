package cn.pixelwar.pwplayer.PlayerStat.DoubleData;

import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;

import java.util.concurrent.ConcurrentHashMap;

public class DoubleData {
    public ConcurrentHashMap<DoubleDataType, Double> singleMap = new ConcurrentHashMap<>();

    public DoubleData() {

    }

    public void addNum(DoubleDataType doubleDataType, double num) {
        singleMap.put(doubleDataType, singleMap.get(doubleDataType) + num);
    }

    public void setNum(DoubleDataType doubleDataType, double num) {
        singleMap.put(doubleDataType, num);
    }

    public void removeNum(DoubleDataType doubleDataType, double num) {
        if ((singleMap.get(doubleDataType) - num) < 0) {
            singleMap.put(doubleDataType, 0.0);
            return;
        }
        singleMap.put(doubleDataType, singleMap.get(doubleDataType) - num);
    }

    public void clearNum(DoubleDataType doubleDataType) {
        singleMap.put(doubleDataType, 0.0);
    }


}
