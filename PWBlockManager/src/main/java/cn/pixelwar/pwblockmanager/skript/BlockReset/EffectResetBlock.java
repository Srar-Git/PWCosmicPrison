package cn.pixelwar.pwblockmanager.skript.BlockReset;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.ResttingBlockDataManager;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectResetBlock extends Effect {
    ResttingBlockDataManager resttingBlockDataManager = new ResttingBlockDataManager();
    private Expression<Block> oldBlock;
    private Expression<String> originIn;
    private Expression<String> resetIn;
    private Expression<Number> resetTime;

    static {
        Skript.registerEffect(EffectResetBlock.class, new String[]{
                "resetrealblock %block% %string% %string% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.oldBlock = (Expression<Block>) expressions[0];
        this.originIn = (Expression<String>) expressions[1];
        this.resetIn = (Expression<String>) expressions[2];
        this.resetTime = (Expression<Number>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "reset block";
    }

    @Override
    protected void execute(Event event) {

        String origin = originIn.getSingle(event);
        String reset = resetIn.getSingle(event);
        Block block = oldBlock.getSingle(event);
        double time = (double) resetTime.getSingle(event);

        resttingBlockDataManager.updateResetting(block, origin, reset, time);
    }
}
