package cn.pixelwar.pwitemmanager.Skript.Item;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.Item.GiveAndDropItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EffectGiveItem extends Effect {
    private Expression<Player> playerin;
    private Expression<ItemType> drops;
    private Expression<Location> locations;

    static {
        Skript.registerEffect(EffectGiveItem.class, new String[]{
                "giveitem %player% %itemtypes% %locations%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.drops = (Expression<ItemType>) expressions[1];
        this.locations = (Expression<Location>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "drop a item with an owner at player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Player owner = this.playerin.getSingle(e);
        Object[] os = this.drops.getArray(e);
        byte b;
        int i;
        Location[] arrayOfLocation;
        for (i = (arrayOfLocation = (Location[]) this.locations.getArray(e)).length, b = 0; b < i; ) {
            Location l = arrayOfLocation[b];
            Location itemDropLoc = l.clone().subtract(0.5D, 0.5D, 0.5D);
            byte b1;
            int j;
            Object[] arrayOfObject;
            for (j = (arrayOfObject = os).length, b1 = 0; b1 < j; ) {
                Object o = arrayOfObject[b1];

                for (ItemStack is : ((ItemType) o).getItem().getAll()) {
                    if (is.getType() != Material.AIR) {
                        GiveAndDropItem.giveItem(owner, is);
                    }
                    b1++;
                }
                b++;
            }

        }
    }
}