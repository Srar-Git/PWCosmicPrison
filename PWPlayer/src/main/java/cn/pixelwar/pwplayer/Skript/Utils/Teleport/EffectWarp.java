package cn.pixelwar.pwplayer.Skript.Utils.Teleport;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerAction.Teleport.Teleport;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectWarp extends Effect {

    private Expression<Player> playerin;
    private Expression<String> locin;

    private Expression<String> bypassin;

    static {
        Skript.registerEffect(EffectWarp.class, new String[]{
                "pwwarp %player% %string% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.locin = (Expression<String>) expressions[1];

        this.bypassin = (Expression<String>) expressions[2];

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "tp player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String bypass = bypassin.getSingle(event);
        String loc = locin.getSingle(event);
        Teleport teleport = new Teleport();
        if (!(Teleport.warpsMap.containsKey(loc))) {
            return;
        }
        Location location = Teleport.warpsMap.get(loc);
        if (bypass.equals("false")) {
            teleport.teleportPlayer(player, location, false);
            return;
        }
        teleport.teleportPlayer(player, location, true);


    }
}
