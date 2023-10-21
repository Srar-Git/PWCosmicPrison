package cn.pixelwar.pwplayer.Listeners;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.NPCSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCLoadListener implements Listener {

    @EventHandler
    public void onNPCLoad(NPCSpawnEvent event) {
        Bukkit.broadcastMessage("npc生成：" + event.getNPC().getName());
    }


}
