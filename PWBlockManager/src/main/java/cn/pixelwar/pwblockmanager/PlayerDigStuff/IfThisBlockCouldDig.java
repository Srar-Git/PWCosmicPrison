package cn.pixelwar.pwblockmanager.PlayerDigStuff;//package cn.pixelwar.pwblockmanager.PlayerDigStuff;
//
//import cn.pixelwar.pwblockmanager.PixelWarBlockManager;
//import com.sk89q.worldguard.bukkit.RegionContainer;
//import com.sk89q.worldguard.protection.managers.RegionManager;
//import com.sk89q.worldguard.protection.regions.ProtectedRegion;
//import org.bukkit.block.Block;
//import org.bukkit.entity.Player;
//
//public class IfThisBlockCouldDig {
//
//    private Block block;
//    private Player player;
//    private ProtectedRegion region;
//
//    public boolean IfPlayerIsInRegion(Player player, String regionString) {
//        RegionContainer container = PixelWarBlockManager.getWorldGuard().getRegionContainer();
//        RegionManager regions = container.get(player.getWorld());
//        ProtectedRegion playerRegion = regions.getRegion(regionString);
//        for ( ProtectedRegion region : regions.getApplicableRegions(PixelWarBlockManager.getWorldGuard().wrapPlayer(player).getPosition())  ) {
//            if (region.equals(playerRegion)) {
//                return true;
//            }
//        }
//    return false;
//    }
//
//}
