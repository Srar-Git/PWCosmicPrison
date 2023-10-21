//package cn.pixelwar.pwpacketstuff.Utils.PowerBall;
//
//import cn.pixelwar.pwblockmanager.BlockStuff.ResttingBlockDataManager;
//import cn.pixelwar.pwpacketstuff.PWPacketStuff;
//import cn.pixelwar.pwpacketstuff.Utils.Glowing;
//import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketBlock;
//import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
//import com.comphenix.protocol.ProtocolLibrary;
//import com.comphenix.protocol.ProtocolManager;
//import com.comphenix.protocol.events.PacketContainer;
//import org.bukkit.*;
//import org.bukkit.block.Block;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.scheduler.BukkitRunnable;
//import org.bukkit.util.Vector;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//
//public class PowerBallShoot {
//
//    public static List<Block> getNearbyBlocks(Location location, int radius) {
//        List<Block> blocks = new ArrayList<Block>();
//        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
//            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
//                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
//                    blocks.add(location.getWorld().getBlockAt(x, y, z));
//                }
//            }
//        }
//        return blocks;
//    }
//
//    public static void PowerballShoot(Player player, ItemStack item) {
//        player.playSound(player.getEyeLocation(), Sound.ENTITY_GHAST_SHOOT, 0.7f, 2f);
//        Location origin = player.getEyeLocation();
//        Random random = new Random();
//        int id = random.nextInt(9999);
//        UUID uuid = UUID.randomUUID();
//        PacketContainer armorStandPacket = cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandPacket(player.getWorld(), origin.add(0, -0.5, 0), id, uuid);
//        PacketContainer armorStandMetaPacket = cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandMetaPacket(id);
//        ItemStack itemStack = new ItemStack(Material.TNT);
//        Glowing glowing = new Glowing();
//        itemStack = glowing.makeGlow(itemStack);
//        PacketContainer armorStandEquipPacket = cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandEquipPacket(id, itemStack);
//        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
//        manager.broadcastServerPacket(armorStandPacket);
//        manager.broadcastServerPacket(armorStandMetaPacket);
//        manager.broadcastServerPacket(armorStandEquipPacket);
//        Vector direction = origin.getDirection();
//        direction.multiply(0.02 /* the range */);
//        Location destination = origin.clone().add(direction); // clone origin so we don't modify it directly
//        direction.normalize();
//        new BukkitRunnable() {
//            int i = 0;
//
//            @Override
//            public void run() {
//                Location loc = origin.add(direction);
//                float x = (float) loc.getX();
//                float y = (float) loc.getY();
//                float z = (float) loc.getZ();
//
//                loc.getWorld().spawnParticle(Particle.FLAME, loc.add(0, -0.2, 0), 0);
//
//                PacketContainer armorStandTeleportPacket = cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandTeleportPacket(
//                        id,
//                        loc.add(direction.getX(), direction.getY(), direction.getZ()),
//                        i * 20,
//                        i * 20);
//                manager.broadcastServerPacket(armorStandTeleportPacket);
//
//                PacketContainer armorStandDestroyPacket = cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.armorStandDestroyPacket(id);
//                i++;
//                Block block = player.getWorld().getBlockAt(loc);
//
//                if (!(block.getType().equals(Material.AIR))) {
//                    player.playSound(player.getEyeLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.6F, 2.0F);
//                    List<Block> blocks = getNearbyBlocks(block.getLocation(), 1);
//                    ResttingBlockDataManager resttingBlockDataManager = new ResttingBlockDataManager();
//                    int coaloreCount = 0, ironoreCount = 0, lapisoreCount = 0, redstoneoreCount = 0, goldoreCount = 0, diamondoreCount = 0, emeraldoreCount = 0;
//                    for (Block b : blocks) {
//                        PacketBlock packetBlock = new PacketBlock();
//                        if (!(b.getType().equals(Material.AIR))) {
//                            cn.pixelwar.pwpacketstuff.Utils.Particles.PowerBall.powerballExplodeFlame(player, b.getLocation().add(0.5, 1.1, 0.5));
//                        }
//                        if (b.getType().equals(Material.COAL_ORE)) {
//                            coaloreCount++;
//                            resttingBlockDataManager.updateResetting(b, "COAL_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.COAL_ORE), false, false);
//                        }
//                        if (b.getType().equals(Material.IRON_ORE)) {
//                            ironoreCount++;
//                            resttingBlockDataManager.updateResetting(b, "IRON_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.IRON_ORE), false, false);
//                        }
//                        if (b.getType().equals(Material.LAPIS_ORE)) {
//                            lapisoreCount++;
//                            resttingBlockDataManager.updateResetting(b, "LAPIS_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.LAPIS_ORE), false, false);
//                        }
//                        if (b.getType().equals(Material.REDSTONE_ORE)) {
//                            redstoneoreCount++;
//                            resttingBlockDataManager.updateResetting(b, "REDSTONE_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.REDSTONE_ORE), false, false);
//                        }
//                        if (b.getType().equals(Material.GOLD_ORE)) {
//                            goldoreCount++;
//                            resttingBlockDataManager.updateResetting(b, "GOLD_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.GOLD_ORE), false, false);
//                        }
//                        if (b.getType().equals(Material.DIAMOND_ORE)) {
//                            diamondoreCount++;
//                            resttingBlockDataManager.updateResetting(b, "DIAMOND_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.DIAMOND_ORE), false, false);
//                        }
//                        if (b.getType().equals(Material.EMERALD_ORE)) {
//                            emeraldoreCount++;
//                            resttingBlockDataManager.updateResetting(b, "EMERALD_ORE", "STONE", 15.0);
//                            packetBlock.PacketStandUp(player, b.getLocation(), new ItemStack(Material.EMERALD_ORE), false, false);
//                        }
//
//                    }
//                    manager.broadcastServerPacket(armorStandDestroyPacket);
//                    Bukkit.getServer().getPluginManager().callEvent(new PowerballHitBlockEvent(player, item, x, y, z, coaloreCount, ironoreCount, lapisoreCount, redstoneoreCount, goldoreCount, diamondoreCount, emeraldoreCount));
//                    cancel();
//                    return;
//                }
//                if (i >= 100) {
//                    manager.broadcastServerPacket(armorStandDestroyPacket);
//                    cancel();
//                    return;
//                }
//            }
//        }.runTaskTimer(PWPacketStuff.getPlugin(), 0L, 3L);
//
//
//    }
//
//
//}
