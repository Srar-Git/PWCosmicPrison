package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockAnimationData;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;

public class EffectAddAnimation extends Effect {
    BlockAnimationData blockAnimationData = new BlockAnimationData();
    private Expression<Player> player;
    private Expression<Number> damage;
    private Expression<Block> block;

    static {
        Skript.registerEffect(EffectAddAnimation.class, new String[]{
                "addblockdamage %player% %block% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.block = (Expression<Block>) expressions[1];
        this.damage = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add damage to block: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player digger = player.getSingle(event);
        double add1 = (double) damage.getSingle(event);
        float add = (float) add1;
        Block breakingBlock = block.getSingle(event);
        String locString = digger + " " + breakingBlock.getLocation();
        Location eyeloc = digger.getEyeLocation();
        Vector direction = eyeloc.getDirection();
        direction.multiply(0.08);
        Location point2 = null;

        for (int i = 0; i <= 80; i++) {
            Location point = eyeloc.add(direction);
            if (point.getBlock().getType().isSolid()) {
                point2 = eyeloc.subtract(direction);
                break;
            }
        }
        Location standard = breakingBlock.getLocation().add(0.5, 0.5, 0.5);
        if (point2.getX() > standard.getX() + 0.6 || point2.getX() < standard.getX() - 0.6)
            point2.setX(standard.getX());
        if (point2.getY() > standard.getY() + 0.6 || point2.getY() < standard.getY() - 0.6)
            point2.setY(standard.getY());
        if (point2.getZ() > standard.getZ() + 0.6 || point2.getZ() < standard.getZ() - 0.6)
            point2.setZ(standard.getZ());

//        blockAnimationData.addBlockAnimationData(locString, add, digger, breakingBlock, point2);
    }
}
