package cn.pixelwar.pwlevel.CustomEvents.CustomListeners;

import cn.pixelwar.pwlevel.CustomEvents.LevelUpEvent;
import cn.pixelwar.pwlevel.PWLevel;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerLevelUP implements Listener {


    @EventHandler
    public void OnPlayerLevelUP(LevelUpEvent event) {
        Player player = event.getPlayer();
        bigSound(player);
    }

    public void PlaySound(Player player, Sound sound, float volume, float pitch) {
        player.playSound(player.getEyeLocation(), sound, volume, pitch);
//        MinecraftKey key = new MinecraftKey(sound);
//        SoundEffect effect = new SoundEffect(key);
//        PacketPlayOutNamedSoundEffect soundpacket = new PacketPlayOutNamedSoundEffect(effect, SoundCategory.BLOCKS , player.getEyeLocation().getX(), player.getEyeLocation().getY(), player.getEyeLocation().getZ(), volume, pitch);
//        ((CraftPlayer) player).getHandle().connection.send(soundpacket);
    }

    public void bigSound(Player player) {

        PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.8f);

        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.5f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 10L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 20L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.7f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 30L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.4f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 40L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.1f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 50L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.7f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 60L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.6f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 70L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.9f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 72L);
        new BukkitRunnable() {
            @Override
            public void run() {
                PlaySound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 2.0f);
            }
        }.runTaskLaterAsynchronously(PWLevel.getPlugin(), 85L);


    }

    public void firework(Player player) {


    }

}
