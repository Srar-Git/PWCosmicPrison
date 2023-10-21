package cn.pixelwar.pwlevel.Listeners;

import cn.pixelwar.pwlevel.CustomEvents.BoosterDoneEvent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnBoosterDone implements Listener {

    @EventHandler
    public void OnPlayerBoosterDone(BoosterDoneEvent event) {
        Player player = event.getPlayer();
        String type = event.getType();
        double multi = event.getMulti();
        switch (type) {
            case "exp":
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "▸ " + ChatColor.WHITE + "你的" + ChatColor.LIGHT_PURPLE + " x" + multi + ChatColor.WHITE + " 经验加成已结束.");
                player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.7f, 1.0f);
//                PacketSound.PlayPacketSound(player, "entity.experience_orb.pickup", player.getEyeLocation().getX(), player.getEyeLocation().getY(), player.getEyeLocation().getZ(), 0.7F, 1.0F);
                break;
            case "energy":
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "▸ " + ChatColor.WHITE + "你的" + ChatColor.LIGHT_PURPLE + " x" + multi + ChatColor.WHITE + " 能量加成已结束.");
//                PacketSound.PlayPacketSound(player, "entity.experience_orb.pickup", player.getEyeLocation().getX(), player.getEyeLocation().getY(), player.getEyeLocation().getZ(), 0.7F, 1.0F);
                player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.7f, 1.0f);
                break;
            case "shard":
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "▸ " + ChatColor.WHITE + "你的" + ChatColor.LIGHT_PURPLE + " x" + multi + ChatColor.WHITE + " 碎片加成已结束.");
//                PacketSound.PlayPacketSound(player, "entity.experience_orb.pickup", player.getEyeLocation().getX(), player.getEyeLocation().getY(), player.getEyeLocation().getZ(), 0.7F, 1.0F);
                player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.7f, 1.0f);
                break;
        }

    }

}
