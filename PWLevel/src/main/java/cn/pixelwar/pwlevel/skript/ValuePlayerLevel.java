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

public class ValuePlayerLevel extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ValuePlayerLevel.class, Integer.class, ExpressionType.COMBINED, "[the] pwlevel of %player%");
    }

    private Expression<Player> player;
    private PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();

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
        return "get pwlevel of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        Player p = player.getSingle(event);
        if (p != null) {
            return new Integer[]{
                    playerExpDataManager.getLevel(p)
            };
        }
        return null;
    }
}
