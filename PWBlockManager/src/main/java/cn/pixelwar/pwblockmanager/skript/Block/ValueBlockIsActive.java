package cn.pixelwar.pwblockmanager.skript.Block;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.Set;

public class ValueBlockIsActive extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ValueBlockIsActive.class, Boolean.class, ExpressionType.COMBINED, "isactive %block% %player%");
    }

    private Expression<Block> blockin;
    private Expression<Player> playerin;

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        blockin = (Expression<Block>) exprs[0];
        playerin = (Expression<Player>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return " ";
    }

    @Override
    @Nullable
    protected Boolean[] get(Event event) {
        Block block = blockin.getSingle(event);
        Player player = playerin.getSingle(event);
        String locString = player + " " + block.getLocation();
        BlockAnimationData blockAnimationData = new BlockAnimationData();
        if (!(blockAnimationData.getBlockAnimationData().containsKey(locString))) {
            return new Boolean[]{false};
        }
        return new Boolean[]{blockAnimationData.ifBlockActive(locString)};
    }
}
