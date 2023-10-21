package cn.pixelwar.pwitemmanager.Utils.Shard;

import cn.pixelwar.pwitemmanager.NBT.GetItemNBT;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (
                action.equals(Action.RIGHT_CLICK_AIR) ||
                        action.equals(Action.RIGHT_CLICK_BLOCK)
        ) {
            GetItemNBT getItemNBT = new GetItemNBT();
            String tier = getItemNBT.getItemStringNBT(event.getPlayer().getItemInHand(), "shardtier");
            if (
                    event.getPlayer().getItemInHand().getType().equals(Material.PRISMARINE_SHARD) ||
                            tier.equals("common") ||
                            tier.equals("uncommon") ||
                            tier.equals("rare") ||
                            tier.equals("epic") ||
                            tier.equals("legend") ||
                            tier.equals("god")
            ) {

            }


        }

    }
}
