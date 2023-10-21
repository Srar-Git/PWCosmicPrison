package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectResetBlock extends Effect {
    BlockAnimationData blockAnimationData = new BlockAnimationData();
    private Expression<Player> player;
    private Expression<Block> block;

    static {
        Skript.registerEffect(EffectResetBlock.class, new String[]{
                "resetblock %player% %block%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.block = (Expression<Block>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "check if block is broken and do stuff: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player digger = player.getSingle(event);
        Block breakingBlock = block.getSingle(event);
        String locString = digger + " " + breakingBlock.getLocation();
        blockAnimationData.resetBlock(locString, digger);
    }
}
