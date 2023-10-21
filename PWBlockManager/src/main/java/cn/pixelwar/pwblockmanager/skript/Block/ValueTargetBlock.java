package cn.pixelwar.pwblockmanager.skript.Block;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.Set;

public class ValueTargetBlock extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(ValueTargetBlock.class, Block.class, ExpressionType.COMBINED, "[the] targetblock of %player%");
    }

    private Expression<Player> playerin;

    @Override
    public Class<? extends Block> getReturnType() {
        return Block.class;
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
        return "get target block of player ";
    }

    @Override
    @Nullable
    protected Block[] get(Event event) {
        Player player = playerin.getSingle(event);
        Block block = player.getTargetBlock((Set<Material>) null, 4);
        return new Block[]{block};

    }
}
