package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant.ApplyEnchantToPickaxe;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class ValueLineNumHasString extends SimpleExpression<Integer> {

static {
        Skript.registerExpression(ValueLineNumHasString.class, Integer.class, ExpressionType.COMBINED, "[the] lorelinrhas %string% %itemtypes%" );
        }


private Expression<String> stringin;
private Expression<ItemType> itemin;
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
public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
    stringin = (Expression<String>) exprs[0];
    itemin = (Expression<ItemType>) exprs[1];
    return true;
}

@Override
public String toString(@Nullable Event event, boolean debug) {
        return "get enchantbook ";
        }

@Override
@Nullable
protected Integer[] get(Event event) {
        String s = stringin.getSingle(event);
        ItemType itemType = itemin.getSingle(event);
        ItemStack item = itemType.getRandom();
        List<String> lore = item.getItemMeta().getLore();
        int line = 1;
        for (String l : lore){
            if (l.contains(s)){
                break;
            }line++;
        }
        return new Integer[]{line};


        }
}