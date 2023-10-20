package cn.pixelwar.pwitemmanager.Skript.Enchant;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant.ApplyEnchantToPickaxe;
import cn.pixelwar.pwitemmanager.NBT.Item.GetItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueCouldApplyNuclear extends SimpleExpression<String> {

static {
        Skript.registerExpression(ValueCouldApplyNuclear.class, String.class, ExpressionType.COMBINED, "[the] couldapplynuclear %player% %number% %string% %number%" );
        }

private Expression<Player> playerin;
private Expression<Number> slotin;
private Expression<String> enchantin;
private Expression<Number> levelin;
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
    playerin = (Expression<Player>) exprs[0];
    slotin = (Expression<Number>) exprs[1];
    enchantin = (Expression<String>) exprs[2];
    levelin = (Expression<Number>) exprs[3];
        return true;
        }

@Override
public String toString(@Nullable Event event, boolean debug) {
        return "get enchantbook ";
        }

@Override
@Nullable
protected String[] get(Event event) {
        Player player = playerin.getSingle(event);
        int slot = slotin.getSingle(event).intValue();
        String enchant = enchantin.getSingle(event);
        int level = levelin.getSingle(event).intValue();
        ApplyEnchantToPickaxe applyEnchantToPickaxe = new ApplyEnchantToPickaxe();
        if (applyEnchantToPickaxe.couldApplyNuclearSlot(player,slot, enchant, level)){
            return new String[]{"yes"};
        }else{
            return new String[]{"no"};
        }

        }
}