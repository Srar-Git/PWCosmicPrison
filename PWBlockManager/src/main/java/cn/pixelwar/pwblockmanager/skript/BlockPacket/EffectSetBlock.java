package cn.pixelwar.pwblockmanager.skript.BlockPacket;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwblockmanager.PWBlockManager;
import cn.pixelwar.pwblockmanager.PlayerDigStuff.PlayerData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;

public class EffectSetBlock extends Effect {
    PlayerData playerData = new PlayerData();
    private Expression<Player> player;
    private Expression<Block> oldBlock;

    static {
        Skript.registerEffect(EffectSetBlock.class, new String[]{
                "setpacketblocktostone %player% %block%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.player = (Expression<Player>) expressions[0];
        this.oldBlock = (Expression<Block>) expressions[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add player crit data to block: " + player.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player seer = player.getSingle(event);
        Block old = oldBlock.getSingle(event);
        new BukkitRunnable() {

            @Override
            public void run() {
                seer.sendBlockChange(old.getLocation(), Material.STONE, (byte) 0);

            }
        }.runTaskTimerAsynchronously(PWBlockManager.getPlugin(), 0, 1L);
    }
}
