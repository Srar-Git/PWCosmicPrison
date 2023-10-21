package cn.pixelwar.pwblockmanager.BlockStuff;

import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwblockmanager.Utils.PacketStuff;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlockAnimationData {
    private static Map<String, BlockData> blockAnimationData = new ConcurrentHashMap<>();
    private static Map<String, BlockActiveData> blockActiveData = new ConcurrentHashMap<>();

    public static Map<String, BlockData> getBlockAnimationData() {
        return blockAnimationData;
    }

    public static Map<String, BlockActiveData> getBlockActiveData() {
        return blockActiveData;
    }


    public void resetBlock(String loc, Player player) {

        new BukkitRunnable() {
            @Override
            public void run() {
                if (blockAnimationData.containsKey(loc)) {
                    sendPacket(blockAnimationData.get(loc).getBlock(), player, -1);
                    blockAnimationData.remove(loc);
                    blockActiveData.remove(loc);
                }
            }
        }.runTaskAsynchronously(PWBlockManager.getPlugin());
    }


    public void addBlockAnimationData(String loc, float damage, Player player, Block block, Location activeLoc) {


        if (blockAnimationData.containsKey(loc)) {
            BlockData newBlockData = new BlockData(new Date(), blockAnimationData.get(loc).getBlockDamage() + damage, block);
            blockAnimationData.put(loc, newBlockData);
            sendPacket(blockAnimationData.get(loc).getBlock(), player, blockAnimationData.get(loc).getBlockDamage());
            if (!(blockActiveData.containsKey(loc))) {
                BlockActiveData newBlockActiveData = new BlockActiveData(player, false, activeLoc);
                blockActiveData.put(loc, newBlockActiveData);
            }
            return;
        } else {
            BlockData newBlockData = new BlockData(new Date(), damage, block);
            blockAnimationData.put(loc, newBlockData);
            sendPacket(blockAnimationData.get(loc).getBlock(), player, blockAnimationData.get(loc).getBlockDamage());
            BlockActiveData newBlockActiveData = new BlockActiveData(player, false, activeLoc);
            blockActiveData.put(loc, newBlockActiveData);
        }


    }


    public void addBlockAnimationDataWithoutCrit(String loc, float damage, Player player, Block block) {


        if (blockAnimationData.containsKey(loc)) {
            BlockData newBlockData = new BlockData(new Date(), blockAnimationData.get(loc).getBlockDamage() + damage, block);
            blockAnimationData.put(loc, newBlockData);
            sendPacket(blockAnimationData.get(loc).getBlock(), player, blockAnimationData.get(loc).getBlockDamage());
            return;
        } else {
            BlockData newBlockData = new BlockData(new Date(), damage, block);
            blockAnimationData.put(loc, newBlockData);
            sendPacket(blockAnimationData.get(loc).getBlock(), player, blockAnimationData.get(loc).getBlockDamage());
        }


    }

    public void checkPlayerPoint(Player player, Block block) {

        Location eyeloc = player.getEyeLocation();
        Vector direction = eyeloc.getDirection();
        direction.multiply(0.08);
        String loc = player + " " + block.getLocation();
        Location point2 = null;
        for (int i = 0; i <= 80; i++) {
            Location point = eyeloc.add(direction);
            if (point.getBlock().getType().isSolid()) {
                point2 = eyeloc.subtract(direction);
                break;
            }
        }
        double x = blockActiveData.get(loc).getActiveLoc().getX();
        double y = blockActiveData.get(loc).getActiveLoc().getY();
        double z = blockActiveData.get(loc).getActiveLoc().getZ();
        if (!(blockActiveData.get(loc).isActive())) {
            if (point2.getX() < x + 0.1 && point2.getX() > x - 0.1 && point2.getY() < y + 0.1 && point2.getY() > y - 0.1 && point2.getZ() < z + 0.1 && point2.getZ() > z - 0.1) {
                blockActiveData.get(loc).setActive(true);
                player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_SNARE, 0.5f, 2f);
            }
        }


    }

    public boolean ifBlockActive(String locString) {
        return blockActiveData.get(locString).isActive();
    }

    public BlockData getBlockAnimationData(String loc) {
        return blockAnimationData.get(loc);
    }

    public void removeBlockAnimationData(String loc) {
        blockAnimationData.remove(blockAnimationData.get(loc));
    }

    public void clearBlockAnimationData() {
        blockAnimationData.clear();
    }

    public void timer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Date currentDate = new Date();
                blockAnimationData.forEach((key, value) -> {
                    if ((currentDate.getTime() - value.getLastDamageTime().getTime()) / 1000 > 20) {
                        blockAnimationData.remove(key);
                        if (blockActiveData.containsKey(key)) blockActiveData.remove(key);
                        return;
                    } else {
                        if (blockActiveData.containsKey(key)) {
                            if (blockActiveData.get(key).isActive()) {


                                blockActiveData.get(key).getPlayer().spawnParticle(Particle.CRIT_MAGIC, blockActiveData.get(key).getActiveLoc(), 0);
//                                packet = new PacketPlayOutWorldParticles(EnumParticle.CRIT_MAGIC, false, (float) blockActiveData.get(key).getActiveLoc().getX(), (float) blockActiveData.get(key).getActiveLoc().getY(), (float) blockActiveData.get(key).getActiveLoc().getZ(), 0, 0, 0, 0, 1);
                            } else {
//                                packet = new PacketPlayOutWorldParticles(EnumParticle.CRIT, false, (float) blockActiveData.get(key).getActiveLoc().getX(), (float) blockActiveData.get(key).getActiveLoc().getY(), (float) blockActiveData.get(key).getActiveLoc().getZ(), 0, 0, 0, 0, 1);
                                blockActiveData.get(key).getPlayer().spawnParticle(Particle.CRIT, blockActiveData.get(key).getActiveLoc(), 0);

                            }

                        }
                    }
                });
            }
        }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 10L);
    }

    public void sendPacket(Block block, Player player, float damage) {
        int stage;
        if (damage >= 100) {
            stage = 9;
        } else {
            stage = (int) Math.floor(damage / 10);
        }
        PacketStuff packetStuff = new PacketStuff();
        Vector vector = new Vector(block.getX(), block.getY(), block.getZ());
        PacketContainer stagePacket = packetStuff.breakStagePacket(vector, stage, getBlockEntityId(block));
        packetStuff.sendPacketToOne(player, stagePacket);

    }

    private int getBlockEntityId(Block block) {
        return ((block.getX() & 0xFFF) << 20 | (block.getZ() & 0xFFF) << 8) | (block.getY() & 0xFF);
    }
}
