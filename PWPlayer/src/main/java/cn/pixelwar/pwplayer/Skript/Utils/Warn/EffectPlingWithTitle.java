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

public class EffectPlingWithTitle extends Effect {
    private Expression<Player> playerin;

    private Expression<String> titlein;
    private Expression<String> subtitlein;
    private Expression<Number> stayin;
    private Expression<Number> cooldownin;

    static {
        Skript.registerEffect(EffectPlingWithTitle.class, new String[]{
                "plingwithtitle %player% %string% %string% %number% %number%",
                //玩家 title subtitle staytime cooldown(秒)
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.titlein = (Expression<String>) expressions[1];
        this.subtitlein = (Expression<String>) expressions[2];
        this.stayin = (Expression<Number>) expressions[3];
        this.cooldownin = (Expression<Number>) expressions[4];
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
        String title = titlein.getSingle(event);
        String subtitle = subtitlein.getSingle(event);
        int stay = stayin.getSingle(event).intValue();
        int cooldown = cooldownin.getSingle(event).intValue();
        warn.plingWithTitle(player, title, subtitle, stay, cooldown);
    }
}
