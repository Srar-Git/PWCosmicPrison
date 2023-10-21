package cn.pixelwar.pwplayer.Skript.Utils.Warn;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.Warn;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectBagFull extends Effect {
    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectBagFull.class, new String[]{
                "bagfull %player%",
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
        return "send warn to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Warn warn = new Warn();
        Player player = playerin.getSingle(event);
        warn.bagFull(player);
    }
}

