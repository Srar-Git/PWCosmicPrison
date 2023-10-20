package cn.pixelwar.pwitemmanager.Skript.OreBag;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.OreBag.OreBagAddEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventValuePlayer extends SimpleExpression<Player> {

    static {
        Skript.registerExpression(EventValuePlayer.class, Player.class, ExpressionType.SIMPLE, new String[]{"[the] owner"});
    }
    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected Player[] get(Event event) {
        if (event instanceof OreBagAddEvent) {
            Player owner = ((OreBagAddEvent) event).getPlayer();
            return new Player[]{owner};
        }
        return null;
    }

}
