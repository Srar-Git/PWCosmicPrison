package cn.pixelwar.pwitemmanager.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectSendOnlySound extends Effect {
    private Expression<Player> playerin;
    private Expression<String> soundin;
    private Expression<Number> volumein;
    private Expression<Number> pitchin;

    static {
        Skript.registerEffect(EffectSendOnlySound.class, new String[]{
                "sendonlysound %player% %string% %number% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.soundin = (Expression<String>) expressions[1];
        this.volumein = (Expression<Number>) expressions[2];
        this.pitchin = (Expression<Number>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "send sound to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Player player = playerin.getSingle(e);
        String sound = soundin.getSingle(e);
        float volume = volumein.getSingle(e).floatValue();
        float pitch = pitchin.getSingle(e).floatValue();
        player.playSound(player.getEyeLocation(), Sound.valueOf(sound), volume, pitch);
    }


}
