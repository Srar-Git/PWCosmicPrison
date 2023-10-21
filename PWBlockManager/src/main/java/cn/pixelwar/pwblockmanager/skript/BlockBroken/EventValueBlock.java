package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.customevents.BlockBrokenEvent;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EventValueBlock extends SimpleExpression<Block> {

    static {
        Skript.registerExpression(EventValueBlock.class, Block.class, ExpressionType.SIMPLE, new String[]{"[the] event-brokenblock"});
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected Block[] get(Event event) {
        if (event instanceof BlockBrokenEvent) {
            Block block = ((BlockBrokenEvent) event).getBlock();
            return new Block[]{block};
        }
        return null;
    }

}
