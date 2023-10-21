package cn.pixelwar.pwblockmanager.skript.BlockBroken;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.BlockStuff.BlockPacketDataManager;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ValueBlockPacketMaterial extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ValueBlockPacketMaterial.class, String.class, ExpressionType.COMBINED, "[the] exist of %block%");
    }

    private Expression<Block> block;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        block = (Expression<Block>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "get packet of block error ";
    }

    @Override
    @Nullable
    protected String[] get(Event event) {
        Block checkedBlock = block.getSingle(event);
        if (BlockPacketDataManager.getBlockPacketData().containsKey(checkedBlock.getLocation())) {
            String material = BlockPacketDataManager.getBlockPacketData().get(checkedBlock.getLocation()).getPacketMaterial().toString();
            return new String[]{material};
        } else {
            return new String[]{"NONE"};
        }
    }
}
