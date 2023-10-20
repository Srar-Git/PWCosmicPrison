package cn.pixelwar.pwitemmanager.Skript.Utils;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetItemNBT;
import cn.pixelwar.pwitemmanager.Utils.GiveItems;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectGiveExpBottle extends Effect {
    private Expression<Player> playerin;
    private Expression<Number> expin;
    private Expression<String> tierin;
    private Expression<Number> amountin;
    private Expression<Number> levelin;

    static {
        Skript.registerEffect(EffectGiveExpBottle.class, new String[]{
                "giveexpbottle %player% %number% %string% %number% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
//        if (matchedPattern == 0){
//        this.playerin = (Expression<Player>) expressions[0];
//        this.pathin = (Expression<String>) expressions[1];
//        this.nbtStringin = (Expression<String>) expressions[2];
//        }else {
        this.playerin = (Expression<Player>) expressions[0];
        this.expin = (Expression<Number>) expressions[1];
        this.tierin = (Expression<String>) expressions[2];
        this.amountin = (Expression<Number>) expressions[3];
        this.levelin = (Expression<Number>) expressions[4];
//        }
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "give player exp bottle: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        GiveItems giveItems = new GiveItems();
        SetItemNBT setItemNBT = new SetItemNBT();
        Player player = playerin.getSingle(e);
        long exp = (long) expin.getSingle(e).longValue();
        int level = levelin.getSingle(e).intValue();
        int amount = amountin.getSingle(e).intValue();
        String tier = tierin.getSingle(e);
        giveItems.giveExpBottle(player, exp, tier, amount, level);

    }


}
