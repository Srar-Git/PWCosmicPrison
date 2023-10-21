package cn.pixelwar.pwplayer.Skript.NPC;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwplayer.Sentinel.Guard.CreateGuard;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectCreateGuard extends Effect {
    private Expression<String> typein;
    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectCreateGuard.class, new String[]{
                "createguard %player% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.typein = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "create npc: ";
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String type = typein.getSingle(event);
        CreateGuard createGuard = new CreateGuard();
        switch (type) {
            case "1":
                createGuard.createWarden1(player.getLocation());
                break;
            case "2":
                createGuard.createWarden2(player.getLocation());
                break;
            case "3":
                createGuard.createWarden3(player.getLocation());
                break;
            case "4":
                createGuard.createWarden4(player.getLocation());
                break;
        }


    }
}
