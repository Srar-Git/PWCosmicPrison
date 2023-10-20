package cn.pixelwar.pwlevel.Commands;

import cn.pixelwar.pwlevel.Files.YamlStorage;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements CommandExecutor {
    PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
    YamlStorage yamlStorage = new YamlStorage();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        // /pwlevel
        if (command.getName().equalsIgnoreCase("pwlevel")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                if (args[0].equalsIgnoreCase("check")) {
                    Player checked = Bukkit.getPlayer(args[1]);
                    player.sendMessage("玩家"+args[1]+"的等级信息：");
                    player.sendMessage("等级：Lv."+ playerExpDataManager.getLevel(checked));
                    player.sendMessage("总经验："+ playerExpDataManager.getTotalExp(checked)+" XP");
                    player.sendMessage("荣誉等级："+ playerExpDataManager.getPrestige(checked));
                }
                if (args[0].equalsIgnoreCase("addxp")) {
                    Player added = Bukkit.getPlayer(args[1]);
                    long addedXP = playerExpDataManager.getTotalExp(added)+ Integer.valueOf(args[2]);
                    playerExpDataManager.createPlayerExpData(added, addedXP, playerExpDataManager.getPrestige(added), playerExpDataManager.getLevel(added));
                }
                if (args[0].equalsIgnoreCase("save")) {
                    Player saved = Bukkit.getPlayer(args[1]);
                    yamlStorage.savePlayerData(saved);
                }
            }

            else if(sender instanceof ConsoleCommandSender){



            }


        }


        return true;
    }

}
