package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.ChatColorCast;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.UUID;

public class PacketPrestigeEffect extends PacketsCreate {
    Random random = new Random();

    public void createArmorStand(int id, Location location, ItemStack item) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStand = armorStandPacket(location.getWorld(), location, id, uuid);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        manager.broadcastServerPacket(armorStand);
        manager.broadcastServerPacket(armorStandEquipPacket);
    }


    public void update(Location location, int id, int yaw, int pitch) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, location, yaw, pitch);
        manager.broadcastServerPacket(armorStandMetaPacket);
        manager.broadcastServerPacket(armorStandTeleportPacket);
    }

    public void delete(int id) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
        manager.broadcastServerPacket(armorStandDestroyPacket);
    }

    public void playEffect(Player player, ItemStack item) {
        int id = random.nextInt(9999);
        int id2 = random.nextInt(9999);
        Vector direction = player.getEyeLocation().getDirection();
        direction.multiply(2);
        Location center = player.getLocation().add(direction.getX(), direction.getY() + 1, direction.getZ());
        createArmorStand(id, center.clone().add(0, 1.5, 0), item);
        createArmorStand(id2, center.clone().add(0, -1.5, 0), new ItemStack(Material.STRUCTURE_VOID));
        new BukkitRunnable() {
            int j = 0;

            @Override
            public void run() {
                Vector direction2 = player.getEyeLocation().getDirection();
                direction2.multiply(3.5);
                Location updateCenter = player.getLocation().add(direction2.getX(), direction2.getY() + 1, direction2.getZ());
                player.getWorld().spawnParticle(Particle.SOUL, updateCenter.clone().add(0, 1, 0), 0);
                player.playSound(player.getEyeLocation(), Sound.ENTITY_BEE_LOOP, 0.4F, 0.1F);
                update(updateCenter.clone().add(0, 1.4, 0), id, j * 20, j * 20);
                update(updateCenter.clone().add(0, -1.4, 0), id2, j * 20, j * 20);
                j++;
                if (j >= 50) {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0, 2L);
        new BukkitRunnable() {
            @Override
            public void run() {
                new BukkitRunnable() {
                    int i = 1;

                    @Override
                    public void run() {
                        Vector direction2 = player.getEyeLocation().getDirection();
                        direction2.multiply(3);
                        Location updateCenter = player.getLocation().add(direction2.getX(), direction2.getY() + 1, direction2.getZ());
                        player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1F, 1.5F);
                        update(updateCenter.clone().add(0, 1.5 - (i * 0.2), 0), id, 0, 0);
                        update(updateCenter.clone().add(0, -1.5 + (i * 0.2), 0), id2, 0, 0);
                        i++;
                        if (i >= 7) {
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0, 2L);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 100L);
        new BukkitRunnable() {
            @Override
            public void run() {
                Vector direction2 = player.getEyeLocation().getDirection();
                direction2.multiply(3);
                Location updateCenter = player.getLocation().add(direction2.getX(), direction2.getY() + 1, direction2.getZ());
                player.getWorld().spawnParticle(Particle.CLOUD, updateCenter, 15, 0.1, 0.1, 0.1, 0.1);
                player.playSound(player.getEyeLocation(), Sound.BLOCK_ANVIL_USE, 1F, 0.1F);
                update(updateCenter, id, 0, 0);
                update(updateCenter, id2, 0, 0);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 116L);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getEyeLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1F, 1F);
                delete(id);
                delete(id2);
                PacketText packetText = new PacketText();
                packetText.createEnchantText(player, item, ChatColorCast.format("&b▸ &d&l工具荣誉升级 &b◂"));
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 120L);


    }

}
