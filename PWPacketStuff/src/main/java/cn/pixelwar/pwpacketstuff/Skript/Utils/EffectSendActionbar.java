package cn.pixelwar.pwpacketstuff.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketBlock;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketItem;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class EffectSendActionbar extends Effect {
    private Expression<Player> playerin;
    private Expression<String> messagein;

    static {
        Skript.registerEffect(EffectSendActionbar.class, new String[]{
                "sendactionbar %player% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.messagein = (Expression<String>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "send actionbar: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String message = messagein.getSingle(event);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));


    }
}
