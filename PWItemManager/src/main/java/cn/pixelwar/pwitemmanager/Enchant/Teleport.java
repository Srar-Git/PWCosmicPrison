package cn.pixelwar.pwitemmanager.Enchant;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Set;

public class Teleport {

    public void EnchantTeleport(Player player, int distance) {
        ArrayList<Block> Blocks = (ArrayList<Block>) player.getLineOfSight((Set) null, distance);
        Vector vec = player.getLocation().getDirection();
        Location playerLoc = player.getLocation().add(vec); //Get the player's location
        for (Block block : Blocks) {
            if (!block.getType().isSolid()) {


                Location loc = block.getLocation().add(0.5d, 0, 0.5d).clone();
                double RealDistance = playerLoc.distance(loc);
                Location ref = playerLoc.clone();


                loc.setYaw(playerLoc.getYaw()); //Set the yaw of the target location to the player's yaw
                loc.setPitch(playerLoc.getPitch()); //Set the pitch of the target location to the player's pitch

                player.teleport(loc);
                player.setFallDistance(0);
            }
        }
    }

}
