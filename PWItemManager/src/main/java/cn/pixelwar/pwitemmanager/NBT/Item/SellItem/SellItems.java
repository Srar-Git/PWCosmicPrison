package cn.pixelwar.pwitemmanager.NBT.Item.SellItem;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.NBT.UpdateSlotLore;
import cn.pixelwar.pwitemmanager.PWItemManager;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SellItems {
    Economy economy = PWItemManager.getEconomy();
    NumberFormat numberFormat = new NumberFormat();
    public void sellHand(Player player){

        ItemStack item = player.getItemInHand();

        if (item==null){
            player.sendMessage(ChatColorCast.format("&c▸ 你手中的物品不能出售"));
            player.playSound(player.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f,1f);
            return;
        }
        int amount = item.getAmount();
        boolean couldSell = false;
        List<String> priceList = new ArrayList<>();
        for (OrePrice orePrice : OrePrice.values()){
            priceList.add(orePrice.toString());
        }
        if (priceList.contains(item.getType().toString())){
            couldSell=true;
        }
        if (item.hasItemMeta() || !couldSell){
            player.sendMessage(ChatColorCast.format("&c▸ 你手中的物品不能出售"));
            player.playSound(player.getEyeLocation(), Sound.ENTITY_VILLAGER_NO, 1f,1f);
            return;
        }
        double price = OrePrice.valueOf(item.getType().toString()).getPrice() * amount;
        player.setItemInHand(new ItemStack(Material.AIR));
        economy.depositPlayer(player, price);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f,2f);
        player.sendMessage(ChatColorCast.format("&d▸ &f你出售了手中的物品获得了 &e&l"+numberFormat.getDoubleFormat(price)+"&2&l$"));
    }

    public double getAllOreMoney(Player player, String materialstr){
        Material material = Material.valueOf(materialstr);
        double sellMoney = 0.0;
        for (int i = 0;i<=36;i++){
            ItemStack item = player.getInventory().getItem(i);
            if (item==null || (!(item.getType().equals(material)))  ){
                continue;
            }
            if (item.hasItemMeta()){
                NBTItem nbtItem = new NBTItem(item);
                if (nbtItem.hasKey("size")){
                    int size = nbtItem.getInteger("size");
                    if (size>0){
                        double price = OrePrice.valueOf(materialstr).getPrice() * size;
                        sellMoney = price + sellMoney;
                        //取出size内的矿物
                        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
                        updateSlotLore.setBagSize(player, 0, i);
                    }
                }
                continue;
            }


            int amount = item.getAmount();
            double price = OrePrice.valueOf(materialstr).getPrice() * amount;
            sellMoney = price + sellMoney;
            player.getInventory().setItem(i,new ItemStack(Material.AIR));
        }
        return sellMoney;
    }

    public void sellOne(Player player, String materialstr){
        double money = getAllOreMoney(player, materialstr);
        economy.depositPlayer(player, money);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f,2f);
        player.sendMessage(ChatColorCast.format("&d▸ &f你出售了一种矿物获得了 &e&l"+numberFormat.getDoubleFormat(money)+"&2&l$"));

    }

    public void sellAll(Player player){
        double money = 0.0;
        for (OrePrice orePrice : OrePrice.values()){
            String materialstr = orePrice.toString();
            double OnceMoney = getAllOreMoney(player, materialstr);
            money = money + OnceMoney;
        }
        economy.depositPlayer(player, money);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f,2f);
        player.sendMessage(ChatColorCast.format("&d▸ &f你出售了背包中的物品获得了 &e&l"+numberFormat.getDoubleFormat(money)+"&2&l$"));
    }

}
