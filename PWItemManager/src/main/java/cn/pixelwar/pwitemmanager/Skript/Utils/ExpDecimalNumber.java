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

public class ExpDecimalNumber extends SimpleExpression<String> {
    private Expression<Number> decimalin;
    private Expression<Number> numberin;
    static {
        Skript.registerExpression(ExpDecimalNumber.class, String.class, ExpressionType.SIMPLE, new String[]{"decimalnumber %number% %number%"});
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
        this.decimalin = (Expression<Number>) expressions[0];
        this.numberin = (Expression<Number>) expressions[1];
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
        int decimal = decimalin.getSingle(event).intValue();
        double number = numberin.getSingle(event).doubleValue();
        String numberout = numberFormat.getDecimalFormat(decimal, number);
        return new String[] {numberout};
    }

}