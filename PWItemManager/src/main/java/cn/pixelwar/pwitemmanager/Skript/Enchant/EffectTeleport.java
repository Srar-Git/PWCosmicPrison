package cn.pixelwar.pwitemmanager.Skript.Enchant;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Enchant.ApplyEnchant.ApplyEnchantToPickaxe;
import cn.pixelwar.pwitemmanager.Enchant.Teleport;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectTeleport extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> distancein;

    static {
        Skript.registerEffect(EffectTeleport.class, new String[]{
                "enchantteleport %player% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.playerin = (Expression<Player>) expressions[0];
        this.distancein = (Expression<Number>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "do teleport enchant: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Teleport teleport = new Teleport();
        Player player = playerin.getSingle(e);
        int distance = distancein.getSingle(e).intValue();
        teleport.EnchantTeleport(player,distance);
    }


}
