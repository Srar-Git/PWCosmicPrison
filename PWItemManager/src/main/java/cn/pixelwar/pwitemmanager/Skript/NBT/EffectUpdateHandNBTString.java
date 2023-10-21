package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetItemNBT;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdateHandNBTString extends Effect {
    private Expression<Player> playerin;
    private Expression<String> pathin;
    private Expression<String> nbtStringin;

    static {
        Skript.registerEffect(EffectUpdateHandNBTString.class, new String[]{
                "updatehanditemnbtstring %player% %string% %string%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.pathin = (Expression<String>) expressions[1];
        this.nbtStringin = (Expression<String>) expressions[2];

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "update nbt of player's tool: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        SetItemNBT setItemNBT = new SetItemNBT();
        Player player = playerin.getSingle(e);
        String path = pathin.getSingle(e);
        String nbtString = nbtStringin.getSingle(e);
        setItemNBT.updateHandNBT(player, path, nbtString);

    }


}
