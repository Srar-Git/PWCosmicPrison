package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.*;

public class Shatter {

    public int doShatter(Player victim, int level, double damage) {
        Random random = new Random();
        List<Block> nearbyBlocks = getNearbyBlocks(victim.getLocation().subtract(0, 2, 0), 2);
        if (nearbyBlocks.isEmpty()) {
            return 0;
        }

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                victim.getWorld().playSound(victim.getEyeLocation(), Sound.ENTITY_ARROW_SHOOT, 1f, 0.1f);
                Block block;
                block = nearbyBlocks.get(random.nextInt(nearbyBlocks.size() - 1));
                nearbyBlocks.remove(block);
                shatterOne(victim, block, damage);
                i++;
                if (i >= level) {
                    cancel();
                }
            }
        }.runTaskTimer(PWPacketStuff.getPlugin(), 0L, 4L);
        return level;
    }

    public void shatterOne(Player victim, Block block, double damage) {

        Location center = block.getLocation();
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(victim.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, new ItemStack(block.getType()));
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.broadcastServerPacket(armorStandPacket);
        manager.broadcastServerPacket(armorStandMetaPacket);
        manager.broadcastServerPacket(armorStandEquipPacket);
        new BukkitRunnable() {
            int j = 1;

            @Override
            public void run() {
                PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, center.clone().add(0, 0.25 * j, 0), j * 5, j * 5);
                manager.broadcastServerPacket(armorStandTeleportPacket);
                j++;
                if (j >= 25) {
                    cancel();
                }
            }
        }.runTaskTimer(PWPacketStuff.getPlugin(), 0L, 2L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, victim.getLocation(), 270, 45);
                manager.broadcastServerPacket(armorStandTeleportPacket);
            }
        }.runTaskLater(PWPacketStuff.getPlugin(), 65L);


        new BukkitRunnable() {
            @Override
            public void run() {
                PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                manager.broadcastServerPacket(armorStandDestroyPacket);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        victim.damage(damage);
                        victim.getWorld().playSound(victim.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 0.1f);
                    }
                }.runTask(PWPacketStuff.getPlugin());

            }
        }.runTaskLater(PWPacketStuff.getPlugin(), 75L);

    }

    public List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    Block block = location.getWorld().getBlockAt(x, y, z);
                    if (block.getType().isBlock()) {
                        blocks.add(block);
                    }

                }
            }
        }
        return blocks;
    }


}
