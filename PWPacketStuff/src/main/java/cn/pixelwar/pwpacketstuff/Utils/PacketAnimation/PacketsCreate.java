package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacketsCreate {
    public static PacketContainer armorStandDestroyPacket(int id) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_DESTROY);
        packet.getModifier().write(0, new IntArrayList(new int[]{id}));
        return packet;
    }

    public static PacketContainer armorStandTeleportPacket(int id, Location loc, int yaw, int pitch) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_TELEPORT);
        packet.getIntegers().write(0, id);
        packet.getDoubles().write(0, loc.getX());
        packet.getDoubles().write(1, loc.getY());
        packet.getDoubles().write(2, loc.getZ());
        packet.getBytes().write(0, (byte) (yaw * 256.0F / 360.0F));
        packet.getBytes().write(1, (byte) (pitch * 256.0F / 360.0F));
        packet.getBooleans().write(0, false);
        return packet;
    }

    public static PacketContainer armorStandEquipPacket(int id, ItemStack item) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_EQUIPMENT);
        packet.getIntegers().write(0, id);
        List<Pair<EnumWrappers.ItemSlot, ItemStack>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(EnumWrappers.ItemSlot.HEAD, item));
        packet.getSlotStackPairLists().writeSafely(0, pairs);
        return packet;
    }

    public static PacketContainer armorStandMetaPacket(int id) {
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
        metadata.setObject(new WrappedDataWatcher.WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte.class)), (byte) (0x01 | 0x08 | 0x10));
        packet.getWatchableCollectionModifier().write(0, metadata.getWatchableObjects());
        return packet;
    }

    public static PacketContainer armorStandPacket(World world, Location location, int id, UUID uuid) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY);
        packet.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        packet.getIntegers().write(0, id);
        packet.getUUIDs().write(0, uuid);
        // Set optional velocity (/8000)
        packet.getIntegers().write(1, 0);
        packet.getIntegers().write(2, 0);
        packet.getIntegers().write(3, 0);
        // Set yaw pitch
        packet.getIntegers().write(4, 0);
        packet.getIntegers().write(5, 0);
        // Set object data
        packet.getIntegers().write(6, 0);
        // Set location
        packet.getDoubles().write(0, location.getX());
        packet.getDoubles().write(1, location.getY());
        packet.getDoubles().write(2, location.getZ());
        return packet;
    }

}
