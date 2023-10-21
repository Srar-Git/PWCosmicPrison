package cn.pixelwar.pwblockmanager.skript.PlayerData;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdatePlayerCritData extends Effect {
    PlayerData playerData = new PlayerData();
    private Expression<Player> player;
    private Expression<Number> crit;

    static {
        Skript.registerEffect(EffectUpdatePlayerCritData.class, new String[]{
                "addplayercritdata %player% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.crit = (Expression<Number>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add player crit data to block: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player digger = player.getSingle(event);
        long add1 = (long) crit.getSingle(event);
        int add = (int) (add1);
        playerData.updateCritData(digger, add);
    }
}
