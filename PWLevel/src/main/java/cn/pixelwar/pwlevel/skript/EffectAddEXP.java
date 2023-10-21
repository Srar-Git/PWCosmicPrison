package cn.pixelwar.pwlevel.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwlevel.PWLevel;
import cn.pixelwar.pwlevel.PlayerData.PlayerBooster.PlayerBoosterDataManager;
import cn.pixelwar.pwlevel.PlayerData.PlayerExp.PlayerExpDataManager;
import cn.pixelwar.pwlevel.PlayerData.PlayerOreExp.PlayerOreExpDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectAddEXP extends Effect {
    PlayerExpDataManager playerExpDataManager = new PlayerExpDataManager();
    PlayerBoosterDataManager playerBoosterDataManager = new PlayerBoosterDataManager();
    PlayerOreExpDataManager playerOreExpDataManager = new PlayerOreExpDataManager();
    private Expression<Player> playerin;
    private Expression<Number> expin;
    private Expression<String> typein;

    static {
        Skript.registerEffect(EffectAddEXP.class, new String[]{
                "addexp %player% %number% %string%",
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.expin = (Expression<Number>) expressions[1];
        this.typein = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "add pixelwar exp to player: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event event) {
        Player player = playerin.getSingle(event);
        String type = typein.getSingle(event);

        if (playerExpDataManager.getPrestige(player) == 0) {
            if (playerExpDataManager.getLevel(player) == 100) {
                return;
            }
        }
        if (playerExpDataManager.getPrestige(player) == 1) {
            if (playerExpDataManager.getLevel(player) == 101) {
                return;
            }
        }
        if (playerExpDataManager.getPrestige(player) == 2) {
            if (playerExpDataManager.getLevel(player) == 102) {
                return;
            }
        }
        if (playerExpDataManager.getPrestige(player) == 3) {
            if (playerExpDataManager.getLevel(player) == 103) {
                return;
            }
        }
        if (playerExpDataManager.getPrestige(player) == 4) {
            if (playerExpDataManager.getLevel(player) == 104) {
                return;
            }
        }
        if (playerExpDataManager.getPrestige(player) == 5) {
            if (playerExpDataManager.getLevel(player) == 105) {
                return;
            }
        }
        if (playerExpDataManager.getPrestige(player) == 6) {
            if (playerExpDataManager.getLevel(player) == 106) {
                return;
            }
        }

        long exp = expin.getSingle(event).longValue();
//        Bukkit.getServer().broadcastMessage("初始经验："+exp);
        double multi = playerBoosterDataManager.getExpMultiple(player);

        float orepercent = playerOreExpDataManager.getOreExpPercent(player, type);
        exp = (long) Math.round(exp * (orepercent));
//        Bukkit.getServer().broadcastMessage("经过Ore的经验："+exp);
        playerOreExpDataManager.addOreExp(player, exp, type);
        exp = (long) Math.round(exp * multi);
//        Bukkit.getServer().broadcastMessage("经过加成的经验："+exp);
        long totalEXP = playerExpDataManager.getTotalExp(player) + exp;
        if (playerExpDataManager.getPrestige(player) == 0) {
            if (totalEXP > PWLevel.config.getLong("levels.level100")) {
                totalEXP = PWLevel.config.getLong("levels.level100");
            }
        }
        if (playerExpDataManager.getPrestige(player) == 1) {
            if (totalEXP > PWLevel.config.getLong("levels.level101")) {
                totalEXP = PWLevel.config.getLong("levels.level101");
            }
        }
        if (playerExpDataManager.getPrestige(player) == 2) {
            if (totalEXP > PWLevel.config.getLong("levels.level102")) {
                totalEXP = PWLevel.config.getLong("levels.level102");
            }
        }
        if (playerExpDataManager.getPrestige(player) == 3) {
            if (totalEXP > PWLevel.config.getLong("levels.level103")) {
                totalEXP = PWLevel.config.getLong("levels.level103");
            }
        }
        if (playerExpDataManager.getPrestige(player) == 4) {
            if (totalEXP > PWLevel.config.getLong("levels.level104")) {
                totalEXP = PWLevel.config.getLong("levels.level104");
            }
        }
        if (playerExpDataManager.getPrestige(player) == 5) {
            if (totalEXP > PWLevel.config.getLong("levels.level105")) {
                totalEXP = PWLevel.config.getLong("levels.level105");
            }
        }
        if (playerExpDataManager.getPrestige(player) == 6) {
            if (totalEXP > PWLevel.config.getLong("levels.level106")) {
                totalEXP = PWLevel.config.getLong("levels.level106");
            }
        }
        playerExpDataManager.createPlayerExpData(player, totalEXP, playerExpDataManager.getPrestige(player), playerExpDataManager.getLevel(player));
        playerExpDataManager.checkPlayerLevel(player);
        playerExpDataManager.setPlayerLevelStuff(player);
    }
}
