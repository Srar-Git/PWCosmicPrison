package cn.pixelwar.pwlevel.skript.Prestige;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import cn.pixelwar.pwlevel.PlayerData.PlayerOreExp.PlayerOreExpDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectSetPrestige extends Effect {
    PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
    PlayerOreExpDataManager playerOreExpDataManager = new PlayerOreExpDataManager();
    private Expression<Player> playerin;
    private Expression<Number> prestigein;

    static {
        Skript.registerEffect(EffectSetPrestige.class, new String[]{
                "setprestige %player% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.prestigein = (Expression<Number>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "set prestige of player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        int prestige = prestigein.getSingle(event).intValue();
        playerExpDataManager.createPlayerExpData(player, 0L, prestige, 0);
        playerExpDataManager.setPlayerLevelStuff(player);
        playerOreExpDataManager.resetOreExp(player);
    }
}
