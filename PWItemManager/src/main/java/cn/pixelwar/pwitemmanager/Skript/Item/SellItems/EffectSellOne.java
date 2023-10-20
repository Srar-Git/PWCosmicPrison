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

public class EffectSellOne extends Effect {
    private Expression<Player> playerin;
    private Expression<String> typein;

    static {
        Skript.registerEffect(EffectSellOne.class, new String[]{
                "sellone %player% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.typein = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "drop a item with an owner at player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Player owner = this.playerin.getSingle(e);
        String type = typein.getSingle(e);
        SellItems sellItems = new SellItems();
        sellItems.sellOne(owner, type);
    }
}