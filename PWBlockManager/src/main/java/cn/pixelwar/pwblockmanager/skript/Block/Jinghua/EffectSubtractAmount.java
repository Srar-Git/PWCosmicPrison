package cn.pixelwar.pwblockmanager.skript.Block.Jinghua;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.Jinghua;
import cn.pixelwar.pwblockmanager.BlockStuff.Jinghua.JinghuaDataManager;
import cn.pixelwar.pwblockmanager.BlockStuff.meteor.MeteorDataManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectSubtractAmount extends Effect {
    JinghuaDataManager jinghuaDataManager = new JinghuaDataManager();
    private Expression<Player> player;
    private Expression<Block> blockin;
    private Expression<Number> amountin;

    static {
        Skript.registerEffect(EffectSubtractAmount.class, new String[]{
                "subtractjinghua %player% %block% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.blockin = (Expression<Block>) expressions[1];
        this.amountin = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "subtract jinghua: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player digger = player.getSingle(event);
        int amount = amountin.getSingle(event).intValue();
        Block subtractBlock = blockin.getSingle(event);
        jinghuaDataManager.subtractAmount(subtractBlock.getLocation(), amount, digger);
    }
}
