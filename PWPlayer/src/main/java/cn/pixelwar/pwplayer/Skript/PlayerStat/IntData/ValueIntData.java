package cn.pixelwar.pwplayer.Skript.PlayerStat.IntData;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueIntData extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ValueIntData.class, Integer.class, ExpressionType.COMBINED, "[the] %string% intdata of %player%");
    }

    private Expression<String> typein;
    private Expression<Player> player;

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
        typein = (Expression<String>) exprs[0];
        player = (Expression<Player>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get digdata of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        Player p = player.getSingle(event);
        String type = typein.getSingle(event);
        int v = IntDataManager.IntDataMap.get(p.getName()).singleMap.get(IntDataType.valueOf(type));
        return new Integer[]{v};
    }
}
