package cn.pixelwar.pwblockmanager.skript.Utils.Powerball;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.customevents.PowerballHitBlockEvent;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EventValueShootItem extends SimpleExpression<ItemType> {
    static {
        Skript.registerExpression(EventValueShootItem.class, ItemType.class, ExpressionType.SIMPLE, new String[]{"[the] event-shootitem"});
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
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
    protected ItemType[] get(Event event) {
        if (event instanceof PowerballHitBlockEvent) {
            ItemStack itemStack = ((PowerballHitBlockEvent) event).getItem();
            ItemType itemType = new ItemType(itemStack);
            return new ItemType[]{itemType};
        }
        return new ItemType[]{null};
    }
}
