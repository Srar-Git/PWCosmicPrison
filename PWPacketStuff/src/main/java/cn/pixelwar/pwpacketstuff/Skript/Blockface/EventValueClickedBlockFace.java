package cn.pixelwar.pwpacketstuff.Skript.Blockface;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nullable;

public class EventValueClickedBlockFace extends SimpleExpression<String> {
    static {
        Skript.registerExpression(EventValueClickedBlockFace.class, String.class, ExpressionType.SIMPLE, new String[]{"[the] event-face"});
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        if (event instanceof PlayerInteractEvent) {
            BlockFace face = ((PlayerInteractEvent) event).getBlockFace();
            String faceString = face.toString();
            return new String[]{faceString};
        }
        return new String[]{"faceString"};
    }
}


