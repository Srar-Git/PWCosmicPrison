package cn.pixelwar.pwitemmanager.Skript.OreBag;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetItemNBT;
import cn.pixelwar.pwitemmanager.OreBag.Orebag;
import cn.pixelwar.pwitemmanager.Skript.NBT.EffectUpdateHandNBTInt;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGiveOre extends Effect {
    private Expression<Player> playerin;
    private Expression<String> materialin;
    private Expression<Number> amountin;

    static {
        Skript.registerEffect(EffectGiveOre.class, new String[]{
                "giveore %player% %string% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.playerin = (Expression<Player>) expressions[0];
        this.materialin = (Expression<String>) expressions[1];
        this.amountin = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "give ore to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Orebag orebag = new Orebag();
        Player player = playerin.getSingle(e);
        int amount = amountin.getSingle(e).intValue();
        orebag.giveOre(player, materialin.getSingle(e), amount);

    }


}
