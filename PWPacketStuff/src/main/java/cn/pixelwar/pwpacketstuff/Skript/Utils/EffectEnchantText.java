package cn.pixelwar.pwpacketstuff.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketText;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EffectEnchantText extends Effect {
    private Expression<Player> playerin;
    private Expression<ItemType> itemin;
    private Expression<String> messagein;

    static {
        Skript.registerEffect(EffectEnchantText.class, new String[]{
                "sendpackettext %player% %itemtypes% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.itemin = (Expression<ItemType>) expressions[1];
        this.messagein = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "send text effect to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        PacketText packetText = new PacketText();
        Player player = this.playerin.getSingle(e);
        ItemType itemType = itemin.getSingle(e);
        ItemStack item = itemType.getRandom();
        String message = messagein.getSingle(e);
        packetText.createEnchantText(player, item, message);
    }
}
