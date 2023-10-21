package cn.pixelwar.pwlevel.skript.Booster;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager.getPlayerExpBoosterDataMap;

public class ValueExpMulti extends SimpleExpression<Double> {
    static {
        Skript.registerExpression(ValueExpMulti.class, Double.class, ExpressionType.COMBINED, "[the] expboostermulti of %player%");
    }

    private Expression<Player> player;
    private PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();

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
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get energy multi of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        Player p = player.getSingle(event);
        if (getPlayerExpBoosterDataMap().containsKey(p.getName())) {
            if (p != null) {
                return new Double[]{
                        playerBoosterDataManager.getExpMultiple(p)
                };
            }
        }
        return new Double[]{1.0};
    }
}
