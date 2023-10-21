package cn.pixelwar.pwplayer.Skript.PlayerStat.Home;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerAction.Teleport.Teleport;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import cn.pixelwar.pwplayer.Utils.ChatColorCast;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGohome extends Effect {

    private Expression<Player> playerin;
    private Expression<String> namein;
    private Expression<String> bypassin;

    static {
        Skript.registerEffect(EffectGohome.class, new String[]{
                "gohome %player% %string% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.namein = (Expression<String>) expressions[1];
        this.bypassin = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "go home to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String name = namein.getSingle(event);
        String bypass = bypassin.getSingle(event);
        if (!(HomeManager.playerHomeMap.get(player.getName()).getOnePlayerHomeMap().containsKey(name))) {
            player.sendMessage(ChatColorCast.format("&c▸ &f该名字不存在,请使用&d&l/homes&f查看你的家"));
            return;
        }
        Location location = HomeManager.playerHomeMap.get(player.getName()).getOnePlayerHomeMap().get(name);
        Teleport teleport = new Teleport();
        if (bypass.equals("false")) {
            teleport.teleportPlayer(player, location, false);
            return;
        }
        teleport.teleportPlayer(player, location, true);


    }
}
