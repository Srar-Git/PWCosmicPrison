package cn.pixelwar.pwpacketstuff.Utils;

import org.bukkit.ChatColor;

public class ChatColorCast {
    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
