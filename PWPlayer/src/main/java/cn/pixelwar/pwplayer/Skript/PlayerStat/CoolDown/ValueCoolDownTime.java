package cn.pixelwar.pwplayer.Skript.PlayerStat.CoolDown;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueCoolDownTime extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ValueCoolDownTime.class, Integer.class, ExpressionType.COMBINED, "[the] %string% cooldown of %player%");
    }

    private Expression<String> typein;
    private Expression<Player> player;

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        typein = (Expression<String>) exprs[0];
        player = (Expression<Player>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get cooldown time of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Integer[] get(Event event) {
        Player p = player.getSingle(event);
        String type = typein.getSingle(event);
        if (CoolDownManager.playerCoolDownMap.containsKey(p.getName())) {
            if (CoolDownManager.playerCoolDownMap.get(p.getName()).getOneCoolDownMap().containsKey(CoolDownType.valueOf(type))) {
                return new Integer[]{
                        CoolDownManager.playerCoolDownMap.get(p.getName()).getOneCoolDownMap().get(CoolDownType.valueOf(type))
                };
            }


        }
        return new Integer[]{0};
    }
}
