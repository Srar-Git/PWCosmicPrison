package cn.pixelwar.pwblockmanager.Utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class PacketStuff {

    public void sendPacketToOne(Player player, PacketContainer packet) {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
    }

    public PacketContainer breakStagePacket(Vector vector, int stage, int id) {
//        Random random = new Random();
//        int id = random.nextInt(9999);
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.BLOCK_BREAK_ANIMATION);
        packet.getBlockPositionModifier().write(0, new BlockPosition(vector));
        packet.getIntegers().write(0, id);
        packet.getIntegers().write(1, stage);
        return packet;
    }

    public PacketContainer critPacket(int id, byte stage) {
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.WORLD_PARTICLES);
        //粒子ID  crit-6 magic-crit-
        packet.getIntegers().write(0, id);
        //数量
        packet.getIntegers().write(1, 1);
        packet.getBytes().write(0, stage);
        return packet;
    }

}
