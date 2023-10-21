package cn.pixelwar.pwpacketstuff.Utils.PacketAnimation;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.Glowing;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.UUID;

import static cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketsCreate.*;

public class PacketItem {

    public void PacketStandUp(Player player, Location blockLoc, ItemStack item, boolean isGlow, boolean hasSound) {
        if (isGlow) {
            Glowing glowing = new Glowing();
            item = glowing.makeGlow(item);
        }
        Location center = blockLoc.add(0.5, 0.15, 0.5);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(player.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, armorStandPacket);
            manager.sendServerPacket(player, armorStandMetaPacket);
            manager.sendServerPacket(player, armorStandEquipPacket);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
        if (hasSound) {
            player.playSound(player.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1.3f, 1.1f);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, 0.4, 0), 45, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 3L);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, -0.2, 0), 135, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 6L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasSound) {
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6f, 2f);
                }
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, player.getLocation().subtract(0, 0.7, 0), 270, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 10L);


        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                    manager.sendServerPacket(player, armorStandDestroyPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);


    }

    public void PacketStandDown(Player player, Location blockLoc, ItemStack item, boolean isGlow, boolean hasSound) {
        if (isGlow) {
            Glowing glowing = new Glowing();
            item = glowing.makeGlow(item);
        }
        Location center = blockLoc.add(0.5, -1.4, 0.5);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(player.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, armorStandPacket);
            manager.sendServerPacket(player, armorStandMetaPacket);
            manager.sendServerPacket(player, armorStandEquipPacket);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
        if (hasSound) {
            player.playSound(player.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1.3f, 1.1f);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, -0.4, 0), 45, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 3L);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, 0.2, 0), 135, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 6L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasSound) {
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6f, 2f);
                }
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, player.getLocation().subtract(0, 0.7, 0), 270, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 10L);


        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                    manager.sendServerPacket(player, armorStandDestroyPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);


    }

    public void PacketStandEast(Player player, Location blockLoc, ItemStack item, boolean isGlow, boolean hasSound) {
        if (isGlow) {
            Glowing glowing = new Glowing();
            item = glowing.makeGlow(item);
        }
        Location center = blockLoc.add(1.3, -0.65, 0.5);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(player.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, armorStandPacket);
            manager.sendServerPacket(player, armorStandMetaPacket);
            manager.sendServerPacket(player, armorStandEquipPacket);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
        if (hasSound) {
            player.playSound(player.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1.3f, 1.1f);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0.4, 0, 0), 45, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 3L);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(-0.2, 0, 0), 135, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 6L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasSound) {
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6f, 2f);
                }
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, player.getLocation().subtract(0, 0.7, 0), 270, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 10L);


        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                    manager.sendServerPacket(player, armorStandDestroyPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);


    }

    public void PacketStandWest(Player player, Location blockLoc, ItemStack item, boolean isGlow, boolean hasSound) {
        if (isGlow) {
            Glowing glowing = new Glowing();
            item = glowing.makeGlow(item);
        }
        Location center = blockLoc.add(-0.3, -0.65, 0.5);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(player.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, armorStandPacket);
            manager.sendServerPacket(player, armorStandMetaPacket);
            manager.sendServerPacket(player, armorStandEquipPacket);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
        if (hasSound) {
            player.playSound(player.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1.3f, 1.1f);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(-0.4, 0, 0), 45, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 3L);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0.2, 0, 0), 135, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 6L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasSound) {
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6f, 2f);
                }
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, player.getLocation().subtract(0, 0.7, 0), 270, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 10L);


        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                    manager.sendServerPacket(player, armorStandDestroyPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);


    }

    public void PacketStandSouth(Player player, Location blockLoc, ItemStack item, boolean isGlow, boolean hasSound) {
        if (isGlow) {
            Glowing glowing = new Glowing();
            item = glowing.makeGlow(item);
        }
        Location center = blockLoc.add(0.5, -0.65, 1.3);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(player.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, armorStandPacket);
            manager.sendServerPacket(player, armorStandMetaPacket);
            manager.sendServerPacket(player, armorStandEquipPacket);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
        if (hasSound) {
            player.playSound(player.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1.3f, 1.1f);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, 0, 0.4), 45, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 3L);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, 0, -0.2), 135, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 6L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasSound) {
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6f, 2f);
                }
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, player.getLocation().subtract(0, 0.7, 0), 270, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 10L);


        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                    manager.sendServerPacket(player, armorStandDestroyPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);


    }

    public void PacketStandNorth(Player player, Location blockLoc, ItemStack item, boolean isGlow, boolean hasSound) {
        if (isGlow) {
            Glowing glowing = new Glowing();
            item = glowing.makeGlow(item);
        }
        Location center = blockLoc.add(0.5, -0.65, -0.3);
        Random random = new Random();
        int id = random.nextInt(999);
        UUID uuid = UUID.randomUUID();
        PacketContainer armorStandPacket = armorStandPacket(player.getWorld(), center, id, uuid);
        PacketContainer armorStandMetaPacket = armorStandMetaPacket(id);
        PacketContainer armorStandEquipPacket = armorStandEquipPacket(id, item);
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        try {
            manager.sendServerPacket(player, armorStandPacket);
            manager.sendServerPacket(player, armorStandMetaPacket);
            manager.sendServerPacket(player, armorStandEquipPacket);
        } catch (InvocationTargetException e) {
            System.out.println("发包错误: " + e.getLocalizedMessage());
        }
        if (hasSound) {
            player.playSound(player.getEyeLocation(), Sound.BLOCK_STONE_BREAK, 1.3f, 1.1f);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, 0, -0.4), 45, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 3L);
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, blockLoc.add(0, 0, 0.2), 135, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 6L);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasSound) {
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.6f, 2f);
                }
                try {
                    PacketContainer armorStandTeleportPacket = armorStandTeleportPacket(id, player.getLocation().subtract(0, 0.7, 0), 270, 45);
                    manager.sendServerPacket(player, armorStandTeleportPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 10L);


        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    PacketContainer armorStandDestroyPacket = armorStandDestroyPacket(id);
                    manager.sendServerPacket(player, armorStandDestroyPacket);
                } catch (InvocationTargetException e) {
                    System.out.println("发包错误: " + e.getLocalizedMessage());
                }
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);


    }

}
