package cn.pixelwar.pwitemmanager;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import cn.pixelwar.pwitemmanager.Commands.ItemStuff;
import cn.pixelwar.pwitemmanager.Enchant.menus.EnchantMenuEvents;
import cn.pixelwar.pwitemmanager.Files.CustomItems;
import cn.pixelwar.pwitemmanager.Listeners.DropListener;
import cn.pixelwar.pwitemmanager.Listeners.EatListener;
import cn.pixelwar.pwitemmanager.Listeners.OnClickGUI;
import cn.pixelwar.pwitemmanager.Listeners.RightClickUseItem;
import cn.pixelwar.pwitemmanager.OreBag.OnOreBagAdd;
import cn.pixelwar.pwitemmanager.OreBag.Orebag;
import cn.pixelwar.pwitemmanager.Shards.Events.ShardMenuEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class PWItemManager extends JavaPlugin implements Listener {

    private static int playerDropDelay;
    private static int stealDelay;
    static PWItemManager instance;
    SkriptAddon addon;
    private static Plugin plugin;
    private static Economy econ = null;

    @Override
    public void onEnable() {

        if (!setupEconomy() ) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //指令注册
        this.getCommand("pixelwaritem").setExecutor(new ItemStuff());

        //items.yml文件设置
        CustomItems.setupItemFile();
        CustomItems.getItemFile().options().copyDefaults(true);
        CustomItems.save();


        //config.yml文件设置
        saveDefaultConfig();
        reload();


        getServer().getPluginManager().registerEvents(new DropListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new OnOreBagAdd(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new EatListener(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new ShardMenuEvents(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new EnchantMenuEvents(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new RightClickUseItem(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new OnClickGUI(), (Plugin)this);
        getServer().getPluginManager().registerEvents(new Orebag(), (Plugin)this);
        getServer().getPluginManager().registerEvents(this, (Plugin)this);

        plugin = this;
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("cn.pixelwar.pwitemmanager");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[PixelWarItemManager] has been enabled!");

    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static PWItemManager getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }
    @Override
    public void onDisable() {

    }

    public static int getPlayerDropDelay() {
        return playerDropDelay;
    }

    public static int getStealDelay() {
        return stealDelay;
    }

    public void reload() {
        reloadConfig();
        this.playerDropDelay = getConfig().getInt("playerDropDelay");
        this.stealDelay = getConfig().getInt("stealDelay");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
}
