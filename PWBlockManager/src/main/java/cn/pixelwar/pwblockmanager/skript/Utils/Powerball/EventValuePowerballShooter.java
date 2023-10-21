package cn.pixelwar.pwblockmanager.skript.Utils.Powerball;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;


public class EventValuePowerballShooter extends SimpleExpression<String> {
    static {
        Skript.registerExpression(EventValuePowerballShooter.class, String.class, ExpressionType.SIMPLE, new String[]{"[the] event-powerballshooter"});
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
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
    protected String[] get(Event event) {
        if (event instanceof PowerballHitBlockEvent) {
            Player shooter = ((PowerballHitBlockEvent) event).getShooter();
            String shooterString = shooter.getDisplayName();
            return new String[]{shooterString};
        }
        return new String[]{"shooterString"};
    }
}


