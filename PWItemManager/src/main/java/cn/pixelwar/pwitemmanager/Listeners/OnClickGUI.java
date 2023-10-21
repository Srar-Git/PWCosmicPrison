package cn.pixelwar.pwitemmanager.Listeners;

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
import org.bukkit.event.inventory.ClickType;
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
        if (event.getView().getTitle().contains("镐荣誉/引擎装配")) {
            Inventory viewInventory = event.getView().getTopInventory();
            Player player = (Player) event.getWhoClicked();
            player.playSound(player.getEyeLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 2.0f);
            //点的是背包里
            if (event.getClickedInventory().equals(player.getInventory())) {
                event.setCancelled(true);
                ItemStack itemStack = event.getCurrentItem();
                if (itemStack.getType().toString().contains("PICKAXE")) {

                    ItemStack back = getButton(
                            Material.BLACK_STAINED_GLASS_PANE,
                            ChatColorCast.format(" "),
                            asList(
                            ),
                            false
                    );
                    for (int i = 0; i < 54; i++) {
                        if (i == 4) {
                            continue;
                        }
                        viewInventory.setItem(i, back);
                    }
                    Requirement req = getRequirement(itemStack, event.getSlot());
                    ItemStack reqItem = req.getRequireItem();
                    NBTItem nbtItem = new NBTItem(reqItem);
                    if (req.isReachRequirement()) {
                        int[] engineSlots = {29, 30, 31, 32, 33, 38, 39, 40, 41, 42};
                        int i = 0;
                        for (Engine engine : Engine.values()) {
                            if (nbtItem.hasKey("engine" + engine.toString())) {
                                ItemStack engineButton = getButton(
                                        Material.BARRIER,
                                        ChatColorCast.format(engine.getName()),
                                        asList(
                                                " ",
                                                ChatColorCast.format(engine.getDescription()),
                                                " ",
                                                ChatColorCast.format("&c你的物品已经装配了此引擎!")
                                        ),
                                        false
                                );
                                viewInventory.setItem(engineSlots[i], engineButton);
                            } else {
                                ItemStack engineButton = getButton(
                                        Material.STRUCTURE_VOID,
                                        ChatColorCast.format(engine.getName()),
                                        asList(
                                                " ",
                                                ChatColorCast.format(engine.getDescription()),
                                                " ",
                                                ChatColorCast.format("&c&l注意!&7升级荣誉会导致你的镐失去所有附魔，等级"),
                                                ChatColorCast.format("&7重置为0级，挖掘数量清空!但是最高等级+15级!"),
                                                ChatColorCast.format(" "),
                                                ChatColorCast.format("&c&l注意!&7若你的镐上拥有&d&l核能荣誉宝石&7则不会"),
                                                ChatColorCast.format("&7清空镐上的任何数据!"),
                                                ChatColorCast.format("&7"),
                                                ChatColorCast.format("&a&l点击&7装配此引擎并升级荣誉等级!")
                                        ),
                                        false,
                                        "engineType",
                                        engine.toString(),
                                        "slot",
                                        event.getSlot()
                                );
                                viewInventory.setItem(engineSlots[i], engineButton);
                            }

                            i++;
                        }
                    }
                    viewInventory.setItem(22, reqItem);
                    player.updateInventory();
                }
            }

            //点的是菜单里
            if (event.getClickedInventory().equals(viewInventory)) {
                event.setCancelled(true);
                ItemStack click = event.getCurrentItem();
                if (click.getType().equals(Material.STRUCTURE_VOID)) {
                    NBTItem nbtItem = new NBTItem(click);
                    int slot = nbtItem.getInteger("slot");
                    String engineType = nbtItem.getString("engineType");
                    ItemStack pickaxe = player.getInventory().getItem(slot);
                    NBTItem nbtItem1 = new NBTItem(pickaxe);
                    if (engineType.equals("CUBE")) {
                        if (nbtItem1.getInteger("toolprestige") != 9) {
                            player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 0.1f);
                            player.sendMessage(ChatColorCast.format("&c▸ 你的镐需要达到荣誉等级&d&l<&b&lIX&d&l>&c才能选择此引擎!"));
                            return;
                        }
                    }
                    LevelUp levelUp = new LevelUp();
                    if (nbtItem1.hasKey("nuclearfreeprestige")) {
                        player.getInventory().setItem(slot, levelUp.getLeveledItemWithoutClear(pickaxe, Engine.valueOf(engineType)));
                        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
                        updateSlotLore.removeSlotPickaxeGem(player, "gemprestige1", slot);
                    } else {
                        player.getInventory().setItem(slot, levelUp.getLeveledItem(pickaxe, Engine.valueOf(engineType)));
                        if (nbtItem1.hasKey("freeprestige")) {
                            UpdateSlotLore updateSlotLore = new UpdateSlotLore();
                            updateSlotLore.removeSlotPickaxeGem(player, "gemprestige0", slot);
                        }
                    }
                    PacketPrestigeEffect packetPrestigeEffect = new PacketPrestigeEffect();
                    ItemStack pickaxeDone = player.getInventory().getItem(slot);
                    packetPrestigeEffect.playEffect(player, pickaxeDone);
                    player.closeInventory();


                }
            }

        }
    }

    public Requirement getRequirement(ItemStack input, int slot) {
        NumberFormat numberFormat = new NumberFormat();
        NBTItem nbtItem = new NBTItem(input);


        int prestige = nbtItem.getInteger("toolprestige");
        int dig = nbtItem.getInteger("tooldig");
        int level = nbtItem.getInteger("toollevel");
        nbtItem.setInteger("clickedSlot", slot);
        input = nbtItem.getItem();
        int requireDig = 9999999;
        int requireLevel = 99999;
        if (input.getType().equals(Material.WOODEN_PICKAXE)) {
            switch (prestige) {
                case 0:
                    requireDig = 20000;
                    requireLevel = 20;
                    break;
                case 1:
                    requireDig = 35000;
                    requireLevel = 25;
                    break;
                case 2:
                    requireDig = 48000;
                    requireLevel = 30;
                    break;
                case 3:
                    requireDig = 62500;
                    requireLevel = 35;
                    break;
                case 4:
                    requireDig = 78000;
                    requireLevel = 40;
                    break;
                case 5:
                    requireDig = 95000;
                    requireLevel = 50;
                    break;
                case 6:
                    requireDig = 112000;
                    requireLevel = 60;
                    break;
                case 7:
                    requireDig = 130000;
                    requireLevel = 70;
                    break;
                case 8:
                    requireDig = 150000;
                    requireLevel = 80;
                    break;
                case 9:
                    requireDig = 175000;
                    requireLevel = 90;
                    break;
                default:
                    break;
            }
        }
        if (input.getType().equals(Material.STONE_PICKAXE)) {
            switch (prestige) {
                case 0:
                    requireDig = 31000;
                    requireLevel = 25;
                    break;
                case 1:
                    requireDig = 48000;
                    requireLevel = 30;
                    break;
                case 2:
                    requireDig = 66000;
                    requireLevel = 35;
                    break;
                case 3:
                    requireDig = 85500;
                    requireLevel = 40;
                    break;
                case 4:
                    requireDig = 105000;
                    requireLevel = 45;
                    break;
                case 5:
                    requireDig = 126000;
                    requireLevel = 55;
                    break;
                case 6:
                    requireDig = 148000;
                    requireLevel = 65;
                    break;
                case 7:
                    requireDig = 171000;
                    requireLevel = 75;
                    break;
                case 8:
                    requireDig = 190000;
                    requireLevel = 85;
                    break;
                case 9:
                    requireDig = 225000;
                    requireLevel = 95;
                    break;
                default:
                    break;
            }
        }
        if (input.getType().equals(Material.GOLDEN_PICKAXE)) {
            switch (prestige) {
                case 0:
                    requireDig = 45500;
                    requireLevel = 30;
                    break;
                case 1:
                    requireDig = 70500;
                    requireLevel = 35;
                    break;
                case 2:
                    requireDig = 97000;
                    requireLevel = 40;
                    break;
                case 3:
                    requireDig = 125000;
                    requireLevel = 45;
                    break;
                case 4:
                    requireDig = 154000;
                    requireLevel = 50;
                    break;
                case 5:
                    requireDig = 185000;
                    requireLevel = 60;
                    break;
                case 6:
                    requireDig = 218000;
                    requireLevel = 70;
                    break;
                case 7:
                    requireDig = 252000;
                    requireLevel = 80;
                    break;
                case 8:
                    requireDig = 287000;
                    requireLevel = 90;
                    break;
                case 9:
                    requireDig = 324000;
                    requireLevel = 100;
                    break;
                default:
                    break;
            }
        }
        if (input.getType().equals(Material.IRON_PICKAXE)) {
            switch (prestige) {
                case 0:
                    requireDig = 65500;
                    requireLevel = 35;
                    break;
                case 1:
                    requireDig = 100500;
                    requireLevel = 40;
                    break;
                case 2:
                    requireDig = 138000;
                    requireLevel = 45;
                    break;
                case 3:
                    requireDig = 177000;
                    requireLevel = 50;
                    break;
                case 4:
                    requireDig = 219000;
                    requireLevel = 55;
                    break;
                case 5:
                    requireDig = 262000;
                    requireLevel = 65;
                    break;
                case 6:
                    requireDig = 308000;
                    requireLevel = 75;
                    break;
                case 7:
                    requireDig = 355000;
                    requireLevel = 85;
                    break;
                case 8:
                    requireDig = 405000;
                    requireLevel = 95;
                    break;
                case 9:
                    requireDig = 456000;
                    requireLevel = 105;
                    break;
                default:
                    break;
            }
        }
        if (input.getType().equals(Material.DIAMOND_PICKAXE)) {
            switch (prestige) {
                case 0:
                    requireDig = 92500;
                    requireLevel = 40;
                    break;
                case 1:
                    requireDig = 142500;
                    requireLevel = 45;
                    break;
                case 2:
                    requireDig = 195000;
                    requireLevel = 50;
                    break;
                case 3:
                    requireDig = 257000;
                    requireLevel = 55;
                    break;
                case 4:
                    requireDig = 309000;
                    requireLevel = 60;
                    break;
                case 5:
                    requireDig = 367000;
                    requireLevel = 70;
                    break;
                case 6:
                    requireDig = 430000;
                    requireLevel = 80;
                    break;
                case 7:
                    requireDig = 495000;
                    requireLevel = 90;
                    break;
                case 8:
                    requireDig = 565000;
                    requireLevel = 100;
                    break;
                case 9:
                    requireDig = 646000;
                    requireLevel = 110;
                    break;
                default:
                    break;
            }
        }

        boolean reachRequirement = false;
        if (dig >= requireDig && level >= requireLevel) {
            reachRequirement = true;
        }

        ItemMeta itemMeta = input.getItemMeta();
        List<String> lore = itemMeta.getLore();
        if (nbtItem.hasKey("freeprestige") || nbtItem.hasKey("nuclearfreeprestige")) {
            reachRequirement = true;
            lore.add(ChatColorCast.format("&a&l✔ &f你的工具已经满足需求!请点击"));
            lore.add(ChatColorCast.format("&f下方引擎来选择你的升级引擎!"));
            itemMeta.setLore(lore);
            input.setItemMeta(itemMeta);
            Requirement requirement = new Requirement();
            requirement.setRequireItem(input);
            requirement.setReachRequirement(reachRequirement);
            return requirement;
        }
        if (prestige >= 10) {
            lore.add(ChatColorCast.format("&c&l✖ &f你的工具已经达到最高荣誉等级&d&l<&b&lX&d&l>"));
        } else {
            lore.add(ChatColorCast.format("&d&l| 升级荣誉需求"));
            lore.add(ChatColorCast.format("&b▸ &f下一荣誉等级: " + "&d&l<&b&l" + getLevel(prestige + 1) + "&d&l>"));
            lore.add(ChatColorCast.format("&b▸ &f需求镐等级: &e&lLv." + requireLevel));
            lore.add(ChatColorCast.format("&b▸ &f需求镐挖掘数量: &a&l" + numberFormat.getIntFormat(requireDig)));
            if (reachRequirement) {
                lore.add(ChatColorCast.format("&a&l✔ &f你的工具已经满足需求!请点击"));
                lore.add(ChatColorCast.format("&f下方引擎来选择你的升级引擎!"));
            } else {
                lore.add(ChatColorCast.format("&c&l✖ &f你的工具不满足需求!请满足要求"));
                lore.add(ChatColorCast.format("&f后再进行荣誉升级!"));
            }
        }
        itemMeta.setLore(lore);
        input.setItemMeta(itemMeta);
        Requirement requirement = new Requirement();
        requirement.setRequireItem(input);
        requirement.setReachRequirement(reachRequirement);
        return requirement;

    }

    public String getLevel(int level) {
        switch (level) {
            case 0:
                return "";
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            default:
                return null;
        }
    }

    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        if (glow) {
            itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);
        return item;
    }

    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow, String nbtPath, String nbt, String nbtPath2, int nbtint) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        if (glow) {
            itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString(nbtPath, nbt);
        nbtItem.setInteger(nbtPath2, nbtint);
        item = nbtItem.getItem();
        return item;
    }

}
