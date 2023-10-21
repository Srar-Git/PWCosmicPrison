package cn.pixelwar.pwplayer.PlayerStat.CoolDown;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Warn {
    public void plingWithTitle(Player player, String title, String subtitle, int stay, int cooldown) {
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.WARN)) {
                return;
            }
        }
        player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.9F, 0.1F);
        player.sendTitle(title, subtitle, 5, stay, 5);
        CoolDownManager.addPlayerCoolDown(player, CoolDownType.WARN, cooldown);
    }

    public void plingWithMessage(Player player, String message, int cooldown) {
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.WARN)) {
                return;
            }
        }
        player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.9F, 0.1F);
        player.sendMessage(message);
        CoolDownManager.addPlayerCoolDown(player, CoolDownType.WARN, cooldown);
    }

    public void levelupWithTitle(Player player, String title, String subtitle, int stay, int cooldown) {
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.WARN)) {
                return;
            }
        }
        player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4F, 0.3F);
        player.sendTitle(title, subtitle, 5, stay, 5);
        CoolDownManager.addPlayerCoolDown(player, CoolDownType.WARN, cooldown);
    }

    public void levelupWithMessage(Player player, String message, int cooldown) {
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.WARN)) {
                return;
            }
        }
        player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.4F, 0.3F);
        player.sendMessage(message);
        CoolDownManager.addPlayerCoolDown(player, CoolDownType.WARN, cooldown);
    }

    public void bagFull(Player player) {
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.WARNBAG)) {
                return;
            }
        }
        player.playSound(player.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 0.9F, 1.0F);
        player.sendMessage(ChatColor.RED + "▸ 你的背包满了，不能装更多东西了！");
        player.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "✖", ChatColor.YELLOW + "你的背包满了，不能装更多东西了", 5, 70, 5);
        CoolDownManager.addPlayerCoolDown(player, CoolDownType.WARNBAG, 30);
    }
}
