package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwitemmanager.NBT.Item.PWItem;
import cn.pixelwar.pwplayer.File.YamlStorage;
import cn.pixelwar.pwplayer.PWPlayer;
import cn.pixelwar.pwplayer.PlayerAction.Teleport.Teleport;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth.PlayerHealthManager;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerWalkSpeed.PlayerWalkSpeedManager;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import cn.pixelwar.pwplayer.Utils.NumberFormat;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class OnJoin implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        //删除NPC
        if (PlayerNPCManager.playerNPCMap.containsKey(p.getName())) {

            NPC npc = CitizensAPI.getNPCRegistry().getById(PlayerNPCManager.playerNPCMap.get(p.getName()).getNPCID());
            PlayerNPCManager.removePlayerNPC(npc);
            PlayerNPCManager.playerNPCMap.remove(p.getName());
        }

        //先加载玩家数据
        YamlStorage yamlStorage = new YamlStorage();


        new BukkitRunnable() {
            @Override
            public void run() {
                Teleport.couldTP.remove(p);
                boolean isFirst = yamlStorage.CheckYamlFile(p);
                yamlStorage.CreatePlayerDataMap(p);
                //设置玩家血量
                PlayerHealthManager playerHealthManager = new PlayerHealthManager();
                PlayerWalkSpeedManager playerWalkSpeedManager = new PlayerWalkSpeedManager();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        playerHealthManager.updateMaxHealth(p);
                        playerWalkSpeedManager.updateMaxWalkSpeed(p);
                        if (DoubleDataManager.getDoubleData(p, DoubleDataType.LASTONLINEHEALTH) > p.getMaxHealth()) {
                            p.setHealth(p.getMaxHealth());
                        } else {
                            p.setHealth(DoubleDataManager.getDoubleData(p, DoubleDataType.LASTONLINEHEALTH));
                        }
                        if (isFirst) {
                            doFirstJoin(p);
                        }
                        //清空玩家背包(玩家战斗模式退出被杀)
                        if (IntDataManager.IntDataMap.get(p.getName()).singleMap.get(IntDataType.CLEARINVONJOIN) == 1) {
                            p.getInventory().clear();
                            Location location = Teleport.warpsMap.get("spawn");
                            p.teleport(location);
                            p.sendMessage(ChatColorCast.format(""));
                            p.sendMessage(ChatColorCast.format("&c▸ 你因为在&d&l战斗模式&c中离开游戏并且被击杀，物品已掉落"));
                            p.sendMessage(ChatColorCast.format(""));
                            IntDataManager.IntDataMap.get(p.getName()).singleMap.put(IntDataType.CLEARINVONJOIN, 0);
                            yamlStorage.saveOffLinePlayerData(p.getName(), IntDataType.CLEARINVONJOIN, 0);
                        }
                    }
                }.runTask(PWPlayer.getPlugin());
            }
        }.runTaskAsynchronously(PWPlayer.getPlugin());


    }

    public void doFirstJoin(Player player) {
        PWPlayer.totalPlayerAmount += 1;
        NumberFormat numberFormat = new NumberFormat();
        player.sendMessage(ChatColorCast.format("&d▸ &f欢迎&b&l" + player.getName() + "&f加入深空之狱. &7(第 " + numberFormat.getIntFormat(PWPlayer.totalPlayerAmount) + " 位玩家)"));
        Location location = Teleport.warpsMap.get("newspawn");
        player.teleport(location);
        ItemStack itemStack = PWItem.wooden_pickaxe.getItem();
        ItemStack itemStack2 = new ItemStack(Material.BREAD, 5);
        player.getInventory().setItem(0, itemStack);
        player.getInventory().setItem(1, itemStack2);
    }

}
