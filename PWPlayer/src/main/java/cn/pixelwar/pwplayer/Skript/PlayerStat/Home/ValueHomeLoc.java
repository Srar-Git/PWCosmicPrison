package cn.pixelwar.pwplayer.Skript.PlayerStat.Home;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownManager;
import cn.pixelwar.pwplayer.PlayerStat.CoolDown.CoolDownType;
import cn.pixelwar.pwplayer.PlayerStat.Home.HomeManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueHomeLoc extends SimpleExpression<Location> {

    static {
        Skript.registerExpression(ValueHomeLoc.class, Location.class, ExpressionType.COMBINED, "[the] homeloc %string% of %player%");
    }

    private Expression<String> typein;
    private Expression<Player> player;

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
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
        return "get home loc of player: " + player.toString(event, debug);
    }

    @Override
    @Nullable
    protected Location[] get(Event event) {
        Player p = player.getSingle(event);
        String name = typein.getSingle(event);
        if (HomeManager.playerHomeMap.containsKey(p.getName())) {
            if (HomeManager.playerHomeMap.get(p.getName()).getOnePlayerHomeMap().containsKey(name)) {
                return new Location[]{
                        HomeManager.playerHomeMap.get(p.getName()).getOnePlayerHomeMap().get(name)
                };
            }


        }
        return new Location[]{null};
    }
}
