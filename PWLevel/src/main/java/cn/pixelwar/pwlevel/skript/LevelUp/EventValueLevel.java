package cn.pixelwar.pwlevel.skript.LevelUp;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwlevel.CustomEvents.LevelUpEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventValueLevel extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(EventValueLevel.class, Integer.class, ExpressionType.SIMPLE, new String[]{"[the] event-level"});
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
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
    protected Integer[] get(Event event) {
        if (event instanceof LevelUpEvent) {
            int level = ((LevelUpEvent) event).getlevel();
            return new Integer[]{level};
        }
        return null;
    }

}
