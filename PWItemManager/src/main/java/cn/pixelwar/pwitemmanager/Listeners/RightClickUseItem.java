package cn.pixelwar.pwitemmanager.Listeners;

import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.NBT.Item.GetItem;
import cn.pixelwar.pwitemmanager.NBT.Item.GiveAndDropItem;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RightClickUseItem  implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR))){
            return;
        }
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        //随机附魔书
        if (item.getItemMeta().getDisplayName().contains("随机附魔书")){
            GetItem getItem = new GetItem();
            NBTItem nbtItem = new NBTItem(item);
            Random random = new Random();
            int tier = nbtItem.getInteger("randombooktier");
            if (tier<1 || tier>6){
                return;
            }
            player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);

            Firework fw = (Firework)event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), Firework.class);
            FireworkMeta fwm = fw.getFireworkMeta();
            fwm.addEffect(FireworkEffect.builder().withColor(Color.BLUE).trail(true).flicker(true).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.LIME).build());
            fwm.setPower(1);
            fw.setFireworkMeta(fwm);
            PWEnchant enchant = getRandomEnchant(tier);
            ItemStack book = getItem.getEnchantBook(enchant.toString(), 1, NumberFormat.getRandomInt(1,50), NumberFormat.getRandomInt(1,100));
            player.sendMessage(ChatColorCast.format("&b▸ &f你获得了附魔书: "+book.getItemMeta().getDisplayName()));
            player.getItemInHand().setAmount(item.getAmount()-1);
            GiveAndDropItem.giveItem(player, book);

        }



    }

    public PWEnchant getRandomEnchant(int tier){
        Random random = new Random();
        PWEnchant[] list = PWEnchant.values();
        List<PWEnchant> tierList = new ArrayList<>();
        for (PWEnchant enchant : list){
            if (enchant.getRank()==tier){
                tierList.add(enchant);
            }
        }
        int n = random.nextInt(tierList.size());
        return tierList.get(n);
    }









}
