package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige.Engine;
import cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige.LevelUp;
import cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige.Requirement;
import cn.pixelwar.pwitemmanager.NBT.UpdateSlotLore;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketPrestigeEffect;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static java.util.Arrays.asList;

public class OnClickGUI implements Listener {

    @EventHandler
    public void OnClickVaultMenu(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            return;
        }
        if (event.getView().getTitle().contains("个人信息")) {
            event.setCancelled(true);
        }
    }

}
