package cn.pixelwar.pwitemmanager.NBT;

import cn.pixelwar.pwitemmanager.PWItemManager;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class UpdateHandLore {
    NumberFormat numberFormat = new NumberFormat();
    public void updateHandEnergy(Player player, int xp, int needXp){
        new BukkitRunnable() {
            @Override
            public void run() {
                Material material = player.getItemInHand().getType();
                if (material.toString().contains("PICKAXE")) {
                    NBTItem nbti = new NBTItem(player.getItemInHand());
                    nbti.setInteger("toolxp", xp);
                    nbti.setInteger("toolneedxp", needXp);
                    ItemStack finalItem = nbti.getItem();
                    int percent = (int) Math.round((double) xp / needXp * 100);
                    int blocks = percent / 2;
                    String block = "";

                    for (int i = 0; i < blocks; i++) {
                        block = block.concat("&b┃");
                        if (i >= 49) {
                            break;
                        }
                    }
                    for (int i = 0; i < 50 - blocks; i++) {
                        block = block.concat("&c┃");
                    }
                    ItemMeta itemMeta = finalItem.getItemMeta();
                    List<String> lore = itemMeta.getLore();
                    int infoLine = 0;
                    for (String str : lore){
                        if (str.contains("| 宇宙能量")){
                            break;
                        }infoLine++;
                    }
                    lore.set(infoLine+1, ChatColorCast.format(block + " &f&l" + percent + "%"));
                    lore.set(infoLine+2, ChatColorCast.format("&7(&f" + numberFormat.getIntFormat(xp) + " &7/ " + numberFormat.getIntFormat(needXp) + ")"));
                    itemMeta.setLore(lore);
                    finalItem.setItemMeta(itemMeta);
                    player.getInventory().setItemInMainHand(finalItem);
                }
                if (
                        material.equals(Material.COAL) || material.equals(Material.COAL_ORE) ||
                                material.equals(Material.IRON_INGOT) || material.equals(Material.IRON_ORE) ||
                                material.equals(Material.LAPIS_LAZULI) || material.equals(Material.LAPIS_ORE) ||
                                material.equals(Material.REDSTONE) || material.equals(Material.REDSTONE_ORE) ||
                                material.equals(Material.GOLD_INGOT) || material.equals(Material.GOLD_ORE) ||
                                material.equals(Material.DIAMOND) || material.equals(Material.DIAMOND_ORE) ||
                                material.equals(Material.EMERALD) || material.equals(Material.EMERALD_ORE)
                ) {
                    NBTItem nbti = new NBTItem(player.getItemInHand());
                    nbti.setInteger("bagxp", xp);
                    nbti.setInteger("bagneedxp", needXp);
                    ItemStack finalItem = nbti.getItem();
                    int percent = (int) Math.round((double) xp / needXp * 100);
                    int blocks = percent / 2;
                    String block = "";

                    for (int i = 0; i < blocks; i++) {
                        block = block.concat("&b┃");
                        if (i >= 49) {
                            break;
                        }
                    }
                    for (int i = 0; i < 50 - blocks; i++) {
                        block = block.concat("&c┃");
                    }
                    ItemMeta itemMeta = finalItem.getItemMeta();
                    List<String> lore = itemMeta.getLore();
                    int infoLine = 0;
                    for (String str : lore){
                        if (str.contains("| 宇宙能量")){
                            break;
                        }infoLine++;
                    }
                    lore.set(infoLine+1, ChatColorCast.format(block + " &f&l" + percent + "%"));
                    lore.set(infoLine+2, ChatColorCast.format("&7(&f" + numberFormat.getIntFormat(xp) + " &7/ " + numberFormat.getIntFormat(needXp) + ")"));
                    itemMeta.setLore(lore);
                    finalItem.setItemMeta(itemMeta);
                    player.getInventory().setItemInMainHand(finalItem);
                }


            }
        }.runTask(PWItemManager.getPlugin());
    }

}
