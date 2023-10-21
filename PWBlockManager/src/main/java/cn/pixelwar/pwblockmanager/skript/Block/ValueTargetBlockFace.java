package cn.pixelwar.pwblockmanager.skript.Block;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.Utils.TargetBlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueTargetBlockFace extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ValueTargetBlockFace.class, String.class, ExpressionType.COMBINED, "[the] targetface of %player%");
    }

    private Expression<Player> playerin;

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
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        playerin = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get target face of player ";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        Player player = playerin.getSingle(event);
        TargetBlockFace targetBlockFace = new TargetBlockFace();
        String face = targetBlockFace.getFace(player);
        return new String[]{face};

    }
}
