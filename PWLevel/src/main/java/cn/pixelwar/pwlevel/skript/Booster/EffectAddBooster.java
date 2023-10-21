package cn.pixelwar.pwlevel.skript.Booster;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;

import static cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager.*;

public class EffectAddBooster extends Effect {
    PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
    private Expression<Player> playerin;
    private Expression<String> typein;
    private Expression<Number> timein;
    private Expression<Number> multiplein;

    static {
        Skript.registerEffect(EffectAddBooster.class, new String[]{
                "addbooster %player% %string% %number% %number%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.typein = (Expression<String>) expressions[1];
        this.timein = (Expression<Number>) expressions[2];
        this.multiplein = (Expression<Number>) expressions[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add pixelwar booster to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String type = typein.getSingle(event);
//        long timelong = (long) timein.getSingle(event);
        int time = (int) timein.getSingle(event).intValue();
        double multiple = (double) multiplein.getSingle(event);

        new BukkitRunnable() {
            @Override
            public void run() {

                switch (type) {
                    case "exp":
                        if (getPlayerExpBoosterDataMap().containsKey(player.getName())) {
                            return;
                        }
                        playerBoosterDataManager.createExpBoosterData(player, time, multiple);
                        break;
                    case "energy":
                        if (getPlayerEnergyBoosterDataMap().containsKey(player.getName())) {
                            return;
                        }
                        playerBoosterDataManager.createEnergyBoosterData(player, time, multiple);
                        break;
                    case "shard":
                        if (getPlayerShardBoosterDataMap().containsKey(player.getName())) {
                            return;
                        }
                        playerBoosterDataManager.createShardBoosterData(player, time, multiple);
                        break;
                    default:
                        break;
                }
            }
        }.runTaskAsynchronously(PWLevel.getPlugin());
    }
}

