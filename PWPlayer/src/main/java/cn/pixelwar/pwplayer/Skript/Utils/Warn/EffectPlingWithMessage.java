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

public class EffectPlingWithMessage extends Effect {
    private Expression<Player> playerin;
    private Expression<String> messagein;
    private Expression<Number> cooldownin;

    static {
        Skript.registerEffect(EffectPlingWithMessage.class, new String[]{
                "plingwithmessage %player% %string% %number%",
                //玩家 message cooldown(秒)
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.messagein = (Expression<String>) expressions[1];
        this.cooldownin = (Expression<Number>) expressions[2];
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
        String message = messagein.getSingle(event);
        int cooldown = cooldownin.getSingle(event).intValue();
        warn.plingWithMessage(player, message, cooldown);
    }
}
