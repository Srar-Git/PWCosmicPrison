package cn.pixelwar.pwitemmanager.Enchant.menus;

import cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant.ApplyEnchantToBag;
import cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant.ApplyEnchantToPickaxe;
import cn.pixelwar.pwitemmanager.Enchant.EnchantEum.PWEnchant;
import cn.pixelwar.pwitemmanager.NBT.Item.GetItem;
import cn.pixelwar.pwitemmanager.NBT.Item.GiveAndDropItem;
import cn.pixelwar.pwitemmanager.NBT.UpdateHandLore;
import cn.pixelwar.pwitemmanager.PWItemManager;
import cn.pixelwar.pwitemmanager.Utils.ChatColorCast;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketText;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static java.util.Arrays.asList;

public class EnchantMenuEvents implements Listener {
    EnchantMenu enchantMenu = new EnchantMenu();
    ApplyEnchantToPickaxe applyEnchantToPickaxe = new ApplyEnchantToPickaxe();
    ApplyEnchantToBag applyEnchantToBag = new ApplyEnchantToBag();
    Random random = new Random();
    GetItem getItem = new GetItem();
    UpdateHandLore updateHandLore = new UpdateHandLore();
    private static final HashMap<Player, Boolean> down = new HashMap<>();
    private static final HashMap<Player, Boolean> ifFirst = new HashMap<>();
    @EventHandler
    public void onClickEnchantMenu(InventoryClickEvent event) {

        if (event.getClickedInventory() == null){
            return;
        }
        if (event.getView().getTitle().contains("附魔池")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            Inventory viewInventory = event.getView().getTopInventory();
            if (event.getClickedInventory().equals(viewInventory)) {
                player.playSound(player.getEyeLocation(), Sound.UI_BUTTON_CLICK, SoundCategory.BLOCKS, 1.0f, 2.0f);
                ItemStack item = viewInventory.getItem(22);
                ItemStack click = event.getCurrentItem();
                if (click.getType().toString().contains("PANE") || click.getType().toString().contains("BARRIER")){
                    return;
                }
                int enchantSlot = IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTSLOTS);
                //是镐
                if (item.getType().toString().contains("PICKAXE")){
                    NBTItem nbtItem = new NBTItem(item);
                    int toollevel = nbtItem.getInteger("toollevel");
                    ItemMeta itemMeta = item.getItemMeta();
                    HashMap<PWEnchant, Integer> enchantHasMap = new HashMap<>();
                    for (String str : nbtItem.getKeys()) {
                        if (str.contains("ae_enchantment")) {
                            int nextLevel = nbtItem.getInteger(str) + 1;
                            str = str.replaceAll("ae_enchantment;", "");
                            enchantHasMap.put(PWEnchant.valueOf(str), nextLevel);
                        }
                    }
                    Collection<Integer> c = enchantHasMap.values();
                    Object[] obj = c.toArray();
                    Arrays.sort(obj);

                    int biggestEnchant;
                    if (obj.length==0){
                        biggestEnchant = 1;
                    }
                    else{
                        biggestEnchant = (int)obj[obj.length-1];
                    }
                    HashMap<PWEnchant, Integer> enchantChanceMap = new HashMap<>();
                    List<PWEnchant> tier1 = new ArrayList<>();
                    List<PWEnchant> tier2 = new ArrayList<>();
                    List<PWEnchant> tier3 = new ArrayList<>();
                    List<PWEnchant> tier4 = new ArrayList<>();
                    List<PWEnchant> tier5 = new ArrayList<>();

                    for (PWEnchant PWEnchant : PWEnchant.values()) {
                        if (!(PWEnchant.getType().equals("镐"))){
                            continue;
                        }
                        if (PWEnchant.getRank()>=6){
                            continue;
                        }
                        if (!(enchantHasMap.containsKey(PWEnchant))) {
                            enchantHasMap.put(PWEnchant, 1);
                            if (PWEnchant.getRank() == 1) {
                                tier1.add(PWEnchant);
                            }
                            if (PWEnchant.getRank() == 2) {
                                tier2.add(PWEnchant);
                            }
                            if (PWEnchant.getRank() == 3) {
                                tier3.add(PWEnchant);
                            }
                            if (PWEnchant.getRank() == 4) {
                                tier4.add(PWEnchant);
                            }
                            if (PWEnchant.getRank() == 5) {
                                tier5.add(PWEnchant);
                            }
                        }

                        if (enchantHasMap.containsKey(PWEnchant)){
                            if (enchantHasMap.get(PWEnchant)< PWEnchant.getMaxLevel()+1){
                                int chance = getChance(enchantHasMap.get(PWEnchant),biggestEnchant, toollevel)-(PWEnchant.getRank()*2);
                                if (chance<=0){
                                    chance = getRandomInt(2,8);
                                }
                                enchantChanceMap.put(PWEnchant, chance);
                                if (PWEnchant.getRank() == 1) {
                                    tier1.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 2) {
                                    tier2.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 3) {
                                    tier3.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 4) {
                                    tier4.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 5) {
                                    tier5.add(PWEnchant);
                                }
                            }
                        }

                    }
                    if (click.getType().equals(Material.TURTLE_EGG) && event.getSlot()==49) {

                        if (ifFirst.containsKey(player)){
                            IntDataManager.IntDataMap.get(player.getName()).singleMap.put(
                                    IntDataType.ENCHANTREROLL,
                                    IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL)-1
                                    );
                        }
                        down.put(player,false);
                        int[] enslots = {3,4,5,11,15,19,25,29,33,39,41,40};
                        List<Integer> greenSlots = new ArrayList<>();
                        for (int i = 0; i < enchantSlot; i++){
                            greenSlots.add(enslots[i]);
                        }
                        ItemStack green = getButton(
                                Material.LIME_STAINED_GLASS_PANE,
                                ChatColorCast.format("&a✔ &f已解锁的附魔格"),
                                asList(" ",
                                        ChatColorCast.format("&7点击下方的&6&l按钮&7来抽取多个随机"),
                                        ChatColorCast.format("&7附魔，然后你可以选择其中&n一个&7!")
                                ),
                                false
                        );
                        for (int i = 0; i < 54; i++){
                            if (greenSlots.contains(i))
                                viewInventory.setItem(i, green);
                        }

                        if (enchantChanceMap.size()<1){
                            ItemStack barrier = enchantMenu.getButton(
                                    Material.BARRIER,
                                    ChatColorCast.format("&c&l你已经拥有所有附魔"),
                                    asList(
                                            " ",
                                            ChatColorCast.format("&7你的物品已经包含了&c所有&7能够使用虫洞"),
                                            ChatColorCast.format("&7附魔池获取的附魔 "),
                                            ChatColorCast.format(""),
                                            ChatColorCast.format("&c&l提示!&7更高级的附魔请通过其他方式获取")
                                    ),
                                    false
                            );
                            viewInventory.setItem(49, barrier);
                            return;
                        }
                        ItemStack barrier = enchantMenu.getButton(
                                Material.BARRIER,
                                ChatColorCast.format("&c&l附魔选择/抽取中"),
                                asList(
                                        " ",
                                        ChatColorCast.format("&7附魔抽取完毕后，请选择你想要的附魔"),
                                        ChatColorCast.format(" "),
                                        ChatColorCast.format("&c&l提示!&7抽取完毕后关闭页面将会&d自动选择&7附魔"),
                                        ChatColorCast.format("&c&l提示!&7你也可以点击该按钮&b&l重新抽取&7进行重抽")
                                ),
                                false
                        );
                        viewInventory.setItem(49, barrier);

                        //                    int enchantSlot = 0;
                        //                    int[] slots = {3,4,5,11,15,19,25,29,33,39,41,40};
                        List<Integer> slots = new ArrayList<>();
                        for (int i = 0; i < 54; i++) {
                            if (viewInventory.getItem(i).getType().equals(Material.LIME_STAINED_GLASS_PANE) &&
                                    viewInventory.getItem(i).getAmount() == 1
                            ) {
                                slots.add(i);
                            }
                        }

                        new BukkitRunnable() {
                            int i = 0;

                            @Override
                            public void run() {
                                if (!(player.getOpenInventory().getTitle().contains("附魔池"))){
                                    cancel();
                                    return;
                                }
                                player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1.0f, 1.4f);

                                if (i >= 20) {
                                    cancel();
                                }
                                i++;
                            }
                        }.runTaskTimerAsynchronously(PWItemManager.getPlugin(), 0l, 3l);

                        NBTItem nbtDust = new NBTItem(viewInventory.getItem(8));
                        int rank1Num = nbtDust.getInteger("rank1");
                        int rank2Num = nbtDust.getInteger("rank2");
                        int rank3Num = nbtDust.getInteger("rank3");
                        int rank4Num = nbtDust.getInteger("rank4");
                        int rank5Num = nbtDust.getInteger("rank5");

                        for (int s : slots) {
                            new BukkitRunnable() {
                                int i = 0;
                                @Override
                                public void run() {
                                    if (!(player.getOpenInventory().getTitle().contains("附魔池"))){
                                        cancel();
                                        return;
                                    }
                                    PWEnchant enchant = getRandomEnchant(tier1, tier2, tier3, tier4, tier5, toollevel);

                                    int rank = enchant.getRank();
                                    int addChance = 0;
                                    switch (rank){
                                        case 1:
                                            addChance = rank1Num;
                                            break;
                                        case 2:
                                            addChance = rank2Num;
                                            break;
                                        case 3:
                                            addChance = rank3Num;
                                            break;
                                        case 4:
                                            addChance = rank4Num;
                                            break;
                                        case 5:
                                            addChance = rank5Num;
                                            break;
                                        default:
                                            break;
                                    }
                                    List <String> buttonLore = new ArrayList<>();
                                    buttonLore.add(" ");
                                    for (String s : enchant.getDescription()){
                                        buttonLore.add(s);
                                    }
                                    buttonLore.add(" ");
                                    if (addChance>0){
                                        buttonLore.add(ChatColorCast.format("&b▪ &f成功率: &a&l" + enchantChanceMap.get(enchant) + "% &7(&e+"+addChance+"%&7)"));
                                        buttonLore.add(ChatColorCast.format("&b▪ &f失败率: &c&l" + (100 - enchantChanceMap.get(enchant)) + "% &7(&e-"+addChance+"%&7)"));
                                    }else{
                                        buttonLore.add(ChatColorCast.format("&b▪ &f成功率: &a&l" + enchantChanceMap.get(enchant) + "%"));
                                        buttonLore.add(ChatColorCast.format("&b▪ &f失败率: &c&l" + (100 - enchantChanceMap.get(enchant)) + "%"));
                                    }

                                    buttonLore.add(" ");
                                    buttonLore.add(ChatColorCast.format("&a&l点击&7选择该附魔"));
                                    int totalChance = enchantChanceMap.get(enchant)+addChance;
                                    if (totalChance>100){
                                        totalChance=100;
                                    }
                                    ItemStack button = enchantMenu.getButton(
                                            enchant.getItem(),
                                            enchant.getRankColor() + enchant.getChineseName() + ChatColor.AQUA + " " + ChatColor.BOLD + getLevel(enchantHasMap.get(enchant)),
                                            buttonLore,
                                            true,
                                            enchant,
                                            enchantHasMap.get(enchant),
                                            totalChance
                                    );
                                    viewInventory.setItem(s, button);
                                    if (i >= 20) {
                                        cancel();
                                        down.put(player, true);
                                        ifFirst.put(player, true);
                                    }
                                    i++;
                                }
                            }.runTaskTimerAsynchronously(PWItemManager.getPlugin(), 0l, 3l);

                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL)>0){
                                    ItemStack roll_enchant = getButton(
                                            Material.TURTLE_EGG,
                                            ChatColorCast.format("&6&l开始抽取附魔"),
                                            asList(
                                                    " ",
                                                    ChatColorCast.format("&7抽取附魔后你的所有已解锁附魔格会变为"),
                                                    ChatColorCast.format("&7相应附魔，你可以选取其中&b&l1个&7进行附魔"),
                                                    ChatColorCast.format("&7抽取完后若没有满意的附魔，你可以重新抽取"),
                                                    ChatColorCast.format(" "),
                                                    ChatColorCast.format("&b▪ &f剩余重抽次数: &d&l"+IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL)),
                                                    ChatColorCast.format(" "),
                                                    ChatColorCast.format("&6&l点击&7开始抽取附魔")
                                            ),
                                            true
                                    );
                                    ItemStack dust = getButton(
                                            Material.SUGAR,
                                            ChatColorCast.format("&6&l附魔之尘几率加成"),
                                            asList(
                                                    " ",
                                                    ChatColorCast.format("&7你可以点击你背包中的附魔之尘"),
                                                    ChatColorCast.format("&7增加相应等级附魔的成功几率."),
                                                    ChatColorCast.format(" "),
                                                    ChatColorCast.format("&b▪ &f普通: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &a优秀: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &9稀有: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &e史诗: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &6传说: &b&l+0%")
                                            ),
                                            true
                                    );
                                    NBTItem nbtDust = new NBTItem(dust);
                                    nbtDust.setInteger("rank1",0);
                                    nbtDust.setInteger("rank2",0);
                                    nbtDust.setInteger("rank3",0);
                                    nbtDust.setInteger("rank4",0);
                                    nbtDust.setInteger("rank5",0);
                                    dust = nbtDust.getItem();
                                    viewInventory.setItem(8,dust);
                                    viewInventory.setItem(49, roll_enchant);
                                    down.remove(player);
                                }
                            }
                        }.runTaskLaterAsynchronously(PWItemManager.getPlugin(), 60l);
                        return;
                    }
                    else {
                        if (down.containsKey(player) && down.get(player)) {
                            NBTItem nbtItem2 = new NBTItem(click);
                            if(nbtItem2.hasKey("enchant")) {
                                PWEnchant enchant = PWEnchant.valueOf(nbtItem2.getString("enchant"));
                                int level = nbtItem2.getInteger("level");
                                int success = nbtItem2.getInteger("success");
                                applyEnchantToPickaxe.applyHandItemEnchantToPickaxe(player, enchant.toString(), level, success, true);
//                            Bukkit.broadcastMessage(event.getSlot() + "你选择了" + pickaxeEnchant + " 抚摸后等级为" + level + " 成功率为" + success);
                                down.remove(player);
                                player.closeInventory();
                                return;
                            }
                        }
                    }













                }
                //是附魔书
                if (item.getType().equals(Material.ENCHANTED_BOOK)){
                    if (click.getType().equals(Material.FIREWORK_ROCKET) && event.getSlot() == 49) {
                        NBTItem nbtItem = new NBTItem(item);
                        int fail = nbtItem.getInteger("levelfail");
                        PWEnchant pwEnchant = PWEnchant.valueOf(nbtItem.getString("enchant"));
                        int num = fail/7;
                        if (num<=0){
                            num=1;
                        }
                        int r  = random.nextInt(100);
                        if (r<=fail){ //失败
                            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                            ItemStack q = getItem.getEnchantPage(pwEnchant.getRank(), num);
                            GiveAndDropItem.giveItem(player,q);
                            PacketText packetText = new PacketText();
                            packetText.createEnchantText(player, player.getItemInHand(),ChatColorCast.format("&c&l升级失败!"));
                            player.sendMessage("");
                            player.sendMessage(ChatColorCast.format("&c▸ 你的附魔书升级失败，你获得了 "+q.getItemMeta().getDisplayName()));
                            player.sendMessage("");
                        }else { //成功

                            int f = getRandomInt(30,100);
                            ItemStack newbook = getItem.getEnchantBook(nbtItem.getString("enchant"),
                                    nbtItem.getInteger("booklevel") + 1,
                                    nbtItem.getInteger("enchantsuccess"),
                                    f);
                            player.getInventory().setItemInMainHand(newbook);
                            PacketText packetText = new PacketText();
                            packetText.createEnchantText(player, player.getItemInHand(), ChatColorCast.format("&d&l升级成功 &b▸ ")+ pwEnchant.getRankColor()+ pwEnchant.getChineseName()+ChatColorCast.format(" &b&l")+getLevel(nbtItem.getInteger("booklevel") + 1));

                        }
                    }
                    player.closeInventory();


                }
                //是存储箱
                if (
                        item.getType().equals(Material.COAL) || item.getType().equals(Material.COAL_ORE) ||
                                item.getType().equals(Material.IRON_INGOT) || item.getType().equals(Material.IRON_ORE) ||
                                item.getType().equals(Material.LAPIS_LAZULI) || item.getType().equals(Material.LAPIS_ORE) ||
                                item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.REDSTONE_ORE) ||
                                item.getType().equals(Material.GOLD_INGOT) || item.getType().equals(Material.GOLD_ORE) ||
                                item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.DIAMOND_ORE) ||
                                item.getType().equals(Material.EMERALD) || item.getType().equals(Material.EMERALD_ORE)
                ){
                    if (click.getType().equals(Material.FIREWORK_ROCKET) && event.getSlot() == 50) {
                        ApplyEnchantToBag applyEnchantToBag = new ApplyEnchantToBag();
                        applyEnchantToBag.levelUp(player);
                        player.closeInventory();
                        return;
                    }
                    if (click.getType().equals(Material.TURTLE_EGG) && event.getSlot() == 48) {
                        if (ifFirst.containsKey(player)){
                            IntDataManager.IntDataMap.get(player.getName()).singleMap.put(
                                    IntDataType.ENCHANTREROLL,
                                    IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL)-1
                            );
                        }
                        down.put(player,false);
                        int[] enslots = {3,4,5,11,15,19,25,29,33,39,41,40};
                        List<Integer> greenSlots = new ArrayList<>();
                        for (int i = 0; i < enchantSlot; i++){
                            greenSlots.add(enslots[i]);
                        }
                        ItemStack green = getButton(
                                Material.LIME_STAINED_GLASS_PANE,
                                ChatColorCast.format("&a✔ &f已解锁的附魔格"),
                                asList(" ",
                                        ChatColorCast.format("&7点击下方的&6&l按钮&7来抽取多个随机"),
                                        ChatColorCast.format("&7附魔，然后你可以选择其中&n一个&7!")
                                ),
                                false
                        );
                        for (int i = 0; i < 54; i++){
                            if (greenSlots.contains(i))
                                viewInventory.setItem(i, green);
                        }


                        NBTItem nbtItem = new NBTItem(item);
                        int baglevel = nbtItem.getInteger("baglevel");
                        HashMap<PWEnchant, Integer> enchantHasMap = new HashMap<>();
                        for (String str : nbtItem.getKeys()) {
                            if (str.contains("ae_enchantment")) {
                                int nextLevel = nbtItem.getInteger(str) + 1;
                                str = str.replaceAll("ae_enchantment;", "");
                                enchantHasMap.put(PWEnchant.valueOf(str), nextLevel);
                            }
                        }
                        Collection<Integer> c = enchantHasMap.values();
                        Object[] obj = c.toArray();
                        Arrays.sort(obj);

                        int biggestEnchant;
                        if (obj.length==0){
                            biggestEnchant = 1;
                        }
                        else{
                            biggestEnchant = (int)obj[obj.length-1];
                        }
                        HashMap<PWEnchant, Integer> enchantChanceMap = new HashMap<>();
                        List<PWEnchant> tier1 = new ArrayList<>();
                        List<PWEnchant> tier2 = new ArrayList<>();
                        List<PWEnchant> tier3 = new ArrayList<>();
                        List<PWEnchant> tier4 = new ArrayList<>();
                        List<PWEnchant> tier5 = new ArrayList<>();

                        for (PWEnchant PWEnchant : PWEnchant.values()) {
                            if (!(PWEnchant.getType().equals("矿物存储箱"))){
                                continue;
                            }
                            if (PWEnchant.getRank()>=6){
                                continue;
                            }
                            if (!(enchantHasMap.containsKey(PWEnchant))) {
                                enchantHasMap.put(PWEnchant, 1);
                                if (PWEnchant.getRank() == 1) {
                                    tier1.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 2) {
                                    tier2.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 3) {
                                    tier3.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 4) {
                                    tier4.add(PWEnchant);
                                }
                                if (PWEnchant.getRank() == 5) {
                                    tier5.add(PWEnchant);
                                }
                            }

                            if (enchantHasMap.containsKey(PWEnchant)){
                                if (enchantHasMap.get(PWEnchant)< PWEnchant.getMaxLevel()+1){
                                    int chance = getChance(enchantHasMap.get(PWEnchant),biggestEnchant, baglevel)-(PWEnchant.getRank()*2);
                                    if (chance<=0){
                                        chance = getRandomInt(2,8);
                                    }
                                    enchantChanceMap.put(PWEnchant, chance);
                                    if (PWEnchant.getRank() == 1) {
                                        tier1.add(PWEnchant);
                                    }
                                    if (PWEnchant.getRank() == 2) {
                                        tier2.add(PWEnchant);
                                    }
                                    if (PWEnchant.getRank() == 3) {
                                        tier3.add(PWEnchant);
                                    }
                                    if (PWEnchant.getRank() == 4) {
                                        tier4.add(PWEnchant);
                                    }
                                    if (PWEnchant.getRank() == 5) {
                                        tier5.add(PWEnchant);
                                    }
                                }
                            }

                        }
                        if (enchantChanceMap.size()<1){
                            ItemStack barrier = enchantMenu.getButton(
                                    Material.BARRIER,
                                    ChatColorCast.format("&c&l你已经拥有所有附魔"),
                                    asList(
                                            " ",
                                            ChatColorCast.format("&7你的物品已经包含了&c所有&7能够使用虫洞"),
                                            ChatColorCast.format("&7附魔池获取的附魔 "),
                                            ChatColorCast.format(""),
                                            ChatColorCast.format("&c&l提示!&7更高级的附魔请通过其他方式获取")
                                    ),
                                    false
                            );
                            viewInventory.setItem(48, barrier);
                            viewInventory.setItem(50, barrier);
                            return;
                        }
                        ItemStack barrier = enchantMenu.getButton(
                                Material.BARRIER,
                                ChatColorCast.format("&c&l附魔选择/抽取中"),
                                asList(
                                        " ",
                                        ChatColorCast.format("&7附魔抽取完毕后，请选择你想要的附魔"),
                                        ChatColorCast.format(" "),
                                        ChatColorCast.format("&c&l提示!&7抽取完毕后关闭页面将会&d自动选择&7附魔"),
                                        ChatColorCast.format("&c&l提示!&7你也可以点击右侧&b&l重新抽取&7进行重抽")
                                ),
                                false
                        );
                        viewInventory.setItem(48, barrier);
                        viewInventory.setItem(50, barrier);

                        //                    int enchantSlot = 0;
                        //                    int[] slots = {3,4,5,11,15,19,25,29,33,39,41,40};
                        List<Integer> slots = new ArrayList<>();
                        for (int i = 0; i < 54; i++) {
                            if (viewInventory.getItem(i).getType().equals(Material.LIME_STAINED_GLASS_PANE) &&
                                    viewInventory.getItem(i).getAmount() == 1
                            ) {
                                slots.add(i);
                            }
                        }

                        new BukkitRunnable() {
                            int i = 0;

                            @Override
                            public void run() {
                                if (!(player.getOpenInventory().getTitle().contains("附魔池"))){
                                    cancel();
                                    return;
                                }
                                player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1.0f, 1.4f);

                                if (i >= 20) {
                                    cancel();
                                }
                                i++;
                            }
                        }.runTaskTimerAsynchronously(PWItemManager.getPlugin(), 0l, 3l);

                        NBTItem nbtDust = new NBTItem(viewInventory.getItem(8));
                        int rank1Num = nbtDust.getInteger("rank1");
                        int rank2Num = nbtDust.getInteger("rank2");
                        int rank3Num = nbtDust.getInteger("rank3");
                        int rank4Num = nbtDust.getInteger("rank4");
                        int rank5Num = nbtDust.getInteger("rank5");

                        for (int s : slots) {
                            new BukkitRunnable() {
                                int i = 0;

                                @Override
                                public void run() {

                                    if (!(player.getOpenInventory().getTitle().contains("附魔池"))){
                                        cancel();
                                        return;
                                    }
//

                                    PWEnchant enchant = getRandomEnchant(tier1, tier2, tier3, tier4, tier5, baglevel);
                                    int rank = enchant.getRank();
                                    int addChance = 0;
                                    switch (rank){
                                        case 1:
                                            addChance = rank1Num;
                                            break;
                                        case 2:
                                            addChance = rank2Num;
                                            break;
                                        case 3:
                                            addChance = rank3Num;
                                            break;
                                        case 4:
                                            addChance = rank4Num;
                                            break;
                                        case 5:
                                            addChance = rank5Num;
                                            break;
                                        default:
                                            break;
                                    }
                                    List <String> buttonLore = new ArrayList<>();
                                    buttonLore.add(" ");
                                    for (String s : enchant.getDescription()){
                                        buttonLore.add(s);
                                    }
                                    buttonLore.add(" ");
                                    if (addChance>0){
                                        buttonLore.add(ChatColorCast.format("&b▪ &f成功率: &a&l" + enchantChanceMap.get(enchant) + "% &7(&e+"+addChance+"%&7)"));
                                        buttonLore.add(ChatColorCast.format("&b▪ &f失败率: &c&l" + (100 - enchantChanceMap.get(enchant)) + "% &7(&e-"+addChance+"%&7)"));
                                    }else{
                                        buttonLore.add(ChatColorCast.format("&b▪ &f成功率: &a&l" + enchantChanceMap.get(enchant) + "%"));
                                        buttonLore.add(ChatColorCast.format("&b▪ &f失败率: &c&l" + (100 - enchantChanceMap.get(enchant)) + "%"));
                                    }
                                    buttonLore.add(" ");
                                    buttonLore.add(ChatColorCast.format("&a&l点击&7选择该附魔"));
                                    int totalChance = enchantChanceMap.get(enchant)+addChance;
                                    if (totalChance>100){
                                        totalChance=100;
                                    }
                                    ItemStack button = enchantMenu.getButton(
                                            enchant.getItem(),
                                            enchant.getRankColor() + enchant.getChineseName() + ChatColor.AQUA + " " + ChatColor.BOLD + getLevel(enchantHasMap.get(enchant)),
                                            buttonLore,
                                            true,
                                            enchant,
                                            enchantHasMap.get(enchant),
                                            totalChance
                                    );
                                    viewInventory.setItem(s, button);
                                    if (i >= 20) {
                                        cancel();
                                        down.put(player, true);
                                        ifFirst.put(player, true);
                                    }
                                    i++;
                                }
                            }.runTaskTimerAsynchronously(PWItemManager.getPlugin(), 0l, 3l);
                        }
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL)>0){
                                    ItemStack roll_enchant = getButton(
                                            Material.TURTLE_EGG,
                                            ChatColorCast.format("&6&l开始抽取附魔"),
                                            asList(
                                                    " ",
                                                    ChatColorCast.format("&7抽取附魔后你的所有已解锁附魔格会变为"),
                                                    ChatColorCast.format("&7相应附魔，你可以选取其中&b&l1个&7进行附魔"),
                                                    ChatColorCast.format("&7抽取完后若没有满意的附魔，你可以重新抽取"),
                                                    ChatColorCast.format(" "),
                                                    ChatColorCast.format("&b▪ &f剩余重抽次数: &d&l"+IntDataManager.IntDataMap.get(player.getName()).singleMap.get(IntDataType.ENCHANTREROLL)),
                                                    ChatColorCast.format(" "),
                                                    ChatColorCast.format("&6&l点击&7开始抽取附魔")
                                            ),
                                            true
                                    );
                                    ItemStack dust = getButton(
                                            Material.SUGAR,
                                            ChatColorCast.format("&6&l附魔之尘几率加成"),
                                            asList(
                                                    " ",
                                                    ChatColorCast.format("&7你可以点击你背包中的附魔之尘"),
                                                    ChatColorCast.format("&7增加相应等级附魔的成功几率."),
                                                    ChatColorCast.format(" "),
                                                    ChatColorCast.format("&b▪ &f普通: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &a优秀: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &9稀有: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &e史诗: &b&l+0%"),
                                                    ChatColorCast.format("&b▪ &6传说: &b&l+0%")
                                            ),
                                            true
                                    );
                                    NBTItem nbtDust = new NBTItem(dust);
                                    nbtDust.setInteger("rank1",0);
                                    nbtDust.setInteger("rank2",0);
                                    nbtDust.setInteger("rank3",0);
                                    nbtDust.setInteger("rank4",0);
                                    nbtDust.setInteger("rank5",0);
                                    dust = nbtDust.getItem();
                                    viewInventory.setItem(8,dust);
                                    viewInventory.setItem(48, roll_enchant);
                                }
                            }
                        }.runTaskLaterAsynchronously(PWItemManager.getPlugin(), 60l);
                        return;
                    }
                    else {
                        if (down.containsKey(player) && down.get(player)) {
                            NBTItem nbtItem2 = new NBTItem(click);
                            if(nbtItem2.hasKey("enchant")) {
                                PWEnchant enchant = PWEnchant.valueOf(nbtItem2.getString("enchant"));
                                int level = nbtItem2.getInteger("level");
                                int success = nbtItem2.getInteger("success");
                                applyEnchantToBag.applyHandItemEnchantToBag(player, enchant.toString(), level, success, true);
//                            Bukkit.broadcastMessage(event.getSlot() + "你选择了" + pickaxeEnchant + " 抚摸后等级为" + level + " 成功率为" + success);
                                down.remove(player);
                                player.closeInventory();
                                return;
                            }
                        }
                    }

                    }
                }
            if (event.getClickedInventory().equals(player.getInventory())){
                if (down.containsKey(player) &&
                        event.getCurrentItem().getType().equals(Material.SUGAR) &&
                        !down.get(player)
                ){
                    player.sendMessage(ChatColorCast.format("&c▸ 请在抽取附魔前选择附魔粉!"));
                    player.playSound(player.getEyeLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f,0.1f);
                    return;
                }
                if (!(event.getCurrentItem().getType().equals(Material.SUGAR))){
                    return;
                }
                if (event.getCurrentItem().getAmount()!=1){
                    return;
                }
                NBTItem nbtItem = new NBTItem(event.getCurrentItem());
                if (!(nbtItem.hasKey("rank"))){
                    return;
                }
                int rank = nbtItem.getInteger("rank");
                int num = nbtItem.getInteger("num");
                ItemStack dust = viewInventory.getItem(8);
                NBTItem nbtDust = new NBTItem(dust);
                int rank1Num = nbtDust.getInteger("rank1");
                int rank2Num = nbtDust.getInteger("rank2");
                int rank3Num = nbtDust.getInteger("rank3");
                int rank4Num = nbtDust.getInteger("rank4");
                int rank5Num = nbtDust.getInteger("rank5");
                switch (rank){
                    case 1:
                        rank1Num = rank1Num + num;
                        break;
                    case 2:
                        rank2Num = rank2Num + num;
                        break;
                    case 3:
                        rank3Num = rank3Num + num;
                        break;
                    case 4:
                        rank4Num = rank4Num + num;
                        break;
                    case 5:
                        rank5Num = rank5Num + num;
                        break;
                    case 6:
                        rank1Num = rank1Num + num;
                        rank2Num = rank2Num + num;
                        rank3Num = rank3Num + num;
                        rank4Num = rank4Num + num;
                        rank5Num = rank5Num + num;
                        break;
                    default:
                        break;
                }
                ItemStack dustDone = getButton(
                        Material.SUGAR,
                        ChatColorCast.format("&6&l附魔之尘几率加成"),
                        asList(
                                " ",
                                ChatColorCast.format("&7你可以点击你背包中的附魔之尘"),
                                ChatColorCast.format("&7增加相应等级附魔的成功几率."),
                                ChatColorCast.format(" "),
                                ChatColorCast.format("&b▪ &f普通: &b&l+"+rank1Num+"%"),
                                ChatColorCast.format("&b▪ &a优秀: &b&l+"+rank2Num+"%"),
                                ChatColorCast.format("&b▪ &9稀有: &b&l+"+rank3Num+"%"),
                                ChatColorCast.format("&b▪ &e史诗: &b&l+"+rank4Num+"%"),
                                ChatColorCast.format("&b▪ &6传说: &b&l+"+rank5Num+"%")
                        ),
                        true
                );
                NBTItem nbtDust2 = new NBTItem(dustDone);
                nbtDust2.setInteger("rank1",rank1Num);
                nbtDust2.setInteger("rank2",rank2Num);
                nbtDust2.setInteger("rank3",rank3Num);
                nbtDust2.setInteger("rank4",rank4Num);
                nbtDust2.setInteger("rank5",rank5Num);
                dustDone = nbtDust2.getItem();
                viewInventory.setItem(8,dustDone);
                player.playSound(player.getEyeLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f,2f);
                player.getInventory().setItem(event.getSlot(),new ItemStack(Material.AIR));
                player.updateInventory();
            }
            }



    }
    @EventHandler
    public void onCloseEnchantMenu(InventoryCloseEvent event){


        if (event.getView().getTitle().contains("附魔池")) {
            Inventory viewInventory = event.getView().getTopInventory();
            Player player = (Player) event.getPlayer();
            if (!(down.containsKey(player))){
                return;
            }
            if (down.get(player)==true){
                ItemStack item = viewInventory.getItem(22);
                ItemStack chose = viewInventory.getItem(3);
                NBTItem nbtItem2 = new NBTItem(chose);
                PWEnchant enchant = PWEnchant.valueOf(nbtItem2.getString("enchant"));
                int level = nbtItem2.getInteger("level");
                int success = nbtItem2.getInteger("success");
                if (item.getType().toString().contains("PICKAXE")) {
                    applyEnchantToPickaxe.applyHandItemEnchantToPickaxe(player, enchant.toString(), level, success, true);
                }
                if (
                        item.getType().equals(Material.COAL) || item.getType().equals(Material.COAL_ORE) ||
                                item.getType().equals(Material.IRON_INGOT) || item.getType().equals(Material.IRON_ORE) ||
                                item.getType().equals(Material.LAPIS_LAZULI) || item.getType().equals(Material.LAPIS_ORE) ||
                                item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.REDSTONE_ORE) ||
                                item.getType().equals(Material.GOLD_INGOT) || item.getType().equals(Material.GOLD_ORE) ||
                                item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.DIAMOND_ORE) ||
                                item.getType().equals(Material.EMERALD) || item.getType().equals(Material.EMERALD_ORE)
                ){
                    applyEnchantToBag.applyHandItemEnchantToBag(player, enchant.toString(), level, success, true);
                }
                down.remove(player);
                ifFirst.remove(player);
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        InventoryView inventoryView = player.getOpenInventory();
        if (inventoryView==null){
            return;
        }
        String title = inventoryView.getTitle();
        Inventory viewInventory = inventoryView.getTopInventory();
        if (title.contains("附魔池")) {
            if (!(down.containsKey(player))){
                return;
            }
            if (down.get(player)==true){
                ItemStack item = viewInventory.getItem(22);
                ItemStack chose = viewInventory.getItem(3);
                NBTItem nbtItem2 = new NBTItem(chose);
                PWEnchant enchant = PWEnchant.valueOf(nbtItem2.getString("enchant"));
                int level = nbtItem2.getInteger("level");
                int success = nbtItem2.getInteger("success");
                if (item.getType().toString().contains("PICKAXE")) {
                    applyEnchantToPickaxe.applyHandItemEnchantToPickaxe(player, enchant.toString(), level, success, true);
                }
                if (
                        item.getType().equals(Material.COAL) || item.getType().equals(Material.COAL_ORE) ||
                                item.getType().equals(Material.IRON_INGOT) || item.getType().equals(Material.IRON_ORE) ||
                                item.getType().equals(Material.LAPIS_LAZULI) || item.getType().equals(Material.LAPIS_ORE) ||
                                item.getType().equals(Material.REDSTONE) || item.getType().equals(Material.REDSTONE_ORE) ||
                                item.getType().equals(Material.GOLD_INGOT) || item.getType().equals(Material.GOLD_ORE) ||
                                item.getType().equals(Material.DIAMOND) || item.getType().equals(Material.DIAMOND_ORE) ||
                                item.getType().equals(Material.EMERALD) || item.getType().equals(Material.EMERALD_ORE)
                ){
                    applyEnchantToBag.applyHandItemEnchantToBag(player, enchant.toString(), level, success, true);
                }
                down.remove(player);
                ifFirst.remove(player);
            }
        }
    }
    public String getLevel(int level){
        switch (level){
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

    public int getChance(int hadLevel,int hadLevelMax, int toolLevel){
        double l1 = (double)hadLevel/(toolLevel);
        if (hadLevel==1){
            return getRandomInt(75,90);
        }
        if (1-l1==0){
            return getRandomInt(15,30);
        }
        double l3 = (double)hadLevel/(hadLevelMax*2);
        int chance = (int)Math.round(100*(1-l1)*(1-l3));
        return chance;
    }

    public int getRandomInt(int min, int max){
        return random.nextInt(max-min+1)+min;
    }

    public PWEnchant getRandomEnchant(List<PWEnchant> tier1,
                                      List<PWEnchant> tier2,
                                      List<PWEnchant> tier3,
                                      List<PWEnchant> tier4,
                                      List<PWEnchant> tier5,
                                      int toolLevel
                                           ){
        List <PWEnchant> list = new ArrayList<>();
        if (toolLevel<=5){
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier3);
        }
        if (toolLevel>5 && toolLevel<=12){
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier3);
            list.addAll(tier3);
            list.addAll(tier4);
        }
        if (toolLevel>12 && toolLevel<=20){
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier3);
            list.addAll(tier3);
            list.addAll(tier3);
            list.addAll(tier4);
            list.addAll(tier4);
            list.addAll(tier5);
        }
        if (toolLevel>20){
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier1);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier2);
            list.addAll(tier3);
            list.addAll(tier3);
            list.addAll(tier3);
            list.addAll(tier3);
            list.addAll(tier4);
            list.addAll(tier4);
            list.addAll(tier4);
            list.addAll(tier5);
            list.addAll(tier5);
        }
        return list.get(random.nextInt(list.size()));
    }
    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow){
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
    public ItemStack getButton(Material material, String name, List<String> lore, boolean glow, PWEnchant enchant, int level, int success){
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
        nbtItem.setString("enchant", enchant.toString());
        nbtItem.setInteger("level", level);
        nbtItem.setInteger("success", success);
        item = nbtItem.getItem();
        return item;
    }

}
