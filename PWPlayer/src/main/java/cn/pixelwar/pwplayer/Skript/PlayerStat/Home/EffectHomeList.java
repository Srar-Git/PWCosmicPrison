package cn.pixelwar.pwplayer.Skript.PlayerStat.Home;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class EffectHomeList extends Effect {

    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectHomeList.class, new String[]{
                "homelist %player%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "go home to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        player.sendMessage(" ");
        player.sendMessage(" ");
        player.sendMessage(ChatColorCast.format("&b&l" + player.getName() + "&d&l家列表 &f(" + HomeManager.playerHomeMap.get(player.getName()).getOnePlayerHomeMap().size() + "/" + IntDataManager.getIntData(player, IntDataType.HOMEMAXHOME) + ")"));
        player.sendMessage(" ");
        AtomicInteger i = new AtomicInteger();
        HomeManager.playerHomeMap.get(player.getName()).getOnePlayerHomeMap().forEach(
                (key, value) -> {
                    i.getAndIncrement();
                    player.sendMessage(ChatColorCast.format(" &d&l#" + i + " &7▸ &f名字: &e" + key + "&6 | &f坐标: &b" + value.getBlockX() + "," + value.getBlockY() + "," + value.getBlockZ()));
                }
        );
        player.sendMessage(" ");
        player.sendMessage(" ");

    }
}
