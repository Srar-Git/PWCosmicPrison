package cn.pixelwar.pwitemmanager.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomItems {

    private static File file;
    private static FileConfiguration itemFile;

    public static void setupItemFile() {

        file = new File(Bukkit.getServer().getPluginManager().getPlugin("PWItemManager").getDataFolder(), "items.yml");

        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                //啊啊啊啊
            }
        }

        itemFile = YamlConfiguration.loadConfiguration(file);


    }

    public static FileConfiguration getItemFile() {
        return itemFile;
    }

    public static void save() {
        try {
            itemFile.save(file);
        } catch (IOException e) {
            System.out.println("保存Items文件错误");
        }
    }


}
