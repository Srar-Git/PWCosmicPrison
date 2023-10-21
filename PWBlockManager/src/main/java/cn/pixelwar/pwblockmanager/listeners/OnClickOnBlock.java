package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.Jinghua;
import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.JinghuaDataManager;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.Meteor;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import cn.pixelwar.pwblockmanager.Utils.ChatColorCast;
import cn.pixelwar.pwblockmanager.Utils.TimeFormat;
import cn.pixelwar.pwblockmanager.customevents.MeteorDownEvent;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnClickOnBlock implements Listener {
    Meteor meteor = new Meteor();
    Jinghua jinghua = new Jinghua();
    MeteorDataManager manager = new MeteorDataManager();
    JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {

//        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
//            jinghuaDataManager.subtractAmount(event.getClickedBlock().getLocation() , 5, event.getPlayer());
//        }
//        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
//            jinghua.randomSpawnJinghua();
//        }

        //如果不是流星矿石/精华矿石，就删掉
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.NETHER_QUARTZ_ORE) &&
                    !(MeteorDataManager.getMeteorDataMap().containsKey(event.getClickedBlock().getLocation()))
            ) {
                event.getClickedBlock().getLocation().getWorld().setType(event.getClickedBlock().getLocation(), Material.AIR);
                return;
            }
        }

        //查询流星矿石剩余量
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if (MeteorDataManager.getMeteorDataMap().containsKey(event.getClickedBlock().getLocation())) {
                PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
                TimeFormat timeFormat = new TimeFormat();
                int level = playerExpDataManager.getLevel(event.getPlayer());
                int amount = MeteorDataManager.getMeteorDataMap().get(event.getClickedBlock().getLocation()).getAmount();
                int time = MeteorDataManager.getMeteorDataMap().get(event.getClickedBlock().getLocation()).getLastTime();
                if (level < 10) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&8&l煤矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));
                    return;
                }
                if (level < 30 && level >= 10) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&7&l铁矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));
                    return;
                }
                if (level < 50 && level >= 30) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&9&l青金石矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));

                    return;
                }
                if (level < 70 && level >= 50) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&c&l红石矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));

                    return;
                }
                if (level < 90 && level >= 70) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&e&l金矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));

                    return;
                }
                if (level < 100 && level >= 90) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&a&l钻石矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));

                    return;
                }
                if (level >= 100) {
                    PlayerData.sendActionbar(event.getPlayer(), ChatColorCast.format("&f剩余&a&l绿宝石矿 &f▸ &d&l" + amount + " &7| &f剩余时间 ▸ &a&l" + timeFormat.getTimeFormat(time)));

                    return;
                }
            }
        }

    }


}
