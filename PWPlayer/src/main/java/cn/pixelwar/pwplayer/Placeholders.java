package cn.pixelwar.pwplayer;

import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PVPRegion;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Utils.NumberFormat;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

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
        return "playerdata";
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

        // %playerdata_pvpregion%
        if (identifier.contains("pvpregion")) {
            return PVPRegion.getPVPRegionWithWGRegionName(p).getName();
        }
//        if (identifier.contains("nearguard")) {
////            AtomicReference<String> name = new AtomicReference<>("");
//            Consumer<String> nameConsumer = name -> {
//                Bukkit.getScheduler().runTask(PWPlayer.getPlugin(), ()->{
//                    name. = GuardManager.getNearNPCName(p ,20);
//                });
//            };
//
//            return nameConsumer.toString();
//        }

        // %playerdata_int-INTdata名字% eg.%playerdata_int-ENCHANTSLOTS%
        if (identifier.contains("int")) {
            return numberFormat.getIntFormat(IntDataManager.getIntData(p, IntDataType.valueOf(identifier.split("-")[1])));
        }
        if (identifier.contains("double")) {
            return numberFormat.getDoubleFormat(DoubleDataManager.getDoubleData(p, DoubleDataType.valueOf(identifier.split("-")[1])));
        }

        return null;
    }
}

