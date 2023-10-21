package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.Glowing;
import cn.pixelwar.pwpacketstuff.customevents.MeteorLandEvent;
import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.*;

public class PacketMeteor {

    public PacketContainer meteorMetaPacket(int id) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
//        WrappedChatComponent wrappedChatComponent = WrappedChatComponent.fromText(ChatColor.RED + "TEST");
//        Optional<WrappedChatComponent> opt = Optional.of(wrappedChatComponent);
        WrappedDataWatcher metadata = new WrappedDataWatcher();
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
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x08 | 0x10));
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        return packet;
    }

    public void createMeteor(Location location) {
        Location spawnLoc = location.clone().add(45, 45, 45);
        ItemStack item = new ItemStack(Material.NETHER_QUARTZ_ORE);
        Glowing glowing = new Glowing();
        item = glowing.makeGlow(item);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(spawnLoc.getWorld(), spawnLoc, id, uuid);
        PacketContainer meteorMetaPacket = meteorMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.broadcastServerPacket(armorStandPacket);
        manager.broadcastServerPacket(meteorMetaPacket);
        manager.broadcastServerPacket(armorStandEquipPacket);

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                spawnLoc.subtract(0.3, 0.3, 0.3);
                spawnLoc.getWorld().playSound(spawnLoc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2.0F, 1.0F);
                spawnLoc.getWorld().spawnParticle(Particle.FLAME, spawnLoc.clone().add(0, 1.5, 0), 20, 0.25, 0.25, 0.25, 0.1);
                PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, spawnLoc, i * 15, i * 15);
                manager.broadcastServerPacket(armorStandTeleportPacket);
                if (spawnLoc.getY() <= location.getY()) {
                    new BukkitRunnable() {
                        int j = 0;

                        @Override
                        public void run() {
                            j++;
                            spawnLoc.getWorld().playSound(spawnLoc, Sound.ENTITY_GENERIC_EXPLODE, 2.0F, 1.0F);
                            spawnLoc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, spawnLoc, 1);
                            if (j >= 6) {
                                cancel();
                            }
                        }
                    }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 3l);
                    PacketContainer destroyPacket = armorStandDestroyPacket(id);
                    manager.broadcastServerPacket(destroyPacket);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            Bukkit.getServer().getPluginManager().callEvent(new MeteorLandEvent(location));
                        }
                    }.runTask(PWPacketStuff.getPlugin());
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 2l);


    }


//    public void meteorExplodeEffect(Location location){
//        List<Block> blocks =  cn.pixelwar.pwpacketstuff.Utils.PowerBall.PowerBallShoot.getNearbyBlocks(location, 2);
//        Glowing glowing = new Glowing();
//        for (Block block : blocks){
//            if (!(block.getType().equals(Material.AIR))){
//                ItemStack item = new ItemStack(block.getType());
//                item = glowing.makeGlow(item);
//            }
//        }
//    }


}
