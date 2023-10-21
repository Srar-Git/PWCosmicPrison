package cn.pixelwar.pwplayer.Listeners;

import cn.pixelwar.pwplayer.File.YamlStorage;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Sentinel.NPC.PlayerNPCManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.UUID;

public class NPCDeathListener implements Listener {

    @EventHandler
    public void onNPCDeath(NPCDeathEvent event) {
        NPC npc = event.getNPC();
        for (String name : PlayerNPCManager.playerNPCMap.keySet()) {
            if (CitizensAPI.getNPCRegistry().getById(PlayerNPCManager.playerNPCMap.get(name).getNPCID()).equals(npc)) {
                //掉落物品
                for (ItemStack itemStack : PlayerNPCManager.playerNPCMap.get(name).getInventory()) {
                    if (itemStack == null) {
                        continue;
                    }
                    Location location = event.getNPC().getEntity().getLocation();
                    event.getNPC().getEntity().getWorld().dropItemNaturally(location, itemStack);
                }
                //清空玩家背包
                YamlStorage yamlStorage = new YamlStorage();
                yamlStorage.saveOffLinePlayerData(name, IntDataType.CLEARINVONJOIN, 1);

                PlayerNPCManager.removePlayerNPC(npc);
                PlayerNPCManager.playerNPCMap.remove(name);
            }
        }
    }


}
