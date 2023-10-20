package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetItemNBT;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdateHandNBTInt extends Effect {
    private Expression<Player> playerin;
    private Expression<String> pathin;
//    private Expression<String> nbtStringin;
    private Expression<Number> nbtNumberin;

    static {
        Skript.registerEffect(EffectUpdateHandNBTInt.class, new String[]{
//                "updatehanditemnbt %player% %string% %string%",
                "updatehanditemnbtint %player% %string% %number%"
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
            this.pathin = (Expression<String>) expressions[1];
            this.nbtNumberin = (Expression<Number>) expressions[2];
//        }
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "update nbt of player's tool: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        SetItemNBT setItemNBT = new SetItemNBT();
        Player player = playerin.getSingle(e);
        String path = pathin.getSingle(e);
//        if (this.nbtStringin != null){
//            String nbtString = nbtStringin.getSingle(e);
//            setItemNBT.updateHandNBT(player, path, nbtString);
//        }
//        if (this.nbtNumberin != null){
//            long nbtNumber1 = (long) nbtNumberin.getSingle(e);
            int nbtNumber = (int) nbtNumberin.getSingle(e).intValue();
            setItemNBT.updateHandNBT(player, path, nbtNumber);
//        }

    }


}