package cn.pixelwar.pwitemmanager.OreBag;

import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class OnOreBagAdd extends Orebag implements Listener {

    @EventHandler
    public void OnOreBagAdd(OreBagAddEvent event) {
        Player player = event.getPlayer();
        if (CoolDownManager.playerCoolDownMap.containsKey(player.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(player.getName()).getOneCoolDownMap().containsKey(CoolDownType.BAGENCHANT)) {
                return;
            }
        }
        doBagEnchant(player, event.getSlot(), event.getOre(), event.getAmount());


        CoolDownManager.addPlayerCoolDown(player, CoolDownType.BAGENCHANT, 3);
    }

}
