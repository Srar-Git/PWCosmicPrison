package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.JinghuaData;
import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.JinghuaDataManager;
import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketJinghua;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Onjoin implements Listener {

    PlayerData playerData = new PlayerData();
    PacketJinghua packetJinghua = new PacketJinghua();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                playerData.updateCritData(event.getPlayer(), 0);
                JinghuaDataManager.getJinghuaDataMap().forEach((key, value) -> {
                    packetJinghua.createText(value.getArmorStandID(),
                            key,
                            new ItemStack(value.getItem()));
                });
            }
        }.runTaskAsynchronously(PWBlockManager.getPlugin());


    }


}
