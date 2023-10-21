package cn.pixelwar.pwplayer;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import cn.pixelwar.pwplayer.File.YamlStorage;
import cn.pixelwar.pwplayer.Listeners.*;
import cn.pixelwar.pwplayer.Listeners.ArmorEquipEvent.ArmorListener;
import cn.pixelwar.pwplayer.Listeners.MythicMobs.MythicMobAttackListener;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerAction.Teleport.Teleport;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPTagManager;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth.PlayerHealthManager;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerWalkSpeed.PlayerWalkSpeedManager;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardTrait;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCManager;
import net.citizensnpcs.nms.v1_8_R3.entity.EntityHumanNPC;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


    /*
        todoList:
        1. 当玩家退出后如果在被追杀怎么办?(直接重置or设置一个通缉等级or直接战斗时间结束重置)
     */

public final class PWPlayer extends JavaPlugin implements Listener {
    static PWPlayer instance;
    SkriptAddon addon;
    private static Plugin plugin;
    public static int totalPlayerAmount;
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.addon = Skript.registerAddon(this);
        instance = this;
        plugin = this;

        net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(GuardTrait.class).withName("guard"));

        try {
            addon.loadClasses("cn.pixelwar.pwplayer");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[PixelWarSKAddon-Vault] has been enabled!");


        setupTimer();
        setupEvents();
        setupConfig();


        //重置所有的guard
        GuardManager.resetAllGuards();
//        GuardManager.getAllGuardLocation();
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    public static PWPlayer getInstance() {
        return instance;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        updateConfig();

        PlayerNPCManager playerNPCManager = new PlayerNPCManager();
        playerNPCManager.clearAllPlayerNPC();
    }

    private void updateConfig() {
        final FileConfiguration config2 = new YamlConfiguration();
        File configFile = new File("plugins/PWPlayer/config.yml");
        try (InputStreamReader Config = new InputStreamReader(new FileInputStream(configFile), "UTF-8")) {
            config2.load(Config);
        } catch (IOException | InvalidConfigurationException ex) {
        }
        config2.set("players", totalPlayerAmount);
        try {
            config2.save(configFile);
        } catch (IOException ex) {
            System.out.println("config信息保存出错");
        }
    }

    private void setupTimer() {
        CoolDownManager coolDownManager = new CoolDownManager();
        coolDownManager.CoolDowntimer();
        Teleport teleport = new Teleport();
        teleport.tpaTimer();
        PVPTagManager pvpTagManager = new PVPTagManager();
        pvpTagManager.PVPTagTimer();
        PlayerHealthManager playerHealthManager = new PlayerHealthManager();
        playerHealthManager.healthBuffTimer();
        PlayerWalkSpeedManager playerWalkSpeedManager = new PlayerWalkSpeedManager();
        playerWalkSpeedManager.WalkSpeedBuffTimer();
        GuardManager guardManager = new GuardManager();
        guardManager.checkGuardPlayerDistanceTimer();
    }

    private void setupEvents() {
        getServer().getPluginManager().registerEvents((Listener) this, (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new OnJoin(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new OnQuit(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new AttackListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new ClickPlayerListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new OnClickGUI(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new DamageListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new ItemDamageListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new DeathListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new NPCDeathListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new NPCLoadListener(), (Plugin) this);
        getServer().getPluginManager().registerEvents((Listener) new MythicMobAttackListener(), (Plugin) this);
//        getServer().getPluginManager().registerEvents((Listener) new OnClickBlock(), (Plugin)this);


        //不检查的装备
        List<String> blockedMaterials = new ArrayList<>();
        getServer().getPluginManager().registerEvents((Listener) new ArmorListener(blockedMaterials), (Plugin) this);

        getServer().getPluginManager().registerEvents((Listener) new EquipListener(), (Plugin) this);

    }

    public void setupConfig() {
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
        YamlStorage yamlStorage = new YamlStorage();
        yamlStorage.loadWarps();
        totalPlayerAmount = PWPlayer.config.getInt("players");
    }

}
