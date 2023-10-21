package cn.pixelwar.pwpacketstuff.Utils.Particles;

import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PowerBall {

    public static void powerballExplodeFlame(Player player, Location loc) {
        Location loc1 = new Location(player.getWorld(), (loc.getX() + 0.25), loc.getY(), (loc.getZ() + 0.25));
        Location loc2 = new Location(player.getWorld(), (loc.getX() - 0.25), loc.getY(), (loc.getZ() + 0.25));
        Location loc3 = new Location(player.getWorld(), (loc.getX() + 0.25), loc.getY(), (loc.getZ() - 0.25));
        Location loc4 = new Location(player.getWorld(), (loc.getX() - 0.25), loc.getY(), (loc.getZ() - 0.25));
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                player.getWorld().spawnParticle(Particle.FLAME, loc, 0);
                player.getWorld().spawnParticle(Particle.FLAME, loc1, 0);
                player.getWorld().spawnParticle(Particle.FLAME, loc2, 0);
                player.getWorld().spawnParticle(Particle.FLAME, loc3, 0);
                player.getWorld().spawnParticle(Particle.FLAME, loc4, 0);

                i++;
                if (i >= 10) {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 20L);

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
        }.runTaskLaterAsynchronously(PWPacketStuff.getPlugin(), 14L);


    }

}
