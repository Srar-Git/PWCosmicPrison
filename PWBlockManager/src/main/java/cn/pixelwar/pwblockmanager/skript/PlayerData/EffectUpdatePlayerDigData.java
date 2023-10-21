package cn.pixelwar.pwblockmanager.skript.PlayerData;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdatePlayerDigData extends Effect {
    PlayerData playerData = new PlayerData();
    private Expression<Player> player;
    private Expression<Number> damage;
    private Expression<Block> block;
    private Expression<String> blockFace;

    static {
        Skript.registerEffect(EffectUpdatePlayerDigData.class, new String[]{
                "addplayerdigdata %player% %block% %number% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.block = (Expression<Block>) expressions[1];
        this.damage = (Expression<Number>) expressions[2];
        this.blockFace = (Expression<String>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add player data to block: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player digger = player.getSingle(event);
        double add1 = (double) damage.getSingle(event);
        float add = (float) add1;
        Block breakingBlock = block.getSingle(event);
        String blockFaces = blockFace.getSingle(event);
        playerData.updatePlayerData(add, breakingBlock, digger, blockFaces);
    }
}
