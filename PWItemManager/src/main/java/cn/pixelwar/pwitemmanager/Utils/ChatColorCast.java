package cn.pixelwar.pwitemmanager.Utils;

import org.bukkit.ChatColor;

public class ChatColorCast {
    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
