package cn.pixelwar.pwblockmanager.skript.Utils.Powerball;


import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

import static cn.pixelwar.pwblockmanager.Utils.PowerBall.PowerBallShoot.PowerballShoot;


public class EffectShootPowerball extends Effect {
    private Expression<Player> playerin;
    private Expression<ItemType> itemin;

    static {
        Skript.registerEffect(EffectShootPowerball.class, new String[]{
                "shootpowerball %player% %itemtypes%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.itemin = (Expression<ItemType>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "shoot powerball player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        ItemStack item = itemin.getSingle(event).getRandom();
        PowerballShoot(player, item);
    }
}
