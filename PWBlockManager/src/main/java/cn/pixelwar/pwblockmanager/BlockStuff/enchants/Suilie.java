package cn.pixelwar.pwblockmanager.BlockStuff.enchants;

import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import cn.pixelwar.pwblockmanager.PWBlockManager;
import de.tr7zw.nbtapi.NBTItem;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Suilie {

    BlockAnimationData blockAnimationData = new BlockAnimationData();


    public void suilieEffect(Player player, float x, float y, float z) {

        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 0.5f, 2);
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                Location loc = new Location(player.getWorld(), (double) x, (double) y, (double) z);
                loc.getWorld().spawnParticle(Particle.SPELL_INSTANT, loc.clone().add(0, 0, 0), 1, 0.25f, 0, 0.25f);
                loc.getWorld().spawnParticle(Particle.SPELL_INSTANT, loc.clone().add(1, 0, 0), 1, 0.25f, 0, 0.25f);
                loc.getWorld().spawnParticle(Particle.SPELL_INSTANT, loc.clone().add(-1, 0, 0), 1, 0.25f, 0, 0.25f);
                loc.getWorld().spawnParticle(Particle.SPELL_INSTANT, loc.clone().add(0, 0, 1), 1, 0.25f, 0, 0.25f);
                loc.getWorld().spawnParticle(Particle.SPELL_INSTANT, loc.clone().add(0, 0, -1), 1, 0.25f, 0, 0.25f);
                i++;
                if (i >= 20) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0L, 5L);
    }

    public void suilieBlocks(Player player, Block centerBlock, int level) {
        List<Block> blocks = getNearbyBlocks(centerBlock.getLocation(), 1);
        for (Block b : blocks) {

            if (b.getType().equals(Material.COAL_ORE) || b.getType().equals(Material.COAL_BLOCK) || b.getType().equals(Material.DEEPSLATE_COAL_ORE) ||
                    b.getType().equals(Material.IRON_ORE) || b.getType().equals(Material.IRON_BLOCK) || b.getType().equals(Material.DEEPSLATE_IRON_ORE) ||
                    b.getType().equals(Material.LAPIS_ORE) || b.getType().equals(Material.LAPIS_BLOCK) || b.getType().equals(Material.DEEPSLATE_LAPIS_ORE) ||
                    b.getType().equals(Material.REDSTONE_ORE) || b.getType().equals(Material.REDSTONE_BLOCK) || b.getType().equals(Material.DEEPSLATE_REDSTONE_ORE) ||
                    b.getType().equals(Material.LEGACY_GLOWING_REDSTONE_ORE) ||
                    b.getType().equals(Material.GOLD_ORE) || b.getType().equals(Material.GOLD_BLOCK) || b.getType().equals(Material.DEEPSLATE_GOLD_ORE) ||
                    b.getType().equals(Material.DIAMOND_ORE) || b.getType().equals(Material.DIAMOND_BLOCK) || b.getType().equals(Material.DEEPSLATE_DIAMOND_ORE) ||
                    b.getType().equals(Material.EMERALD_ORE) || b.getType().equals(Material.EMERALD_BLOCK) || b.getType().equals(Material.DEEPSLATE_EMERALD_ORE) ||
                    b.getType().equals(Material.NETHER_QUARTZ_ORE)
            ) {
//                        for (int i = 0; i < level + 5; i++) {
                String locString = player + " " + b.getLocation();
                blockAnimationData.addBlockAnimationDataWithoutCrit(locString, (float) 9 * level, player, b);
//                        }

            }

        }
        blocks.clear();
    }


    public List<Block> getNearbyBlocks(Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for (int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for (int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for (int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    public void checkSuilie(Player player, Block block) {
        Lucky lucky = new Lucky();
        ItemStack tool = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(tool);
        if (nbtItem.hasKey("ae_enchantment;suilie")) {
            int enchantLevel = nbtItem.getInteger("ae_enchantment;suilie");
            double chance = enchantLevel * 100 / 2 - (enchantLevel - 1) * 20;
            if (nbtItem.hasKey("ae_enchantment;lucky")) {
                chance = chance * lucky.getLuckyChance(player);
            }
            if (nbtItem.hasKey("ae_enchantment;nlucky")) {
                chance = chance + nbtItem.getInteger("ae_enchantment;nlucky");
            }
            int n5 = RandomUtils.nextInt(0, 10000);
            if (n5 < chance) {
//                Bukkit.getServer().broadcastMessage(""+(enchantLevel*100/3-(enchantLevel-1)*10));
                suilieBlocks(player, block, enchantLevel);
                suilieEffect(player, (float) (block.getX() + 0.5), (float) (block.getY() + 1.2), (float) (block.getZ() + 0.5));
            }
        }
    }

}
