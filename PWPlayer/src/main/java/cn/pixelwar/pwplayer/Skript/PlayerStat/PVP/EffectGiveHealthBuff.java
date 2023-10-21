package cn.pixelwar.pwplayer.Skript.PlayerStat.PVP;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataManager;
import cn.pixelwar.pwplayer.PlayerStat.IntData.IntDataType;
import cn.pixelwar.pwplayer.PlayerStat.PVP.PlayerHealth.PlayerHealthManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGiveHealthBuff extends Effect {

    private Expression<Player> playerin;
    private Expression<Number> healthin;
    private Expression<Number> timein;

    static {
        //玩家 血量 时间
        Skript.registerEffect(EffectGiveHealthBuff.class, new String[]{
                "givehealthbuff %player% %number% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.healthin = (Expression<Number>) expressions[1];
        this.timein = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "givehealthbuff to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        double health = healthin.getSingle(event).doubleValue();
        int time = timein.getSingle(event).intValue();
        PlayerHealthManager playerHealthManager = new PlayerHealthManager();
        playerHealthManager.giveBuff(player, health, time);
    }
}
