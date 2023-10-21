package cn.pixelwar.pwplayer.Skript.PlayerStat.DoubleData;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataManager;
import cn.pixelwar.pwplayer.PlayerStat.DoubleData.DoubleDataType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectRemoveDoubleData extends Effect {

    private Expression<Player> playerin;
    private Expression<String> typein;
    private Expression<Number> numin;

    static {
        Skript.registerEffect(EffectRemoveDoubleData.class, new String[]{
                "removedoubledata %player% %string% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.typein = (Expression<String>) expressions[1];
        this.numin = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "remove doubledata to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String type = typein.getSingle(event);
        double num = numin.getSingle(event).doubleValue();
        DoubleDataManager.DoubleDataMap.get(player.getName()).removeNum(DoubleDataType.valueOf(type), num);
    }
}
