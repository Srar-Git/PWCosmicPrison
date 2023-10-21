package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.ChatColorCast;
import cn.pixelwar.pwpacketstuff.Utils.Glowing;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandEquipPacket;
import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandPacket;

public class PacketText extends PacketsCreate {
    Random random = new Random();

    public void createArmorStand(int id, Location location, ItemStack item) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStand = armorStandPacket(location.getWorld(), location, id, uuid);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        manager.broadcastServerPacket(armorStand);
        manager.broadcastServerPacket(armorStandEquipPacket);

    }

    public void update(Location location, int id, int yaw, int pitch, String message) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id, 0, message);
        PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, location, yaw, pitch);
        manager.broadcastServerPacket(armorStandMetaPacket);
        manager.broadcastServerPacket(armorStandTeleportPacket);
    }

    public void delete(int id) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
        manager.broadcastServerPacket(armorStandDestroyPacket);
    }

    public PacketContainer armorStandMetaPacket(int id, int swing, String message) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
//        WrappedChatComponent wrappedChatComponent = WrappedChatComponent.fromText(ChatColor.RED + "TEST");
//        Optional<WrappedChatComponent> opt = Optional.of(wrappedChatComponent);
        WrappedDataWatcher metadata = new WrappedDataWatcher();
        String text = ChatColorCast.format(message);
//        WrappedChatComponent wrappedChatComponent = WrappedChatComponent.fromText(ChatColor.WHITE + "当前还需要挖掘"+ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+swing+ChatColor.WHITE +"次");
//        Optional<WrappedChatComponent> opt = Optional.of(wrappedChatComponent);
        Optional<?> opt = Optional.of(WrappedChatComponent.fromChatMessage(text)[0].getHandle());
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)), opt);
//        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.get(WrappedChatComponent.class)), opt);
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean.class)), true);
        //0
        //Bit mask	Meaning
        //0x01	   Is on fire
        //0x02	   Is crouching
        //0x04	   Unused (previously riding)
        //0x08	   Is sprinting
        //0x10	   Is swimming
        //0x20     Is invisible
        //0x40	   has glowing effect
        //0x80	   Is flying with an elytra
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x20 | 0x01));
//        Bit mask	Meaning
//        0x01	   Is Small
//        0x04	   Has Arms
//        0x08	   Has no BasePlate
//        0x10	   Is Marker
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10));
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        return packet;
    }

    public void createAttackText(Player victim, String line) {
        Random random = new Random();
        double d = random.nextDouble();
        Location newloc = victim.getLocation().add(d, 1.5, d);
        int id = random.nextInt(9999);
        createArmorStand(id, newloc, new ItemStack(Material.AIR));
        new BukkitRunnable() {
            int j = 1;

            @Override
            public void run() {
                Location loc = newloc.clone().add(0, j * 0.08, 0);
                update(loc, id, 0, 0, line);
                j++;
                if (j >= 25) {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 1L);
        new BukkitRunnable() {
            @Override
            public void run() {
                delete(id);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 28L);

    }

    public void createEnchantText(Player player, ItemStack item, String message) {
        String itemName = "";
        if (item.getItemMeta() == null) {
            item = new ItemStack(Material.BARRIER);
        } else {
            itemName = item.getItemMeta().getDisplayName();
        }
        int id = random.nextInt(9999);
        int id2 = random.nextInt(9999);
        Vector direction = player.getEyeLocation().getDirection();
        direction.multiply(2.5);
        Location location = player.getLocation().add(direction.getX(), direction.getY() + 1, direction.getZ());
        createArmorStand(id, location, item);
        createArmorStand(id2, location.clone().add(0, -0.3, 0), new ItemStack(Material.AIR));
        String finalItemName = itemName;
        new BukkitRunnable() {
            int j = 0;

            @Override
            public void run() {
                Vector direction2 = player.getEyeLocation().getDirection();
                direction2.multiply(2.5);
                Location updateLocation = player.getLocation().add(direction2.getX(), direction2.getY() + 1, direction2.getZ());
                Location updateLocation2 = updateLocation.clone().add(0, -0.3, 0);
                player.getWorld().spawnParticle(Particle.SPELL_WITCH, updateLocation.clone().add(0, -0.3, 0), 0);
                update(updateLocation, id, j * 20, j * 20, message);
                update(updateLocation2, id2, 0, 0, finalItemName);
                j++;
                if (j >= 50) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0, 2L);
        new BukkitRunnable() {
            @Override
            public void run() {
                delete(id);
                delete(id2);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 105L);
    }

    public void createLevelUpText(Player player, int level, String message) {
        ItemStack item = new ItemStack(Material.WOODEN_PICKAXE);
        if (level < 30) {
            item = new ItemStack(Material.WOODEN_PICKAXE);
        }
        if (level < 50 && level >= 30) {
            item = new ItemStack(Material.STONE_PICKAXE);
        }
        if (level < 70 && level >= 50) {
            item = new ItemStack(Material.GOLDEN_PICKAXE);
        }
        if (level < 90 && level >= 70) {
            item = new ItemStack(Material.IRON_PICKAXE);
        }
        if (level >= 90) {
            item = new ItemStack(Material.DIAMOND_PICKAXE);
        }
        int id = random.nextInt(9999);
        int id2 = random.nextInt(9999);
        Vector direction = player.getEyeLocation().getDirection();
        direction.multiply(2.5);
        Location location = player.getLocation().add(direction.getX(), direction.getY() + 1, direction.getZ());
        createArmorStand(id, location, item);
        new BukkitRunnable() {
            int j = 0;

            @Override
            public void run() {
                Vector direction2 = player.getEyeLocation().getDirection();
                direction2.multiply(2.5);
                Location updateLocation = player.getLocation().add(direction2.getX(), direction2.getY() + 1, direction2.getZ());
                player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, updateLocation, 0);
                update(updateLocation, id, j * 20, j * 20, message);
                j++;
                if (j >= 100) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0, 2L);
        new BukkitRunnable() {
            @Override
            public void run() {
                delete(id);
                delete(id2);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 205L);
    }


}
