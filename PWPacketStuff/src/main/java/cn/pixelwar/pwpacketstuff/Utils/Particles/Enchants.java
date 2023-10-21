package cn.pixelwar.pwpacketstuff.Utils.Particles;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class Enchants {

    public static void ExplodeEffect(Player player, Location loc) {
        player.playSound(player.getEyeLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.6f, 2);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                player.spawnParticle(Particle.FLAME, loc, 10, 0.5, 0.5, 0.5, 0.1);

                i++;
                if (i >= 20) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 5L);
        new BukkitRunnable() {
            @Override
            public void run() {
                new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.5f, 2f);
                        i++;
                        if (i >= 6) {
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 2L);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);
    }

    public static void NExplodeEffect(Player player, Location loc) {
        player.playSound(player.getEyeLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.6f, 2);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                player.spawnParticle(Particle.SOUL_FIRE_FLAME, loc, 10, 0.5, 0.5, 0.5, 0.1);
                player.spawnParticle(Particle.SPELL_WITCH, loc, 10, 0.5, 0.5, 0.5, 0.1);

                i++;
                if (i >= 20) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 5L);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                new BukkitRunnable() {
                    int i = 0;

                    @Override
                    public void run() {
                        player.playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 0.5f, 2f);
                        i++;
                        if (i >= 6) {
                            cancel();
                        }
                    }
                }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 2L);
            }
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 15L);
    }

    public static void TiquEffect(Player player, Location loc) {
        Random random = new Random();

//        player.playSound(player.getEyeLocation(), Sound.ENTITY_BAT_LOOP , 0.6f, 2);
        Location loc0 = loc.clone().add(0, 0.5, 0);
        Location loc1 = loc.clone().add(-0.25, 0.5, -0.25);
        Location loc2 = loc.clone().add(0.25, 0.5, -0.25);
        Location loc3 = loc.clone().add(0.25, 0.5, 0.25);
        Location loc4 = loc.clone().add(-0.25, 0.5, 0.25);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions2 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions3 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions4 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions5 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                player.spawnParticle(Particle.REDSTONE, loc0, 3, dustOptions);
                player.spawnParticle(Particle.REDSTONE, loc1, 3, dustOptions2);
                player.spawnParticle(Particle.REDSTONE, loc2, 3, dustOptions3);
                player.spawnParticle(Particle.REDSTONE, loc3, 3, dustOptions4);
                player.spawnParticle(Particle.REDSTONE, loc4, 3, dustOptions5);


                i++;
                if (i >= 20) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 5L);


    }

    public static void NTiquEffect(Player player, Location loc) {
        Random random = new Random();

//        player.playSound(player.getEyeLocation(), Sound.ENTITY_BAT_LOOP , 0.6f, 2);
        player.spawnParticle(Particle.SPELL_WITCH, loc, 10, 0.5, 0.5, 0.5, 0.1);
        Location loc0 = loc.clone().add(0, 0.5, 0);
        Location loc1 = loc.clone().add(-0.25, 0.5, -0.25);
        Location loc2 = loc.clone().add(0.25, 0.5, -0.25);
        Location loc3 = loc.clone().add(0.25, 0.5, 0.25);
        Location loc4 = loc.clone().add(-0.25, 0.5, 0.25);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions2 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions3 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions4 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                Particle.DustOptions dustOptions5 = new Particle.DustOptions(Color.fromRGB(random.nextInt(254), random.nextInt(254), random.nextInt(254)), 1.0F);
                player.spawnParticle(Particle.REDSTONE, loc0, 3, dustOptions);
                player.spawnParticle(Particle.REDSTONE, loc1, 3, dustOptions2);
                player.spawnParticle(Particle.REDSTONE, loc2, 3, dustOptions3);
                player.spawnParticle(Particle.REDSTONE, loc3, 3, dustOptions4);
                player.spawnParticle(Particle.REDSTONE, loc4, 3, dustOptions5);


                i++;
                if (i >= 20) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 5L);


    }

    public static void EatEffect(Player player) {
        ItemStack itemCrackData = new ItemStack(Material.COOKED_BEEF);
        Vector direction = player.getEyeLocation().getDirection();
        direction.multiply(0.3 /* the range */);
        Location loc = player.getEyeLocation().add(direction);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                player.playSound(player.getEyeLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
                player.spawnParticle(Particle.ITEM_CRACK, loc, 0, itemCrackData);
                i++;
                if (i >= 5) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 4L);
    }
}
