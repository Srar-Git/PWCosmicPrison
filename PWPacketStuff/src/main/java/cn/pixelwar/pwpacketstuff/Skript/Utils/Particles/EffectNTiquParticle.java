package cn.pixelwar.pwpacketstuff.Skript.Utils.Particles;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectNTiquParticle extends Effect {
    private Expression<Player> playerin;
    private Expression<Location> locin;

    static {
        Skript.registerEffect(EffectTiquParticle.class, new String[]{
                "ntiquparticle %player% %location%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.locin = (Expression<Location>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "particle effect to player: " + playerin.toString(event, debug) + " and x location: ";
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        Location loc = locin.getSingle(event);
        cn.pixelwar.pwpacketstuff.Utils.Particles.Enchants.NTiquEffect(player, loc);
    }
}

