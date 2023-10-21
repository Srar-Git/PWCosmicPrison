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

public class ValueEnchantBook extends SimpleExpression<ItemType> {

    static {
        Skript.registerExpression(ValueEnchantBook.class, ItemType.class, ExpressionType.COMBINED, "[the] enchantbook %string% %number% %number% %number%");
    }

    private Expression<String> namein;
    private Expression<Number> levelin;
    private Expression<Number> successin;
    private Expression<Number> failin;

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
        levelin = (Expression<Number>) exprs[1];
        successin = (Expression<Number>) exprs[2];
        failin = (Expression<Number>) exprs[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get enchantbook ";
    }

    @Override
    @Nullable
    protected ItemType[] get(Event event) {
        GetItem getItem = new GetItem();
        String name = namein.getSingle(event);
        int level = levelin.getSingle(event).intValue();
        int success = successin.getSingle(event).intValue();
        int fail = failin.getSingle(event).intValue();
        ItemType itemType = new ItemType(getItem.getEnchantBook(name, level, success, fail));
        return new ItemType[]{itemType};
    }
}