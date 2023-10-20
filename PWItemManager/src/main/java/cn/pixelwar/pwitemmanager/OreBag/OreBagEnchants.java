package cn.pixelwar.pwitemmanager.OreBag;//package cn.pixelwar.pixelwaritemmanager.OreBag;
//
//import me.yic.xconomy.XConomyAPI;
//import org.bukkit.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//
//import java.math.BigDecimal;
//
//public class OreBagEnchants {
//
//    public void autoSell(Player player, ItemStack ore, int amount){
//        double price = 0;
//        if(ore.getType().equals(Material.COAL_ORE)){
//            price = (double) 0.06 * amount;
//        }
//        if(ore.getType().equals(Material.IRON_ORE)){
//            price = (double) 0.21 * amount;
//        }
//        if(ore.getType().equals(Material.LAPIS_ORE)){
//            price = (double) 0.54 * amount;
//        }
//        if(ore.getType().equals(Material.REDSTONE_ORE)){
//            price = (double) 1.62 * amount;
//        }
//        if(ore.getType().equals(Material.GOLD_ORE)){
//            price = (double) 4.86 * amount;
//        }
//        if(ore.getType().equals(Material.DIAMOND_ORE)){
//            price = (double) 7.35 * amount;
//        }
//        if(ore.getType().equals(Material.EMERALD_ORE)){
//            price = (double) 27.35 * amount;
//        }
//        if(ore.getType().equals(Material.COAL)){
//            price = (double) 0.32 * amount;
//        }
//        if(ore.getType().equals(Material.IRON_INGOT)){
//            price = (double) 1.1 * amount;
//        }
//        if(ore.equals(new ItemStack(Material.INK_SACK, 1, (short)12))){
//            price = (double) 2.85 * amount;
//        }
//        if(ore.getType().equals(Material.REDSTONE)){
//            price = (double) 8.58 * amount;
//        }
//        if(ore.getType().equals(Material.GOLD_INGOT)){
//            price = (double) 25.76 * amount;
//        }
//        if(ore.getType().equals(Material.DIAMOND)){
//            price = (double) 38.94 * amount;
//        }
//        if(ore.getType().equals(Material.EMERALD)){
//            price = (double) 144.92 * amount;
//        }
//        BigDecimal prizeDecimal = new BigDecimal(price);
//        XConomyAPI xcapi = new XConomyAPI();
//        xcapi.changebalance(player.getUniqueId(), player.getName(), prizeDecimal, true);
//        player.sendTitle(ChatColor.GRAY+ "存储箱自动出售获得" + ChatColor.YELLOW +""+ChatColor.BOLD+price+ChatColor.DARK_GREEN +""+ChatColor.BOLD +" $"  ,ChatColor.WHITE+"触发附魔"+ChatColor.GRAY+" ▸ "+ChatColor.GREEN+"自动出售", 10, 80, 10);
//
//
//
//
//
//
//
//
//    }
//
//}
