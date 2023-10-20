package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.UpdateSlotLore;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EffectAddItemEnergy extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> xpin;
    private Expression<ItemType> itemin;

    static {
        Skript.registerEffect(EffectAddItemEnergy.class, new String[]{
                "additemenergy %player% %number% %itemtypes%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.xpin = (Expression<Number>) expressions[1];
        this.itemin = (Expression<ItemType>) expressions[2];

        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "update energy of item: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        UpdateSlotLore updateSlotLore = new UpdateSlotLore();
        Player player = playerin.getSingle(e);
        int xp = xpin.getSingle(e).intValue();
        ItemStack item = itemin.getSingle(e).getRandom();
        updateSlotLore.addItemEnergy(player, xp, item);
    }
}
