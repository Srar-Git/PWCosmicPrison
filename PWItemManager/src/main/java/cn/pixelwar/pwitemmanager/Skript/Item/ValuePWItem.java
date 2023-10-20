package cn.pixelwar.pwitemmanager.Skript.Item;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.Item.GetItem;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValuePWItem extends SimpleExpression<ItemType> {

    static {
        Skript.registerExpression(ValuePWItem.class, ItemType.class, ExpressionType.COMBINED, "[the] pwitem %string%");
    }

    private Expression<String> namein;
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
        namein = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get pwitem ";
    }

    @Override
    @Nullable
    protected ItemType[] get(Event event) {
        GetItem getItem = new GetItem();
        String name = namein.getSingle(event);
        ItemType itemType = new ItemType(getItem.getItem(name));
        return new ItemType[]{itemType};
    }
}
