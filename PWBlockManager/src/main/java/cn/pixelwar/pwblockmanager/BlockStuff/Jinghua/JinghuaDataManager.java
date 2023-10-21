package cn.pixelwar.pwblockmanager.BlockStuff.Jinghua;

import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorData;
import cn.pixelwar.pwblockmanager.customevents.JinghuaDownEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JinghuaDataManager {

    private static Map<Location, JinghuaData> JinghuaDataMap = new ConcurrentHashMap<>();
    private static Map<String, Integer> JinghuaAmountDataMap = new ConcurrentHashMap<>();

    public static Map<String, Integer> getJinghuaAmountDataMap() {
        return JinghuaAmountDataMap;
    }

    public void setTypeAmount(String type, int amount) {
        JinghuaAmountDataMap.put(type, amount);
    }

    public int getTypeAmount(String type) {
        if (JinghuaAmountDataMap.containsKey(type)) {
            return JinghuaAmountDataMap.get(type);
        }
        return 0;
    }


    public static Map<Location, JinghuaData> getJinghuaDataMap() {
        return JinghuaDataMap;
    }

    public void createJinghuaData(Location location, int amount, int totalAmount, int armorStandID, Material originalType, Material jinghuaType, Material item) {
        JinghuaData jinghuaData = new JinghuaData(amount, totalAmount, armorStandID, originalType, jinghuaType, item);
        JinghuaDataMap.put(location, jinghuaData);
    }

    public void subtractAmount(Location location, int amount, Player player) {
        if (JinghuaDataMap.containsKey(location)) {
            amount = JinghuaDataMap.get(location).getAmount() - amount;
            if (amount <= 0) {
                Bukkit.getServer().getPluginManager().callEvent(new JinghuaDownEvent(location, JinghuaDataMap.get(location).getJinghuaType(), player));
                return;
            }
            setAmount(location, amount);
        }

    }

    public void setAmount(Location location, int amount) {
        if (JinghuaDataMap.containsKey(location)) {
            JinghuaDataMap.get(location).setAmount(amount);
        } else {
            return;
        }
    }

    public int getAmount(Location location) {
        if (JinghuaDataMap.containsKey(location)) {
            return JinghuaDataMap.get(location).getAmount();
        } else {
            return 0;
        }
    }

    public int getTotalAmount(Location location) {
        if (JinghuaDataMap.containsKey(location)) {
            return JinghuaDataMap.get(location).getTotalAmount();
        } else {
            return 0;
        }
    }

    public int getID(Location location) {
        if (JinghuaDataMap.containsKey(location)) {
            return JinghuaDataMap.get(location).getArmorStandID();
        } else {
            return 0;
        }
    }

    public Material getOriginalType(Location location) {
        if (JinghuaDataMap.containsKey(location)) {
            return JinghuaDataMap.get(location).getOriginalType();
        } else {
            return null;
        }
    }

    public Material getJinghuaType(Location location) {
        if (JinghuaDataMap.containsKey(location)) {
            return JinghuaDataMap.get(location).getJinghuaType();
        } else {
            return null;
        }
    }

    public Material getItem(Location location) {
        if (JinghuaDataMap.containsKey(location)) {
            return JinghuaDataMap.get(location).getItem();
        } else {
            return null;
        }
    }


}
