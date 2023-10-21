package cn.pixelwar.pwpacketstuff.Skript.Utils.Meteor;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.Skript.Utils.EffectSendActionbar;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketMeteor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectSpawnMeteor extends Effect {
    private Expression<Location> locin;

    static {
        Skript.registerEffect(EffectSpawnMeteor.class, new String[]{
                "spawnmeteor %location%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.locin = (Expression<Location>) expressions[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "spawn meteor: ";
    }

    @Override
    protected void execute(Event event) {
        Location loc = locin.getSingle(event);
        PacketMeteor packetMeteor = new PacketMeteor();
        packetMeteor.createMeteor(loc);


    }
}