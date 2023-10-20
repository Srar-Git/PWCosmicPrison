package cn.pixelwar.pwlevel;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import cn.pixelwar.pwlevel.CustomEvents.CustomListeners.OnPlayerLevelUP;
import cn.pixelwar.pwlevel.DisabledEvents.*;
import cn.pixelwar.pwlevel.Listeners.OnBoosterDone;
import cn.pixelwar.pwlevel.Listeners.OnPlayerJoin;
import cn.pixelwar.pwlevel.Listeners.OnPlayerQuit;
import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import cn.pixelwar.pwlevel.Utils.SaveData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PWLevel extends JavaPlugin {
    private static Plugin plugin;
    PWLevel instance;
    public static FileConfiguration config;
    SkriptAddon addon;

    private static String connectionURL;

    public static String getConnectionURL() {
        return connectionURL;
    }

    public static Plugin getPlugin() {
        return plugin;}

    @Override
    public void onEnable() {
//        Levels.setupLevelFile();
//        Levels.getLevelFile().options().copyDefaults(true);
//        Levels.saveLevelFile();
//        System.out.println("level1xp: " + Levels.getLevelFile().getLong("levels.l1xp"));
        instance = this;
        this.addon = Skript.registerAddon(this);
        plugin = this;
        setupConfig();
        RegisterEvents();
        PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
        playerBoosterDataManager.BoosterTimer();
//        connectionURL = "jdbc:h2:" + getDataFolder().getAbsolutePath() + "/LevelData/PlayerDB";
//        Database.InitializeDatabase();

        try {
            addon.loadClasses("cn.pixelwar.pwlevel");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[PixelWarSKAddon-Level] has been enabled!");
        SaveData saveData = new SaveData();
        saveData.savingTimer();
    }
    public SkriptAddon getAddonInstance() {
        return addon;
    }
    @Override
    public void onDisable() {
        SaveData saveData = new SaveData();
        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
            saveData.saveData(player);
        }
    }

    public void RegisterEvents(){
        getServer().getPluginManager().registerEvents((Listener)new OnPlayerLevelUP(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnBlockBreak(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnEntityDeath(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnExpBottleDrop(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnFishEvent(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnFurnaceExp(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnPlayerExpChange(), (Plugin)this);
        getServer().getPluginManager().registerEvents((Listener)new OnPlayerDeath(), (Plugin)this);
//        getServer().getPluginManager().registerEvents((Listener)new OnAsyncPlayerJoin(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new OnBoosterDone(), this);
    }


    private void setupConfig(){
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
    }
}
