package cn.pixelwar.pwblockmanager.BlockStuff.Jinghua;

import cn.pixelwar.pwblockmanager.BlockStuff.ResttingBlockDataManager;
import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwpacketstuff.PWPacketStuff;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketJinghua;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Jinghua {


    JinghuaFile jinghuaFile = new JinghuaFile();

    public void createJinghua(Location location, Material item, Material orig, Material Jinghua) {
        JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
        PacketJinghua packetJinghua = new PacketJinghua();
        Random random = new Random();
        int id = random.nextInt(99999);
        //创建yml数据
        jinghuaFile.saveJinghuaData(location);

        //创建map数据 & 设置位置方块
        jinghuaDataManager.createJinghuaData(location, 150, 150, id, orig, Jinghua, item);

        new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().setType(location, Jinghua);
            }
        }.runTask(PWBlockManager.getPlugin());

        //上方hologram
        packetJinghua.createText(id, location.clone().add(0.5, 1.7, 0.5), new ItemStack(item));
        new BukkitRunnable() {
            int i = 1;

            @Override
            public void run() {
                if (!(JinghuaDataManager.getJinghuaDataMap().containsKey(location))) {
                    packetJinghua.delete(id);
                    cancel();
                }
                try {
                    packetJinghua.update(location.clone().add(0.5, 1.7, 0.5), id, JinghuaDataManager.getJinghuaDataMap().get(location).getAmount(), i * 10, i * 10);
                } catch (NullPointerException E) {
                }
                i++;

            }
        }.runTaskTimerAsynchronously(PWPacketStuff.getPlugin(), 0L, 2l);


    }

    public void randomSpawnJinghua() {
        JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
        AtomicBoolean coalDown = new AtomicBoolean(false);
        AtomicBoolean ironDown = new AtomicBoolean(false);
        AtomicBoolean lapisDown = new AtomicBoolean(false);
        AtomicBoolean redstoneDown = new AtomicBoolean(false);
        AtomicBoolean goldDown = new AtomicBoolean(false);
        AtomicBoolean diamondDown = new AtomicBoolean(false);
        AtomicBoolean emeraldDown = new AtomicBoolean(false);
        ResttingBlockDataManager.getResettingBlockData().forEach((key, value) -> {
            //煤矿
            if (!(coalDown.get())) {
                if (value.getOriginMaterial().toString().contains("COAL")) {
                    if (jinghuaDataManager.getTypeAmount("coal") < PWBlockManager.config.getInt("jinghua.total.coal")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            coalDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.COAL, Material.AIR, Material.DEEPSLATE_COAL_ORE);
                            jinghuaDataManager.setTypeAmount("coal", jinghuaDataManager.getTypeAmount("coal") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "精华煤矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.DARK_GRAY + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.DARK_GRAY + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.DARK_GRAY + key.clone().add(0, 1, 0).getBlockZ());

                        }
                    }
                }
            }
            //铁矿
            if (!(ironDown.get())) {
                if (value.getOriginMaterial().toString().contains("IRON")) {
                    if (jinghuaDataManager.getTypeAmount("iron") < PWBlockManager.config.getInt("jinghua.total.iron")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            ironDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.IRON_INGOT, Material.AIR, Material.DEEPSLATE_IRON_ORE);
                            jinghuaDataManager.setTypeAmount("iron", jinghuaDataManager.getTypeAmount("iron") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.GOLD + "" + ChatColor.BOLD + "精华铁矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.GOLD + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.GOLD + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.GOLD + key.clone().add(0, 1, 0).getBlockZ());

                        }
                    }
                }
            }
            //青金石矿
            if (!(lapisDown.get())) {
                if (value.getOriginMaterial().toString().contains("LAPIS")) {
                    if (jinghuaDataManager.getTypeAmount("lapis") < PWBlockManager.config.getInt("jinghua.total.lapis")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            lapisDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.LAPIS_LAZULI, Material.AIR, Material.DEEPSLATE_LAPIS_ORE);
                            jinghuaDataManager.setTypeAmount("lapis", jinghuaDataManager.getTypeAmount("lapis") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.BLUE + "" + ChatColor.BOLD + "精华青金石矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.BLUE + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.BLUE + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.BLUE + key.clone().add(0, 1, 0).getBlockZ());

                        }
                    }
                }
            }
            //红石矿
            if (!(redstoneDown.get())) {
                if (value.getOriginMaterial().toString().contains("REDSTONE")) {
                    if (jinghuaDataManager.getTypeAmount("redstone") < PWBlockManager.config.getInt("jinghua.total.redstone")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            redstoneDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.REDSTONE, Material.AIR, Material.DEEPSLATE_REDSTONE_ORE);
                            jinghuaDataManager.setTypeAmount("redstone", jinghuaDataManager.getTypeAmount("redstone") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.RED + "" + ChatColor.BOLD + "精华红石矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.RED + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.RED + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.RED + key.clone().add(0, 1, 0).getBlockZ());

                        }
                    }
                }
            }
            //金矿
            if (!(goldDown.get())) {
                if (value.getOriginMaterial().toString().contains("GOLD")) {
                    if (jinghuaDataManager.getTypeAmount("gold") < PWBlockManager.config.getInt("jinghua.total.gold")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            goldDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.GOLD_INGOT, Material.AIR, Material.DEEPSLATE_GOLD_ORE);
                            jinghuaDataManager.setTypeAmount("gold", jinghuaDataManager.getTypeAmount("gold") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.YELLOW + "" + ChatColor.BOLD + "精华金矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.YELLOW + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.YELLOW + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.YELLOW + key.clone().add(0, 1, 0).getBlockZ());

                        }
                    }
                }
            }
            //钻石矿
            if (!(diamondDown.get())) {
                if (value.getOriginMaterial().toString().contains("DIAMOND")) {
                    if (jinghuaDataManager.getTypeAmount("diamond") < PWBlockManager.config.getInt("jinghua.total.diamond")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            diamondDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.DIAMOND, Material.AIR, Material.DEEPSLATE_DIAMOND_ORE);
                            jinghuaDataManager.setTypeAmount("diamond", jinghuaDataManager.getTypeAmount("diamond") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.AQUA + "" + ChatColor.BOLD + "精华钻石矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.AQUA + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.AQUA + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.AQUA + key.clone().add(0, 1, 0).getBlockZ());

                        }
                    }
                }
            }
            //绿宝石矿
            if (!(emeraldDown.get())) {
                if (value.getOriginMaterial().toString().contains("EMERALD")) {
                    if (jinghuaDataManager.getTypeAmount("emerald") < PWBlockManager.config.getInt("jinghua.total.emerald")) {
                        if (key.getWorld().getBlockAt(key.clone().add(0, 1, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 2, 0)).getType().equals(Material.AIR) ||
                                key.getWorld().getBlockAt(key.clone().add(0, 3, 0)).getType().equals(Material.AIR)
                        ) {
                            emeraldDown.set(true);
                            createJinghua(key.clone().add(0, 1, 0), Material.EMERALD, Material.AIR, Material.DEEPSLATE_EMERALD_ORE);
                            jinghuaDataManager.setTypeAmount("emerald", jinghuaDataManager.getTypeAmount("emerald") + 1);
                            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "▸ " + ChatColor.WHITE + "有一颗" + ChatColor.GREEN + "" + ChatColor.BOLD + "精华绿宝石矿" + ChatColor.WHITE + "生成于 " + ChatColor.GRAY + "x:" + ChatColor.GREEN + key.clone().add(0, 1, 0).getBlockX() + ChatColor.GRAY + " y:" + ChatColor.GREEN + key.clone().add(0, 1, 0).getBlockY() + ChatColor.GRAY + " z:" + ChatColor.GREEN + key.clone().add(0, 1, 0).getBlockZ());
                        }
                    }
                }
            }


        });


    }

    public void removeJinghua(Location location) {
        JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
        PacketJinghua packetJinghua = new PacketJinghua();
        if (!(JinghuaDataManager.getJinghuaDataMap().containsKey(location))) {
            return;
        }


        //删除文件
        jinghuaFile.removeJinghuaData(location);
        //还原方块 & 删除datamap
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getWorld().setType(location, Material.AIR);

            }
        }.runTask(PWBlockManager.getPlugin());
        packetJinghua.delete(JinghuaDataManager.getJinghuaDataMap().get(location).getArmorStandID());
        String type = JinghuaDataManager.getJinghuaDataMap().get(location).getJinghuaType().toString();
        if (type.contains("COAL")) {
            jinghuaDataManager.setTypeAmount("coal", jinghuaDataManager.getTypeAmount("coal") - 1);
        }
        if (type.contains("IRON")) {
            jinghuaDataManager.setTypeAmount("iron", jinghuaDataManager.getTypeAmount("iron") - 1);
        }
        if (type.contains("LAPIS")) {
            jinghuaDataManager.setTypeAmount("lapis", jinghuaDataManager.getTypeAmount("lapis") - 1);
        }
        if (type.contains("REDSTONE")) {
            jinghuaDataManager.setTypeAmount("redstone", jinghuaDataManager.getTypeAmount("redstone") - 1);
        }
        if (type.contains("GOLD")) {
            jinghuaDataManager.setTypeAmount("gold", jinghuaDataManager.getTypeAmount("gold") - 1);
        }
        if (type.contains("DIAMOND")) {
            jinghuaDataManager.setTypeAmount("diamond", jinghuaDataManager.getTypeAmount("diamond") - 1);
        }
        if (type.contains("EMERALD")) {
            jinghuaDataManager.setTypeAmount("emerald", jinghuaDataManager.getTypeAmount("emerald") - 1);
        }

        JinghuaDataManager.getJinghuaDataMap().remove(location);


    }

}
