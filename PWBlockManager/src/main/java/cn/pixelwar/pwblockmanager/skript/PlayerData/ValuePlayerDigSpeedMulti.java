package cn.pixelwar.pwblockmanager.skript.PlayerData;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValuePlayerDigSpeedMulti extends SimpleExpression<Float> {

    static {
        Skript.registerExpression(ValuePlayerDigSpeedMulti.class, Float.class, ExpressionType.COMBINED, "[the] digspeedmulti of %player%");
    }

    private Expression<Player> player;
    private PlayerData playerData = new PlayerData();

    @Override
    public Class<? extends Float> getReturnType() {
        return Float.class;
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
        return "get crit of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Float[] get(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return new Float[]{
                    playerData.getSpeedMultiple(p)
            };
        }
        return null;
    }
}
