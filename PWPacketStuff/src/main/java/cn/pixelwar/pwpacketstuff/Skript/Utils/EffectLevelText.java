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

public class EffectLevelText extends Effect {
    private Expression<Player> playerin;
    private Expression<String> messagein;
    private Expression<Number> levelin;

    static {
        Skript.registerEffect(EffectLevelText.class, new String[]{
                "sendpacketleveltext %player% %number% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.messagein = (Expression<String>) expressions[2];
        this.levelin = (Expression<Number>) expressions[1];
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
        int level = levelin.getSingle(e).intValue();
        String message = messagein.getSingle(e);
        packetText.createLevelUpText(player, level, message);
    }
}

