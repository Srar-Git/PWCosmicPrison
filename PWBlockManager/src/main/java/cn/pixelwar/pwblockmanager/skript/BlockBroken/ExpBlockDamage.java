package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nullable;

public class ExpBlockDamage extends SimpleExpression<Float> {
    BlockAnimationData blockAnimationData = new BlockAnimationData();

    static {
        Skript.registerExpression(ExpBlockDamage.class, Float.class, ExpressionType.SIMPLE, new String[]{"[the] event-blockdamage"});
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected Float[] get(Event event) {
        if (event instanceof PlayerInteractEvent) {
            Block block = ((PlayerInteractEvent) event).getClickedBlock();
            Player digger = ((PlayerInteractEvent) event).getPlayer();
            String locString = digger + " " + block.getLocation();
            float damage = blockAnimationData.getBlockAnimationData(locString).getBlockDamage();

            return new Float[]{damage};
        }
        return new Float[]{0f};
    }
}
