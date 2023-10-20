package cn.pixelwar.pwitemmanager.Skript.Item.SellItems;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.Item.SellItem.SellItems;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectSellAll extends Effect {
    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectSellAll.class, new String[]{
                "sellall %player%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "drop a item with an owner at player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Player owner = this.playerin.getSingle(e);
        SellItems sellItems = new SellItems();
        sellItems.sellAll(owner);
    }
}