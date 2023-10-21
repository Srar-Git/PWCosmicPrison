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

public class EffectUpdateSlotNBTString extends Effect {
    private Expression<Player> playerin;
    private Expression<String> pathin;
    private Expression<String> nbtStringin;
    private Expression<Number> slotin;

    static {
        Skript.registerEffect(EffectUpdateSlotNBTString.class, new String[]{
                "updateslotitemnbtstring %player% %number% %string% %string%"
//                "updateslotitemnbtint %player% %number% %string% %number%"
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.playerin = (Expression<Player>) expressions[0];
        this.slotin = (Expression<Number>) expressions[1];
        this.pathin = (Expression<String>) expressions[2];
        this.nbtStringin = (Expression<String>) expressions[3];
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

        String nbtString = nbtStringin.getSingle(e);
        setSlotNBT.setSlotItemNBT(player, slot, path, nbtString);


    }


}
