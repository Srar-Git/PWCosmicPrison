package cn.pixelwar.pwplayer.Skript.PlayerStat.DoubleData;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueDoubleData extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ValueDoubleData.class, Double.class, ExpressionType.COMBINED, "[the] %string% doubledata of %player%");
    }

    private Expression<String> typein;
    private Expression<Player> player;

    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        typein = (Expression<String>) exprs[0];
        player = (Expression<Player>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get doubledata of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        Player p = player.getSingle(event);
        String type = typein.getSingle(event);
        double v = DoubleDataManager.DoubleDataMap.get(p.getName()).singleMap.get(DoubleDataType.valueOf(type));
        return new Double[]{v};
    }
}
