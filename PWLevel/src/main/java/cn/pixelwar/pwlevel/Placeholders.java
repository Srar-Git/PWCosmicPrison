package cn.pixelwar.pwlevel;

import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import cn.pixelwar.pwlevel.PlayerData.PlayerOreExp.PlayerOreExpDataManager;
import cn.pixelwar.pwlevel.Utils.NumberFormat;
import cn.pixelwar.pwlevel.Utils.TimeFormat;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

import static cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager.*;
import static cn.pixelwar.pwlevel.PlayerData.PlayerOreExp.PlayerOreExpDataManager.getPlayerOreExpDataMap;


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
        return "pwlevel";
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
        PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
        PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
        PlayerOreExpDataManager playerOreExpDataManager = new PlayerOreExpDataManager();
        if (
                identifier.equals("ironpercent") ||
                        identifier.equals("lapispercent") ||
                        identifier.equals("redstonepercent") ||
                        identifier.equals("goldpercent") ||
                        identifier.equals("diamondpercent") ||
                        identifier.equals("emeraldpercent")
        ) {

            if (getPlayerOreExpDataMap().containsKey(p.getName())) {
                String type = identifier.substring(0, identifier.indexOf("percent"));
                return "" + Math.round(playerOreExpDataManager.getOreExpPercent(p, type) * 100);
            } else {
                return 0 + "";
            }
        }
        if (identifier.equals("bestore")) {
            int level = playerExpDataManager.getLevel(p);
            NumberFormat numberFormat = new NumberFormat();
            if (level < 10) {
                return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + PWLevel.config.getLong("orexp.coal") + ChatColor.WHITE + "XP/个";
            }
            if (level >= 10 && level < 30) {
                long ironxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "iron") * PWLevel.config.getLong("orexp.iron"));
                long coalxp = PWLevel.config.getLong("orexp.coal");
                if (ironxp >= coalxp) {
                    return ChatColor.GOLD + "铁矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(ironxp) + ChatColor.WHITE + "XP/个";
                } else {
                    return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(coalxp) + ChatColor.WHITE + "XP/个";
                }
            }
            if (level >= 30 && level < 50) {
                long ironxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "iron") * PWLevel.config.getLong("orexp.iron"));
                long lapisxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "lapis") * PWLevel.config.getLong("orexp.lapis"));
                long coalxp = PWLevel.config.getLong("orexp.coal");
                if (lapisxp >= coalxp && lapisxp >= ironxp) {
                    return ChatColor.BLUE + "青金石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(lapisxp) + ChatColor.WHITE + "XP/个";
                }
                if (ironxp >= coalxp && ironxp >= lapisxp) {
                    return ChatColor.GOLD + "铁矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(ironxp) + ChatColor.WHITE + "XP/个";
                } else {
                    return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(coalxp) + ChatColor.WHITE + "XP/个";
                }
            }
            if (level >= 50 && level < 70) {
                long ironxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "iron") * PWLevel.config.getLong("orexp.iron"));
                long lapisxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "lapis") * PWLevel.config.getLong("orexp.lapis"));
                long redstonexp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "redstone") * PWLevel.config.getLong("orexp.redstone"));
                long coalxp = PWLevel.config.getLong("orexp.coal");
                if (lapisxp >= coalxp && lapisxp >= ironxp && lapisxp >= redstonexp) {
                    return ChatColor.BLUE + "青金石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(lapisxp) + ChatColor.WHITE + "XP/个";
                }
                if (redstonexp >= coalxp && redstonexp >= ironxp && redstonexp >= lapisxp) {
                    return ChatColor.RED + "红石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(redstonexp) + ChatColor.WHITE + "XP/个";
                }
                if (ironxp >= coalxp && ironxp >= lapisxp && ironxp >= redstonexp) {
                    return ChatColor.GOLD + "铁矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(ironxp) + ChatColor.WHITE + "XP/个";
                } else {
                    return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(coalxp) + ChatColor.WHITE + "XP/个";
                }
            }
            if (level >= 70 && level < 90) {
                long ironxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "iron") * PWLevel.config.getLong("orexp.iron"));
                long lapisxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "lapis") * PWLevel.config.getLong("orexp.lapis"));
                long redstonexp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "redstone") * PWLevel.config.getLong("orexp.redstone"));
                long goldxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "gold") * PWLevel.config.getLong("orexp.gold"));
                long coalxp = PWLevel.config.getLong("orexp.coal");
                if (goldxp >= coalxp && goldxp >= ironxp && goldxp >= redstonexp && goldxp >= lapisxp) {
                    return ChatColor.YELLOW + "金矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(goldxp) + ChatColor.WHITE + "XP/个";
                }
                if (lapisxp >= coalxp && lapisxp >= ironxp && lapisxp >= redstonexp && lapisxp >= goldxp) {
                    return ChatColor.BLUE + "青金石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(lapisxp) + ChatColor.WHITE + "XP/个";
                }
                if (redstonexp >= coalxp && redstonexp >= ironxp && redstonexp >= lapisxp && redstonexp >= goldxp) {
                    return ChatColor.RED + "红石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(redstonexp) + ChatColor.WHITE + "XP/个";
                }
                if (ironxp >= coalxp && ironxp >= lapisxp && ironxp >= redstonexp && ironxp >= goldxp) {
                    return ChatColor.GOLD + "铁矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(ironxp) + ChatColor.WHITE + "XP/个";
                } else {
                    return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(coalxp) + ChatColor.WHITE + "XP/个";
                }
            }
            if (level >= 90 && level < 100) {
                long ironxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "iron") * PWLevel.config.getLong("orexp.iron"));
                long diamondxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "diamond") * PWLevel.config.getLong("orexp.diamond"));
                long lapisxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "lapis") * PWLevel.config.getLong("orexp.lapis"));
                long redstonexp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "redstone") * PWLevel.config.getLong("orexp.redstone"));
                long goldxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "gold") * PWLevel.config.getLong("orexp.gold"));
                long coalxp = PWLevel.config.getLong("orexp.coal");
                if (diamondxp >= coalxp && diamondxp >= ironxp && diamondxp >= redstonexp && diamondxp >= lapisxp && diamondxp >= goldxp) {
                    return ChatColor.AQUA + "钻石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(diamondxp) + ChatColor.WHITE + "XP/个";
                }
                if (goldxp >= coalxp && goldxp >= ironxp && goldxp >= redstonexp && goldxp >= lapisxp && goldxp >= diamondxp) {
                    return ChatColor.YELLOW + "金矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(goldxp) + ChatColor.WHITE + "XP/个";
                }
                if (lapisxp >= coalxp && lapisxp >= ironxp && lapisxp >= redstonexp && lapisxp >= goldxp && lapisxp >= diamondxp) {
                    return ChatColor.BLUE + "青金石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(lapisxp) + ChatColor.WHITE + "XP/个";
                }
                if (redstonexp >= coalxp && redstonexp >= ironxp && redstonexp >= lapisxp && redstonexp >= goldxp && redstonexp >= diamondxp) {
                    return ChatColor.RED + "红石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(redstonexp) + ChatColor.WHITE + "XP/个";
                }
                if (ironxp >= coalxp && ironxp >= lapisxp && ironxp >= redstonexp && ironxp >= goldxp && ironxp >= diamondxp) {
                    return ChatColor.GOLD + "铁矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(ironxp) + ChatColor.WHITE + "XP/个";
                } else {
                    return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(coalxp) + ChatColor.WHITE + "XP/个";
                }
            }
            if (level >= 100) {
                long ironxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "iron") * PWLevel.config.getLong("orexp.iron"));
                long emeraldxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "emerald") * PWLevel.config.getLong("orexp.emerald"));
                long diamondxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "diamond") * PWLevel.config.getLong("orexp.diamond"));
                long lapisxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "lapis") * PWLevel.config.getLong("orexp.lapis"));
                long redstonexp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "redstone") * PWLevel.config.getLong("orexp.redstone"));
                long goldxp = Math.round(playerOreExpDataManager.getOreExpPercent(p, "gold") * PWLevel.config.getLong("orexp.gold"));
                long coalxp = PWLevel.config.getLong("orexp.coal");
                if (emeraldxp >= coalxp && emeraldxp >= ironxp && emeraldxp >= redstonexp && emeraldxp >= lapisxp && emeraldxp >= goldxp && emeraldxp >= diamondxp) {
                    return ChatColor.GREEN + "绿宝石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(emeraldxp) + ChatColor.WHITE + "XP/个";
                }
                if (diamondxp >= coalxp && diamondxp >= ironxp && diamondxp >= redstonexp && diamondxp >= lapisxp && diamondxp >= goldxp && diamondxp >= emeraldxp) {
                    return ChatColor.AQUA + "钻石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(diamondxp) + ChatColor.WHITE + "XP/个";
                }
                if (goldxp >= coalxp && goldxp >= ironxp && goldxp >= redstonexp && goldxp >= lapisxp && goldxp >= diamondxp && goldxp >= emeraldxp) {
                    return ChatColor.YELLOW + "金矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(goldxp) + ChatColor.WHITE + "XP/个";
                }
                if (lapisxp >= coalxp && lapisxp >= ironxp && lapisxp >= redstonexp && lapisxp >= goldxp && lapisxp >= diamondxp && lapisxp >= emeraldxp) {
                    return ChatColor.BLUE + "青金石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(lapisxp) + ChatColor.WHITE + "XP/个";
                }
                if (redstonexp >= coalxp && redstonexp >= ironxp && redstonexp >= lapisxp && redstonexp >= goldxp && redstonexp >= diamondxp && redstonexp >= emeraldxp) {
                    return ChatColor.RED + "红石矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(redstonexp) + ChatColor.WHITE + "XP/个";
                }
                if (ironxp >= coalxp && ironxp >= lapisxp && ironxp >= redstonexp && ironxp >= goldxp && ironxp >= diamondxp && ironxp >= emeraldxp) {
                    return ChatColor.GOLD + "铁矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(ironxp) + ChatColor.WHITE + "XP/个";
                } else {
                    return ChatColor.GRAY + "煤矿 " + ChatColor.LIGHT_PURPLE + "▸ " + numberFormat.getLongFormat(coalxp) + ChatColor.WHITE + "XP/个";
                }
            }


        }
        if (identifier.equals("shardtime")) {

            if (getPlayerShardBoosterDataMap().containsKey(p.getName())) {
                TimeFormat timeFormat = new TimeFormat();
                return "" + timeFormat.getTimeFormat(playerBoosterDataManager.getShardBoosterTime(p));
            } else {
                return ChatColor.RED + "✖ " + ChatColor.WHITE + "未激活";
            }
        }
        if (identifier.equals("shardmulti")) {

            if (getPlayerShardBoosterDataMap().containsKey(p.getName())) {

                return "x" + playerBoosterDataManager.getShardMultiple(p);
            } else {
                return "";
            }
        }
        if (identifier.equals("exptime")) {

            if (getPlayerExpBoosterDataMap().containsKey(p.getName())) {
                TimeFormat timeFormat = new TimeFormat();
                return "" + timeFormat.getTimeFormat(playerBoosterDataManager.getExpBoosterTime(p));
            } else {
                return ChatColor.RED + "✖ " + ChatColor.WHITE + "未激活";
            }
        }
        if (identifier.equals("expmulti")) {

            if (getPlayerExpBoosterDataMap().containsKey(p.getName())) {

                return "x" + playerBoosterDataManager.getExpMultiple(p);
            } else {
                return "";
            }
        }
        if (identifier.equals("energytime")) {

            if (getPlayerEnergyBoosterDataMap().containsKey(p.getName())) {
                TimeFormat timeFormat = new TimeFormat();
                return "" + timeFormat.getTimeFormat(playerBoosterDataManager.getEnergyBoosterTime(p));
            } else {
                return ChatColor.RED + "✖ " + ChatColor.WHITE + "未激活";
            }
        }
        if (identifier.equals("energymulti")) {

            if (getPlayerEnergyBoosterDataMap().containsKey(p.getName())) {

                return "x" + playerBoosterDataManager.getEnergyMultiple(p);
            } else {
                return "";
            }
        }
        if (identifier.equals("level")) {
            return "" + playerExpDataManager.getLevel(p);
        }
        if (identifier.equals("nextlevel")) {
            if ((playerExpDataManager.getPrestige(p) == 0 && playerExpDataManager.getLevel(p) == 100) ||
                    (playerExpDataManager.getPrestige(p) == 1 && playerExpDataManager.getLevel(p) == 101) ||
                    (playerExpDataManager.getPrestige(p) == 2 && playerExpDataManager.getLevel(p) == 102) ||
                    (playerExpDataManager.getPrestige(p) == 3 && playerExpDataManager.getLevel(p) == 103) ||
                    (playerExpDataManager.getPrestige(p) == 4 && playerExpDataManager.getLevel(p) == 104) ||
                    (playerExpDataManager.getPrestige(p) == 5 && playerExpDataManager.getLevel(p) == 105) ||
                    (playerExpDataManager.getPrestige(p) == 6 && playerExpDataManager.getLevel(p) == 106)
            ) {
                return "荣誉等级" + ChatColor.LIGHT_PURPLE + "" + (playerExpDataManager.getPrestige(p) + 1);
            }
            return "" + (playerExpDataManager.getLevel(p) + 1);
        }
        if (identifier.equals("totalexp")) {
            NumberFormat numberFormat = new NumberFormat();
            return "" + numberFormat.getLongFormat(playerExpDataManager.getTotalExp(p));
        }
        if (identifier.equals("prestige")) {
            return "" + playerExpDataManager.getPrestige(p);
        }
        if (identifier.equals("prestige1")) {
            int prestige = playerExpDataManager.getPrestige(p);
            if (prestige == 0) {
                return "";
            }
            if (prestige == 1) {
                return ChatColor.WHITE + "§一";
            }
            if (prestige == 2) {
                return ChatColor.WHITE + "§二";
            }
            if (prestige == 3) {
                return ChatColor.WHITE + "§三";
            }
            if (prestige == 4) {
                return ChatColor.WHITE + "§四";
            }
            if (prestige == 5) {
                return ChatColor.WHITE + "§五";
            }
            if (prestige == 6) {
                return ChatColor.WHITE + "§六";
            }
        }
        if (identifier.equals("exptonext")) {
            if ((playerExpDataManager.getPrestige(p) == 0 && playerExpDataManager.getLevel(p) == 100) ||
                    (playerExpDataManager.getPrestige(p) == 1 && playerExpDataManager.getLevel(p) == 101) ||
                    (playerExpDataManager.getPrestige(p) == 2 && playerExpDataManager.getLevel(p) == 102) ||
                    (playerExpDataManager.getPrestige(p) == 3 && playerExpDataManager.getLevel(p) == 103) ||
                    (playerExpDataManager.getPrestige(p) == 4 && playerExpDataManager.getLevel(p) == 104) ||
                    (playerExpDataManager.getPrestige(p) == 5 && playerExpDataManager.getLevel(p) == 105) ||
                    (playerExpDataManager.getPrestige(p) == 6 && playerExpDataManager.getLevel(p) == 106)
            ) {
                return "请升级";
            }
            NumberFormat numberFormat = new NumberFormat();
            long nxetExp = PWLevel.config.getLong("levels.level" + (playerExpDataManager.getLevel(p) + 1));
            nxetExp = nxetExp * (1 + playerExpDataManager.getPrestige(p));
            return numberFormat.getLongFormat(nxetExp - playerExpDataManager.getTotalExp(p)) + "XP";
        }
        if (identifier.equals("percent")) {
            if ((playerExpDataManager.getPrestige(p) == 0 && playerExpDataManager.getLevel(p) == 100) ||
                    (playerExpDataManager.getPrestige(p) == 1 && playerExpDataManager.getLevel(p) == 101) ||
                    (playerExpDataManager.getPrestige(p) == 2 && playerExpDataManager.getLevel(p) == 102) ||
                    (playerExpDataManager.getPrestige(p) == 3 && playerExpDataManager.getLevel(p) == 103) ||
                    (playerExpDataManager.getPrestige(p) == 4 && playerExpDataManager.getLevel(p) == 104) ||
                    (playerExpDataManager.getPrestige(p) == 5 && playerExpDataManager.getLevel(p) == 105) ||
                    (playerExpDataManager.getPrestige(p) == 6 && playerExpDataManager.getLevel(p) == 106)
            ) {
                return ChatColor.RED + "已满级";
            }
            long nxetExp = PWLevel.config.getLong("levels.level" + (playerExpDataManager.getLevel(p) + 1));
            nxetExp = nxetExp * (1 + playerExpDataManager.getPrestige(p));
            long lastExp = PWLevel.config.getLong("levels.level" + playerExpDataManager.getLevel(p));
            long totalneed = nxetExp - lastExp;
            long thislevelexp = playerExpDataManager.getTotalExp(p) - lastExp;
            float percent = ((float) thislevelexp / totalneed * 100);
            BigDecimal percentBigDecimal = new BigDecimal(percent);
            double percentRounded = percentBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            return percentRounded + "%";
        }

        return null;
    }
}
