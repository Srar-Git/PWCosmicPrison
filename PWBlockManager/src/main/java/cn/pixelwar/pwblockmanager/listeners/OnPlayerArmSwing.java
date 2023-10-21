package cn.pixelwar.pwblockmanager.listeners;

import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import cn.pixelwar.pwblockmanager.BlockStuff.enchants.Break;
import cn.pixelwar.pwblockmanager.BlockStuff.enchants.Suilie;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import cn.pixelwar.pwblockmanager.customevents.BlockBrokenEvent;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Set;

public class OnPlayerArmSwing implements Listener {

    PlayerData playerData = new PlayerData();
    BlockAnimationData blockAnimationData = new BlockAnimationData();

    @EventHandler
    public void onPlayerArmSwing(PlayerAnimationEvent event) {

//        new BukkitRunnable() {
//            @Override
//            public void run() {

        Block block = event.getPlayer().getTargetBlock((Set<Material>) null, 5);
        if (block.getType().equals(Material.AIR)) return;

        Block isDiggingBlock = null;
        try {
            isDiggingBlock = playerData.getIsDigBlock(event.getPlayer());
            if (!(couldDig(isDiggingBlock, event.getPlayer()))) {
                return;
            }
            if (isDiggingBlock.equals(block)) {
                try {
                    blockAnimationData.checkPlayerPoint(event.getPlayer(), isDiggingBlock);
                } catch (NullPointerException e) {
                }
                Suilie suilie = new Suilie();
                suilie.checkSuilie(event.getPlayer(), isDiggingBlock);
                Break breakEnchant = new Break();
                breakEnchant.goBreak(event.getPlayer(), isDiggingBlock);
                String locString = event.getPlayer() + " " + block.getLocation();
                String blockFace = playerData.getIsDigBlockFace(event.getPlayer());
                Location activeLoc = getActiveLoc(isDiggingBlock, event.getPlayer(), blockFace);
                float digSpeed = playerData.getDigSpeed(event.getPlayer());
                try {
                    if (blockAnimationData.ifBlockActive(locString)) {
                        float speedMultiple = playerData.getSpeedMultiple(event.getPlayer());
                        digSpeed = digSpeed * speedMultiple;
                    }
                } catch (NullPointerException e) {
                }
                blockAnimationData.addBlockAnimationData(locString, digSpeed, event.getPlayer(), block, activeLoc);
                if (blockAnimationData.getBlockAnimationData(locString).getBlockDamage() >= 100) {
                    Block finalIsDiggingBlock = isDiggingBlock;
//                            new BukkitRunnable() {
//                                @Override
//                                public void run() {
//                                    Bukkit.getServer().getPluginManager().callEvent(new BlockBrokenEvent(event.getPlayer(), finalIsDiggingBlock, blockFace));
//                                }
//                            }.runTask(PixelWarBlockManager.getPlugin());
                    Bukkit.getServer().getPluginManager().callEvent(new BlockBrokenEvent(event.getPlayer(), isDiggingBlock, blockFace));

                }
            }
        } catch (NullPointerException e) {
        }
//            }
//        }.runTaskAsynchronously(PixelWarBlockManager.getPlugin());
    }


    public Location getActiveLoc(Block block, Player player, String blockFace) {
        Location eyeloc = player.getEyeLocation();
        Vector direction = eyeloc.getDirection();
        direction.multiply(0.08);
        Location point2 = null;

        for (int i = 0; i <= 80; i++) {
            Location point = eyeloc.add(direction);
            if (point.getBlock().getType().isSolid()) {
                point2 = eyeloc.subtract(direction);
                break;
            }
        }
        Location standard = block.getLocation().add(0.5, 0.5, 0.5);

        switch (blockFace) {
            case "UP":
                point2.setY(standard.getY() + 0.55);
                if (point2.getX() > standard.getX() + 0.5 || point2.getX() < standard.getX() - 0.5)
                    point2.setX(standard.getX());
                if (point2.getZ() > standard.getZ() + 0.5 || point2.getZ() < standard.getZ() - 0.5)
                    point2.setZ(standard.getZ());
                break;
            case "DOWN":
                point2.setY(standard.getY() - 0.55);
                if (point2.getX() > standard.getX() + 0.5 || point2.getX() < standard.getX() - 0.5)
                    point2.setX(standard.getX());
                if (point2.getZ() > standard.getZ() + 0.5 || point2.getZ() < standard.getZ() - 0.5)
                    point2.setZ(standard.getZ());
                break;
            case "EAST":
                point2.setX(standard.getX() + 0.55);
                if (point2.getY() > standard.getY() + 0.5 || point2.getY() < standard.getY() - 0.5)
                    point2.setY(standard.getY());
                if (point2.getZ() > standard.getZ() + 0.5 || point2.getZ() < standard.getZ() - 0.5)
                    point2.setZ(standard.getZ());
                break;
            case "WEST":
                point2.setX(standard.getX() - 0.55);
                if (point2.getY() > standard.getY() + 0.5 || point2.getY() < standard.getY() - 0.5)
                    point2.setY(standard.getY());
                if (point2.getZ() > standard.getZ() + 0.5 || point2.getZ() < standard.getZ() - 0.5)
                    point2.setZ(standard.getZ());
                break;
            case "SOUTH":
                point2.setZ(standard.getZ() + 0.55);
                if (point2.getY() > standard.getY() + 0.5 || point2.getY() < standard.getY() - 0.5)
                    point2.setY(standard.getY());
                if (point2.getX() > standard.getX() + 0.5 || point2.getX() < standard.getX() - 0.5)
                    point2.setX(standard.getX());
                break;
            case "NORTH":
                point2.setZ(standard.getZ() - 0.55);
                if (point2.getY() > standard.getY() + 0.5 || point2.getY() < standard.getY() - 0.5)
                    point2.setY(standard.getY());
                if (point2.getX() > standard.getX() + 0.5 || point2.getX() < standard.getX() - 0.5)
                    point2.setX(standard.getX());
                break;

        }

        return point2;
    }

    public boolean couldDig(Block block, Player player) {
        ItemStack tool = player.getItemInHand();
        if (!(
                tool.getType().equals(Material.WOODEN_PICKAXE) ||
                        tool.getType().equals(Material.STONE_PICKAXE) ||
                        tool.getType().equals(Material.GOLDEN_PICKAXE) ||
                        tool.getType().equals(Material.IRON_PICKAXE) ||
                        tool.getType().equals(Material.DIAMOND_PICKAXE))
        ) {
            return false;
        }
        PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
        int level = playerExpDataManager.getLevel(player);
        if (tool.getType().equals(Material.STONE_PICKAXE)) {
            if (level < 30) {
                return false;
            }
        }
        if (tool.getType().equals(Material.GOLDEN_PICKAXE)) {
            if (level < 50) {
                return false;
            }
        }
        if (tool.getType().equals(Material.IRON_PICKAXE)) {
            if (level < 70) {
                return false;
            }
        }
        if (tool.getType().equals(Material.DIAMOND_PICKAXE)) {
            if (level < 90) {
                return false;
            }
        }

        NBTItem nbtItem = new NBTItem(tool);
        if (nbtItem.getInteger("toollevel") < nbtItem.getInteger("toolmaxlevel")) {
            if (nbtItem.getLong("toolxp") >= nbtItem.getLong("toolneedxp")) {
                return false;
            }
        }

        return true;
    }


}