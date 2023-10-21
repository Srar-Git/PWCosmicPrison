package cn.pixelwar.pwitemmanager.Commands;

import cn.pixelwar.pwitemmanager.Files.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemStuff implements CommandExecutor {

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("pixelwaritem")) {
                player.sendMessage("长度：" + args.length);
                if (args[0].equalsIgnoreCase("playerinfo")) {
                    player.sendMessage("Saturation: " + player.getSaturation());
                    player.sendMessage("Exhaustion: " + player.getExhaustion());
                }
                if (args.length != 2) {
                    return false;
                }

                if (args[0].equalsIgnoreCase("save")) {
                    CustomItems.getItemFile().set(args[1], player.getItemInHand());
                    CustomItems.save();
                }
                if (args[0].equalsIgnoreCase("get")) {
                    ItemStack customItem = (ItemStack) CustomItems.getItemFile().get(args[1]);
                    player.getInventory().addItem(customItem);
                }


            }
        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
