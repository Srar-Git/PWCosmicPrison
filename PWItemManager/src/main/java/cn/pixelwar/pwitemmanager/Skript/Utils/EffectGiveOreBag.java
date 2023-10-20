package cn.pixelwar.pwitemmanager.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetItemNBT;
import cn.pixelwar.pwitemmanager.Utils.GiveItems;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGiveOreBag extends Effect {
    private Expression<Player> playerin;
    private Expression<String> typein;

    static {
        Skript.registerEffect(EffectGiveOreBag.class, new String[]{
                "giveorebag %player% %string%"
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
        return "give player orebag: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        GiveItems giveItems = new GiveItems();
        Player player = playerin.getSingle(e);
        String type = typein.getSingle(e);
        giveItems.giveOreBag(player, type);

    }


}


