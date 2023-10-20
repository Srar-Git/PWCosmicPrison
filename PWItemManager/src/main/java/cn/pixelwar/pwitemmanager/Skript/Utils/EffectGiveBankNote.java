package cn.pixelwar.pwitemmanager.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetItemNBT;
import cn.pixelwar.pwitemmanager.Utils.GiveItems;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGiveBankNote extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> moneyin;

    static {
        Skript.registerEffect(EffectGiveBankNote.class, new String[]{
                "givebanknote %player% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.playerin = (Expression<Player>) expressions[0];
        this.moneyin = (Expression<Number>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "give player banknote: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        GiveItems giveItems = new GiveItems();
        SetItemNBT setItemNBT = new SetItemNBT();
        Player player = playerin.getSingle(e);
        double money = moneyin.getSingle(e).doubleValue();
        giveItems.giveBanknote(player, money);

    }


}


