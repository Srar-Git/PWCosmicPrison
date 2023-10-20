package cn.pixelwar.pwitemmanager.NBT;

import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GetDigSpeed {

    public static double getDigSpeed(Player player, Block digBlock) {
        GetItemNBT getItemNBT = new GetItemNBT();
        ItemStack item = player.getItemInHand();
        int efficiency = getItemNBT.getItemIntNBT(item, "ae_enchantment;efficiency");
        int nefficiency = getItemNBT.getItemIntNBT(item, "ae_enchantment;nefficiency");
        String engine = getItemNBT.getItemStringNBT(item, "engine");
        double speed = 0;
        double eff = 0.5;
        double neff = 1.5;
        if (digBlock.getType().equals(Material.COAL_ORE) || digBlock.getType().equals(Material.COAL_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_COAL_ORE)) {
            if (item.getType().equals(Material.LEGACY_AIR)) {
                speed = 0.5;
            }
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = 1.5+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 3+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = 4+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 5+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (digBlock.getType().equals(Material.IRON_ORE) || digBlock.getType().equals(Material.IRON_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_IRON_ORE)) {
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 0.9+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = 1.3+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 1.8+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = 3+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 4+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (digBlock.getType().equals(Material.LAPIS_ORE) || digBlock.getType().equals(Material.LAPIS_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_LAPIS_ORE)) {
            if(PlayerExpDataManager.getPlayerExpDataMap().get(player.getName()).getLevel()<30){
                speed = 0.01;
            }
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 0.5+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = 1.0+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 1.5+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = 2+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 3+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (digBlock.getType().equals(Material.REDSTONE_ORE) || digBlock.getType().equals(Material.REDSTONE_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_REDSTONE_ORE)) {
            if(PlayerExpDataManager.getPlayerExpDataMap().get(player.getName()).getLevel()<50){
                speed = 0.01;
            }
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 0.1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = eff+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 1.0+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = 1.7+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 2.5+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (digBlock.getType().equals(Material.GOLD_ORE) || digBlock.getType().equals(Material.GOLD_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_GOLD_ORE)) {
            if(PlayerExpDataManager.getPlayerExpDataMap().get(player.getName()).getLevel()<70){
                speed = 0.01;
            }
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 0.1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = 0.1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 0.8+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = 1.0+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 2+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (digBlock.getType().equals(Material.DIAMOND_ORE) || digBlock.getType().equals(Material.DIAMOND_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) {
            if(PlayerExpDataManager.getPlayerExpDataMap().get(player.getName()).getLevel()<90){
                speed = 0.01;
            }
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 0.1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = 0.1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 0.6+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = 1.0+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 1.5+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (digBlock.getType().equals(Material.EMERALD_ORE) || digBlock.getType().equals(Material.EMERALD_BLOCK) || digBlock.getType().equals(Material.DEEPSLATE_EMERALD_ORE)) {
            if(PlayerExpDataManager.getPlayerExpDataMap().get(player.getName()).getLevel()<100){
                speed = 0.01;
            }
            if (item.getType().equals(Material.WOODEN_PICKAXE)) {
                speed = 0.01+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.STONE_PICKAXE)) {
                speed = 0.01+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.GOLDEN_PICKAXE)) {
                speed = 0.01+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.IRON_PICKAXE)) {
                speed = eff+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
            if (item.getType().equals(Material.DIAMOND_PICKAXE)) {
                speed = 1+(double)(eff*efficiency)+(double)(nefficiency*neff);
            }
        }
        if (engine.contains("speed")){
            speed = speed*2;
        }
        return speed;

    }


}
