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

public class EffectGiveSkin extends Effect {
    private Expression<Player> playerin;
    private Expression<String> skinin;
    private Expression<String> equipin;

    static {
        Skript.registerEffect(EffectGiveSkin.class, new String[]{
                "giveskin %player% %string% %string%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {

        this.playerin = (Expression<Player>) expressions[0];
        this.skinin = (Expression<String>) expressions[1];
        this.equipin = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "give player skin: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        GiveItems giveItems = new GiveItems();
        Player player = playerin.getSingle(e);
        String skin = skinin.getSingle(e);
        String equip = equipin.getSingle(e);
        giveItems.giveSkin(player, skin, equip);

    }


}
