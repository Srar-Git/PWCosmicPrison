package cn.pixelwar.pwpacketstuff.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketBlock;
import cn.pixelwar.pwpacketstuff.Utils.PacketAnimation.PacketItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.Locale;

public class EffectShowOreAnimation extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> xin;
    private Expression<Number> yin;
    private Expression<Number> zin;
    private Expression<String> materialin;
    private Expression<String> facein;
    private Expression<String> glowin;
    private Expression<String> soundin;

    static {
        Skript.registerEffect(EffectShowOreAnimation.class, new String[]{
                "genpacketitem %player% %number% %number% %number% %string% %string% %string% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.xin = (Expression<Number>) expressions[1];
        this.yin = (Expression<Number>) expressions[2];
        this.zin = (Expression<Number>) expressions[3];
        this.materialin = (Expression<String>) expressions[4];
        this.facein = (Expression<String>) expressions[5];
        this.glowin = (Expression<String>) expressions[6];
        this.soundin = (Expression<String>) expressions[7];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "generate effect to player: " + playerin.toString(event, debug) + " and x location: " + xin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        PacketItem packetItem = new PacketItem();
        PacketBlock packetBlock = new PacketBlock();
        Player player = playerin.getSingle(event);
        int x = xin.getSingle(event).intValue();
        int y = yin.getSingle(event).intValue();
        int z = zin.getSingle(event).intValue();
        Location location = new Location(player.getWorld(), x, y, z);
        String itemname = materialin.getSingle(event);
        String clickedBlockFace = facein.getSingle(event);
        ItemStack item = new ItemStack(Material.matchMaterial(itemname));
        boolean isGlow = true;
        boolean hasSound = true;
        if (glowin.getSingle(event).equalsIgnoreCase("true")) {
            isGlow = true;
        } else {
            isGlow = false;
        }
        if (soundin.getSingle(event).equalsIgnoreCase("true")) {
            hasSound = true;
        } else {
            hasSound = false;
        }

        if (item.getType().isBlock()) {
            if (clickedBlockFace.equalsIgnoreCase("UP")) {
                packetBlock.PacketStandUp(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("DOWN")) {
                packetBlock.PacketStandDown(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("EAST")) {
                packetBlock.PacketStandEast(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("WEST")) {
                packetBlock.PacketStandWest(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("SOUTH")) {
                packetBlock.PacketStandSouth(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("NORTH")) {
                packetBlock.PacketStandNorth(player, location, item, isGlow, hasSound);
                return;
            }

        } else {
            if (clickedBlockFace.equalsIgnoreCase("UP")) {
                packetItem.PacketStandUp(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("DOWN")) {
                packetItem.PacketStandDown(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("EAST")) {
                packetItem.PacketStandEast(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("WEST")) {
                packetItem.PacketStandWest(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("SOUTH")) {
                packetItem.PacketStandSouth(player, location, item, isGlow, hasSound);
                return;
            }
            if (clickedBlockFace.equalsIgnoreCase("NORTH")) {
                packetItem.PacketStandNorth(player, location, item, isGlow, hasSound);
                return;
            }
        }


    }
}

