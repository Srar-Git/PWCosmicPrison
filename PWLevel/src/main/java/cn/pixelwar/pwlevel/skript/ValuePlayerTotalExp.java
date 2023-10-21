package cn.pixelwar.pwlevel.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValuePlayerTotalExp extends SimpleExpression<Long> {

    static {
        Skript.registerExpression(ValuePlayerTotalExp.class, Long.class, ExpressionType.COMBINED, "[the] pixelwartotalexp of %player%");
    }

    private Expression<Player> player;
    private PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get pixelwartotalexp of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Long[] get(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return new Long[]{
                    playerExpDataManager.getTotalExp(p)
            };
        }
        return null;
    }
}
