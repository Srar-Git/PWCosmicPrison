package cn.pixelwar.pwplayer.Sentinel.NPC;

import cn.pixelwar.pwplayer.Sentinel.Guard.GuardManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.HologramTrait;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.mcmonkey.sentinel.SentinelTrait;

import java.util.HashMap;

public class PlayerNPCManager {

    /*
    关服的时候记得清空，删除所有playerNPC
     */
    public static HashMap<String, PlayerNPCInfo> playerNPCMap = new HashMap<>();

    public NPC createPlayerNPC(PlayerNPCInfo playerNPCInfo) {
        NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, playerNPCInfo.getName());
        npc.data().setPersistent(NPC.ALWAYS_USE_NAME_HOLOGRAM_METADATA, false);
        ItemStack[] equips = playerNPCInfo.getEquipments();
        for (ItemStack i : equips) {
            if (null == i) {
                continue;
            }
            if (i.getType().toString().contains("HELMET")) {
                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, i);
            }
            if (i.getType().toString().contains("CHESTPLATE")) {
                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.CHESTPLATE, i);
            }
            if (i.getType().toString().contains("LEGGINGS")) {
                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.LEGGINGS, i);
            }
            if (i.getType().toString().contains("BOOTS")) {
                npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.BOOTS, i);
            }
        }
        if (null != playerNPCInfo.getHandItem()) {
            npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HAND, playerNPCInfo.getHandItem());
        }
        npc.getOrAddTrait(HologramTrait.class).addLine(ChatColorCast.format((int) playerNPCInfo.getHealth() + "&f&c&l❤"));
        npc.getOrAddTrait(HologramTrait.class).addLine(ChatColorCast.format("&c&l✖ &f玩家在战斗模式中离线"));
        SentinelTrait sentinelTrait = npc.getOrAddTrait(SentinelTrait.class);
        sentinelTrait.health = playerNPCInfo.getHealth();
        sentinelTrait.fightback = false;


        npc.spawn(playerNPCInfo.getLocation());
        playerNPCInfo.setNPCID(npc.getId());
        playerNPCMap.put(playerNPCInfo.getName(), playerNPCInfo);
        return npc;
    }

    public void clearAllPlayerNPC() {
        for (String playerName : playerNPCMap.keySet()) {
            CitizensAPI.getNPCRegistry().getById(playerNPCMap.get(playerName).getNPCID()).destroy();
        }
        playerNPCMap.clear();
    }

    public static void removePlayerNPC(NPC npc) {
        //看是否被guard追杀
        if (GuardManager.guardHuntingPlayerMap.values().contains(npc)) {
            GuardManager.resetGuard(npc.getEntity());
        }
        npc.destroy();
    }

}
