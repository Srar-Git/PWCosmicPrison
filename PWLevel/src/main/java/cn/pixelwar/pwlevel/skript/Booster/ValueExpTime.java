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

public class ValueExpTime extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ValueExpTime.class, Integer.class, ExpressionType.COMBINED, "[the] expboostertime of %player%");
    }

    private Expression<Player> player;
    private PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();

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
        player = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get exp booster time of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        Player p = player.getSingle(event);
        if (getPlayerExpBoosterDataMap().containsKey(p.getName())) {
            if (p != null) {
                return new Integer[]{
                        playerBoosterDataManager.getExpBoosterTime(p)
                };
            }
        }
        return new Integer[]{0};
    }
}
