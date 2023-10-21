package cn.pixelwar.pwblockmanager.skript.Utils.Powerball;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;


public class EventValuePowerballIronOre extends SimpleExpression<Integer> {
    static {
        Skript.registerExpression(EventValuePowerballIronOre.class, Integer.class, ExpressionType.SIMPLE, new String[]{"[the] event-powerballironore"});
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
        if (event instanceof PowerballHitBlockEvent) {
            int oreCount = ((PowerballHitBlockEvent) event).getIronoreCount();

            return new Integer[]{oreCount};
        }
        return new Integer[]{0};
    }
}
