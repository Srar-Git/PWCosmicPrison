package cn.pixelwar.pwitemmanager.Skript.OreBag;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.OreBag.OreBagAddEvent;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EventValueOre extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(EventValueOre.class, ItemStack.class, ExpressionType.SIMPLE, new String[]{"[the] event-ore"});
    }
    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
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
    protected ItemStack[] get(Event event) {
        if (event instanceof OreBagAddEvent) {
            Material orem = ((OreBagAddEvent) event).getOre();
            ItemStack ore = new ItemStack(orem);
            return new ItemStack[]{ore};
        }
        return null;
    }

}
