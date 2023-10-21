package cn.pixelwar.pwplayer.Listeners;//package cn.pixelwar.pwplayer.Listeners;
//
//import com.sk89q.worldedit.bukkit.BukkitAdapter;
//import com.sk89q.worldedit.world.World;
//import com.sk89q.worldguard.WorldGuard;
//import com.sk89q.worldguard.protection.ApplicableRegionSet;
//import com.sk89q.worldguard.protection.managers.RegionManager;
//import com.sk89q.worldguard.protection.regions.ProtectedRegion;
//import com.sk89q.worldguard.protection.regions.RegionContainer;
//import com.sk89q.worldguard.protection.regions.RegionQuery;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.player.PlayerInteractEvent;
//
//import java.util.Set;
//
//import static org.bukkit.Bukkit.getServer;
//
//public class OnClickBlock implements Listener {
//
//    @EventHandler
//    public void quit(PlayerInteractEvent e) {
//        Player p = e.getPlayer();
////        RegionManager manager = WorldGuardPlugin.getRegionContainer().get(world);
////        WorldGuardPlugin wg = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
//        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
//        RegionManager rgm = container.get(BukkitAdapter.adapt(p.getWorld()));
//        RegionQuery query = container.createQuery();
//        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(p.getLocation()));
//        for (ProtectedRegion region : set){
//            e.getPlayer().sendMessage(region.getId());
//            e.getPlayer().sendMessage(region.toString());
//        }
////        Set<ProtectedRegion> regionSet;
////        regionSet = ars.getRegions();
//
//    }
//
//}
