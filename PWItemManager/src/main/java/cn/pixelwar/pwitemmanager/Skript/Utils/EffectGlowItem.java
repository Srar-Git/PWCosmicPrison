package cn.pixelwar.pwitemmanager.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Utils.GiveItems;
import cn.pixelwar.pwitemmanager.Utils.Glowing;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGlowItem extends Effect {
    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectGlowItem.class, new String[]{
                "glowhand %player%"
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
        return "glow item in hand: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Glowing glowing = new Glowing();
        Player player = playerin.getSingle(e);
        glowing.makeHandGlow(player);

    }


}
