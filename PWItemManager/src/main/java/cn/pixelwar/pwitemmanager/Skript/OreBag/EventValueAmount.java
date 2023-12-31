package cn.pixelwar.pwitemmanager.Skript.OreBag;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.OreBag.OreBagAddEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventValueAmount extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(EventValueAmount.class, Integer.class, ExpressionType.SIMPLE, new String[]{"[the] event-amount"});
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
        if (event instanceof OreBagAddEvent) {
            int amount = ((OreBagAddEvent) event).getAmount();
            return new Integer[]{amount};
        }
        return null;
    }

}