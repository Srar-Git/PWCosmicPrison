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

public class ValueEnchantDust extends SimpleExpression<ItemType> {

    static {
        Skript.registerExpression(ValueEnchantDust.class, ItemType.class, ExpressionType.COMBINED, "[the] enchantdust %number% %number%");
    }

    private Expression<Number> rankin;
    private Expression<Number> numin;

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
        rankin = (Expression<Number>) exprs[0];
        numin = (Expression<Number>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get enchantdust ";
    }

    @Override
    @Nullable
    protected ItemType[] get(Event event) {
        GetItem getItem = new GetItem();
        int rank = rankin.getSingle(event).intValue();
        int num = numin.getSingle(event).intValue();
        ItemType itemType = new ItemType(getItem.getEnchantDust(rank, num));
        return new ItemType[]{itemType};
    }
}