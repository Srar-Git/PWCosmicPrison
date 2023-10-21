package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockPacketDataManager;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdateBlockPacket extends Effect {
    BlockPacketDataManager packetDataManager = new BlockPacketDataManager();
    private Expression<Block> oldBlock;
    private Expression<String> newBlock;
    private Expression<Number> num;

    static {
        Skript.registerEffect(EffectUpdateBlockPacket.class, new String[]{
                "updateblockpacket %block% %string% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.oldBlock = (Expression<Block>) expressions[0];
        this.newBlock = (Expression<String>) expressions[1];
        this.num = (Expression<Number>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "update block packet info";
    }

    @Override
    protected void execute(Event event) {
        Block OldBlock = oldBlock.getSingle(event);
        String NewBlock = newBlock.getSingle(event);
        long number = (long) num.getSingle(event);
        int resetTime = (int) number;
        packetDataManager.updatePacketData(OldBlock, NewBlock, resetTime);
    }
}
