package cn.pixelwar.pwitemmanager.Skript.NBT;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import cn.pixelwar.pwitemmanager.NBT.SetSlotNBT;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffectUpdateSlotNBTDouble extends Effect {
    private Expression<Player> playerin;
    private Expression<String> pathin;
    private Expression<Number> nbtNumberin;
    private Expression<Number> slotin;

    static {
        Skript.registerEffect(EffectUpdateSlotNBTDouble.class, new String[]{
                "updateslotitemnbtdouble %player% %number% %string% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
//        if (matchedPattern == 0){
//            this.playerin = (Expression<Player>) expressions[0];
//            this.slotin = (Expression<Number>) expressions[1];
//            this.pathin = (Expression<String>) expressions[2];
//            this.nbtStringin = (Expression<String>) expressions[3];
//        }else {
        this.playerin = (Expression<Player>) expressions[0];
        this.slotin = (Expression<Number>) expressions[1];
        this.pathin = (Expression<String>) expressions[2];
        this.nbtNumberin = (Expression<Number>) expressions[3];
//        }
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "update nbt of slot item in player's inventory: " + playerin.toString(event, debug);
    }

    @Override
    protected void execute(Event e) {
        SetSlotNBT setSlotNBT = new SetSlotNBT();
        Player player = playerin.getSingle(e);
        String path = pathin.getSingle(e);

//        long slotLong = (long) slotin.getSingle(e);
        int slot = (int) slotin.getSingle(e).intValue();
//        if (this.nbtStringin != null){
//            String nbtString = nbtStringin.getSingle(e);
//            setSlotNBT.setSlotItemNBT(player, slot, path, nbtString);
//        }
//        if (this.nbtNumberin != null){
//            long nbtNumber1 = (long) nbtNumberin.getSingle(e);
        double nbtNumber = (int) nbtNumberin.getSingle(e).doubleValue();
        setSlotNBT.setSlotItemNBT(player, slot, path, nbtNumber);
//        }

    }


}
