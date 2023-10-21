package cn.pixelwar.pwblockmanager.BlockStuff.enchants;

import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import cn.pixelwar.pwblockmanager.PWBlockManager;
import de.tr7zw.nbtapi.NBTItem;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Break {
    BlockAnimationData blockAnimationData = new BlockAnimationData();

    public void breakEffect(Player player, Block block) {
        player.spawnParticle(Particle.SPELL, block.getLocation().add(0.5, 1.2, 0.5), 5, 0.2, 0, 0.2);
//        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SPELL, true, (float) (block.getX()+0.5),  (float)(block.getY()+1.2), (float) (block.getZ() +0.5), 0.2f, 0, 0.2f, 0, 5);
//        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 0.5f, 2);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 0.5f, 2);
            }
        }.runTaskLaterAsynchronously(PWBlockManager.getPlugin(), 3L);
    }

    public void goBreak(Player player, Block block) {
        Lucky lucky = new Lucky();
        ItemStack tool = player.getItemInHand();
        NBTItem nbtItem = new NBTItem(tool);
        if (nbtItem.hasKey("ae_enchantment;pohuai")) {
            int enchantLevel = nbtItem.getInteger("ae_enchantment;pohuai");
            double chance = enchantLevel * 100 / 2 - (enchantLevel - 1) * 10;
            if (nbtItem.hasKey("ae_enchantment;lucky")) {
                chance = chance * lucky.getLuckyChance(player);
            }
            if (nbtItem.hasKey("ae_enchantment;nlucky")) {
                chance = chance + nbtItem.getInteger("ae_enchantment;nlucky");
            }
            int n5 = RandomUtils.nextInt(0, 10000);
            if (n5 < chance) {
                breakEffect(player, block);
                String locString = player + " " + block.getLocation();
                blockAnimationData.addBlockAnimationDataWithoutCrit(locString, 100.0f, player, block);
            }
        }
    }

}
