package cn.pixelwar.pwblockmanager;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.JinghuaFile;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.Meteor;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorFile;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorTimer;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import cn.pixelwar.pwblockmanager.listeners.*;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import cn.pixelwar.pwblockmanager.BlockStuff.ResttingBlockDataManager;
import cn.pixelwar.pwpacketstuff.CustomListeners.PowerballHitBlockListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PWBlockManager extends JavaPlugin implements Listener {
    MeteorDataManager meteorDataManager = new MeteorDataManager();
    MeteorFile meteorFile = new MeteorFile();
    MeteorTimer meteorTimer = new MeteorTimer();
    JinghuaFile jinghuaFile = new JinghuaFile();
    ResttingBlockDataManager resttingBlockDataManager = new ResttingBlockDataManager();
    PlayerData playerData = new PlayerData();
    BlockAnimationData blockAnimationData = new BlockAnimationData();
    private static Plugin plugin;
    SkriptAddon addon;
    public static FileConfiguration config;

    public static Plugin getPlugin() {
        return plugin;
    }


    @Override
    public void onEnable() {
        plugin = this;
        setupConfig();
        registerEvents();
        blockAnimationData.timer();
        resttingBlockDataManager.blockResetTimer();
        setupMeteor();
        setupJinghua();

//        使用packet实现方块效果
//        packetDataManager.packetTimer();

        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("cn.pixelwar.pwblockmanager");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[PixelWarSKAddon-BlockManager] has been enabled!");

    }

    @Override
    public void onDisable() {
        blockAnimationData.clearBlockAnimationData();
        resttingBlockDataManager.clearResettings();
        meteorFile.saveAllMeteor();
        jinghuaFile.saveAllJinghua();
    }

    private void setupConfig() {
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
    }

    private void setupMeteor() {
        meteorFile.CheckMeteorFile();
        meteorFile.CreateRegions();
        meteorFile.CreateAllDataMap();
        meteorTimer.meteorTimer();
    }

    private void setupJinghua() {
        jinghuaFile.CheckJinghuaFile();
        jinghuaFile.CreateAllDataMap();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new OnPlayerArmSwing(), this);
        getServer().getPluginManager().registerEvents(new OnClickOnBlock(), this);
        getServer().getPluginManager().registerEvents(new OnMeteorDown(), this);
        getServer().getPluginManager().registerEvents(new OnJinghuaDown(), this);
        getServer().getPluginManager().registerEvents(new OnMeteorLand(), this);
        getServer().getPluginManager().registerEvents(new Onjoin(), this);
        getServer().getPluginManager().registerEvents(new Onquit(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBroken(), this);
        getServer().getPluginManager().registerEvents(new PowerballHitBlockListener(), this);
    }

    //    @EventHandler
//    public void Onclick(PlayerInteractEvent event){
//        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//            Location location = event.getClickedBlock().getLocation();
//            Meteor meteor = new Meteor();
//            meteor.randomSpawnMeteor(Bukkit.getWorld("world"));
//        }
//    }
    @EventHandler
    public void Onbreak(BlockBreakEvent event) {
        Location loc = event.getBlock().getLocation();

        if (MeteorDataManager.getMeteorDataMap().containsKey(loc)) {
            Meteor manager = new Meteor();
            manager.removeMeteor(loc);
        }
    }

}
