package cn.pixelwar.pwpacketstuff;


import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import cn.pixelwar.pwpacketstuff.CustomListeners.PowerballHitBlockListener;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketBlock;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketItem;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketJinghua;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandDestroyPacket;

public final class PWPacketStuff extends JavaPlugin implements Listener {

    PWPacketStuff instance;
    SkriptAddon addon;
    private static Plugin plugin;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PowerballHitBlockListener(), this);
        getServer().getPluginManager().registerEvents(this, this);
        plugin = this;
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("cn.pixelwar.pwpacketstuff");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getLogger().info("[PWPacketStuff] has been enabled!");
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public PWPacketStuff getInstance() {
        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    @Override
    public void onDisable() {
    }


//    @EventHandler
//    public void click(PlayerInteractEvent event){
//        Bukkit.broadcastMessage(event.getClickedBlock().getType().toString());
//    }


}

