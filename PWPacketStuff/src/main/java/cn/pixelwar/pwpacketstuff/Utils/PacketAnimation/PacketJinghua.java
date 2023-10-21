package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;


import cn.pixelwar.pwpacketstuff.Utils.Glowing;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.*;

public class PacketJinghua {


    public void createText(int id, Location location, ItemStack item) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        Glowing glowing = new Glowing();
        item = glowing.makeGlow(item);

        UUID uuid = UUID.randomUUID();
        PacketContainer armorStand = armorStandPacket(location.getWorld(), location, id, uuid);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        manager.broadcastServerPacket(armorStand);
        manager.broadcastServerPacket(armorStandEquipPacket);

    }

    public void update(Location location, int id, int swing, int yaw, int pitch) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id, swing);
        PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, location, yaw, pitch);


        manager.broadcastServerPacket(armorStandMetaPacket);
        manager.broadcastServerPacket(armorStandTeleportPacket);
    }

    public void delete(int id) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
        manager.broadcastServerPacket(armorStandDestroyPacket);
    }

    public PacketContainer armorStandMetaPacket(int id, int swing) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, id);
//        WrappedChatComponent wrappedChatComponent = WrappedChatComponent.fromText(ChatColor.RED + "TEST");
//        Optional<WrappedChatComponent> opt = Optional.of(wrappedChatComponent);
        WrappedDataWatcher metadata = new WrappedDataWatcher();
        String text = ChatColor.WHITE + "当前还需要挖掘" + ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + swing + ChatColor.WHITE + "次";
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


}
