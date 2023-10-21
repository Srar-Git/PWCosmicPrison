package cn.pixelwar.pwitemmanager.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Utils.NumberFormat;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExpNumberIntFormat extends SimpleExpression<String> {
    private Expression<Number> numberin;

    static {
        Skript.registerExpression(ExpNumberIntFormat.class, String.class, ExpressionType.SIMPLE, new String[]{"intformat of %number%"});
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
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.numberin = (Expression<Number>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        NumberFormat numberFormat = new NumberFormat();
//        long numberlong = (long) numberin.getSingle(event);
        int numberint = numberin.getSingle(event).intValue();
        String number = numberFormat.getIntFormat(numberint);
        return new String[]{number};
    }

}
