package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.GetItemNBT;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ExpGetIntNBT extends SimpleExpression<Integer> {
    private Expression<ItemType> itemin;
    private Expression<String> pathin;
    static {
        Skript.registerExpression(ExpGetIntNBT.class, Integer.class, ExpressionType.SIMPLE, new String[]{"tag %string% of nbt of %itemtypes%"});
    }
    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.pathin = (Expression<String>) expressions[0];
        this.itemin = (Expression<ItemType>) expressions[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return null;
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        GetItemNBT getItemNBT = new GetItemNBT();
        String path = pathin.getSingle(event);
        ItemType itemType = itemin.getSingle(event);
        ItemStack item = itemType.getRandom();
        return new Integer[] {getItemNBT.getItemIntNBT(item, path)};
    }

}

