package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.GetItemNBT;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ExpGetStringNBT extends SimpleExpression<String> {
    private Expression<ItemType> itemin;
    private Expression<String> pathin;
    static {
        Skript.registerExpression(ExpGetStringNBT.class, String.class, ExpressionType.SIMPLE, new String[]{"stringtag %string% of nbt of %itemtypes%"});
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
    protected String[] get(Event event) {
        GetItemNBT getItemNBT = new GetItemNBT();
        String path = pathin.getSingle(event);
        ItemType itemType = itemin.getSingle(event);
        ItemStack item = itemType.getRandom();
        return new String[] {getItemNBT.getItemStringNBT(item, path)};
    }

}
