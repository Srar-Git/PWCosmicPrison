package cn.pixelwar.pwitemmanager;

import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This class will automatically register as a placeholder expansion
 * when a jar including this class is added to the /plugins/placeholderapi/expansions/ folder
 */
public class Placeholders extends PlaceholderExpansion {

    /**
     * This method should always return true unless we
     * have a dependency we need to make sure is on the server
     * for our placeholders to work!
     * This expansion does not require a dependency so we will always return true
     */
    @Override
    public boolean canRegister() {
        return true;
    }

    /**
     * The name of the person who created this expansion should go here
     */
    @Override
    public String getAuthor() {
        return "An_Lan";
    }

    /**
     * The placeholder identifier should go here
     * This is what tells PlaceholderAPI to call our onPlaceholderRequest method to obtain
     * a value if a placeholder starts with our identifier.
     * This must be unique and can not contain % or _
     */
    @Override
    public String getIdentifier() {
        return "tool";
    }

    /**
     * if an expansion requires another plugin as a dependency, the proper name of the dependency should
     * go here. Set this to null if your placeholders do not require another plugin be installed on the server
     * for them to work
     */
    @Override
    public String getPlugin() {
        return null;
    }

    /**
     * This is the version of this expansion
     */
    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier is found and needs a value
     * We specify the value identifier in this method
     */
    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        NumberFormat numberFormat = new NumberFormat();
        // %example_placeholder1%
        if (identifier.equals("type")) {
            ItemStack tool = p.getItemInHand();
            if (tool.getType().equals(Material.WOODEN_PICKAXE) ||
                    tool.getType().equals(Material.STONE_PICKAXE) ||
                    tool.getType().equals(Material.GOLDEN_PICKAXE) ||
                    tool.getType().equals(Material.IRON_PICKAXE) ||
                    tool.getType().equals(Material.DIAMOND_PICKAXE) ||
                    tool.getType().equals(Material.NETHERITE_PICKAXE)
            ) {
                return "pickaxe";
            }
            if (tool.getType().equals(Material.WOODEN_AXE) || tool.getType().equals(Material.WOODEN_SWORD) ||
                    tool.getType().equals(Material.STONE_AXE) || tool.getType().equals(Material.STONE_SWORD) ||
                    tool.getType().equals(Material.GOLDEN_AXE) || tool.getType().equals(Material.GOLDEN_SWORD) ||
                    tool.getType().equals(Material.IRON_AXE) || tool.getType().equals(Material.IRON_SWORD) ||
                    tool.getType().equals(Material.DIAMOND_AXE) || tool.getType().equals(Material.DIAMOND_SWORD) ||
                    tool.getType().equals(Material.DIAMOND_AXE) || tool.getType().equals(Material.NETHERITE_SWORD)
            ) {
                return "sword";
            }
            return "other";

        }
        if (identifier.equals("xp")) {
            if (p.getItemInHand().getType().equals(Material.AIR)) {
                return "" + 0;
            }
            NBTItem nbti = new NBTItem(p.getItemInHand());
            return numberFormat.getLongFormat(nbti.getInteger("toolxp"));
        }
        if (identifier.equals("toollevel")) {
            if (p.getItemInHand().getType().equals(Material.AIR)) {
                return "" + 0;
            }
            NBTItem nbti = new NBTItem(p.getItemInHand());
            return "" + nbti.getInteger("toollevel");
        }
        // %example_placeholder2%
        if (identifier.equals("needxp")) {
            if (p.getItemInHand().getType().equals(Material.AIR)) {
                return "" + 0;
            }
            NBTItem nbti = new NBTItem(p.getItemInHand());
            return numberFormat.getLongFormat(nbti.getInteger("toolneedxp"));
        }
        if (identifier.equals("xppercent")) {
            if (p.getItemInHand().getType().equals(Material.AIR)) {
                return "" + 0;
            }
            NBTItem nbti = new NBTItem(p.getItemInHand());
            long toolxp = nbti.getInteger("toolxp");
            long toolneedxp = nbti.getInteger("toolneedxp");
            int percent = Math.round((float) toolxp / toolneedxp * 100);
            if (percent <= 20) {
                return ChatColor.RED + "" + percent;
            }
            if (percent <= 40) {
                return ChatColor.GOLD + "" + percent;
            }
            if (percent <= 70) {
                return ChatColor.YELLOW + "" + percent;
            } else {
                return ChatColor.GREEN + "" + percent;
            }

        }

        return null;
    }
}
