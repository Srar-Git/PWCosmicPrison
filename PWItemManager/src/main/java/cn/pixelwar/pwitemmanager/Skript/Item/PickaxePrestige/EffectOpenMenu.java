package cn.pixelwar.pwitemmanager.Skript.Item.PickaxePrestige;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.Item.GiveAndDropItem;
import cn.pixelwar.pwitemmanager.NBT.Item.PickaxePrestige.Menu;
import cn.pixelwar.pwitemmanager.Skript.Item.EffectGiveItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EffectOpenMenu extends Effect {
    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectOpenMenu.class, new String[]{
                "openpickaxepristigegui %player%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "drop a item with an owner at player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Player player = this.playerin.getSingle(e);
        Menu menu = new Menu();
        menu.openMainMenu(player);
    }
}
