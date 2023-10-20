package cn.pixelwar.pwitemmanager.Skript.Utils.GUI;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.Enchant.menus.EnchantMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectEnchantGUI extends Effect {
    private Expression<Player> playerin;

    static {
        Skript.registerEffect(EffectEnchantGUI.class, new String[]{
                "enchantgui %player%"
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
        return "11: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        Player player = playerin.getSingle(e);
        EnchantMenu enchantMenu = new EnchantMenu();
        enchantMenu.createGUI(player, player.getItemInHand());

    }


}
