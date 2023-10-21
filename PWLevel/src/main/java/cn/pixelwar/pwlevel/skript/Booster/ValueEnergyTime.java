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

import static cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager.getPlayerEnergyBoosterDataMap;

public class ValueEnergyTime extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ValueEnergyTime.class, Integer.class, ExpressionType.COMBINED, "[the] energyboostertime of %player%");
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
        return "get energy booster time of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        Player p = player.getSingle(event);
        if (getPlayerEnergyBoosterDataMap().containsKey(p.getName())) {
            if (p != null) {
                return new Integer[]{
                        playerBoosterDataManager.getEnergyBoosterTime(p)
                };
            }
        }
        return new Integer[]{0};
    }
}
