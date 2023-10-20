package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.GetDigSpeed;
import cn.pixelwar.pwitemmanager.NBT.GetItemNBT;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class ValueBreakSpeedOfPlayer extends SimpleExpression<Double> {

    static {
        Skript.registerExpression(ValueBreakSpeedOfPlayer.class, Double.class, ExpressionType.COMBINED, "[the] breakspeed of %player% with %block%");
    }

    private Expression<Player> playerin;
    private Expression<Block> blockin;
    @Override
    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        playerin = (Expression<Player>) exprs[0];
        blockin = (Expression<Block>) exprs[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get packet of block error ";
    }

    @Override
    @Nullable
    protected Double[] get(Event event) {
        Block digBlock = blockin.getSingle(event);
        Player player = playerin.getSingle(event);
        return new Double[]{GetDigSpeed.getDigSpeed(player, digBlock)};
    }
}
