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

public class EffectGiveEnergy extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> energyin;
    private Expression<Number> amountin;

    static {
        Skript.registerEffect(EffectGiveEnergy.class, new String[]{
                "giveenergy %player% %number% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.playerin = (Expression<Player>) expressions[0];
        this.energyin = (Expression<Number>) expressions[1];
        this.amountin = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "give player energy: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        GiveItems giveItems = new GiveItems();
        SetItemNBT setItemNBT = new SetItemNBT();
        Player player = playerin.getSingle(e);
        long energy = (long) energyin.getSingle(e).longValue();
        int amount = amountin.getSingle(e).intValue();
        giveItems.giveEnergy(player, energy, amount);

    }


}

