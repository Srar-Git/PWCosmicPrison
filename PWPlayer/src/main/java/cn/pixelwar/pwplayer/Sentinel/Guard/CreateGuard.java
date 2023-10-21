package cn.pixelwar.pwplayer.Sentinel.Guard;

import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.mcmonkey.sentinel.SentinelTrait;

public class CreateGuard {

    public void createWarden1(Location spawnLoc) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§b§l银河守卫");
        npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "An_Lan");
        npc.data().setPersistent(NPC.ALWAYS_USE_NAME_HOLOGRAM_METADATA, false);

        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta1 = chestplate.getItemMeta();
        itemMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        itemMeta1.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta1.setDisplayName(ChatColorCast.format("&b&l银河护甲"));
        chestplate.setItemMeta(itemMeta1);

        ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta itemMeta2 = leg.getItemMeta();
        itemMeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        itemMeta2.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta2.setDisplayName(ChatColorCast.format("&b&l银河护腿"));
        leg.setItemMeta(itemMeta2);

        ItemStack boot = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta itemMeta3 = boot.getItemMeta();
        itemMeta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10, true);
        itemMeta3.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta3.setDisplayName(ChatColorCast.format("&b&l银河靴子"));
        boot.setItemMeta(itemMeta3);

        ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta itemMeta4 = axe.getItemMeta();
        itemMeta4.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
        itemMeta4.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta4.setDisplayName(ChatColorCast.format("&b&l银河重斧"));
        axe.setItemMeta(itemMeta4);

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leg);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boot);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, axe);
        npc.getOrAddTrait(HologramTrait.class).addLine(ChatColorCast.format("&f1000&c&l❤"));
        spawnLoc.setPitch(0);
        npc.getOrAddTrait(GuardTrait.class).setFixedLoc(spawnLoc);
        npc.spawn(spawnLoc);
    }

    public void createWarden2(Location spawnLoc) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§6§l裁决者");
        npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "RyanTheScot");
        npc.data().setPersistent(NPC.ALWAYS_USE_NAME_HOLOGRAM_METADATA, false);

        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta1 = chestplate.getItemMeta();
        itemMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        itemMeta1.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta1.setDisplayName(ChatColorCast.format("&6&l裁决者护甲"));
        chestplate.setItemMeta(itemMeta1);

        ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta itemMeta2 = leg.getItemMeta();
        itemMeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        itemMeta2.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta2.setDisplayName(ChatColorCast.format("&6&l裁决者护腿"));
        leg.setItemMeta(itemMeta2);

        ItemStack boot = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta3 = boot.getItemMeta();
        itemMeta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 6, true);
        itemMeta3.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta3.setDisplayName(ChatColorCast.format("&6&l裁决者靴子"));
        boot.setItemMeta(itemMeta3);

        ItemStack axe = new ItemStack(Material.IRON_SWORD);
        ItemMeta itemMeta4 = axe.getItemMeta();
        itemMeta4.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
        itemMeta4.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta4.setDisplayName(ChatColorCast.format("&6&l裁决者重剑"));
        axe.setItemMeta(itemMeta4);

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leg);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boot);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, axe);
        npc.getOrAddTrait(HologramTrait.class).addLine(ChatColorCast.format("&f500&c&l❤"));
        spawnLoc.setPitch(0);
        npc.getOrAddTrait(GuardTrait.class).setFixedLoc(spawnLoc);
        npc.spawn(spawnLoc);
    }

    public void createWarden3(Location spawnLoc) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§e§l执法者");
        npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "PuNkD");
        npc.data().setPersistent(NPC.ALWAYS_USE_NAME_HOLOGRAM_METADATA, false);

        ItemStack chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta itemMeta1 = chestplate.getItemMeta();
        itemMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        itemMeta1.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta1.setDisplayName(ChatColorCast.format("&E&l执法者护甲"));
        chestplate.setItemMeta(itemMeta1);

        ItemStack leg = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta itemMeta2 = leg.getItemMeta();
        itemMeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        itemMeta2.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta2.setDisplayName(ChatColorCast.format("&E&l执法者护腿"));
        leg.setItemMeta(itemMeta2);

        ItemStack boot = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta itemMeta3 = boot.getItemMeta();
        itemMeta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        itemMeta3.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta3.setDisplayName(ChatColorCast.format("&E&l执法者靴子"));
        boot.setItemMeta(itemMeta3);

        ItemStack axe = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta itemMeta4 = axe.getItemMeta();
        itemMeta4.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
        itemMeta4.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta4.setDisplayName(ChatColorCast.format("&E&l执法者重剑"));
        axe.setItemMeta(itemMeta4);

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leg);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boot);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, axe);
        npc.getOrAddTrait(HologramTrait.class).addLine(ChatColorCast.format("&f200&c&l❤"));
        spawnLoc.setPitch(0);
        npc.getOrAddTrait(GuardTrait.class).setFixedLoc(spawnLoc);
        npc.spawn(spawnLoc);
    }

    public void createWarden4(Location spawnLoc) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§a§l守卫");
        npc.data().set(NPC.PLAYER_SKIN_UUID_METADATA, "ImW10t");
        npc.data().setPersistent(NPC.ALWAYS_USE_NAME_HOLOGRAM_METADATA, false);

        ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta itemMeta1 = chestplate.getItemMeta();
        itemMeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        itemMeta1.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta1.setDisplayName(ChatColorCast.format("&a&l守卫护甲"));
        chestplate.setItemMeta(itemMeta1);

        ItemStack leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta itemMeta2 = leg.getItemMeta();
        itemMeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        itemMeta2.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta2.setDisplayName(ChatColorCast.format("&a&l守卫护腿"));
        leg.setItemMeta(itemMeta2);

        ItemStack boot = new ItemStack(Material.CHAINMAIL_BOOTS);
        ItemMeta itemMeta3 = boot.getItemMeta();
        itemMeta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
        itemMeta3.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta3.setDisplayName(ChatColorCast.format("&a&l守卫靴子"));
        boot.setItemMeta(itemMeta3);

        ItemStack axe = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta4 = axe.getItemMeta();
        itemMeta4.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        itemMeta4.addEnchant(Enchantment.DURABILITY, 100, true);
        itemMeta4.setDisplayName(ChatColorCast.format("&a&l守卫重剑"));
        axe.setItemMeta(itemMeta4);

        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, chestplate);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, leg);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, boot);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, axe);
        npc.getOrAddTrait(HologramTrait.class).addLine(ChatColorCast.format("&f100&c&l❤"));
        spawnLoc.setPitch(0);
        npc.getOrAddTrait(GuardTrait.class).setFixedLoc(spawnLoc);
        npc.spawn(spawnLoc);
    }


}
